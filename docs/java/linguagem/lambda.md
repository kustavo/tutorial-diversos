# Lambda

[TOC]

## Introdução

Dentre algumas novidades do Java 8 está a expressão *lambda*, funcionalidade esta comum em muitas linguagens funcionais (LISP, Ruby, JavaScript, etc). A expressão *lambda* possui o formato `(argumento) -> (corpo)` e permite passar funções e comportamentos como argumentos em uma chamada de método. Isso foi algo novo em Java, que até então permitia apenas passagem de objetos como argumentos.

Quando o corpo da expressão *lambda* possui somente uma instrução, não é necessário utilizar a palavra chave `return`. Uma expressão *lambda* pode ocorrer de várias formas:

```java
    () -> 20                // Não recebe nada e sempre retorna "20"
    x -> x * x              // Recebe algo e retorna seu quadrado
    (x,y) -> x + y          // Recebe dois valores e retorna a soma
    (int x, int y) -> x + y // Recebe dois valores inteiros e retorna a soma

    (int x, int y) -> {     // Recebe dois valores interos, imprime e retorna a soma
        System.out.println(x+y);
        return x+y;
    }
```

Para entender melhor a aplicabilidade de *lambda*, abaixo temos um exemplo sem o uso da expressão *lambda*.

```java
package _lambda.exemplo_sem_lambda;

public class Exemplo {

    public static int calcular(OperacaoMatematica om, int num1, int num2) {

        return om.executar(num1, num2);
    }

    public static void main(String[] args) {

        System.out.println("20 + 15 = " + calcular(new Soma(), 20, 15));
        System.out.println("20 - 15 = " + calcular(new Subtracao(), 20, 15));
    }
}

class Soma implements OperacaoMatematica {

    @Override
    public int executar(int a, int b) {
        return a + b;
    }
}

class Subtracao implements OperacaoMatematica {

    @Override
    public int executar(int a, int b) {
        return a - b;
    }
}

interface OperacaoMatematica {

    public int executar(int a, int b);
}

// Saida:
// > 20 + 15 = 35
// > 20 + 15 = 5
```

Esse exemplo pode ser melhorado utilizando classes anônimas, eliminando as duas classes `Soma` e `Subtracao`.

```java
package _lambda.exemplo_sem_lambda_otimizado;

public class Exemplo {

    public static int calcular(OperacaoMatematica om, int num1, int num2) {

        return om.executar(num1, num2);
    }

    public static void main(String[] args) {

        System.out.println("20 + 15 = " + calcular(new OperacaoMatematica() {
            public int executar(int a, int b) {
                return a + b;
            }
        },  20, 15));

        System.out.println("20 + 15 = " + calcular(new OperacaoMatematica() {
            public int executar(int a, int b) {
                return a - b;
            }
        }, 20, 15));
    }
}

interface OperacaoMatematica {

    public int executar(int a, int b);
}
```

Entretanto o código ficou muito mais confuso e complexo. Com o recurso de expressão *lambda*, o código fica mais limpo, simples e compacto. A notação `@FunctionalInterface` é opcional, mas é uma boa prática, já que deixa explícito para o compilador que a interface deve implementar somente um método abstrato. Métodos padrão e métodos estáticos ainda poderão existir.

```java
package _lambda.exemplo1_lambda;

public class Exemplo {

    static int calcular(OperacaoMatematica om, int num1, int num2) {
        return om.executar(num1, num2);
    }

    public static void main(String[] args) {
        System.out.println("20 + 15 = " + calcular((a,b) -> a + b, 20, 15));
        System.out.println("20 - 15 = " + calcular((a,b) -> a - b, 20, 15));
    }
}

@FunctionalInterface
interface OperacaoMatematica {

    int executar(int a, int b);

    /*
     * A expressão lambda funciona se a interface possui apenas um método abstrato.
     */
    // int imprimir (int a, int b); // ERRO!
}

// Saida
// > 20 + 15 = 35
// > 20 - 15 = 5
```

Ainda podemos compactar mais o código eliminando a interface funcional `OperacaoMatematica`. Para isso, o Java 8 disponibiliza o pacote `java.util.function` que fornece várias interfaces funcionais para usar com expressões `lambda` e referência de métodos. No exemplo temos o uso da interface funcional `BiFunction`.

```java
package _lambda.exemplo_lambda_interface_funcional;

import java.util.function.BiFunction;

public class Exemplo {

    static int calcular(BiFunction<Integer, Integer, Integer> bf, int num1, int num2) {
        return bf.apply(num1, num2);
    }

    public static void main(String[] args) {
        System.out.println("20 + 15 = " + calcular((a, b) -> a + b, 20, 15));
        System.out.println("20 - 15 = " + calcular((a, b) -> a - b, 20, 15));
    }
}

// Saida
// > 20 + 15 = 35
// > 20 - 15 = 5
```

## Interfaces funcionais

Uma interface funcional (em inglês, *functional interface*) é uma interface que possui apenas um único método abstrato, ao qual os parâmetros e tipos de retorno da expressão lambda são combinados ou adaptados. Alguns exemplos de interfaces funcionais:

| Interface  | Método                         | Descrição                                |
| ---------- | ------------------------------ | ---------------------------------------- |
| Comparator | `int compare​(T o1, T o2) { }` | Comparação entre dois elementos          |
| Runnable   | `void run() { }`               | Usado para criar um *thread*             |
| Callable   | `V call() throws Exception`    | Computa o resultado ou lança uma exceção |

Introduzidas no Java 8:

| Interface     | Método                                   | Descrição                                      |
| ------------- | ---------------------------------------- | ---------------------------------------------- |
| Predicate     | `boolean test​(T t)`                     | Operação que retorna um booleano               |
| Consumer      | `void accept​(T t)`                      | Operação que não retorna valor (void)          |
| Function      | `R apply​(T t)`                          | Operação que retorna algum valor               |
| Supplier      | `T get()`                                | Operação sem parâmetro que retorna algum valor |
| UnaryOperator | `static <T> UnaryOperator<T>` identity() | Operação que retorna mesmo valor do parâmetro  |

Existem interfaces derivadas usadas para outros números de parâmetros como: `BiConsumer`, `BiFunction`, `BinaryOperator`, `BiPredicate`, etc. E interfaces derivadas usadas para retornar ou receber valores de tipos específicos como: `IntPredicate`, `LongConsumer`, `IntFunction`, `IntToDoubleFunction`, `BooleanSupplier`, `DoubleUnaryOperator`, etc.

Abaixo segue um exemplo usando interface `Comparator`. O método `Collections.sort` espera receber um `List` e uma implementação de `Comparator`.

```java
package _lambda.exemplo_lambda_interfaces_funcionais;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class Exemplo {

    public static void main(String[] args) {

        ArrayList<String> lista = new ArrayList<>();

        lista.addAll(Arrays.asList("HAA!", "HAAAAA!", "HA!"));

        /*
         * Sem o uso de lambda
         */
        Collections.sort(lista, new Comparator<String>(){
            public int compare(String valor1, String valor2){
                return valor1.length() - valor2.length();
            }
        });

        /*
         * Com o uso de lambda
         */
        Collections.sort(lista, (v1, v2) -> v1.length() - v2.length());

        System.out.println(lista);
    }
}

// Saida:
// > [HA!, HAA!, HAAAAA!]
```

Exemplo de criação de uma nova interface funcional.

```java
package _lambda.exemplo2_lambda;

public class Exemplo {

    public static void main(String[] args) {

        /*
         * Criação de uma classe anônima se lambda
         */
        Validador semLambda = new Validador() {
            public boolean isPositivo(int valor) {
                return valor > 0;
            }
        };

        /*
         * Criação de uma classe anônima com lambda
         */
        Validador comLambda = (v) -> v > 0;

        System.out.println(semLambda.isPositivo(-10));
        System.out.println(comLambda.isPositivo(-10));
    }
}

interface Validador {
    boolean isPositivo(int valor);
}

// Saida:
// > false
// > false
```

## Referências de métodos

Uma referência de método (em inglês,* method reference*) é a sintaxe abreviada de uma expressão *lambda* que executa apenas **um** método.

Antes do Java 8, se usássemos apenas um método de um objeto em outro método, tínhamos que passar o objeto completo como argumento. Com expressões *lambdas* podemos usar métodos como se fossem objetos ou valores primitivos como por exemplo: `objeto::metodoNome`

Existe 4 tipos de referências de métodos:

- Referência a um método estático.
- Referência a um método de uma instância.
- Referência a um método de uma instância de classe anônima.
- Referência a um construtor.

### Referência a um método estático {ignore="true"}

Referência a um método estático definido na classe.

Sintaxe: `ClasseNome::metodoEstaticoNome`
Lambda: `(args) -> ClasseNome.metodoEstaticoNome(args)`

```java
package _lambda.exemplo_referencia_metodo_estatico;

public class Exemplo {

    static void dizerAlgo(){
        System.out.println("Hello!");
    }

    public static void main(String[] args) {

        // Referências de método
        Falante falanteR = Exemplo::dizerAlgo;

        // Lambda
        Falante falanteL = () -> Exemplo.dizerAlgo();

        falanteR.dizer();
        falanteL.dizer();
    }
}

interface Falante {
    void dizer();
}

// Saida:
// > Hello!
// > Hello!
```

### Referente a um método de uma instância

Referência a um método de um objeto instância de uma classe.

Sintaxe: `objeto::instanciaMetodoNome`
Lambda: `(args) -> objeto.instanciaMetodoNome(args)`

```java
package _lambda.exemplo_referencia_metodo_instancia;

public class Exemplo {

    void dizerAlgo() {
        System.out.println("Hello!");
    }

    public static void main(String[] args) {

        Exemplo objeto = new Exemplo();

        // Referências de método
        Falante falanteR = objeto::dizerAlgo;

        // Lambda
        Falante falanteL = () -> objeto.dizerAlgo();

        falanteR.dizer();
        falanteL.dizer();
    }
}

interface Falante {
    void dizer();
}

// Saida:
// > Hello!
// > Hello!
```

### Referência a um método de uma instância de classe anônima

Referência a um método de um objeto instância de uma classe anônima.

Sintaxe: `ClasseNome::instanciaMetodoNome`
Lambda: `(arg0,rest) -> arg0.instanciaMetodoNome(rest)` onde `arg0` é o tipo de `ClasseNome`.

```java
package _lambda.exemplo_referencia_metodo_instancia_anonima;

public class Exemplo {

    void dizerAlgo() {
        System.out.println("Hello!");
    }

    public static void main(String[] args) {

        // Referências de método
        Falante falanteR = new Exemplo()::dizerAlgo;

        // Lambda
        Falante falanteL = () -> new Exemplo().dizerAlgo();

        falanteR.dizer();
        falanteL.dizer();
    }
}

interface Falante {
    void dizer();
}

// Saida:
// > Hello!
// > Hello!
```

### Referência a um construtor

Referência a um construtor.

Sintaxe: `ClasseNome::new`
Lambda: `(args) -> new ClasseNome(args)`

```java
package _lambda.exemplo_referencia_metodo_instancia_anonima;

public class Exemplo {

    public static void main(String[] args) {

        // Referências de método
        Falante falanteR = Mensageiro::new;

        // Lambda
        Falante falanteL = (s) -> new Mensageiro(s);

        falanteR.dizer("Hello!");
        falanteL.dizer("Hello!");
    }
}

class Mensageiro {

    Mensageiro(String s) {
        System.out.println(s);
    }
}

interface Falante {
    void dizer(String s);
}

// Saida:
// > Hello!
// > Hello!
```
