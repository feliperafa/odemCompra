package com.example.helping.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.helping.api.model.Cliente;
import com.example.helping.domain.exception.NegocioException;
import com.example.helping.domain.repository.ClienteInterface;

@Service
public class CadastroClienteService {

	@Autowired
	private ClienteInterface clienteInterface;

	public Cliente salvar(Cliente cliente) {
		Cliente clienteExistente = clienteInterface.findByEmail(cliente.getEmail());
		if(clienteExistente != null && !clienteExistente.equals(cliente)) {
				throw new NegocioException("Já existe um cliente cadastrado com esse E-mail.");
		}
		return clienteInterface.save(cliente);
	}

	public void deleteForId(Long clienteId) {
		clienteInterface.deleteById(clienteId);

	}

}
