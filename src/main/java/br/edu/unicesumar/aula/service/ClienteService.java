package br.edu.unicesumar.aula.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.edu.unicesumar.aula.domain.Cliente;
import br.edu.unicesumar.aula.repository.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	public Page<Cliente> findAll(Pageable pageable) {
		return clienteRepository.findAll(pageable);
	}

	public Optional<Cliente> findById(UUID id) {
		return this.clienteRepository.findById(id);
	}

	public Cliente save(Cliente novoCliente) {
		if(this.clienteRepository.existsByCpf(novoCliente.getCpf())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "CPF já existe no sistema.");
        }	
		return this.clienteRepository.save(novoCliente); 
	}

    public Optional<Cliente> update(Cliente clienteExistente) {
        if(this.clienteRepository.existsById(clienteExistente.getId())) {
            Cliente clienteAtualizado = this.clienteRepository.save(clienteExistente);
            return Optional.of(clienteAtualizado);
        }
        return Optional.empty();
    }
	
	public void deleteById(UUID id) {
		this.clienteRepository.deleteById(id);
	}

	public boolean existeEsseId(UUID id) {
        return this.clienteRepository.existsById(id);
    }
	
}
