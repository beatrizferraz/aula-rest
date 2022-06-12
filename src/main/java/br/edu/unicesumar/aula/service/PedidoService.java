package br.edu.unicesumar.aula.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.edu.unicesumar.aula.domain.Pedido;
import br.edu.unicesumar.aula.domain.ItemPedido;

import br.edu.unicesumar.aula.repository.PedidoRepository;

@Service
public class PedidoService {
    
    @Autowired
    private PedidoRepository pedidoRepository;

    public double calculaTotalItem(ItemPedido item) {
        double totalItem = 0.0;
        totalItem = item.getValorUnitario() * item.getQuantidade();
        item.setPrecoTotal(totalItem);
        return totalItem;
    }

    public double calculaTotalPedido(List<ItemPedido> itens) {
        double totalPedido = 0.0;

        for( ItemPedido itemPedido: itens ) { 
            totalPedido = totalPedido + calculaTotalItem(itemPedido);
        } 
        return totalPedido;
    }

    public double calculaValorFinalPedido(Pedido pedido) {
        double valorFinal = 0.0;
        valorFinal = (calculaTotalPedido(pedido.getItemPedido()) + pedido.getTaxaEntrega()) - pedido.getDesconto();
        pedido.setValorFinalPedido(valorFinal);
        return valorFinal;
    }

    public Page<Pedido> findAll(Pageable pageable) {
        return this.pedidoRepository.findAll(pageable);
    } 

    public Optional<Pedido> findById(UUID id) {
        return this.pedidoRepository.findById(id);
    }

    public Pedido save(Pedido novoPedido) {
        novoPedido.setValorTotal(calculaTotalPedido(novoPedido.getItemPedido()));
        novoPedido.setValorFinalPedido(calculaValorFinalPedido(novoPedido));
        return this.pedidoRepository.save(novoPedido); 
    }

    public void deleteById(UUID id) {
        this.pedidoRepository.deleteById(id);
    }

    public void deleteAll() {
        this.pedidoRepository.deleteAll();
    }

    public boolean existeEsseId(UUID id) {
        return this.pedidoRepository.existsById(id);
    }
}

