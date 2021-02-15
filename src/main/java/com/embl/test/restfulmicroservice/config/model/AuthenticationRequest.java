package com.embl.test.restfulmicroservice.config.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationRequest implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 2464454915592683173L;
	/**
	 * 
	 */
	private String username;
    private String password;
}