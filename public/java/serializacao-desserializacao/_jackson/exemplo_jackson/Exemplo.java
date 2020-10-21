package exemplo_jackson;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.*;
import java.io.File;
import java.io.IOException;

public class Exemplo {

    static String path = System.getProperty("user.dir") + "/exemplo_jackson/";

    static void serializar(Pessoa pessoa, String arquivo) {

        final ObjectMapper mapper = new ObjectMapper();

        /**
         * Deixar json formatado com indentações
         */
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        try {

            /**
             * Json em String
             */
            final String json = mapper.writeValueAsString(pessoa);
            System.out.println(json);

            /**
             * Json escrito em arquivo
             */
            final File file = new File(path + arquivo);
            mapper.writeValue(file, pessoa);

        } catch (final IOException e) {

            e.printStackTrace();
        }
    }

    static Pessoa desserializar(String arquivo) {

        Pessoa pessoa = new Pessoa();

        final ObjectMapper mapper = new ObjectMapper();

        try {

            pessoa = mapper.readValue(new File(path + arquivo), Pessoa.class);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return pessoa;
    }

    public static void main(String[] args) {

        Endereco endereco = new Endereco("Rua Um", "101");
        Pessoa pessoa = new Pessoa("José", 54, endereco);

        Exemplo.serializar(pessoa, "json.txt");

        pessoa = Exemplo.desserializar("json.txt");
        System.out.println(pessoa);
    }
}

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
class Pessoa {

    String nome;
    int idade;
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
}

// Saida
// >
//    {
//      "nome" : "José",
//      "idade" : 54,
//      "endereco" : {
//        "rua" : "Rua Um",
//        "numero" : "101"
//      }
//    }
// > Pessoa(nome=José, idade=54, endereco=Endereco(rua=Rua Um, numero=101))