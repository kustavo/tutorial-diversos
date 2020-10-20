# Interface

[TOC]

## Introdução

Interface é um recurso da orientação a objeto utilizado em Java que define ações que devem ser obrigatoriamente executadas, mas que cada classe pode executar de forma diferente. As interfaces são padrões definidos através de contratos ou especificações. Um contrato define um determinado conjunto de métodos que serão implementados nas classes que assinarem esse contrato. Uma interface é 100% abstrata, ou seja, os seus métodos são definidos como `abstract`, e as variáveis por padrão são sempre constantes (`static final`).

Como a linguagem Java não tem herança múltipla, as interfaces ajudam nessa questão, pois uma classe pode herdada apenas uma classe, mas pode implementar inúmeras interfaces. As classes que forem implementar uma interface terão de adicionar todos os métodos da interface ou se transformar em uma classe abstrata.

<div class='importante' markdown='1'>

- Uma interface não é considerada uma classe e sim uma entidade.
- Diferente de classes abstratas, há como criar métodos `default` que possuem corpo.
- Seus métodos são implicitamente `public` e `abstract`.
- Métodos `static` devem ter corpo e não são abstratos, ou seja, não obriga a sua implementação.
- Não há como fazer uma instância de uma interface e nem como criar um construtor.
- Funcionam como um tipo de "contrato", onde são especificados os campos, métodos e funções que as classes que implementem essa interface são obrigadas a implementar.
- Já que java não suporta heranças múltiplas, as interfaces são usadas para implementá-las.

</div>

```java
package exemplo_interface;

interface Interface {

    /*
     * Os métodos aceitam somente o modificador de acesso public ou default.
     * Entretanto, em uma interface, todos os métodos são public, e as variáveis são
     * public e static, mesmo quando não declarados.
     */

    public abstract boolean feliz(); // public abstract são desnecessários

    String hello = "Variável estática";

    String dizer(String msg);

    /*
     * A partir do Java 8, é possível criar métodos com corpo padrão, caso não seja
     * implementado.
     */

    default void cantar(String msg) { // public implícito

        System.out.println(msg);
    }

    /*
     * Métodos estáticos sempre possuem corpos e NÃO são abstratos, ou seja, não
     * obriga a sua implatação.
     */
    static void estatico(String msg) {

        System.out.println(msg);
    }
}

class Exemplo implements Interface {

    /*
     * Todos métodos implementados devem usar o modificador de acesso public
     */

    public boolean feliz() {

        return true;
    }

    public String dizer(String msg) {

        return msg;
    }

    /*
     * Não é obrigatório declarar métodos default, pois possuem um corpo padrão.
     */
    // public void cantar(String msg) {}

    public static void main(String[] args) {

        /*
         * As variáveis são sempre estáticas
         */
        System.out.println(Interface.hello);

        Interface.estatico("Método estático");

        /*
         * Depois que uma classe Java implementa uma interface Java, é possível usar uma
         * instância dessa classe como uma instância dessa interface.
         */
        Interface minhaInterface = new Exemplo();
        String dito = minhaInterface.dizer("Implementado");
        System.out.println(dito);

    }
}

// Saida:
// > Variável estática
// > Método estático
// > Implementado
```

## Interfaces múltiplas

```java
package exemplo_interface_multipla;

interface InterfaceA {

    void pensar();

    void dizer(String msg);

    default void cantar() {

        System.out.println("Figaro!");
    }
}

interface InterfaceB {

    void pensar();

    void dizer();

    /*
     * Não pode haver dois métodos default com mesmo nome sendo implementados pela
     * classe Exemplo
     */

    // default void cantar() { System.out.println("La la!"); }
}

class Exemplo implements InterfaceA, InterfaceB {

    public void pensar() {

        System.out.println("...");
    }

    public void dizer() {

        System.out.println("Olá");
    }

    public void dizer(String msg) {

        System.out.println(msg);
    }

    public static void main(String[] args) {

        Exemplo minhaInterface = new Exemplo();
        minhaInterface.pensar();
        minhaInterface.dizer();
        minhaInterface.dizer("Oi");

    }
}

// Saida:
// > ...
// > Olá
// > Oi
```

## Herança em interfaces

```java
package exemplo_interface_heranca;

interface InterfaceA {

    void pensar();
}

interface InterfaceB extends InterfaceA {

    void dizer();
}

class Exemplo implements InterfaceB {

    public void pensar() {

        System.out.println("...");
    }

    public void dizer() {

        System.out.println("Olá");
    }

    public static void main(String[] args) {

        Exemplo minhaInterface = new Exemplo();
        minhaInterface.pensar();
        minhaInterface.dizer();
    }
}

// Saida:
// > ...
// > Olá
```
