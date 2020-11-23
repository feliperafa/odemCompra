package com.example.helping.api.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.helping.api.model.Cliente;
import com.example.helping.domain.repository.ClienteInterface;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

	@Autowired
	private ClienteInterface clienteInterface;

	// Metodo para listar todos os Clientes
	@GetMapping
	public List<Cliente> listar() {
		return clienteInterface.findAll();

	}

	// Metodo para Listar Cliente por Id
	@GetMapping("/{clienteId}")
	public ResponseEntity<Cliente> buscar(@PathVariable Long clienteId) {
		Optional<Cliente> cliente = clienteInterface.findById(clienteId);

		if (cliente.isPresent()) {
			return ResponseEntity.ok(cliente.get());
		}
		return ResponseEntity.notFound().build();
	}

	// Metodo para criar Novo Cliente
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente adicionar(@Valid @RequestBody Cliente cliente) {
		return clienteInterface.save(cliente);
	}

	// Metodod para atualizar Cliente ja existente
	@PutMapping("/{clienteId}")
	public ResponseEntity<Cliente> atualizar(@Valid @PathVariable Long clienteId, @RequestBody Cliente cliente) {

		if (!clienteInterface.existsById(clienteId)) {
			return ResponseEntity.notFound().build();
		}
		cliente.setId(clienteId);
		cliente = clienteInterface.save(cliente);

		return ResponseEntity.ok(cliente);
	}

	@DeleteMapping("/{clienteId}")
	public ResponseEntity<Void> deleteForId(@PathVariable Long clienteId) {

		if (!clienteInterface.existsById(clienteId)) {
			return ResponseEntity.notFound().build();
		}
		clienteInterface.deleteById(clienteId);
		return ResponseEntity.noContent().build();
	}
}
