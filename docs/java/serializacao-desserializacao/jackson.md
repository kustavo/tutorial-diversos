# Jackson Json

[TOC]

## Introdução

Jackson é um processador JSON de alto desempenho para Java. Seus desenvolvedores exaltam a combinação de campos rápidos, corretos, leves e ergonômicos da biblioteca.

Jackson fornece muitas maneiras de trabalhar, incluindo POJO simples convertido para/de JSON para casos simples. Jackson também fornece um conjunto de anotações para mapeamento.

## Mapeamento

| Estrutura                         | Descrição                                          |
| --------------------------------- | -------------------------------------------------- |
| `ObjectMapper`                    | Classe responsável por fazer todo o *data-binding* |
| `ObjectMapper:writeValueAsString` | Escreve json em string                             |
| `ObjectMapper:writeValue`         | Escreve json em arquivo                            |
| `ObjectMapper:readValue`          | Converte json para o objeto                        |

```java
package exemplo_jackson;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.*;
import java.io.File;
import java.io.IOException;

public class Exemplo {

    static String path = System.getProperty("user.dir")
            + "/exemplo_jackson/";

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
```

<div class='importante' markdown='1'>

Dar preferência para `ObjectReader/ObjectWriter` ao invés de `ObjectMapper`. O `ObjectReader` e o `ObjectWriter` são mais seguros de usar - eles são completamente imutáveis e livremente compartilháveis entre threads - eles também podem ser um pouco mais eficientes, já que podem evitar algumas das pesquisas que o `ObjectMapper` tem que fazer.

```java
ObjectMapper objectMapper = new ObjectMapper();
ObjectReader reader = objectMapper.readerFor(Map.class);
```

</div>

## Anotações

| Anotação                                     | Descrição                                                     |
| -------------------------------------------- | ------------------------------------------------------------- |
| `@JsonProperty`                              | Renomeia um campo                                             |
| `@JsonInclude(JsonInclude.Include.NON_NULL)` | Ignora todos campos nulos da classe ou de um campo específico |
| `@JsonIgnoreProperties({"A", "B"})`          | Ignora os campos informados (notação de classe)               |
| `@JsonIgnore`                                | Ignora o campo informado (notação de campo)                   |

```java
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
@JsonIgnoreProperties({"idade", "sexo"})

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
//    {
//      "nome" : "José",
//      "endereco_residencial" : {
//        "rua" : "Rua Um"
//      }
//    }
// > Pessoa(nome=José, idade=0, sexo=null , endereco=Endereco(rua=Rua Um, numero=null, bairro=null))
```

| Anotação           | Descrição                                                                                                  |
| ------------------ | ---------------------------------------------------------------------------------------------------------- |
| `@JsonView(Class)` | Indica quais campo serão considerados na serialização e desserialização baseado na classe *View* informada |

`@JsonView` é usado para indicar qual o campo será incluído durante a serialização e desserialização e é muito útil quando é necessário incluir diferentes campos de objeto durante a serialização para diferentes casos de uso.

No exemplo abaixo, a *view* `Privado` é uma subclasse da *view* `Publico` portanto herda todos os campos disponíveis na *view* `Publico`. Caso algum campo não for incluso em alguma *view*, ele sempre será considerado em qualquer *view*.

```java
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
```

## Links

- Anotações
  - <https://github.com/FasterXML/jackson-annotations/wiki/Jackson-Annotations>
  - <https://www.baeldung.com/jackson-annotations>
