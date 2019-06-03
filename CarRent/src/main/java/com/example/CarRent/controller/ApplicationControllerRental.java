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

import com.example.CarRent.data.RentalDto;
import com.example.CarRent.model.Rental;
import com.example.CarRent.service.ServiceRental;


@RestController
@RequestMapping("/rent")
public class ApplicationControllerRental {
	@Autowired private ServiceRental serviceRental;
	@Autowired private MapperRental mapperRental;
	/**
	 * Get all the Rentals or all the rentals of a client
	 * @param client
	 * @return List<RentalDto>
	 */
	@GetMapping("/client/{client}")
	public List<RentalDto> get(@PathVariable(value="client",required=false) String client,
			@PathVariable(value="10",required=false) Integer size,
			@PathVariable(value="0",required=false) Integer page){
		return mapperRental.entityToDtoList(serviceRental.findAllRentals(client,PageRequest.of(page, size)));
	}
	/**
	 * Get a Rental by a id of a car
	 * @param idRental
	 * @return ResponseEntity<RentalDto>
	 */
	@GetMapping("/id/{id}")
	public ResponseEntity<RentalDto> get(@PathVariable("idCar") Integer idRental) {
		final Optional<Rental> rental = serviceRental.findRentalById(idRental);
		if(rental.isPresent())
			return ResponseEntity.ok(mapperRental.entityToDto(rental.get()));
		else
			return ResponseEntity.notFound().build();
	}
	/**
	 * Update the Rental id with the new data
	 * @param idRental, rentalDto
	 * @return ResponseEntity<RentalDto>
	 */
	@PutMapping("/id/{idCar}")
	public ResponseEntity<RentalDto> put(@PathVariable("idCar") Integer idRental,@RequestBody RentalDto rentalDto) {
		if(Optional.ofNullable(idRental).isPresent()) 
			if(Optional.ofNullable(rentalDto).isPresent()) {				
				serviceRental.updateEntity(idRental,mapperRental.dtoToEntity(rentalDto));
				return ResponseEntity.ok().build();
			}
			else return ResponseEntity.notFound().build();					
		else
			return ResponseEntity.notFound().build();
	}
	/**
	 * Create a new Rental
	 * @param rentalDto
	 * @return ResponseEntity<RentalDto>
	 */
	@PostMapping
	public ResponseEntity<RentalDto> post(@RequestBody RentalDto rentalDto) {
		if(Optional.ofNullable(rentalDto).
				map(x -> serviceRental.validData(x.getCar(),x.getRentDateStart())).
				get())
			return Optional.ofNullable(rentalDto).
					map(x -> mapperRental.dtoToEntity(x)).
					map(x -> serviceRental.saveEntity(x)).
					map(x -> mapperRental.entityToDto(x)).
					map(x -> ResponseEntity.ok(x)).
					orElse(ResponseEntity.notFound().build());
		else
			return ResponseEntity.badRequest().build();
	}
	/**
	 * Delete a client by id
	 * @param idClient
	 */
	@DeleteMapping("/{idClient}")
	public ResponseEntity<RentalDto> delete(@PathVariable("idRental") Integer idRental){
		if(Optional.ofNullable(idRental).isPresent()) {
			serviceRental.deleteEntity(idRental);
			return ResponseEntity.ok().build();
		}
		else
			return ResponseEntity.notFound().build();
	}

	
}
