# Enum

[TOC]

## Introdução

Uma enumeração é um tipo de dado que representa um grupo de constantes. Sua sintaxe é muito semelhante à declaração de uma classe, exceto pelo uso da palavra-chave `enum` que antecede seu nome. De uma maneira simplificada, uma enumeração pode ser visto em Java como uma "classe" especial para tratar constantes.

<div class='importante'>

* As instâncias dos itens do `enum` são criadas e nomeadas junto com a declaração da classe, portanto são fixas e imutáveis;

* Os itens do `enum` devem ser declarados antes de qualquer campo, método ou construtor;

* Não é permitido criar novas instâncias de um `enum` com a palavra-chave `new`;

* Enums são singleton por padrão: temos uma única instância de cada constante por aplicação;

* O construtor sempre possui restrição de acesso *private*.

* Tem sua própria API de coleções que é *type safe*: `EnumMap` e `EnumSet`;

</div>

```java
package _enum.exemplo_enum;

enum Turno {

    MANHA, TARDE, NOITE;

    public static void main(String[] args) {

        /*
         * Enums limitam os valores que podem ser atribuídos a uma variável.
         * O "Turno." é opcional, já que se refere a própria classe.
         */
        Turno turno = Turno.MANHA;

        System.out.println(turno);
    }
}

// Saida:
// > MANHA
```

O tipo `enum` em Java nada mais é do que uma "açúcar sintático" (*syntax sugar*) de criação de uma subclasse que herda de `java.lang.Enum` com alguns membros estáticos. Se fizéssemos a engenharia reversa no `.class` da *enum* `Turno` nós teríamos algo como:

```java
final class Turno extends java.lang.Enum {

    static final Turno MANHA = new TipoDeDocumento("MANHA", 0);
    static final Turno CPF = new TipoDeDocumento("TARDE", 1);
    static final Turno CPF = new TipoDeDocumento("NOITE", 2);
}
```

Cada constante é construída em cima de dois valores: uma *string* contendo o nome da constante e um inteiro com valor incrementado (ordinal) que é único para a instância. Basicamente uma enum é convertida em uma lista de constantes de objetos Java.

## Herança

Ao declarar uma `enum` estamos implicitamente estendendo a classe `java.lang.Enum`. Isso cria algumas limitações, porque o Java não suporta herança múltipla, o que impede uma classe `enum` de estender outras classes. Porém, uma classe `enum` pode ter campos, assim como construtores e métodos.

```java
package _enum.exemplo_enum_heranca;

enum Turno {

    MANHA(1, "manhã"),
    TARDE(2, "tarde"),
    NOITE(3, "noite");

    private int id;
    private String descricao;


    /*
     * Construtor, sempre possui restrição private
     */
    Turno(int id, String descricao) {

        this.id = id;
        this.descricao = descricao;
    }

    String getDescricao() {

        return descricao;
    }

    /*
     * Modificar as propriedades de um enum deve ser EVITADA, vai contra a
     * sua característica imutável.
     */
    void setDescricao(String descricao) {

        this.descricao = descricao;
    }

    public static void main(String[] args) {

        System.out.println(Turno.MANHA);
        System.out.println(Turno.MANHA.id + "-" + Turno.MANHA.getDescricao());

        /*
         * Permitido mas deve ser evitado
         */
        Turno.MANHA.setDescricao("desconhecido");

        System.out.println(Turno.MANHA.id + "-" + Turno.MANHA.getDescricao());
    }
}

// Saida:
// > MANHA
// > 1-manhã
// > 1-desconhecido
```

Uma vez que a classe `Turno` herda de `java.lang.Enum`, ela herda alguns métodos:

| Método               | Retorno                          | Descrição                                                            |
| -------------------- | -------------------------------- | -------------------------------------------------------------------- |
| values()             | [ ]                              | Retorna um *array* contendo todos os itens da enum                   |
| toString(), name()   | *String*                         | Retorna uma *String* com o nome da instância (em maiúsculas).        |
| valueOf(String nome) | static \<T extends Enum\<T\>\> T | Retorna o objeto da classe enum cujo nome é a *string* do argumento. |
| ordinal()            | int                              | Retorna o número de ordem do objeto na enumeração.                   |

```java
package _enum.exemplo_enum_heranca_metodos;

enum Turno {

    MANHA(1, "manhã"),
    TARDE(2, "tarde"),
    NOITE(3, "noite");

    private int id;
    private String descricao;


    Turno(int id, String descricao) {

        this.id = id;
        this.descricao = descricao;
    }

    public static void main(String[] args) {

        for (Turno t : Turno.values()) {

            System.out.println(t.ordinal() + " " + t.name() + " "
                    + t.descricao);
        }

        Turno v = Turno.MANHA;
        System.out.println(v.descricao);

        /*
         * Mesma forma, mas passando como argumento
         */
        Turno s = Turno.valueOf("MANHA");
        System.out.println(s.descricao);
    }
}

// Saida:
// > 0 MANHA manhã
// > 1 TARDE tarde
// > 2 NOITE noite
// > manhã
// > manhã
```

Um Enum pode ser comparado com outro objeto através do método equals.

```java
package _enum.exemplo_enum_comparacao;

enum Turno {

    MANHA(1, "manhã"),
    TARDE(2, "tarde"),
    NOITE(3, "noite");

    private int id;
    private String descricao;


    Turno(int id, String descricao) {

        this.id = id;
        this.descricao = descricao;
    }

    public static void comparaEnum(Turno opcao){

        if (opcao.equals(Turno.MANHA)) {

            System.out.println("Turno matutino");

        } else if (opcao.equals(Turno.TARDE)) {

            System.out.println("Turno vespertino");

        } else if (opcao.equals(Turno.NOITE)){

            System.out.println("Turno noturno");
        }
    }

    public static void main(String[] args) {

        comparaEnum(Turno.TARDE);
    }
}

// Saida
// > Turno vespertino
```
