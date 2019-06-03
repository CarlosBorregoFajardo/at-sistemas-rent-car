package com.example.CarRent.data;

import lombok.Data;

@Data
public class RateDto {
	private Integer id;
    private Double price;
    private String startDate;
    private String endDate;
}