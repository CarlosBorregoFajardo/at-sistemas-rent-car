package com.example.CarRent.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.CarRent.model.Car;
import com.example.CarRent.repository.CarRepository;

@Service
public class ServiceCar {
	@Autowired private CarRepository carRepository;
	@Autowired private ServiceCar serviceRental;
	/**
	 * Find all cars by the year of registration
	 * @param registrationYear
	 * @return List<Car>
	 */
	public List<Car> findAllCars(String registrationYear, Pageable pageable){
		return carRepository.findAllByRegistrationYearContainingOrRegistrationYearIsNull(registrationYear, pageable);
	}
	/**
	 * Find a car by id
	 * @param idcar
	 * @return Optional<Car>
	 */
	public Optional<Car> findCarById(Integer idCar){
		return carRepository.findOneByIdCar(idCar);
	}
	/**
	 * Save a car
	 * @param carEntity
	 * @return Car
	 */
	public Car saveEntity(Car carEntity) {
		return carRepository.save(carEntity);
	}
	/**
	 * Update the car id with the new data
	 * @param id, carEntity
	 */
	public void updateEntity(Integer  id,Car carEntity) {
		Car car = findCarById(id).get();
		car.setIdCar(carEntity.getIdCar());
		car.setCarPlate(carEntity.getCarPlate());
		car.setRegistrationYear(carEntity.getRegistrationYear());
		carRepository.save(car);
	}
	/**
	 * Delete the car id
	 * @param id
	 */
	public void deleteEntity(Integer id) {
		carRepository.deleteById(id);
	}
	/**
	 * return the car with more benefits 
	 * @param dateStart, dateEnd
	 * @return Car
	 */
	public Car benefit(String dateStart,String dateEnd) {
		return serviceRental.benefit(dateStart, dateEnd);
	}
}