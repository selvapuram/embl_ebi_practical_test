package com.embl.test.restfulmicroservice.controllers;

import java.util.Locale;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.embl.test.restfulmicroservice.config.model.PersonV1DTO;
import com.embl.test.restfulmicroservice.config.model.PersonWrapperDTO;
import com.embl.test.restfulmicroservice.constants.DocumentationConstants;
import com.embl.test.restfulmicroservice.service.impl.PersonCrudService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Info;
import io.swagger.annotations.SwaggerDefinition;

@SwaggerDefinition(info = @Info(title = "Person Entity", version = "v1.0", description = "Person Entity"))
@Api(value = "person", tags = "Persons")
@RestController
@RequestMapping("/v1/persons")
public class PersonController {
	
	private PersonCrudService personService;
	
	public PersonController(final PersonCrudService personCrudService) {
		this.personService = personCrudService;
	}
	
	@ApiOperation(value = "${swagger.getallpersons.ApiOperation.value}",
		    notes = "${swagger.getallpersons.ApiOperation.notes}", response = PersonWrapperDTO.class,
		    nickname = "getAllPersons")
	@ApiResponses(value = {@ApiResponse(code = 200, message = DocumentationConstants.RESPONSECODE_200),
		@ApiResponse(code = 204, message = DocumentationConstants.RESPONSECODE_204),
		@ApiResponse(code = 400, message = DocumentationConstants.RESPONSECODE_400),
	    @ApiResponse(code = 401, message = DocumentationConstants.RESPONSECODE_401),
	    @ApiResponse(code = 403, message = DocumentationConstants.RESPONSECODE_403),
	    @ApiResponse(code = 404, message = DocumentationConstants.RESPONSECODE_404),
	    @ApiResponse(code = 500, message = DocumentationConstants.RESPONSECODE_500)})
	@GetMapping("/")
	public ResponseEntity<PersonWrapperDTO> getAllPersons(@ApiParam(value = DocumentationConstants.PAGE_DESC,
		      defaultValue = "1",
		      required = true) @RequestParam(value = "page",
		      required = true, defaultValue = "1") int page, 
			@ApiParam(value = DocumentationConstants.PAGE_SIZE_DESC,
		      defaultValue = "50",
		      required = true) @RequestParam(value = "size", required = false, defaultValue = "50") int size,
			@ApiParam(value = "${swagger.common.locale.ApiParam.value}", required = false, example = "en_US") @RequestParam(
				      value = "locale",
				      required = false, defaultValue = "en_US") String locale) {
		PersonWrapperDTO toRet = personService.getEntities(page, size, new Locale(locale));
		if(toRet.getPerson().isEmpty()) {
			return ResponseEntity.noContent().header("Content-Length", "0").build();	
		} else {
			return ResponseEntity.ok(toRet);
		}
	}
	
	@ApiOperation(value = "${swagger.getperson.ApiOperation.value}",
		    notes = "${swagger.getperson.ApiOperation.notes}", response = PersonWrapperDTO.class,
		    nickname = "getPersonById")
	@GetMapping("/{id}")
	@ApiResponses(value = {@ApiResponse(code = 200, message = DocumentationConstants.RESPONSECODE_200),
		    @ApiResponse(code = 400, message = DocumentationConstants.RESPONSECODE_400),
		    @ApiResponse(code = 401, message = DocumentationConstants.RESPONSECODE_401),
		    @ApiResponse(code = 403, message = DocumentationConstants.RESPONSECODE_403),
		    @ApiResponse(code = 404, message = DocumentationConstants.RESPONSECODE_404),
		    @ApiResponse(code = 500, message = DocumentationConstants.RESPONSECODE_500)})
	public ResponseEntity<PersonWrapperDTO> getPersonById(@ApiParam(value = "${swagger.common.personId.ApiParam.value}", required = true, example = "1",
		      allowEmptyValue = false) @PathVariable int id, 
			@ApiParam(value = "${swagger.common.locale.ApiParam.value}", required = false, example = "en_US") @RequestParam(
		      value = "locale",
		      required = false, defaultValue = "en_US") String locale) {
		return ResponseEntity.ok(personService.getEntityById(id, new Locale(locale)));
	}
	
	@ApiOperation(value = "${swagger.createperson.ApiOperation.value}",
		    notes = "${swagger.createperson.ApiOperation.notes}", response = PersonWrapperDTO.class,
		    nickname = "createPerson")
	@PostMapping("/")
	@ApiResponses(value = {@ApiResponse(code = 201, message = DocumentationConstants.RESPONSECODE_201),
		    @ApiResponse(code = 400, message = DocumentationConstants.RESPONSECODE_400),
		    @ApiResponse(code = 401, message = DocumentationConstants.RESPONSECODE_401),
		    @ApiResponse(code = 403, message = DocumentationConstants.RESPONSECODE_403),
		    @ApiResponse(code = 404, message = DocumentationConstants.RESPONSECODE_404),
		    @ApiResponse(code = 500, message = DocumentationConstants.RESPONSECODE_500)})
	public ResponseEntity<PersonWrapperDTO> createPerson(@ApiParam(value = "${swagger.common.person.ApiParam.value}",
		      required = true) @RequestBody(required = true) 
			@Valid PersonV1DTO personDTO) {
		personDTO.setId(null);
		return new ResponseEntity<PersonWrapperDTO>(personService.createEntity(personDTO), HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	@ApiOperation(value = "${swagger.updateperson.ApiOperation.value}",
    notes = "${swagger.updateperson.ApiOperation.notes}", response = PersonWrapperDTO.class,
    nickname = "createPerson")
	@ApiResponses(value = {@ApiResponse(code = 200, message = DocumentationConstants.RESPONSECODE_200),
		    @ApiResponse(code = 400, message = DocumentationConstants.RESPONSECODE_400),
		    @ApiResponse(code = 401, message = DocumentationConstants.RESPONSECODE_401),
		    @ApiResponse(code = 403, message = DocumentationConstants.RESPONSECODE_403),
		    @ApiResponse(code = 404, message = DocumentationConstants.RESPONSECODE_404),
		    @ApiResponse(code = 500, message = DocumentationConstants.RESPONSECODE_500)})
	public ResponseEntity<PersonWrapperDTO> updatePerson(@ApiParam(value = "${swagger.common.person.ApiParam.value}",
		      required = true) @RequestBody(required = true) 
		@Valid PersonV1DTO personDTO, @ApiParam(value = "${swagger.common.personId.ApiParam.value}", required = true, example = "1",
			      allowEmptyValue = false) @PathVariable int id, 
		@ApiParam(value = "${swagger.common.locale.ApiParam.value}", required = false, example = "en_US") @RequestParam(
			      value = "locale",
			      required = false, defaultValue = "en_US") String locale) {
		personDTO.setId(id);
		return ResponseEntity.ok(personService.updateEntityById(personDTO, new Locale(locale)));
	}
	
	@DeleteMapping("/")
	@ApiOperation(value = "${swagger.deletepersons.ApiOperation.value}",
    notes = "${swagger.deletepersons.ApiOperation.notes}", response = ResponseEntity.class,
    nickname = "deletePersons")
	@ApiResponses(value = {@ApiResponse(code = 202, message = DocumentationConstants.RESPONSECODE_202),
		    @ApiResponse(code = 400, message = DocumentationConstants.RESPONSECODE_400),
		    @ApiResponse(code = 401, message = DocumentationConstants.RESPONSECODE_401),
		    @ApiResponse(code = 403, message = DocumentationConstants.RESPONSECODE_403),
		    @ApiResponse(code = 404, message = DocumentationConstants.RESPONSECODE_404),
		    @ApiResponse(code = 500, message = DocumentationConstants.RESPONSECODE_500)})
	public ResponseEntity<Object> deletePersons() {
		personService.deleteAllEntities();
		return ResponseEntity.accepted().build();
	}
	
	@DeleteMapping("/{id}")
	@ApiOperation(value = "${swagger.deleteperson.ApiOperation.value}",
    notes = "${swagger.deleteperson.ApiOperation.notes}", response = ResponseEntity.class,
    nickname = "deletePerson")
	@ApiResponses(value = {@ApiResponse(code = 204, message = DocumentationConstants.RESPONSECODE_204),
		    @ApiResponse(code = 400, message = DocumentationConstants.RESPONSECODE_400),
		    @ApiResponse(code = 401, message = DocumentationConstants.RESPONSECODE_401),
		    @ApiResponse(code = 403, message = DocumentationConstants.RESPONSECODE_403),
		    @ApiResponse(code = 404, message = DocumentationConstants.RESPONSECODE_404),
		    @ApiResponse(code = 500, message = DocumentationConstants.RESPONSECODE_500)})
	public ResponseEntity<Object> deletePersonById(@ApiParam(value = "${swagger.common.personId.ApiParam.value}", required = true, example = "1",
		      allowEmptyValue = false) @PathVariable int id, 
			@ApiParam(value = "${swagger.common.locale.ApiParam.value}", required = false, example = "en_US") 
			@RequestParam(
			      value = "locale",
			      required = false, defaultValue = "en_US") String locale) {
		personService.deleteEntityById(id, new Locale(locale));
		return ResponseEntity.noContent().header("Content-Length", "0").build();
	}
}
