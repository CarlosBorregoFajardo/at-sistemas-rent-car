package com.example.CarRent.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.CarRent.data.CarDto;
import com.example.CarRent.data.RateDto;
import com.example.CarRent.service.ServiceRate;


@RestController
@RequestMapping("/rate")
public class ApplicationControllerRate {
	@Autowired private ServiceRate serviceRate;
	@Autowired private MapperRate mapperRate;

   /**
	* Create a new Rate
	* @param rateDto
	* @return ResponseEntity<RateDto>
	*/
	@PostMapping
	public ResponseEntity<RateDto> post(@RequestBody RateDto rateDto) {
			return Optional.ofNullable(rateDto).
					map(x -> mapperRate.dtoToEntity(x)).
					map(x -> serviceRate.saveEntity(x)).
					map(x -> mapperRate.entityToDto(x)).
					map(x -> ResponseEntity.ok(x)).
					orElse(ResponseEntity.notFound().build());
	}
	/**
	 * add a car to a rate
	 * @param idCar, idRate
	 * @return ResponseEntity<CarDto>
	 */
	@PutMapping("/idRate/{idRate}/idCar/{idCar}")
	public ResponseEntity<CarDto> put(@PathVariable("idCar") Integer idCar,@PathVariable("idRate") Integer idRate) {
		if(Optional.ofNullable(idCar).isPresent()) 
			if(Optional.ofNullable(idRate).isPresent()) {				
				serviceRate.addRate(idCar, idRate);
				return ResponseEntity.ok().build();
			}
			else return ResponseEntity.notFound().build();					
		else
			return ResponseEntity.notFound().build();
	}
}