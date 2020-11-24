package com.example.helping.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.helping.api.model.Cliente;

@Repository
public interface ClienteInterface  extends JpaRepository<Cliente, Long>{

	
	List<Cliente> findByNome(String nome);
	List<Cliente> findByNomeContaining(String nome);
	Cliente findByEmail(String email);
}
