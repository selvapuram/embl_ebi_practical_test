package com.embl.test.restfulmicroservice.service.contract;

import java.util.Locale;

public interface IPersonCrudService<T, U> {
	
	U createEntity(T dto);
	
	U updateEntityById(T dto, Locale locale);
	
	U getEntityById(Integer id, Locale locale);
	
	U getEntities(int page, int size, Locale locale);
	
	void deleteEntityById(Integer id, Locale locale);
	
	void deleteAllEntities();

}
