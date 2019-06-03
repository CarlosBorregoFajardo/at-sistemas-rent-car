package com.example.CarRent.controller;

import java.util.List;

public interface MapperService<T, S> {
	/**
	 * Mapper Object S to Object T
	 * @param MapDto
	 * @return
	 */
	T entityToDto(S mapDto);
	
	/**
	 * Mapper Object T to Object S
	 * @param MapEnt
	 * @return
	 */
	S dtoToEntity(T mapEnt);
	
	/**
	 * Mapper List of Object S to ListofObject T
	 * @param List<S> MapDto
	 * @return
	 */
	List<T> entityToDtoList(List<S> mapDto);

}
