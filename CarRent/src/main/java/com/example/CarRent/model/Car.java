package com.example.CarRent.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import lombok.Data;

@Data
@Entity
public class Car {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer idCar;
	//@Column(nullable = false, unique = true, length = 32)
	private String carPlate;
	private String registrationYear;
	
	@OneToMany(mappedBy = "car",fetch =  FetchType.LAZY)
	private Set<Rental> rents = new HashSet();
	@ManyToMany(mappedBy="cars",fetch = FetchType.LAZY)
	private Set<Rate> rates = new HashSet();
}