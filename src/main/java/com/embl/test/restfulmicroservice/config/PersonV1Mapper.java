package com.embl.test.restfulmicroservice.config;

import java.util.List;

import org.mapstruct.Mapper;

import com.embl.test.restfulmicroservice.config.model.PersonV1DTO;
import com.embl.test.restfulmicroservice.entity.PersonV1Entity;

@Mapper(componentModel = "spring")
public interface PersonV1Mapper {
	
	PersonV1Entity toEntity(PersonV1DTO person);

	PersonV1DTO fromEntity(PersonV1Entity person);

    List<PersonV1DTO> fromEntityList(List<PersonV1Entity> person);

}
