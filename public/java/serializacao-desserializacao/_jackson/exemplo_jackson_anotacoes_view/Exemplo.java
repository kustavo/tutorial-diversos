package exemplo_jackson_anotacoes_view;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.*;

import java.io.IOException;

public class Exemplo {

    static String serializar(Pessoa pessoa, Class<?> view) {

        String json = null;
        final ObjectMapper mapper = new ObjectMapper();

        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        try {

            json = mapper.writerWithView(view)
                    .writeValueAsString(pessoa);

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

        String json = null;
        Pessoa pessoa = null;

        Endereco endereco = new Endereco("Rua Um", null, "Centro");
        Pessoa pessoaOriginal = new Pessoa("José", 54, "M", endereco);

        /*
         * View pública
         */
        json = Exemplo.serializar(pessoaOriginal, ViewsPessoa.Publico.class);
        pessoa = Exemplo.desserializar(json);

        System.out.println(json);
        System.out.println(pessoa);

        /*
         * View privada
         */
        json = Exemplo.serializar(pessoaOriginal, ViewsPessoa.Privado.class);
        pessoa = Exemplo.desserializar(json);

        System.out.println(json);
        System.out.println(pessoa);
    }
}

class ViewsPessoa {

    public static class Publico{};

    public static class Privado extends Publico{};
}

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
class Pessoa {

    @JsonView(ViewsPessoa.Publico.class)
    String nome;

    @JsonView(ViewsPessoa.Privado.class)
    int idade;

    String sexo;

    @JsonView(ViewsPessoa.Privado.class)
    Endereco endereco;
}

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
class Endereco {

    String rua;
    String numero;
    String bairro;
}

// Saida:
// >
//    {
//      "nome" : "José",
//      "sexo" : "M"
//    }
// > Pessoa(nome=José, idade=0, sexo=M, endereco=null)
// >
//    {
//      "nome" : "José",
//      "idade" : 54,
//      "sexo" : "M",
//      "endereco" : {
//        "rua" : "Rua Um",
//        "numero" : null,
//        "bairro" : "Centro"
//      }
//    }
// > Pessoa(nome=José, idade=54, sexo=M, endereco=Endereco(rua=Rua Um, numero=null, bairro=Centro))