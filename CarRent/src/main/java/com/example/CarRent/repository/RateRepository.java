package com.example.CarRent.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.CarRent.model.Rate;

public interface RateRepository extends JpaRepository<Rate,Integer>{
	Optional<Rate> findOneByIdRate(Integer idRate);
}
