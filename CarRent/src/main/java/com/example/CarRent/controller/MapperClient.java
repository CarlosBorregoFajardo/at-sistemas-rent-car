package com.example.CarRent.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.example.CarRent.data.ClientDto;
import com.example.CarRent.model.Client;

@Component
public class MapperClient implements MapperService<ClientDto,Client>{	
	/**
	 * Mapper a Client to a ClientDto
	 * @param clientEntity
	 * @return ClientDto
	 */
	@Override
	public ClientDto entityToDto(Client clientEntity) {
		ClientDto clientDto = new ClientDto();
		clientDto.setId(clientEntity.getIdClient());
		clientDto.setDni(clientEntity.getDni());
		clientDto.setName(clientEntity.getName());
		return clientDto;
	}
	/**
	 * Mapper a ClientDto to a Client
	 * @param clientDto
	 * @return Client
	 */
	@Override
	public Client dtoToEntity(ClientDto clientDto){
		Client clientEntity = new Client();
		clientEntity.setIdClient(clientDto.getId());
		clientEntity.setDni(clientDto.getDni());
		clientEntity.setName(clientDto.getName());
		return clientEntity;
	}
	/**
	 * Mapper a List of Client to a List of ClientDto
	 * @param clients
	 * @return List<ClientDto>
	 */
	@Override
	public List<ClientDto> entityToDtoList(List<Client> clients){
		List<ClientDto> clientsDto = new ArrayList<ClientDto>();
		for(int i = 0; i < clients.size(); i++) 
			clientsDto.add(entityToDto(clients.get(i)));
		return clientsDto;	
	}
}
