# Herança

[TOC]

## Introdução

A herança é um princípio da POO que permite a criação de novas classes a partir de outras previamente criadas. Essas novas classes são chamadas de subclasses, ou classes derivadas; e as classes já existentes, que deram origem às subclasses, são chamadas de superclasses, ou classes base. Deste modo é possível criar uma hierarquia dessas classes, tornando, assim, classes mais amplas e classes mais específicas. Uma subclasse herda métodos e campos de sua superclasse; apesar disso, pode escrevê-los novamente para uma forma mais específica de representar o comportamento do método herdado.

```java
package exemplo_heranca;

class Pessoa {

    String nome;
    int idade;

    Pessoa(String nome, int idade) {

        this.nome = nome;
        this.idade = idade;
    }

    double tirarCopias(int quantidade) {
        return 0.10 * (double) quantidade;
    }

    /*
     * A palavra especial "final" bloqueia sobrescrita do método
     */
    final String estudar(String disciplina) {
        return "Estudando " + disciplina;
    }
}

class Aluno extends Pessoa {

    String matricula;

    Aluno(String nome, int idade) {
        super(nome, idade);
    }

    /*
     * A notação @Override não é obrigatória, mas caso um método esteja anotado, ele
     * necessariamente precisa estar reescrevendo um método da classe mãe.
     */
    @Override
    double tirarCopias(int quantidade) {
        return 0.07 * (double) quantidade;
    }

    /*
     * Se o método abaixo for usado ocorrerá erro, pois o método não pode ser
     * sobrescrito.
     */

    // @Override
    // public final String estudar(String disciplina) {}

    // public String estudar(String disciplina) {}
}

public class Exemplo {

    public static void main(String[] args) {

        Aluno a = new Aluno("Jose Francisco", 32);
        System.out.println("Nome: " + a.nome);
        System.out.println("Idade: " + a.idade);
        System.out.println("Valor copias: R$" + a.tirarCopias(100));
        System.out.println("Estudando: " + a.estudar("Geografia"));
    }
}

// Saida:
// > Nome: Jose Francisco
// > Idade: 32
// > Valor copias: R$7.000000000000001
// > Estudando: Estudando Geografia
```

A palavra-chave `super` é usada para chamar um método ou campo da **superclasse imediata**. Não é possível acessar com o `super` uma classe em um segundo nível de hierarquia.

Usando o `super` como método, será chamado o construtor da superclasse. E o comando `super(...)` deve vir antes de qualquer comando dentro do construtor.

Através de herança é possível chamar um método mesmo que esteja em outros níveis na hierarquia de herança. O método chamado será o encontrado em menor nível em relação ao mesmos métodos de níveis superiores. Uma vez que esse método em menor nível na hierarquia, sobrescreveu os demais.

```java
package exemplo_heranca_super;

class ClasseAvo {

    ClasseAvo() { System.out.println("Avô"); }

    void diga() { System.out.println("Sou o avô"); }

    void repita() { System.out.println("Sou o avô!!!"); }
}

class ClassePai extends ClasseAvo {

    ClassePai() { System.out.println("Pai"); }

    void diga() { System.out.println("Sou o pai"); }
}

class ClasseFilho extends ClassePai {

    ClasseFilho() { System.out.println("Filho"); }

    ClasseFilho(String msg) { System.out.println(msg); }

    void diga() { System.out.println("Sou o filho"); }
}

class Exemplo extends ClasseFilho {

    /*
     * O super() no construtor não é necessário, mas se for usado deve vir
     * no ínicio do construtor. Não há como chamar dois super(). Se não for
     * especificado qual o construtor da superclasse, será chamado o construtor
     * padrão (sem parâmetros).
     */
    Exemplo() {

        super("Filho malcriado");
        // super(); // ERRO!
        System.out.println("Eu\n");
    }

    /*
     * O super está chamando o método diga() da superclasse
     */
    void diga() {

        System.out.println("Sou eu");
        super.diga();
    }

    public static void main(String[] args) {

        Exemplo t = new Exemplo();

        /*
         * Como o método foi sobrescrito, irá chamar o seu própio
         */
        t.diga();

        /*
         * Através de herança chama o método da ClasseAvo
         */
        t.repita();
    }
}

// Saida
// > Avô
// > Pai
// > Filho malcriado
// > Eu

// > Sou eu
// > Sou o filho
// > Sou o avô!!!
```

## Herança múltipla

Java não possui suporte para herança múltipla, mas isso pode ser contornado através do uso de interfaces.

<span class='mais' markdown='1'>[Interface](interface.md)<span>

```java
package exemplo_heranca_multipla;

interface Calcular {

    default double calcular() {

        return 10 * 5.5;
    }
}

interface Cantar {

    default void cantar() {

        System.out.println("La la la!");
    }
}

/*
 * Herança multipla por meio de interfaces
 */
class Pessoa implements Calcular, Cantar {
}

class Exemplo {

    public static void main(String[] args) {

        Pessoa pessoa = new Pessoa();
        pessoa.cantar();
        System.out.println(pessoa.calcular());
    }
}

// Saida:
// > La la la!
// > 55.0
```

## Classe abstrata

A palavra chave `abstract` impede que uma classe possa ser instanciada. Uma classe que herda uma classe abstrata é obrigada a implementar os métodos abstratos.

<span class='mais' markdown='1'>[Classes](classe.md)<span>

```java
package exemplo_abstrata;

abstract class Abstract {

    String funcaoNaoAbstrata() {
        return "método não abstrato";
    };

    abstract String funcaoAbstrata();
}

class Exemplo extends Abstract {

    /*
     * Obrigado a implementar os métodos abstratos da classe pai
     */
    String funcaoAbstrata() {
        return "método abstrato implementado";
    }

    public static void main(String[] args) {

        Exemplo t = new Exemplo();
        System.out.println(t.funcaoAbstrata());
        System.out.println(t.funcaoNaoAbstrata());

        /*
         * Não é possível instanciar uma classe abstrata
         */
        // Abstract a = new Abstract(); // ERRO!
    }
}

// Saida:
// > método abstrato implementado
// > método não abstrato
```

## Classe final

Uma classe `final`, é uma classe que não permite que seja criada subclasses dela. Ou seja, ela não pode ser herdada.

```java
package exemplo_final;

final class Exemplo {

    void funcaoNAbstrata() {

        System.out.println("Função não abstrata");
    }
}

/*
 * Não pode haver herança de uma classe declarada como "final"
 */
// class Teste extends Exemplo { } // ERRO!
```
