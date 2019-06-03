package com.example.CarRent.data;

import com.example.CarRent.model.Car;
import com.example.CarRent.model.Client;

import lombok.Data;

@Data
public class RentalDto {
	private String rentDateStart;
	private String rentDateEnd;
	private Double rentPrice;
	private Client client;
	private Car car;
}
