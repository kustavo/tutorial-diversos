# Método

[TOC]

## Introdução

Métodos são blocos de código que pertencem a uma classe e tem por finalidade realizar uma tarefa (linguagens c++, c#, java). Eles são análogos às funções das linguagens não orientadas a objeto.

## Argumentos

Em Java, todos os valores primitivos e objetos, são passados para o métodos como valor. Em Java não existem ponteiros, ou seja, não é possível acessarmos a memória do computador diretamente, isso é feito pela JVM. Portanto, **não existe passagem de parâmetro por referência em Java**, apenas por valor.

<div class='importante' markdown='1'>

Ao passar um objeto como argumento para um método, é possível alterar os campos associados ao objeto transmitido, mas se tentarmos reatribuir outro valor ao parâmetro, essa reatribuição será perdida quando o escopo do método for encerrado.

</div>

```java
package exemplo_metodo;

class Valor {

    private int valor;

    public Valor(int valor) {
        this.valor = valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public int getValor() {
        return valor;
    }
}

class Exemplo {

    static void metodo(Valor valorObjeto, int valorLocal) {

        valorObjeto.setValor(200);
        valorLocal = 20;

        System.out.println("\nDentro do metodo");
        System.out.println("Valor Objeto: " + valorObjeto.getValor()); // 200
        System.out.println("Valor Local: " + valorLocal);              // 20
    }

    static void metodoMudancaReferencia(Valor valorObjeto, int valorLocal) {

        /* Atribuição por valor */
        valorObjeto = new Valor(300);
        valorLocal = 30;

        System.out.println("\nDentro do metodo");
        System.out.println("Valor Objeto: " + valorObjeto.getValor()); // 300
        System.out.println("Valor Local: " + valorLocal);              // 30
    }

    public static void main(String[] args) {

        Valor valorObjeto = new Valor(100);
        int valorLocal = 10;

        System.out.println("\nAntes do metodo");
        System.out.println("Valor Objeto: " + valorObjeto.getValor()); // 100
        System.out.println("Valor Local: " + valorLocal);              // 10

        metodo(valorObjeto, valorLocal);

        System.out.println("\nDepois do metodo");
        System.out.println("Valor Objeto: " + valorObjeto.getValor()); // 200
        System.out.println("Valor Local: " + valorLocal);              // 10

        metodoMudancaReferencia(valorObjeto, valorLocal);

        System.out.println("\nDepois do metodo");
        System.out.println("Valor Objeto: " + valorObjeto.getValor()); // 200
        System.out.println("Valor Local: " + valorLocal);              // 10
    }
}

// Saida
// > Antes do metodo
// > Valor Objeto: 100
// > Valor Local: 10
//
// > Dentro do metodo
// > Valor Objeto: 200
// > Valor Local: 20
//
// > Depois do metodo
// > Valor Objeto: 200
// > Valor Local: 10
//
// > Dentro do metodo
// > Valor Objeto: 300
// > Valor Local: 30
//
// > Depois do metodo
// > Valor Objeto: 200
// > Valor Local: 10
```

O funcionamento da passagem de argumentos em Java, pode ser melhor entendido vendo as áreas da memória:

<div class='imagem' markdown='1'>

![memoria-stack-heap](./_metodo/memoria-stack-heap.png)

</div>

Na área **stack** ficam as variáveis locais dos métodos que pertencem a um dos 8 tipos primitivos do Java (byte, short, int, long, float, double, boolean e char) e também as referências para os objetos armazenados na área de **heap**. Na área de **heap** ficam os objetos.

O que aconteceu é que, ao passarmos uma variável (tipo primitivo) como argumento para os métodos em Java, esse método recebe uma cópia do valor da variável e, da mesma forma, se passarmos um objeto como argumento ele recebe uma cópia do valor da referência desse objeto.

Se alterarmos o valor da variável dentro do método, como é uma cópia, não afetará seu valor externamente. No caso de objetos, ele acessa o objeto na área de **heap** por meio da cópia da referência que recebeu como argumento, que aponta para o mesmo objeto instanciado fora do método, e por isso, uma alteração feita no campo de um objeto dentro do método alterará seu valor fora dele.

## Modificadores

Informações sobre os modificadores de métodos podem ser vistas no link abaixo:

<span class='mais' markdown='1'>[Modificadores](modificadores.md)</span>
