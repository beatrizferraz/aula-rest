package br.edu.unicesumar.aula.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.edu.unicesumar.aula.domain.Produto;
import br.edu.unicesumar.aula.repository.ProdutoRepository;

@Service
public class ProdutoService {
    
    @Autowired
    private ProdutoRepository produtoRepository;

    public Page<Produto> findAll(Pageable pageable) {
        return this.produtoRepository.findAll(pageable);
    } 

    public Optional<Produto> findById(Long id) {
        return this.produtoRepository.findById(id);
    }

    public Produto save(Produto novoProduto) {
        return this.produtoRepository.save(novoProduto);
    }

    public Optional<Produto> update(Produto produtoExistente) {
        if(this.produtoRepository.existsById(produtoExistente.getId())) {
            Produto produtoAtualizado = this.produtoRepository.save(produtoExistente);
            return Optional.of(produtoAtualizado);
        }
        return Optional.empty();
    }

    public void deleteById(Long id) {
        this.produtoRepository.deleteById(id);
    }

    public boolean existeEsseId(Long id) {
        return this.produtoRepository.existsById(id);
    }
}
