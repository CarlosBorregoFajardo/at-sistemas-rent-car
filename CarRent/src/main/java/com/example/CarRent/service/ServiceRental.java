package com.example.CarRent.service;

import java.time.LocalDate;
import java.time.Period;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.CarRent.model.Car;
import com.example.CarRent.model.Rental;
import com.example.CarRent.repository.RentalRepository;


@Service
public class ServiceRental {
	@Autowired private RentalRepository rentalRepository;	
	/**
	 * Search all rentals of a client or all clients if client is null 
	 * @param client
	 * @return List<Rental>
	 */
	public List<Rental> findAllRentals(String client, Pageable pageable){
		return rentalRepository.findAllByClientContainingOrClientIsNull(client, pageable);
	}
	/**
	 * Search a rental
	 * @param idRental
	 * @return Optional<Rental>
	 */
	public Optional<Rental> findRentalById(Integer idRental){
		return rentalRepository.findOneByIdRental(idRental);
	}	
	/**
	 * Save a Rental 
	 * @param rentalEntity
	 */
	public Rental saveEntity(Rental rentalEntity) {
		return rentalRepository.save(rentalEntity);
	}
	/**
	 * Update the rent id with the new data
	 * @param id, rentalEntity
	 */
	public void updateEntity(Integer  id,Rental rentalEntity) {
		Rental rental = findRentalById(id).get();
		rental.setRentDateStart(rentalEntity.getRentDateStart());
		rental.setRentDateEnd(rentalEntity.getRentDateEnd());
		rental.setRentPrice(rentalEntity.getRentPrice());
		rental.setClient(rentalEntity.getClient());
		rental.setCar(rentalEntity.getCar());
		rentalRepository.save(rental);
	}
	/**
	 * Delete entity id
	 * @param id
	 */
	public void deleteEntity(Integer id) {
		rentalRepository.deleteById(id);
	}
	/**
	 * check if the date is valid for a rent
	 * @param car, Start
	 * @return boolean
	 */
	public boolean validData(Car car,String dateStart) {
		List<Rental> rentals = rentalRepository.findAllByCar(car,PageRequest.of(0,100));
		LocalDate date = LocalDate.parse(dateStart);
		Boolean flag = true;
		for(int i =0;flag & i < rentals.size();i++)
			if(date.compareTo(LocalDate.parse(rentals.get(i).getRentDateEnd())) < 0)
				flag = false;
		return flag;
	}
	/**
	 * return the car with more benefits 
	 * @param dateStart, dateEnd
	 * @return Car
	 * 
	 */
	public Car benefit(String dateStart,String dateEnd) {
		List<Rental> rents= rentalRepository.AllRentsBeetweenDates(dateStart,dateEnd);
		Map<Car, Double> benefit = RentsToMap(rents);
		Double best = 0.0;
		Car car = new Car();
		for (Map.Entry<Car, Double> entry : benefit.entrySet()) {
			if(entry.getValue() > best){
				car = entry.getKey();
				best = entry.getValue();
				}
		}
		return car;
	}
	/**
	 * Mapper Rents to Map
	 * @param rents
	 * @return Map<Car,Double>
	 * 
	 */
	public Map<Car,Double> RentsToMap(List<Rental> rents){
		Map<Car, Double> map = new HashMap();
		for(int i = 0; i<rents.size();i++){
			final Rental rent = rents.get(i);
			final LocalDate end = LocalDate.parse(rent.getRentDateEnd());
			final LocalDate start = LocalDate.parse(rent.getRentDateStart());
			final Double periodBenefit = Period.between(start,end).getDays()*rent.getRentPrice();
			if(map.containsKey(rent.getCar()))
				map.replace(rent.getCar(), map.get(rent.getCar())+ periodBenefit);
			else
				map.put(rent.getCar(),periodBenefit);
		}
		return map;
	}
	
	
}