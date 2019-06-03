package com.example.CarRent.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.example.CarRent.data.RentalDto;
import com.example.CarRent.model.Rental;

@Component
public class MapperRental  implements MapperService<RentalDto,Rental>{
	/**
	 * Mapper a Rental to a RentalDto
	 * @param rentalEntity
	 * @return RentalDto
	 */
	@Override
	public RentalDto entityToDto(Rental rentalEntity) {
		RentalDto rentalDto = new RentalDto();
		rentalDto.setRentDateStart(rentalEntity.getRentDateStart());
		rentalDto.setRentDateEnd(rentalEntity.getRentDateEnd());
		rentalDto.setRentPrice(rentalEntity.getRentPrice());
		rentalDto.setClient(rentalEntity.getClient());
		rentalDto.setCar(rentalEntity.getCar());
		return rentalDto;
	}
	/**
	 * Mapper a RentalDto to a Rental
	 * @param rentalDto
	 * @return Rental
	 */
	@Override
	public Rental dtoToEntity(RentalDto rentalDto) {
		Rental rentalEntity = new Rental();
		rentalEntity.setRentDateStart(rentalDto.getRentDateStart());
		rentalEntity.setRentDateEnd(rentalDto.getRentDateEnd());
		rentalEntity.setRentPrice(rentalDto.getRentPrice());
		return rentalEntity;		
	}
	/**
	 * Mapper a List of Rental entities to a list of RentalDto
	 * @param rentals
	 * @return List<RentalDto>
	 */
	@Override
	public List<RentalDto> entityToDtoList(List<Rental> rentals){
		List<RentalDto> rentalsDto = new ArrayList<RentalDto>();
		for(int i = 0; i < rentals.size(); i++) 
			rentalsDto.add(entityToDto(rentals.get(i)));
		return rentalsDto;	
	}
}
