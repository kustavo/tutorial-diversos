# Optional

[TOC]

## Introdução

 `Optional` é uma classe presente a partir do Java 8 usada para lidar com `NullPointerException`. A principal proposta de `Optional` é encapsular o retorno de métodos e informar se um valor do tipo `<T>` está presente ou ausente. Podemos pensar em um `Optional` como uma classe que pode ou não conter um valor não nulo.

Evitar um `NullPointerException` pode ser muito verboso, em objetos encadeados. Por exemplo, em `String pais = endereco.getBairro().getCidade().getEstado().getPais().getNome()`, teríamos quer verificar se cada método aninhado retorna um valor diferente de `null` antes de chamar o próximo método.

```java
package _optional.exemplo_null_pointer_exception;

import java.util.ArrayList;

class Exemplo {

    public static void main(String[] args) {

        ArrayList<Cidade> cidades = new ArrayList<>();

        Pais p1 = new Pais("Brasil");
        Estado e1 = new Estado(p1);
        Cidade c1 = new Cidade(e1);

        Estado e2 =  new Estado();
        Cidade c2 = new Cidade(e2);

        cidades.add(c1);
        cidades.add(c2);

        for (Cidade c : cidades) {

            if (c.getEstado() != null) {

                Estado estado = c.getEstado();

                /*
                 * A segunda cidade não tem o país, portanto irá retornar
                 * NullPointerException. Portanto a solução é verificar todos objetos
                 * aninhados até o país.
                 */
                // System.out.println(estado.getPais().getNome()); // ERRO!

                if (estado.getPais() != null) {

                    Pais pais = estado.getPais();

                    System.out.println(pais.getNome());

                } else {

                    System.out.println("N/A");
                }
            }
        }
    }
}

class Cidade {

    // ... outros campos

    Estado estado;

    Cidade(Estado estado) { this.estado = estado; }

    Estado getEstado() { return this.estado; }
}

class Estado {

    // ... outros campos

    Pais pais;

    Estado() {}
    Estado(Pais pais) { this.pais = pais; }

    Pais getPais() { return this.pais; }
}

class Pais {

    String nome;

    Pais(String nome) { this.nome = nome; }

    String getNome() { return this.nome; }
}

// Saida:
// > Brasil
// > N/A
```

Com `Optional`, poderíamos ter algo como o código abaixo. Entretanto, mesmo sendo uma boa prática, esta não é o forma recomendada, já que não reduzimos a verbosidade.

```java
for (Cidade c : cidades) {

    if (c.getEstado() != null) {

        Estado estado = c.getEstado();

        /*
         * ofNullable(): retorna o valor se existir, senão retorna um empty Optional
         */
        Optional<Pais> pais = Optional.ofNullable(estado.getPais());

        /*
         * isPresent(): verifica se existe algum valor
         */
        if (pais.isPresent()) {

            /*
             * get(): retorna o valor contido no objeto Optional
             */
            System.out.println(pais.get().getNome());

        } else {

            System.out.println("N/A");
        }
    }
}
```

Com expressão *lambda* e com o método `ifPresent` demos reduzir a verbosidade.

```java
for (Cidade c : cidades) {

    if (c.getEstado() != null) {

        Estado estado = c.getEstado();

        Optional<Pais> pais = Optional.ofNullable(estado.getPais());

        /*
         * ifPresent(): se existe algum valor executa a ação com o dado valor
         */
        pais.ifPresent(p -> System.out.println(p.getNome())).orElse("N/A"));
    }
}
```

São os métodos das classes que devem saber os valores retornados e se estes podem ser nulos. Portanto, podemos melhorar o código, usando `Optional` diretamente nos métodos das classes. Assim, podemos usar expressão *lambda* e os métodos `map` e `flatMap`. Agora todos os objetos aninhados estão protegidos contra um `NullPointerException`.

```java
package _optional.exemplo_optional;

import java.util.ArrayList;
import java.util.Optional;

class Exemplo {

    public static void main(String[] args) {

        ArrayList<Cidade> cidades = new ArrayList<>();

        Pais p1 = new Pais("Brasil");
        Estado e1 = new Estado(p1);
        Cidade c1 = new Cidade(e1);

        Estado e2 = new Estado();
        Cidade c2 = new Cidade(e2);

        cidades.add(c1);
        cidades.add(c2);

        for (Cidade c : cidades) {

            /*
             * map(): aplica o argumento de função ao valor e, em seguida, retorna o
             *      resultado agrupado em um Optional, como ex:
             *      [1,2],[1,3] => [[1,2],[1,3]]
             * flatMap(): aceita um argumento de função que é aplicado a um valor
             *      Optional e retorna o resultado diretamente. Ou seja, "achata" objetos
             *       aninhados como: Optional<Optional<T>> transformado em apenas
             *       Optional<T>, como ex:
             *      [1,2], [1,3] => [1,2,1,3]
             * orElse(): retorna o valor presente se existir, senão retorna o argumento
             */
            System.out.println(c.getEstado().flatMap(Estado::getPais).map(Pais::getNome)
                    .orElse("N/A"));
        }
    }
}

class Cidade {

    // ... outros campos

    Estado estado;

    Cidade(Estado estado) { this.estado = estado; }

    Optional<Estado> getEstado() { return Optional.ofNullable(this.estado); }
}

class Estado {

    // ... outros campos

    Pais pais;

    Estado() { }
    Estado(Pais pais) { this.pais = pais; }

    Optional<Pais> getPais() { return Optional.ofNullable(this.pais); }
}

class Pais {

    String nome;

    Pais(String nome) { this.nome = nome; }

    String getNome() { return this.nome; }
}

// Saida:
// > Brasil
// > N/A
```

Algumas outras funções de `Optional`:

```java
package _optional.exemplo_metodos;

import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Stream;

class Exemplo {

    public static void main(String[] args) {

        ArrayList<Carro> carros = new ArrayList<>();

        carros.add(new Carro("Uno", 1000.0));
        carros.add(new Carro("Gol", 2000.0));
        carros.add(new Carro("Celta", 1500.0));
        carros.add(new Carro("HB20"));

        for (Carro carro : carros) {

            /*
             * ifPresent(): se um valor estiver presente, executa a ação no valor,
             * caso contrário, não faz nada.
             *
             * return: 1000.0 2000.0 1500.0
             */
            carro.getSeguro().ifPresent(s -> System.out.print(s.getValor() + " "));

            /*
             * or(): Se um valor estiver presente, retorna o valor, caso contrário,
             * retorna o valor Optional
             *
             * return: 1000.0 2000.0 1500.0 0.0
             */
            System.out.print(carro.getSeguro()
                    .or(() -> Optional.ofNullable(new Seguro(0.0)))
                    .map(Seguro::getValor).get());

            /*
             * orElse(): se um valor estiver presente, retorna o valor, caso contrário,
             * retorna o valor definido no parâmetro
             *
             * return: 1000.0 2000.0 1500.0 0.0
             */
            System.out.print(carro.getSeguro().map(Seguro::getValor)
                    .orElse(0.0) + " ");

            /*
             * ifPresentOrElse(): se um valor estiver presente, executa uma determinada
             * ação, caso contrário, executa uma outra ação
             *
             * return: 500.0 1000.0 750.0 0.0
             */
            carro.getSeguro().map(Seguro::getValor)
                    .ifPresentOrElse(s -> System.out.print(s * 0.5 + " "),
                            () -> System.out.print(0.0 + " "));

            /*
             * orElseThrow(): se um valor estiver presente, retorna o valor, caso
             * contrário, lança a exceção informada
             */
            System.out.print(carro.getSeguro().map(Seguro::getValor)
                    .orElseThrow(IllegalArgumentException::new));

            /*
             * filter(): retorna o valor se está presente satisfaz a condição, caso
             * contrário retorna um Optional vazio.
             *
             * return: 2000.0 1500.0
             */
            carro.getSeguro().filter(s -> s.getValor() > 1000.0)
                    .ifPresent(s -> System.out.print(s.getValor() + " "));

            /*
             * stream(): Se um valor estiver presente, retorna uma Stream contendo apenas
             * esse valor, caso contrário, retorna uma Stream vazia.
             *
             * return: 1000.0 2000.0 1500.0
             */
            Stream<Double> stream = carro.getSeguro().map(Seguro::getValor).stream();
            stream.forEach(System.out::println);
        }
    }
}

class Carro {

    String modelo;
    Seguro seguro;

    Carro(String modelo) {
        this.modelo = modelo;
    }

    Carro(String modelo, Double valorSeguro) {
        this.modelo = modelo;
        seguro = new Seguro(valorSeguro);
    }

    Optional<Seguro> getSeguro() {
        return Optional.ofNullable(this.seguro);
    }
}

class Seguro {

    Double valor;

    Seguro(Double valor) {
        this.valor = valor;
    }

    Double getValor() {
        return this.valor;
    }
}
```

Para valores primitivos não utilize a forma genérica, escolha OptionalInt, OptionalLong e OptionalDouble

```java

// Evite
Optional<Integer> preco = Optional.of(50);


// Prefira
OptionalInt preco = OptionalInt.of(50);
OptionalLong preco = OptionalLong.of(50L);
OptionalDouble preco = OptionalDouble.of(50.43d);
```

## Links

- [Boas práticas usando Optional](https://strn.com.br/artigos/2018/12/04/26-raz%C3%B5es-pelas-quais-o-uso-correto-do-optional-n%C3%A3o-%C3%A9-opcional/)
