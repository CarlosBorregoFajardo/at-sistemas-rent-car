package com.example.CarRent.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;

@Data
@Entity
public class Client {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer idClient;
	private String name;
	@Column(nullable = false)
	private String dni;
	
	@OneToMany(mappedBy = "client",fetch =  FetchType.LAZY)
	private Set<Rental> rents = new HashSet<Rental>();
}
