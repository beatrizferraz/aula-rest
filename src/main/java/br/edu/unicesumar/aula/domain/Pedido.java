package br.edu.unicesumar.aula.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Pedido {
    
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    //private LocalDateTime dataHora = LocalDateTime.now();

    private String status;

    private double valorTotal;

    private double taxaEntrega;

    private double desconto;

    private String tipoPagamento;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "itemPedido_id")
    private List<ItemPedido> itemPedido = new ArrayList<>();

    // @OneToOne
    // @JoinColumn(name = "pedido_id")
    // private Endereco enderecoEntrega = new Endereco(); 
}
