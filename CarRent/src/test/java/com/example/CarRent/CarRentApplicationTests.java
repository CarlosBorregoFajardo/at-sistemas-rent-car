package com.example.CarRent;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.CarRent.model.Client;
import com.example.CarRent.repository.ClientRepository;
import com.example.CarRent.service.ServiceClient;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CarRentApplicationTests {
	@Mock private ClientRepository clientRepository;
	@InjectMocks private ServiceClient serviceClient;
	
	@Test
	public void EncontrarClientePorId() {
		Integer id = 1;
		Client client = new Client();
		client.setIdClient(1);
		client.setName("juan");
		client.setDni("3203202P");
		Optional<Client> clientOptional = Optional.of(client);
		
		Mockito.when(clientRepository.findOneByIdClient(id)).thenReturn(clientOptional);
		
		Optional<Client> resultado = serviceClient.findClientById(id);
		assertEquals(client, resultado.get());				
	}
	@Test
	public void pruebaNuloEncontrarClientePorId() {
		Integer id = null;	
		
		Mockito.when(clientRepository.findOneByIdClient(id)).thenReturn(null);
		
		Optional<Client> resultado = serviceClient.findClientById(id);
		assertNull(resultado);
	}
}
