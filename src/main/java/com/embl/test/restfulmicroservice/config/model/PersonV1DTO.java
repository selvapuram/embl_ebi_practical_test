package com.embl.test.restfulmicroservice.config.model;

import java.io.Serializable;
import java.util.Locale;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModelProperty.AccessMode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PersonV1DTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -481442337366436480L;
	
	@ApiModelProperty(value = "${person.id.ApiModelProperty.value}", required = true, accessMode = AccessMode.READ_ONLY)
	@JsonProperty(value ="id", access = Access.READ_ONLY)
	private Integer id;
	
	@JsonProperty("first_name")
	@ApiModelProperty(value = "${person.firstName.ApiModelProperty.value}", required = true)
	private String firstName;
	
	@JsonProperty("last_name")
	@ApiModelProperty(value = "${person.lastName.ApiModelProperty.value}", required = true)
	private String lastName;
	
	@JsonProperty("age")
	@ApiModelProperty(value = "${person.age.ApiModelProperty.value}", required = true)
	private int age;
	
	@JsonProperty("favourite_colour")
	@ApiModelProperty(value = "${person.favcolor.ApiModelProperty.value}", required = false)
	private String favouriteColour;
	
	@JsonIgnore
	private Locale locale;

}
