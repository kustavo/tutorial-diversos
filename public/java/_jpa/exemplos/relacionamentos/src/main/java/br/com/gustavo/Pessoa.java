package br.com.gustavo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;

@Entity @Table(name="pessoas")
@Getter @Setter
@NoArgsConstructor
class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String nome;

    @ElementCollection
    @CollectionTable(name="cliente_enderecos")
    private Collection<Endereco> endereco;

    @ElementCollection
    @CollectionTable(name="apelidos") // opcional
    @Column(name="apelido_pessoa")    // opcional
    private Collection<String> apelidos;


    Pessoa(String nome) {

        this.nome = nome;
    }

}