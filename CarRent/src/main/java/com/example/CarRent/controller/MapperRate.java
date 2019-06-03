package com.example.CarRent.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.example.CarRent.data.RateDto;
import com.example.CarRent.model.Rate;

@Component
public class MapperRate implements MapperService<RateDto,Rate>{
	/**
	 * Mapper a Rate to a RateDto
	 * @param rateEntity
	 * @return RateDto
	 */
	@Override
	public RateDto entityToDto(Rate rateEntity) {
		RateDto rateDto = new RateDto();
		rateDto.setId(rateEntity.getIdRate());
		rateDto.setPrice(rateEntity.getRatePrice());
		rateDto.setStartDate(rateEntity.getRateStartDate());
		rateDto.setEndDate(rateEntity.getRateEndDate());
		return rateDto;		
	} 
	/**
	 * Mapper a RatesDto to a Rate
	 * @param rateDto
	 * @return Rate
	 */
	@Override
	public Rate dtoToEntity(RateDto rateDto) {
		Rate rateEntity = new Rate();
		rateEntity.setIdRate(rateDto.getId());
		rateEntity.setRatePrice(rateDto.getPrice());
		rateEntity.setRateStartDate(rateDto.getStartDate());
		rateEntity.setRateEndDate(rateDto.getEndDate());
		return rateEntity;
	}
	/**
	 * Mapper a List of Rate to a List of RateDto
	 * @param rates
	 * @return List<RateDto>
	 */
	@Override
	public List<RateDto> entityToDtoList(List<Rate> rates){
		List<RateDto> ratesDto = new ArrayList<RateDto>();
		for(int i = 0; i < rates.size(); i++) 
			ratesDto.add(entityToDto(rates.get(i)));
		return ratesDto;	
	}
}
