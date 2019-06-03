package com.example.CarRent.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import lombok.Data;

@Data
@Entity
public class Rate {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private	Integer idRate;
	@Column(nullable = false)
	private String RateStartDate;
	@Column(nullable = false)
	private String RateEndDate;
	@Column(nullable = false)
	private Double RatePrice;
	
	@ManyToMany(fetch =  FetchType.LAZY)
	private Set<Car> cars = new HashSet();
}