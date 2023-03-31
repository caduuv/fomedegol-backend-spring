package br.edu.ifce.fomedegolservico.core.model;



import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "usuarios")
@Getter
@Setter
public class Usuario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sequencial_usuario")
    private Long sequencial;

    @Column(name = "nome", length = 100)
    private String nome;

    @Column(name = "email", length = 150)
    private String email;

    @Column(name = "cpf", length = 11)
    private String cpf;

    @Column(name = "data_criacao")
    private Date dataCriacao;

    @Column(name = "uid_firebase")
    private String uIDFirebase;

    @Column(name = "telefone")
    private Long telefone;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "sequencialUsuario")
    private List<CartaoCredito> cartoesCredito;

}
