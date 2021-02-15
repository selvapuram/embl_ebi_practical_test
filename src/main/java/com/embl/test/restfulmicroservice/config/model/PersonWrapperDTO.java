package com.embl.test.restfulmicroservice.config.model;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModelProperty.AccessMode;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PersonWrapperDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7235194630108436260L;
	
	@Getter
	@Setter
	@ApiModelProperty(value = "${person.list.ApiModelProperty.value}", required = true, accessMode = AccessMode.READ_ONLY)
	private List<PersonV1DTO> person;
	
	@Getter
	@Setter
	@ApiModelProperty(value = "${person.pagination.ApiModelProperty.value}", required = true, accessMode = AccessMode.READ_ONLY)
	@JsonInclude(value = Include.NON_NULL, content = Include.NON_EMPTY)
	private PaginationInfo pageInfo;
	
	public static PersonWrapperDTO build(PersonV1DTO person) {
		PersonWrapperDTO object = new PersonWrapperDTO();
		object.setPerson(Collections.singletonList(person));
		return object;
	}
	
	public static PersonWrapperDTO build(List<PersonV1DTO> persons) {
		PersonWrapperDTO object = new PersonWrapperDTO();
		object.setPerson(persons);
		return object;
	}

	public static PersonWrapperDTO buildEmpty() {
		PersonWrapperDTO object = new PersonWrapperDTO();
		object.setPerson(Collections.emptyList());
		return object;
	}

}
