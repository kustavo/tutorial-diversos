# Generics

[TOC]

## Introdução

Programação genérica, ou *generics* foram projetados para estender o sistema de tipos do Java para permitir que um tipo ou método possa ser usado em objetos de tipos diferentes enquanto fornece segurança de tipo (*type safety*) em tempo de compilação". Os tipos genéricos são delimitados pelos caracteres `<>` (operador diamante).

<div class='importante' markdown='1'>

*Generics* não podem ser aplicadas sobre tipos primitivos (`int`, `long`, `char`, `boolean`).

</div>

Java usa uma pequena convenção de nomenclatura para as letras de identificação de *generics* (que são vastamente utilizadas no Framework de coleções Java), sendo:

- `<E>` - Elemento
- `<K>` - Chave
- `<N>` - Número
- `<T>` - Tipo
- `<V>` - Valor

## Motivação

Ao recuperar valores de uma lista, sempre era necessário realizar o `cast` para um determinado tipo. Isso devido ao fato que a interface `List` recebia um `Object` como parâmetro em seu método `add`. Como em Java todos os tipos extendem `Object`, qualquer tipo poderia ser inserido em uma lista. Não é possível ter segurança de qual tipo será retornado pela lista em tempo de compilação. Erros poderão ser percebidos somente em tempo de execução.

```java
package exemplo_generics_motivacao.exemplo1;

import java.util.ArrayList;
import java.util.List;

public class Exemplo {

    static void semGenerics() {

        List v = new ArrayList();

        v.add("String");

        /*
         * Erro em tempo de execução
         */
        Integer i = (Integer)v.get(0); // ERRO!
    }

    static void comGenerics() {

        List<String> v = new ArrayList<String>();

        v.add("String");

        /*
         * Erro em tempo de compilação
         */
        //Integer i = v.get(0); // ERRO!

    }

    public static void main(String[] args) {

        semGenerics();
        comGenerics();
    }
}
```

Com o uso de *generics* não é mais necessário converter o emento da lista em um tipo específico. Porque `v.get(0)` é definido como String pelo código gerado pelo compilador. Dessa forma incompatibilidades de tipos serão detectadas em tempo de compilação.

As listas com *generics* (`List<>`) ao contrário dos *arrays* são invariantes e só podem ser atribuídas a variáveis com o mesmo tipo genérico. Portanto são mais seguras, pois o erro é detectado em tempo de compilação.

Outro exemplo, onde ocorreria uma exceção em tempo de execução, mesmo sem uma conversão explícita.

```java
package _generics.exemplo_generics_motivacao.exemplo2;

import java.util.ArrayList;
import java.util.List;

class Animal { }

class Cao extends Animal { }

class Gato extends Animal { }

class Exemplo {

    static void semGenerics() {

        Gato[] gatos = new Gato[2];
        Animal[] animais = gatos;

        /*
         * Compila pois Cao é um Animal, mas ocorrerá erro em tempo de execução
         */
        // animais[0] = new Cao(); // ERRO!
    }

    static void comGenerics() {

        List<Gato> gatosList = new ArrayList<>();

        /*
         * Erro em tempo de compilação
         */
        // List<Animal> animaisList = gatosList; // ERRO!
    }

    public static void main(String[] args) {

        semGenerics();
        comGenerics();
    }
}
```

## Generics em classes e interfaces

Uma classe ou interface é considerada genérica, quando possui ao lado de seu nome um identificador como: `<T>`.

No exemplo abaixo, a letra `T` representa um tipo que será inserido ao instanciar a classe. A letra `T` é apenas uma convenção para `type`, qualquer identificador pode ser utilizado.

```java
package exemplo_generics_classe;

import java.util.Arrays;

class Exemplo<T> {

    private Object[] elements = new Object[0];

    public T get(int index) {

        return (T) elements[index];
    }

    public void add(T element) {

        /*
         * Copia o array para o novo tamanho, truncando ou preenchendo com zeros
         */
        elements = Arrays.copyOf(elements, elements.length + 1);

        elements[elements.length - 1] = element;
    }

    public static void main(String[] args) {

        /*
         * Não é necessário repetir os tipos no new, basta usar <>
         */
        Exemplo<String> list = new Exemplo<>();

        /*
         * Espera-se uma string
         */
        // list.add(1); // ERRO!
        list.add("2");

        /*
         * Não há necessidade de cast, pois o compilador sabe que é uma String
         */
        String result = list.get(0);
        System.out.println(result);
    }
}

// Saida
// > 2
```

No exemplo abaixo, as letras `T` e `V` representa um tipo de chave e um tipo de valor que que serão passados ao implementar a interface.

```java
package exemplo_generics_interface;

interface Generics<T, V> {

    T metodoA(V valor);
}

class Exemplo implements Generics<String, Integer> {

    /*
     * Implementando o método conforme informado no Generics
     */
    public String metodoA(Integer valor) {

        return Integer.toString(valor);
    }

    public static void main(String[] args) {

        Exemplo t = new Exemplo();

        System.out.println(t.metodoA(1));
    }
}

// Saida
// > 1
```

<div class='importante' markdown='1'>

É possível definir uma classe ou interface com tipos genéricos em parâmetros de tipo.

```java
Classe<Integer, Objeto<String>> c = new Classe<>(10, new Objeto<>());
```

No exemplo temos a classe `Objeto` com tipo genérico definida como parâmetro de tipo genérico da classe `Classe`.

</div>

## Generics em métodos

Não são somente as classes e interfaces que possuem a flexibilidade de *generics*, também podemos criar métodos genéricos.

Assim como nas classes genéricas, é necessários a declaração dos tipos que poderão ser usados tanto nos parâmetros como nas variáveis internas. Os tipos genéricos ficam entre os modificadores de acesso do método e seu tipo de retorno.

<div class='importante' markdown='1'>

Se a classe também é genérica, e possui um identificador de tipo igual ao do método, o do método vai sobrescrever o da classe.

</div>

```java
package exemplo_generics_metodo;

class GenericsA {

    @Override
    public String toString() {
        return this.getClass().toString();
    }
}
class  GenericsB extends GenericsA { }

class Exemplo  {

    static <T, K> T converte(K par) {

        return (T) par;
    }

    static <T> void print(T par) {

        System.out.println(par.toString());
    }

    public static void main(String[] args) {

        GenericsA g = converte(new GenericsB());
        print(g);
    }
}

// Saida
// > cclass exemplo_generics_metodo.GenericsB
```

Conforme o exemplo, no método `converte()` `<T, K>` está declarando os tipos genéricos que serão usados no método, enquanto que `T` indica seu tipo de retorno.

## Tipos genéricos limitados

Um benefício maior na utilização de *generics* se dá ao especificar-se uma restrição aos tipos de parâmetro possíveis. Sem o uso de `wildcards` há apenas um tipo de limitador: *upper bounded*.

Ao utilizarmos `<T extends Animal>` `Animal` é a classe de hierarquia mais alta (*upper*) aceita como parâmetro. Portanto dessa forma definimos o limite superior para o tipo genérico.

```java
package _generics.exemplo_generics_limite_upper;

import java.util.ArrayList;
import java.util.List;

class SerVivo { }

class Animal extends SerVivo {

    String getEspecies() { return "..."; }
}

class Cao extends Animal { }

class Exemplo {

    /*
     * O limite é superior, Animal é a classe mais alta permitida.
     */
    static <T extends Animal> void print(List<T> objs) {
        for (Animal obj : objs) {
            System.out.println(obj.getEspecies());
        }
    }

    public static void main(String[] args) {

        print(new ArrayList<Animal>());
        print(new ArrayList<Cao>());

        /*
         * SerVivo é uma classe superior a Animal
         */
        //print(new ArrayList<SerVivo>()); // ERRO!
    }
}
```

É possível usar `extends` para mais de uma classe. Nesse caso o tipo deverá ser um subclasse de todos os tipos informados.

```java
Class A {}
interface B {}
interface C {}

class D <T extends A & B & C> {}
```

<div class='importante' markdown='1'>

O `extends` é usado em um sentido geral para significar `extends` (como em classes) ou `implements` (como em interfaces). Portanto para interfaces, não é utilizado `<T implements Interface>` e sim `<T extends Interface>`.

</div>

## Wildcards

Ao contrário de *arrays* (que são covariantes em Java), diferentes instanciações de um tipo genérico não são compatíveis entre si, nem mesmo explicitamente. Conforme o exemplo abaixo, com a declaração `List<Animal> animais` e `List<Cao> caes` o compilador reportaria um erro de conversão para ambos *casts*: `(List<Cao>) animais` e `(List<Animal>) caes`.

```java
package _generics.exemplo_generics_wildcard_motivacao;

import java.util.ArrayList;
import java.util.List;

class Animal {

    String getEspecies() { return getClass().toString(); }
}

class Cao extends Animal { }

class Gato extends Animal { }

class Exemplo {

    /*
     * Exemplo usando parâmetro de tipo genérico
     */
    static <T extends Animal> void print(List<T> objs) {
        for (T obj : objs) {
            System.out.println(obj.getEspecies());
        }
    }

    /*
     * Exemplo usando wildcard
     */
    static void printWC(List<? extends Animal> objs) {
        for (Animal obj : objs) {
            System.out.println(obj.getEspecies());
        }
    }

    public static void main(String[] args) {

        /*
         * Sem wildcard, não é possível converter superclasse <-> subclasse
         */

        List<Animal> listaAnimal = new ArrayList<Animal>();
        List<Cao> listaCao = new ArrayList<Cao>();
        List<Gato> listaGato = new ArrayList<Gato>();

        // listaAnimal = new ArrayList<Cao>(); // ERRO!
        // listaCao = new ArrayList<Animal>(); // ERRO!
        // listaGato = new ArrayList<Cao>();   // ERRO!


        /*
         * Com wildcard, List<? extends Animal> pode receber atribuição de:
         * List<Animal>, List<Cat>, List<Dog>
         */

        List<? extends Animal> listaBaseAnimal;

        listaBaseAnimal = new ArrayList<Animal>();
        listaBaseAnimal = new ArrayList<Gato>();
        listaBaseAnimal = new ArrayList<Cao>();
    }
}
```

Essa incompatibilidade pode ser suavizada pelo *Wildcard* se `?` é usado como um tipo de parâmetro: `List<?>` é o tipo abstrato para todas as parametrizações do tipo genérico. Isso permite que objetos do tipo `List<Animal>` e `List<Cao>` sejam atribuídos com segurança a uma variável ou parâmetro de método do tipo `List<?>`. Usando `List<? extends Animal>` permite o mesmo, restringindo a compatibilidade ao `Animal` e suas subclasses. Outra possibilidade é `List<? super Cao>`, que também aceita os dois objetos e restringe a compatibilidade ao `Cao` e a todos as suas superclasse.

No exemplo acima os dois métodos (`print` e `printWC`) são similares. Métodos que fazem o uso do parâmetro de tipo genérico `<T>` podem usar este tipo para declarar novos objetos dentro do método, enquanto que com *wildcards* isso não é possível.

<div class='importante' markdown='1'>

Não há como utilizar *wildcards* na declaração de tipos genéricos de métodos, classes e interfaces. *Wildcards* são usados apenas como **argumentos**.

</div>

Usando *Wildcards* é possível utilizar mais um limitador de tipo: *Lower Bounded*. Portanto existem três formas de definir um tipo genérico.

- *Upper Bounded Wildcards*: `Class<? extends A>`
- *Lower Bounded Wildcards*: `Class<? super A>`
- *Unbounded Wildcards*: `Class<?>`

### Upper Bounded Wildcards

Ao utilizarmos `<? extends Animal>` `Animal` é a classe de hierarquia mais alta (*upper*) aceita como parâmetro. Portanto dessa forma definimos o limite superior para o tipo genérico.

```java
package _generics.exemplo_generics_wildcard_upper;

import java.util.ArrayList;
import java.util.List;

class SerVivo { }

class Animal extends SerVivo {

    String getEspecies() { return return "..."; }
}

class Cao extends Animal { }

class Exemplo {

    /*
     * O limite é superior, Animal é a classe mais alta permitida.
     */
    static void print(List<? extends Animal > objs) {
        for (Animal obj : objs) {
            System.out.println(obj.getEspecies());
        }
    }

    public static void main(String[] args) {

        print(new ArrayList<Animal>());
        print(new ArrayList<Cao>());

        /*
         * SerVivo é uma classe superior a Animal
         */
        // print(new ArrayList<SerVivo>()); // ERRO!
    }
}
```

### Lower Bounded Wildcards

Ao utilizarmos `<? super Animal>` `Animal` é a classe de hierarquia mais baixa (*lower*) aceita como parâmetro. Portanto dessa forma definimos o limite inferior para o tipo genérico.

```java
package _generics.exemplo_generics_wildcard_lower;

import java.util.ArrayList;
import java.util.List;

class SerVivo { }

class Animal extends SerVivo {

    String getEspecies() { return "..."; }
}

class Cao extends Animal { }

class Exemplo {

    /*
     * O limite é inferior, Animal é a classe mais baixa permitida.
     */
    static void print(List<? super Animal> objs) {
        for (Animal obj : (List<Animal>) objs) {
            System.out.println(obj.getEspecies());
        }
    }

    public static void main(String[] args) {

        print(new ArrayList<Animal>());
        print(new ArrayList<SerVivo>());

        /*
         * Cao é uma classe inferior a Animal
         */
        // print(new ArrayList<Cao>()); // ERRO!

    }
}
```

### Unbounded Wildcards

Utilizada quando precisamos de um tipo genérico mas não nos importamos com qual.

```java
package _generics.exemplo_generics_wildcard_unbounded;

import java.util.ArrayList;
import java.util.List;

class SerVivo { }

class Animal extends SerVivo {

    String getEspecies() { return "..."; }
}

class Cao extends Animal { }

class Exemplo {

    /*
     * Não se sabe o tipo de objeto, portanto não é possível chamar o método
     * getEspecies() de Animal.
     */
    static void print(List<?> objs) {
        for (Object obj : objs) {
            // System.out.println(obj.getEspecies()); // ERRO!
            System.out.println(obj.toString());
        }
    }

    public static void main(String[] args) {

        print(new ArrayList<Animal>());
        print(new ArrayList<Cao>());
        print(new ArrayList<SerVivo>());
    }
}
```

A notação `<?>` é equivalente a `<? extends Object>`. Por isso nenhum valor pode ser inserido nesta lista a não ser `null`.

```java
List<?> objects = new ArrayList<>();

/*
 * Erro em tempo de compilação
 */
// objects.add(new Object()); // ERRO!
// objects.add(new Cao());    // ERRO!
```

Entretanto não se deve pensar no `<?>` como sendo a mesma coisa que a classe `Object`, pois com ela o mesmo exemplo seria válido.

```java
List<Object> objects = new ArrayList<>();

objects.add(new Object());
objects.add(new Cao());
```
