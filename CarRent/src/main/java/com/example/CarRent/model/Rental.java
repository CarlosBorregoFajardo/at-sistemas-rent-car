package com.example.CarRent.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;

@Data
@Entity
public class Rental {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private	Integer idRental;
	@Column(nullable = false)
	private String rentDateStart;
	@Column(nullable = false)
	private String rentDateEnd;
	@Column(nullable = false)
	private Double rentPrice;
	
	@ManyToOne private Client client;
	@ManyToOne private Car car;
}