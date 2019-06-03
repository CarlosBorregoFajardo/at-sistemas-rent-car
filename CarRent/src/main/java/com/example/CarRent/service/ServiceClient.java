package com.example.CarRent.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.CarRent.model.Client;
import com.example.CarRent.repository.ClientRepository;


@Service
public class ServiceClient {
	@Autowired private ClientRepository clientRepository;
	/**
	 * Finds all clients by name
	 * @param name
	 * @return List<Client>
	 */
	public List<Client> findAllClients(String name, Pageable pageable){
		return clientRepository.findAllByNameContainingOrNameIsNull(name, pageable);
	}
	/**
	 * Finds a client by id
	 * @param id
	 * @return Optional<Client>
	 */	
	public Optional<Client> findClientById(Integer id){
		return clientRepository.findOneByIdClient(id);
	}	
	/**
	 * Save a client
	 * @param clientEntity
	 * @return Client
	 */
	public Client saveEntity(Client clientEntity) {
		return clientRepository.save(clientEntity);
	}
	/**
	 * Update client id with the new data
	 * @param id, clientEntity
	 */
	public void updateEntity(Integer  id,Client clientEntity) {
		Client client = findClientById(id).get();
		client.setIdClient(clientEntity.getIdClient());
		client.setDni(clientEntity.getDni());
		client.setName(clientEntity.getName());
		clientRepository.save(client);
	}
	/**
	 * Delete the client id
	 * @param id
	 */
	public void deleteEntity(Integer id) {
		clientRepository.deleteById(id);
	}
}

