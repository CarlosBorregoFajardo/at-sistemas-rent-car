package com.example.CarRent.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.CarRent.model.Car;
import com.example.CarRent.model.Rental;

public interface RentalRepository extends JpaRepository<Rental,Integer> {

	Optional<Rental> findOneByIdRental(Integer idRental);
	List<Rental> findAllByClientContainingOrClientIsNull(String client, Pageable pageable);
	List<Rental> findAllByCar(Car car, Pageable pageable);

	@Query(value = "SELECT rent FROM Rental WHERE rent.renDateStart >= ?1 and rent.rentDateEnd <= ?2", nativeQuery = true)
	List<Rental> AllRentsBeetweenDates(String DateStart, String DateEnd);
}
