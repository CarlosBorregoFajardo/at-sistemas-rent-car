package com.example.CarRent.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.example.CarRent.data.CarDto;
import com.example.CarRent.model.Car;

@Component
public class MapperCar implements MapperService<CarDto,Car>{
	/**
	 * Mapper a Car to a CarDto
	 * @param carEntity
	 * @return CarDto
	 */	
	@Override
	public CarDto entityToDto(Car carEntity) {
		CarDto carDto = new CarDto();
		carDto.setId(carEntity.getIdCar());
		carDto.setCarPlate(carEntity.getCarPlate());
		carDto.setRegistrationYear(carEntity.getRegistrationYear());
		return carDto;
	}
	/**
	 * Mapper a CartDto to a Car
	 * @param carDto
	 * @return Car
	 */
	@Override
	public Car dtoToEntity(CarDto carDto) {
		Car carEntity = new Car();
		carEntity.setIdCar(carDto.getId());
		carEntity.setCarPlate(carDto.getCarPlate());
		carEntity.setRegistrationYear(carDto.getRegistrationYear());
		return carEntity;
	}
	/**
	 * Mapper a List of Car to a List of CarDto
	 * @param cars
	 * @return List<CarDto>
	 */
	@Override
	public List<CarDto> entityToDtoList(List<Car> cars){
		List<CarDto> carsDto = new ArrayList<CarDto>();
		for(int i = 0; i < cars.size(); i++) 
			carsDto.add(entityToDto(cars.get(i)));
		return carsDto;	
	}
}