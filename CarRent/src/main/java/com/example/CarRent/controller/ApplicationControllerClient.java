package com.example.CarRent.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.CarRent.data.ClientDto;
import com.example.CarRent.model.Client;
import com.example.CarRent.service.ServiceClient;


@RestController
@RequestMapping("/client")
public class ApplicationControllerClient {
	@Autowired private ServiceClient serviceClient;
	@Autowired private MapperClient mapperClient;
	

	@GetMapping("/name/{name}")
	public List<ClientDto> get(@PathVariable(value="",required=false) String name,
			@PathVariable(value="10",required=false) Integer size,
			@PathVariable(value="0",required=false) Integer page) {
		return mapperClient.entityToDtoList(serviceClient.findAllClients(name, PageRequest.of(page, size)));
	}	
	@GetMapping("/id/{idClient}")
	public ResponseEntity<ClientDto> get(@PathVariable("idClient") Integer idClient) {
		final Optional<Client> client = serviceClient.findClientById(idClient);
		if(client.isPresent())
			return ResponseEntity.ok(mapperClient.entityToDto(client.get()));
		else
			return ResponseEntity.notFound().build();
	}	
	
	@PutMapping("/{idCar}")
	public ResponseEntity<ClientDto> put(@PathVariable("idCar") Integer idClient,@RequestBody ClientDto clientDto) {
		if(Optional.ofNullable(idClient).isPresent()) 
			if(Optional.ofNullable(clientDto).isPresent()) {				
				serviceClient.updateEntity(idClient,mapperClient.dtoToEntity(clientDto));
				return ResponseEntity.ok().build();
			}
			else return ResponseEntity.notFound().build();					
		else
			return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	public ResponseEntity<ClientDto> post(@RequestBody ClientDto clientDto) {
		return Optional.ofNullable(clientDto).
			map(x -> mapperClient.dtoToEntity(x)).
			map(x -> serviceClient.saveEntity(x)).
			map(x -> mapperClient.entityToDto(x)).
			map(x -> ResponseEntity.ok(x)).
			orElse(ResponseEntity.notFound().build());
	}
	
	@DeleteMapping("/{idClient}")
	public ResponseEntity<ClientDto> delete(@PathVariable("idClient") Integer idClient){
		if(Optional.ofNullable(idClient).isPresent()) {
			serviceClient.deleteEntity(idClient);
			return ResponseEntity.ok().build();
		}
		else
			return ResponseEntity.notFound().build();
	}
}