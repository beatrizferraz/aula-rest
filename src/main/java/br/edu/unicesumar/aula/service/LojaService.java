package br.edu.unicesumar.aula.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.edu.unicesumar.aula.domain.Loja;
import br.edu.unicesumar.aula.repository.LojaRepository;

@Service
public class LojaService {
    
    @Autowired
    private LojaRepository lojaRepository;

    public Page<Loja> findAll(Pageable pageable) {
        return this.lojaRepository.findAll(pageable);
    } 

    public Optional<Loja> findById(Long id) {
        return this.lojaRepository.findById(id);
    }

    public Loja save(Loja novaLoja) {
        return this.lojaRepository.save(novaLoja);
    }

    public Optional<Loja> update(Loja lojaExistente) {
        if(this.lojaRepository.existsById(lojaExistente.getId())) {
            Loja lojaAtualizada = this.lojaRepository.save(lojaExistente);
            return Optional.of(lojaAtualizada);
        }
        return Optional.empty();
    }

    public void deleteById(Long id) {
        this.lojaRepository.deleteById(id);
    }

    public boolean existeEsseId(Long id) {
        return this.lojaRepository.existsById(id); 
    }
}
