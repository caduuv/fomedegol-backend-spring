package br.edu.ifce.fomedegolservico.core.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "cartoes_credito")
@Getter
@Setter
public class CartaoCredito implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sequencial_cartao")
    private Long sequencialCartao;

    @Column(name = "sequencial_usuario")
    private Long sequencialUsuario;

    @Column(name = "numero_cartao")
    private BigDecimal numeroCartao;

    @Column(name = "cvv")
    private BigDecimal cvv;

    @Column(name = "nome_titular")
    private String nomeTitular;

    @Column(name = "mes_validade")
    private Integer mesValidade;

    @Column(name = "ano_validade")
    private Integer anoValidade;

    @Column(name = "apelido")
    private String apelido;

}
