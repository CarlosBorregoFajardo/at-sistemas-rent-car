package com.example.CarRent.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.CarRent.data.CarDto;
import com.example.CarRent.model.Car;
import com.example.CarRent.service.ServiceCar;


@RestController
@RequestMapping("/car")
public class ApplicationControllerCar {
	@Autowired private ServiceCar serviceCar;
	@Autowired private MapperCar mapperCar;
	
	/**
	 * Get all the cars by registration year
	 * @param registrationYear
	 * @return List<CarDto>
	 */
	@GetMapping("/year/{registrationYear}/{size}/{page}")
	public List<CarDto> get(@PathVariable(value="",required=false) String registrationYear,
							@PathVariable(value="10",required=false) Integer size,
							@PathVariable(value="0",required=false) Integer page) {
		return mapperCar.entityToDtoList(serviceCar.findAllCars(registrationYear,PageRequest.of(page, size)));
	}	
	/**
	 * Get a car by id
	 * @param idCar
	 * @return ResponseEntity<CarDto>
	 */
	@GetMapping("/id/{idCar}")
	public ResponseEntity<CarDto> get(@PathVariable("idCar") Integer idCar) {
		final Optional<Car> car = serviceCar.findCarById(idCar);
		if(car.isPresent())
			return ResponseEntity.ok(mapperCar.entityToDto(car.get()));
		else
			return ResponseEntity.notFound().build();
	}
	/**
	 * Get the best Car rented between two dates
	 * @param dateStart, dateEnd
	 * @return Car
	 */
	@GetMapping("/benefit/{dateStart}{dateEnd}")
	public CarDto get(@PathVariable(value="dateStart") String dateStart,
			@PathVariable(value="dateEnd") String dateEnd){
		return mapperCar.entityToDto(serviceCar.benefit(dateStart, dateEnd));
	}	
	/**
	 * Update the car id with the new data
	 * @param idCar, carDto
	 * @return ResponseEntity<CarDto>
	 */
	@PutMapping("/{idCar}")
	public ResponseEntity<CarDto> put(@PathVariable("idCar") Integer idCar,@RequestBody CarDto carDto) {
		if(Optional.ofNullable(idCar).isPresent()) 
			if(Optional.ofNullable(carDto).isPresent()) {				
				serviceCar.updateEntity(idCar,mapperCar.dtoToEntity(carDto));
				return ResponseEntity.ok().build();
			}
			else return ResponseEntity.notFound().build();					
		else
			return ResponseEntity.notFound().build();
	}
	/**
	 * create a new Car
	 * @param carDto
	 * @return ResponseEntity<CarDto>
	 */
	@PostMapping
	public ResponseEntity<CarDto> post(@RequestBody CarDto carDto) {
		return Optional.ofNullable(carDto).
			map(x -> mapperCar.dtoToEntity(x)).
			map(x -> serviceCar.saveEntity(x)).
			map(x -> mapperCar.entityToDto(x)).
			map(x -> ResponseEntity.ok(x)).
			orElse(ResponseEntity.notFound().build());
	}
	/**
	 * Delete a Car by id
	 * @param idCar
	 */
	@DeleteMapping("/{idCar}")
	public ResponseEntity<CarDto> delete(@PathVariable("idCar") Integer idCar){
		if(Optional.ofNullable(idCar).isPresent()) {
			serviceCar.deleteEntity(idCar);
			return ResponseEntity.ok().build();
		}
		else
			return ResponseEntity.notFound().build();
	}
}
