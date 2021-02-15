package com.embl.test.restfulmicroservice.config.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class PaginationInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7943520762467839772L;
	
	private int page;
	
	private int size;
	
	private int totalPages;
	
	private long totalCount;

}
