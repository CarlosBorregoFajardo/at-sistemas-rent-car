package com.example.CarRent.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.CarRent.model.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client,Integer>{
	Optional<Client> findOneByIdClient(Integer IdClient);
	List<Client> findAllByNameContainingOrNameIsNull(String name, Pageable pageable);

}
