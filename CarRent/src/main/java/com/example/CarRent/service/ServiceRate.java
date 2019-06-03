package com.example.CarRent.service;


import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.CarRent.model.Car;
import com.example.CarRent.model.Rate;
import com.example.CarRent.repository.RateRepository;

@Service
public class ServiceRate {
	@Autowired private RateRepository rateRepository;
	@Autowired private ServiceCar serviceCar;
	/**
	 * Save a Rate 
	 * @param ratelEntity
	 * @return Rate
	 */
	public Rate saveEntity(Rate rateEntity) {
		return rateRepository.save(rateEntity);
	}
	/** 
	 * find a Rate with a id  
	 * @param idRate
	 * @return Optional<Rate>
	 */
	public Optional<Rate> findById(Integer idRate) {
		return rateRepository.findOneByIdRate(idRate);	
	}
	/** 
	 * add a Rate to a Car
	 * @param idCar, idRate
	 */
	public void addRate(Integer idCar,Integer idRate) {
		Set<Car> car =  new HashSet();
		car.add(Optional.ofNullable(idCar).
								map(x -> serviceCar.findCarById(x)).
								get().get());
		Optional<Rate> tmpRate = findById(idRate);
		if(tmpRate.isPresent()){
			tmpRate.get().setCars(car);
			rateRepository.save(tmpRate.get());
		}
	}
}