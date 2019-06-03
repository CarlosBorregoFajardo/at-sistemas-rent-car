package com.example.CarRent.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.CarRent.model.Car;

@Repository
public interface CarRepository extends JpaRepository<Car,Integer> {
	Optional<Car> findOneByIdCar(Integer idCar);
	List<Car> findAllByRegistrationYearContainingOrRegistrationYearIsNull(String registrationYear, Pageable pageable );
}
