package com.embl.test.restfulmicroservice.service.impl;

import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.embl.test.restfulmicroservice.config.PersonV1Mapper;
import com.embl.test.restfulmicroservice.config.model.PaginationInfo;
import com.embl.test.restfulmicroservice.config.model.PersonV1DTO;
import com.embl.test.restfulmicroservice.config.model.PersonWrapperDTO;
import com.embl.test.restfulmicroservice.entity.PersonV1Entity;
import com.embl.test.restfulmicroservice.exception.PersonNotFoundException;
import com.embl.test.restfulmicroservice.repository.PersonRepository;
import com.embl.test.restfulmicroservice.service.contract.IPersonCrudService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PersonCrudService implements IPersonCrudService<PersonV1DTO, PersonWrapperDTO>{
	
	private PersonRepository personRepository;
	
	private PersonV1Mapper personMapper;
	
	private MessageSource messageSource;
	
	@Autowired
	public PersonCrudService(final PersonRepository personRepository, 
			final PersonV1Mapper personMapper, 
			final MessageSource messageSource) {
		this.personRepository = personRepository;
		this.personMapper = personMapper;
		this.messageSource = messageSource;
	}

	@Override
	public PersonWrapperDTO createEntity(PersonV1DTO dto) {
		PersonV1Entity personEntity = personMapper.toEntity(dto);
		personEntity = personRepository.save(personEntity);
		return PersonWrapperDTO.build(personMapper.fromEntity(personEntity));
	}

	@Override
	public PersonWrapperDTO updateEntityById(PersonV1DTO dto, Locale locale) {
		doNullCheckForId(dto.getId(), locale);
		if(personRepository.existsById(dto.getId())) {
			return PersonWrapperDTO.build(personMapper.fromEntity(personRepository.save(personMapper.toEntity(dto))));
		} else {
			log.error("User Not Found");
			throw new PersonNotFoundException(messageSource.getMessage("person.error.notfound", null, "Person not found", locale));
		}
		
	}

	
	@Override
	public PersonWrapperDTO getEntityById(Integer id, Locale locale) {
		doNullCheckForId(id, locale);
		Optional<PersonV1Entity> entity = personRepository.findById(id);
		return PersonWrapperDTO.build(personMapper.fromEntity(entity.orElseThrow(() -> 
			new PersonNotFoundException(messageSource.getMessage("person.error.notfound", 
					null, "Person not found", locale)))));
	}

	@Override
	public PersonWrapperDTO getEntities(int page, int size, Locale locale) {
		if(page < 0 || size < 0) {
			log.error("Invalid page or size");
			throw new IllegalArgumentException(messageSource.getMessage("person.error.pagesizeinvalid", null, "PageAndSizeInvalid", locale));
		}
		PageRequest pageRequest = PageRequest.of((page-1), size);
		Page<PersonV1Entity> pagedEntites = personRepository.findAll(pageRequest);
		if(pagedEntites.isEmpty()) {
			log.warn("Result is empty");
			return PersonWrapperDTO.buildEmpty();
		} else {
			PaginationInfo pageInfo = PaginationInfo.builder().page(pagedEntites.getNumber() + 1)
					.size(pagedEntites.getSize())
					.totalPages(pagedEntites.getTotalPages())
					.totalCount(pagedEntites.getTotalElements())
					.build();
			PersonWrapperDTO personWrapper = PersonWrapperDTO.build(pagedEntites.toList()
					.stream()
					.map(obj -> personMapper.fromEntity(obj))
					.collect(Collectors.toList()));
			personWrapper.setPageInfo(pageInfo);
			return personWrapper;
		}
	}

	@Override
	public void deleteEntityById(Integer id, Locale locale) {
		doNullCheckForId(id, locale);
		if(personRepository.existsById(id)) {
			personRepository.deleteById(id);
		} else {
			throw new PersonNotFoundException(messageSource.getMessage("person.error.notfound", null, "Person not found", locale));
		}
		
	}

	@Override
	public void deleteAllEntities() {
		personRepository.deleteAllInBatch();
	}
	
	private void doNullCheckForId(Integer id, Locale locale) throws PersonNotFoundException {
		if(id == null || id == 0) {
			log.error("person id cannot be null");
			throw new PersonNotFoundException(messageSource.getMessage("person.error.null", null, "Person Id cannot be null", locale));
		}
	}


}
