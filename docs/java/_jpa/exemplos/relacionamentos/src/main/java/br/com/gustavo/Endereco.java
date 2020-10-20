package br.com.gustavo;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="enderecos")
@Getter @Setter
@NoArgsConstructor
@Embeddable
public class Endereco {

    @Id
    private long id;

    private String rua;

    private int numero;


    Endereco(String rua, int numero) {

        this.rua = rua;
        this.numero = numero;

    }
}
