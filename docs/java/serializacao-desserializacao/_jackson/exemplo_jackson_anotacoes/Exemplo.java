package exemplo_jackson_anotacoes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.*;

import java.io.IOException;

public class Exemplo {

    static String serializar(Pessoa pessoa) {

        String json = null;
        final ObjectMapper mapper = new ObjectMapper();

        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        try {

            json = mapper.writeValueAsString(pessoa);

        } catch (final IOException e) {
            e.printStackTrace();
        }

        return json;
    }

    static Pessoa desserializar(String json) {

        Pessoa pessoa = new Pessoa();

        final ObjectMapper mapper = new ObjectMapper();

        try {

            pessoa = mapper.readValue(json, Pessoa.class);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return pessoa;
    }

    public static void main(String[] args) {

        Endereco endereco = new Endereco("Rua Um", null, "Centro");
        Pessoa pessoaOriginal = new Pessoa("José", 54, "M", endereco);

        String json = Exemplo.serializar(pessoaOriginal);
        Pessoa pessoa = Exemplo.desserializar(json);

        System.out.println(json);
        System.out.println(pessoa);
    }
}

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

/**
 * Ignora campos nulos (nível de classe)
 */
@JsonInclude(JsonInclude.Include.NON_NULL)

/**
 * Ignora os campos (nível de classe)
 */
@JsonIgnoreProperties({ "idade", "sexo" })

class Pessoa {

    String nome;
    int idade;
    String sexo;

    /**
     * Nomeação de campo
     */
    @JsonProperty("endereco_residencial")
    Endereco endereco;
}

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
class Endereco {

    String rua;

    /**
     * Ignora campos nulos (nível de campo)
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    String numero;

    /**
     * Ignora os campos (nível de campo)
     */
    @JsonIgnore
    String bairro;
}

// Saida
// >
// {
// "nome" : "José",
// "endereco_residencial" : {
// "rua" : "Rua Um"
// }
// }
// > Pessoa(nome=José, idade=0, sexo=null , endereco=Endereco(rua=Rua Um,
// numero=null, bairro=null))