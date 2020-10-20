# Coleções

[TOC]

## Introdução

Uma coleção é uma estrutura de dados que permite armazenar e manipular um grupo de objetos. Para a manipulação das coleções, Java disponibiliza um conjunto de interfaces e classes denominado **Collections Framework**, que faz parte do pacote `java.util`.

<div class='imagem' markdown='1' style="width: 80%">

![java-hierarquia-colecoes](_colecoes/java-hierarquia-colecoes.png)

</div>

A hierarquia da *Collections Framework* tem uma segunda árvore. São as classes e interfaces relacionadas a mapas, que não são derivadas de `Collection`. Essas interfaces, mesmo não sendo consideradas coleções em Java, podem ser manipuladas como tal.

<div class='imagem' markdown='1' style="width: 60%">

![java-hierarquia-map](_colecoes/java-hierarquia-map.png)

</div>

Existem três tipos genéricos de coleção:

- **Listas ordenadas**: As listas ordenadas permitem que o programador insira itens em uma determinada ordem e recupere esses itens em uma ordem pré-determinada. As interfaces base para listas ordenadas são chamadas de `List` e `Queue`.

- **Conjuntos**: Conjuntos são coleções não ordenadas que podem ser iteradas e contêm cada elemento no máximo uma vez. A interface base para conjuntos é chamada de `Set`.

- **Mapas (ou dicionários)**: Os mapas armazenam referências a objetos com uma chave de pesquisa para acessar os valores do objeto. A interface base para mapas é denominada `Map`.

## List

Uma lista (em inglês *list*) é uma coleção que permite elementos duplicados e mantém uma ordenação específica entre os elementos. Quando a lista é percorrida os elementos serão encontrados em uma ordem pré-determinada, definida na hora da inserção destes elementos.

A interface de `List` é implementada pelas classes `ArrayList`, `LinkedList`, `Vector` e `Stack`.

### ArrayList

A classe `ArrayList` usa uma lista dinâmica para armazenar os elementos. Para cada remoção ou adição em uma posição da lista, os elementos das posições posteriores devem ser realocados.

Possui as seguintes características:

- Mantém a ordem de inserção.
- Não é sincronizado.
- Pode acessar elementos aleatoriamente.
- Performance melhor acessar dados.
- Performance pior para adição e remoção de dados.

```java
package _colecoes.exemplo_array_list;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

class Exemplo {

    public static void main(String[] args) {

        ArrayList<String> list = new ArrayList<String>();

        list.addAll(Arrays.asList("A", "B", "D")); // adicionar vários
        list.add(2, "C");                          // adicionar na posição
        list.add("E");                             // adicionar no final

        System.out.println(list.contains("C"));   // se existe o objeto
        System.out.println(list.indexOf("C"));    // posição do objeto
        System.out.println(list.get(2));          // obter objeto na posição

        list.remove(3);                           // remover na posição

        System.out.println(list.size());          // tamanho da lista


        /*
         * Percorrendo a lista através do Iterator
         */
        System.out.println();

        Iterator itr = list.iterator();

        while(itr.hasNext()){

            System.out.println(itr.next());
        }
    }
}

// Saida:
// > true
// > 2
// > C
// > 4

// > A
// > B
// > C
// > E
```

### Vector

A classe `Vector` é semelhante ao `ArrayList`, no entanto, ela é **sincronizada**, ou seja, somente uma `thread` pode acessar a lista por vez.

Possui as seguintes características:

- Mantém a ordem de inserção.
- Sincronizado (seguro para *multithread*).
- Pode acessar elementos aleatoriamente.
- Maior overhead devido a sincronização.
- Performance melhor acessar dados.
- Performance pior para adição e remoção de dados.
- Pode percorrer a lista usando enumeração.

<div class='importante' markdown='1'>

Devido ao maior *overhead* causado pela sincronização, e por ser uma classe legada, a classe `CopyOnWriteArrayList` pode ser considerada uma melhor opção para trabalhar com *multithread*.

</div>

```java
package _colecoes.exemplo_vector;

import java.util.Vector;

class Exemplo {

    public static void main(String[] args) {

        Vector<String> list = new Vector<String>();
    }
}
```

### Stack

A classe `Stack` (pilha) é a subclasse de `Vector` que implementa a estrutura de dados *last-in-first-out* (o último a entrar é o primeiro a sair).

- Mantém a ordem de inserção.
- Sincronizado (seguro para *multithread*).
- Pode acessar elementos aleatoriamente.
- Maior overhead devido a sincronização.
- Performance melhor acessar dados.
- Performance pior para adição e remoção de dados.
- Pode percorrer a lista usando enumeração.

<div class='importante' markdown='1'>

Devido ao maior *overhead* causado pela sincronização, e por ser uma classe legada, as classes que implementam a interface `Deque` como por exemplo, `ConcurrentLinkedDeque` pode ser considerada uma melhor opção para trabalhar com *multithread*.

</div>

```java
package _colecoes.exemplo_stack;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Stack;

class Exemplo {

    public static void main(String[] args) {

        /*
         * Além dos métodos do Vector, Stack possui outros métodos
         */

        Stack<String> list = new Stack<String>();

        list.addAll(Arrays.asList("B", "C", "D")); // adicionar vários

        System.out.println(list.empty());   // verifica se lista vazia

        list.push("A");                     // adiciona elemento no inicio

        System.out.println(list.peek());    // obtém primeiro elemento

        list.pop();                         // remove primeiro elemento

        /*
         * Retorna posição elemento com base 1
         */
        System.out.println(list.search("C"));


        /*
         * Percorrendo a lista através do Iterator
         */
        System.out.println();

        Iterator itr = list.iterator();

        while(itr.hasNext()){

            System.out.println(itr.next());
        }
    }
}

// Saida:
// > false
// > A
// > 2

// > B
// > C
// > D
```

### LinkedList

A classe `LinkedList` usa uma lista duplamente encadeada para armazenar os elementos, sendo assim não precisa realocar os elementos durante inserções e remoções na lista, consequentemente realiza manipulações na lista de forma mais rápida.

Possui as seguintes características:

- Atua como lista e fila (implementa `List` e `Deque`).
- Mantém a ordem de inserção.
- Não é sincronizado.
- Não pode acessar elementos aleatoriamente.
- Performance pior para acessar dados.
- Performance melhor para adição e remoção de dados.

<div class='importante' markdown='1'>

A classe `LinkedList` também pode implementar uma pilha, mas sem sincronização.

</div>

```java
package _colecoes.exemplo_linked_list;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;

class Exemplo {

    public static void main(String[] args) {

        /*
         * Além dos métodos do ArrayList, LinkedList possui outros métodos
         */
        LinkedList<String> list = new LinkedList<String>();

        list.addAll(Arrays.asList("B", "C", "D")); // adicionar vários
        list.addFirst("A");                     // adiciona no início
        list.addLast("E");                      // adiciona no final

        /*
         * Obtém o primeiro e último objeto
         */
        System.out.println(list.getFirst());
        System.out.println(list.getLast());

        /*
         * Obtém o primeiro e último objeto. Se a lista for vazia retorna null
         */
        System.out.println(list.peekFirst()); // ou list.peek()
        System.out.println(list.peekLast());

        /*
         * Obtém o primeiro e último objeto e o remove da lista. Se a lista for
         * vazia retorna null.
         */
        list.pollFirst(); // ou list.poll()
        list.pollLast();


        /*
         * Adiciona e remove um objeto como em uma pilha, ou seja, adiciona no
         * início (primeira posição) e também remove o primeiro.
         */
        list.push("A");
        list.pop();


        /*
         * Percorrendo a lista através do Iterator
         */
        System.out.println();

        Iterator itr = list.iterator();

        while(itr.hasNext()){

            System.out.println(itr.next());
        }
    }
}

// Saida:
// > A
// > E
// > A
// > E

// > B
// > C
// > D
```

## Queue

Uma fila (em inglês *queue*) é uma coleção que ordena o elemento na maneira *first-in-first-out* (o primeiro a entrar é o primeiro a sair). Não suportam ordenação, já que não são listas.

A interface de `Queue` é implementada pela classe `PriorityQueue` e pelas classes `ArrayDeque` e `LinkedList` através da subinterface `Deque`.

### PriorityQueue

Na classe `PriorityQueue`, os elementos **não** são ordenados de maneira *first-in-first-out*, e sim por sua ordem natural padrão, ou por prioridade através do método `compare` de uma classe que implementa a interface `Comparator`.

Possui as seguintes características:

- Ordem de inserção baseado em nível de prioridade (se existir).
- Os objetos da fila devem ser comparáveis.
- Não é sincronizado (`PriorityBlockingQueue` é sua versão sincronizada).
- Não pode acessar elementos aleatoriamente.
- Não permite elemento `null`.
- Performance melhor para acessar dados.
- Performance pior para adição e remoção de dados.

Exemplo de ordenação por ordem natural padrão:

```java
package _colecoes.exemplo_priority_queue;

import java.util.Arrays;
import java.util.PriorityQueue;

class Exemplo {

    public static void main(String[] args) {

        PriorityQueue<String> fila = new PriorityQueue<String>();

        fila.addAll(Arrays.asList("C", "E", "A")); // adicionar vários
        fila.add("D");
        fila.add("B");

        /*
         * Remove e retorna a cabeça da fila. A fila é ordenada em sua ordem
         * natural (A-Z).
         */
        while (!fila.isEmpty()) {

            System.out.println(fila.remove());
        }
    }
}

// Saida:
// > A
// > B
// > C
// > D
// > E
```

Exemplo de ordenação por método de comparação:

```java
package _colecoes.exemplo_priority_queue_comparator;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

class Exemplo {

    public static void main(String[] args) {

        /*
         * Capacidade inicial (quantidade de elementos que podem ser adicionados
         * sem a necessidade de realizar uma nova realocação de memória) e o
         * método usado para a ordenação da fila.
         */
        PriorityQueue<String> fila =
                new PriorityQueue<String>(10, new Comparador());

        fila.addAll(Arrays.asList("CC", "E", "AAA"));
        fila.add("D");
        fila.add("BBBB");

        /*
         * Remove e retorna a cabeça da fila. A fila é ordenada de acordo com
         * o método compare de Comparador.
         */
        while (!fila.isEmpty()) {

            System.out.println(fila.remove());
        }
    }
}

class Comparador implements Comparator<String> {

    @Override
    public int compare(String x, String y) {

        return x.length() - y.length();
    }
}

// Saida
// > E
// > D
// > CC
// > AAA
// > BBBB
```

### ArrayDeque

A classe `ArrayDeque` é uma fila que suporta inserção e remoção de elementos nas duas extremidades. `Deque` é um acrônimo para "*double ended queue*". Tanto `LinkedList` quanto `ArrayDeque` implementam a interface `Deque`, portanto possui métodos em comum.

Remover o último elemento de `LinkedList` tem complexidade `O(n)` já que percorre toda a lista para chegar ao fim. Portanto para adicionar ou remover elementos de ambas as extremidades, deve-se escolher `ArrayDeque`, que possui complexidade `O(1)`.

Possui as seguintes características:

- Diferente de filas, pode adicionar ou remover elementos em ambos lados.
- Não é sincronizado (`PriorityBlockingQueue` é sua versão sincronizada).
- Não pode acessar elementos aleatoriamente.
- Não permite elemento `null`.
- Performance pior para acessar dados.
- Performance melhor para adição e remoção de dados.

```java
package _colecoes.exemplo_array_deque;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Iterator;

class Exemplo {

    public static void main(String[] args) {

        ArrayDeque<String> fila = new ArrayDeque<>();


        fila.addAll(Arrays.asList("B", "C", "D")); // adicionar vários
        fila.addFirst("A");                     // adiciona no início
        fila.addLast("E");                      // adiciona no final

        /*
         * Obtém o primeiro e último objeto
         */
        System.out.println(fila.getFirst());
        System.out.println(fila.getLast());

        /*
         * Obtém o primeiro e último objeto. Se a fila for vazia retorna null
         */
        System.out.println(fila.peekFirst()); // ou fila.peek()
        System.out.println(fila.peekLast());

        /*
         * Obtém o primeiro e último objeto e o remove da fila. Se a filaa for
         * vazia retorna null.
         */
        fila.pollFirst(); // ou fila.poll()
        fila.pollLast();


        /*
         * Adiciona e remove um objeto como em uma pilha, ou seja, adiciona no
         * início (primeira posição) e também remove o primeiro.
         */
        fila.push("A");
        fila.pop();


        /*
         * Percorrendo a fila através do Iterator
         */
        System.out.println();

        Iterator itr = fila.iterator();

        while(itr.hasNext()){

            System.out.println(itr.next());
        }
    }
}

// Saida:
// > A
// > E
// > A
// > E

// > B
// > C
// > D
```

## Set

Um conjunto (em inglês *set*) funciona de forma análoga aos conjuntos da matemática, ele é uma coleção que não permite elementos duplicados. Além disso, a ordem em que os elementos são armazenados pode não ser a ordem na qual eles foram inseridos no conjunto. A interface não define como deve ser este comportamento. Tal ordem varia de implementação para implementação.

A interface de `Set` é implementada pelas classes `HashSet`, `LinkedHashSet`, `TreeSet`.

### HashSet

A classe `HashSet` é usada para criar uma coleção que usa uma tabela *hash* para armazenamento.

Possui as seguintes características:

- Armazena os elementos usando um mecanismo chamado *hashing*.
- Não mantém a ordem de inserção.
- Não contém valores duplicados.
- Não é sincronizado.
- Não pode acessar elementos aleatoriamente.
- Permite valor nulo.
- Performance melhor para operações de busca.

```java
package _colecoes.exemplo_hash_set;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;

class Exemplo {

    public static void main(String[] args) {

        HashSet<String> set = new HashSet<>();

        set.addAll(Arrays.asList("A", "B", "C", "D")); // adicionar vários

        /*
         * Conjuntos não permitem valores duplicados, portanto não será
         * adicionado.
         */
        set.add("B");

        /*
         * Remove objeto
         */
        set.remove("C");

        System.out.println(set.size()); // número de elementos no conjunto

        /*
         * Percorrendo a fila através do Iterator
         */
        System.out.println();

        Iterator itr = set.iterator();

        while(itr.hasNext()){

            Object obj = itr.next();

            System.out.println(obj + " - " + obj.hashCode());
        }
    }
}

// Saida:
// > 3

// > A - 65
// > B - 66
// > D - 68
```

### LinkedHashSet

A classe `LinkedHashSet` é uma lista duplamente encadeada para armazenar os elementos, sendo assim não precisa realocar os elementos durante inserções e remoções no conjunto, consequentemente realiza manipulações no conjunto de forma mais rápida.

Possui as seguintes características:

- Armazena os elementos usando um mecanismo chamado *hashing*.
- Mantém a ordem de inserção.
- Não contém valores duplicados.
- Não é sincronizado.
- Permite valor nulo.
- Não pode acessar elementos aleatoriamente.
- Performance melhor para operações de busca.

```java
package _colecoes.exemplo_linked_hash_set;

import java.util.LinkedHashSet;

class Exemplo {

    public static void main(String[] args) {

        LinkedHashSet<String> set = new LinkedHashSet<>();
    }
}
```

### TreeSet

A classe `TreeSet` é semelhante a `HashSet`, entretanto mantém a ordem ascendente dos elementos.

Possui as seguintes características:

- Armazena os elementos usando um mecanismo chamado *hashing*.
- Mantém a ordem ascendente.
- Não contém valores duplicados.
- Não permite valor nulo.
- Não é sincronizado.
- Melhor performance para operações de busca.
- Não pode acessar elementos aleatoriamente.
- Performance melhor para adição e remoção de dados.

```java
package _colecoes.exemplo_tree_set;

import java.util.Arrays;
import java.util.Iterator;
import java.util.TreeSet;

class Exemplo {

    public static void main(String[] args) {

        TreeSet<String> set = new TreeSet<>();

        set.addAll(Arrays.asList("B", "A", "D", "C")); // adicionar vários


        /*
         * Percorrendo a fila através do Iterator
         */
        System.out.println();

        Iterator itr = set.iterator();

        while(itr.hasNext()){

            Object obj = itr.next();

            System.out.println(obj + " - " + obj.hashCode());
        }
    }
}

// Saida:
// > A - 65
// > B - 66
// > C - 67
// > D - 68
```

## Map

Um mapa (em inglês *map*) é composto por um conjunto de associações entre um objeto chave a um objeto valor. É equivalente ao conceito de dicionário, usado em várias linguagens. A busca de um objeto valor através de sua chave permite uma performance muito superior para busca de dados em relação as outra coleções.

A interface de `Map` é implementada pelas classes `HashMap`, `LinkedHashMap`, `TreeMap`.

<div class='importante' markdown='1'>

A classe `HashTable` também implementa a interface `Map`. Ela foi a primeira coleção baseada em tabela *hash*. Entretanto, o seu uso não é recomendado por ser uma classe legada e por possuir *overhead* causado pela sincronização. A classe `ConcurrentHashMap` é a classe recomendada para uma solução segura para *multithread*.

</div>

<div class='importante' markdown='1'>

A interface `Map` não deriva de `Collection`, portanto não se pode usar `iterators` diretamente.

</div>

### HashMap

A classe `HashMap` assim como os conjuntos, é usada para criar uma coleção que usa uma tabela *hash* para armazenamento. Entretanto a chave usada na tabela *hash* é fornecida pelo usuário.

Possui as seguintes características:

- Armazena os elementos baseando em chaves (*hash*).
- Não mantém a ordem de inserção.
- Não contém valores duplicados.
- Não é sincronizado.
- Permite uma chave nula e múltiplos elementos nulos.
- Não pode acessar elementos aleatoriamente.
- Performance melhor para operações de busca.

```java
package _colecoes.exemplo_hash_map;

import java.util.HashMap;

class Exemplo {

    public static void main(String[] args) {

        HashMap<Integer,String> map = new HashMap<>();

        map.put(100,"A"); // adiciona o par chave-valor
        map.put(101,"B");
        map.put(102,"C");
        map.put(100,"X"); // sobrescreve o valor correspondente a chave

        map.putIfAbsent(100, "Z"); // adiciona somente se chave não existe

        System.out.println(map.get(100));   // obtém o valor correspondente a chave

        /*
         * Substitui o valor correspondente a chave
         */
        map.replace(100, "A");

        /*
         * Substitui o valor correspondente a chave somente se o valor corresponde
         */
        map.replace(100, "W", "Z");

        System.out.println(map.containsValue("C")); // se possui o valor
        System.out.println(map.containsKey(102));   // se possui a chave

        System.out.println(map.entrySet()); // retorna a visão do mapa
        System.out.println(map.keySet());   // retorna a visão das chaves

        System.out.println(map.size());     // retorna a quantidade de elementos
    }
}

// Saida:
// > X
// > true
// > true
// > [100=A, 101=B, 102=C]
// > [100, 101, 102]
// > 3
```

### LinkedHashMap

A classe `LinkedHashMap` implementa a interface `Hashtable` e `Linked list` de `Map`.

- Armazena os elementos baseando em chaves (*hash*).
- Não contém valores duplicados.
- Mantém a ordem de inserção.
- Não é sincronizado.
- Permite uma chave nula e múltiplos elementos nulos.
- Não pode acessar elementos aleatoriamente.
- Performance pior para acessar dados.
- Performance melhor para adição e remoção de elementos.

```java
package _colecoes.exemplo_linked_hash_map;

import java.util.LinkedHashMap;

class Exemplo {

    public static void main(String[] args) {

        LinkedHashMap<Integer,String> map = new LinkedHashMap<>();
    }
}
```

### TreeMap

A classe `TreeMap` implementa a interface `SortedMap` que por sua vez implementa a interface `Map`. Ela é uma implementação baseada na árvore rubro-negra que possui formas eficientes de armazenar pares chave-valor de forma ordenada.

- Armazena os elementos baseando em chaves (*hash*).
- Mantém a ordem ascendente (chaves).
- Não contém valores duplicados.
- Não é sincronizado.
- Não pode acessar elementos aleatoriamente.
- Não permite chave nula mas permite múltiplos elementos nulos.
- Performance melhor para acessar dados.
- Performance pior para adição e remoção de elementos.

```java
package _colecoes.exemplo_tree_map;

import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

class Exemplo {

    public static void main(String[] args) {

        TreeMap<Integer,String> map = new TreeMap<>();

        map.put(101,"B");
        map.put(103,"A");
        map.put(102,"C");

        System.out.println(map.entrySet());

        /*
         * Classes que implementam Map não deriva de Collection, portanto não possuem
         * o método iterator().
         */
        Iterator<Integer> keyIterator = map.keySet().iterator();

        while (keyIterator.hasNext()) {

            Integer chave = keyIterator.next();
            String valor = map.get(chave);
            System.out.print(chave + "=" + valor + " ");
        }

        System.out.println("\n" + map.higherKey(101)); // a maior chave na sequencia
        System.out.println(map.lowerKey(103));         // a menor chave na sequencia

        System.out.println(map.firstEntry());          // primeiro par chave-valor
        System.out.println(map.lastEntry());           // último par chave-valor

        System.out.println(map.firstKey());            // primeiro chave
        System.out.println(map.lastKey());             // última chave

        System.out.println(map.containsKey(101));      // se contém a chave
        System.out.println(map.containsValue("C"));    // se contém o valor
    }
}

// Saida:
// > [101=B, 102=C, 103=A]
// > 101=B 102=C 103=A
// > 102
// > 102
// > 101=B
// > 103=A
// > 101
// > 103
// > true
// > true
```

## Iterando coleções

Java fornece cinco métodos para iterar sobre coleções: `for`, `enumeration`, `iterator`, `enhanced-for`, `forEach`.

### For

Esta é a construção mais familiar na programação, entretanto exige que a coleção armazene elementos em forma de índice. Portanto poderá ser usado apenas para coleções que implementam a interface `List`, como por exemplo:  `ArrayList`, `LinkedList`, `Vector` e `Stack`.

```java
package _colecoes.exemplo_iteracao_for;

import java.util.*;

class Exemplo {

    public static void main(String[] args) {

        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.addAll(Arrays.asList("A", "B", "C"));

        for (int i = 0; i < arrayList.size(); i++) {
            System.out.print(arrayList.get(i) + " ");
        }
    }
}

// Saida:
// > A B C
```

### Enumeration

Usado para iterar coleções legadas como: `Vector`, `Stack` e `Hashtable`,

```java
package _colecoes.exemplo_iteracao_enumeration;

import java.util.*;

class Exemplo {

    public static void main(String[] args) {

        Vector<String> vector = new Vector<>();
        vector.addAll(Arrays.asList("A", "B", "C"));

        Enumeration e = vector.elements();

        while (e.hasMoreElements()) {

            System.out.print(e.nextElement() + " ");
        }
    }
}

// Saida:
// > A B C
```

### Iterator

Devido às limitações do `for` clássico, o método `iterator()` foi criado para nos permitir iterar todos os tipos de coleções que implementam a interface `Collection`, uma vez que esta interface define que cada coleção deve implementar este o método. Portanto, classes que implementam a interface `map` não possuem o método `iterator()`.

```java
package _colecoes.exemplo_iteracao_iterator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

class Exemplo {

    public static void main(String[] args) {

        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.addAll(Arrays.asList("A", "B", "C"));

        Iterator<String> iterator = arrayList.iterator();

        /*
         * hasNext(): retorna true se há mais elementos na iteração
         * next(): retorna o próximo elemento da iteração
         * remove(): remove o atual elemento do iteração
         */
        while (iterator.hasNext()) {

            String valor = iterator.next();
            System.out.print(valor + " ");

            if (valor.equals("B")) {
                iterator.remove();
            }
        }

        /*
         * Reiniciando a iteração
         */
        iterator = arrayList.iterator();

        System.out.println();
        while (iterator.hasNext()) {

            System.out.print(iterator.next() + " ");
        }
    }
}

// Saida:
// > A B C
// > A C
```

Para coleções que implementam a interface `map`, podemos utilizar o método `iterator()` da seguinte forma:

```java
package _colecoes.exemplo_iteracao_iterator_map;

import java.util.HashMap;
import java.util.Iterator;

class Exemplo {

    public static void main(String[] args) {

        HashMap<Integer,String> hashMap = new HashMap<>();

        hashMap.put(100,"A");
        hashMap.put(101,"B");
        hashMap.put(102,"C");

        Iterator<Integer> keyIterator = hashMap.keySet().iterator();

        while (keyIterator.hasNext()) {

            Integer chave = keyIterator.next();
            String valor = hashMap.get(chave);
            System.out.println(chave + "=" + valor);
        }
    }
}

// Saida:
// > A B C
// > A C
```

#### ListIterator

Para iterar coleções que implementam a interface `List` também podemos utilizar `ListIterator`, que fornece meios para realizar a iteração bidirecionalmente.

```java
package _colecoes.exemplo_iteracao_list_iterator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.ListIterator;

class Exemplo {

    public static void main(String[] args) {

        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.addAll(Arrays.asList("A", "B", "C"));

        ListIterator<String> listIterator = arrayList.listIterator();

        while (listIterator.hasNext()) {

            System.out.print(listIterator.next() + " ");
        }

        /*
         * hasPrevious(): retorna true se há mais elementos anteriores na iteração
         * previous(): retorna o elemento anterior da iteração
         */
        System.out.println();
        while (listIterator.hasPrevious()) {

            System.out.print(listIterator.previous() + " ");
        }
    }
}

// Saida:
// > A B C
// > C B A
```

### Enhanced-for

O *loop* `for` aprimorado é uma sintaxe mais sucinta para percorrer uma coleção.

```java
package _colecoes.exemplo_iteracao_enhanced_for;

import java.util.*;

class Exemplo {

    public static void main(String[] args) {

        /*
         * Classes que implementam Collection
         */
        HashSet<String> hashSet = new HashSet<>();
        hashSet.addAll(Arrays.asList("A", "B", "C"));

        for (String hs : hashSet) {
            System.out.print(hs + " ");
        }

        /*
         * Classes que implementam Map
         */
        HashMap<Integer, String> hashMap = new HashMap<>();
        hashMap.put(100, "A");
        hashMap.put(101, "B");
        hashMap.put(102, "C");

        System.out.println();
        for (Integer key : hashMap.keySet()) {

            System.out.print(key + "=" + hashMap.get(key) + " ");
        }
    }
}

// Saida:
// > A B C
// > 100=A 101=B 102=C
```

### ForEach

O Java 8 com expressões *Lambda* introduziu uma maneira totalmente nova de iterar sobre coleções usando o método `forEach`.

```java
package _colecoes.exemplo_iteracao_for_each;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

class Exemplo {

    public static void main(String[] args) {

        /*
         * Classes que implementam Collection
         */
        HashSet<String> hashSet = new HashSet<>();
        hashSet.addAll(Arrays.asList("A", "B", "C"));

        hashSet.forEach(name -> System.out.print(name + " "));


        /*
         * Classes que implementam Map
         */
        HashMap<Integer, String> hashMap = new HashMap<>();
        hashMap.put(100, "A");
        hashMap.put(101, "B");
        hashMap.put(102, "C");

        System.out.println();
        hashMap.forEach((key, value) -> System.out.print(key + "=" + value + " "));
    }
}

// Saida:
// > A B C
// > 100=A 101=B 102=C
```

## Métodos equals() e hashCode()

O método `equals(Object o)` é usado para indicar se algum outro objeto (`Object o`) é igual ao objeto atual (no qual o método foi invocado). Por padrão simplesmente verifica as referências dos dois objetos para verificar sua igualdade, que por padrão, é o endereço de memória.

O método `hashCode()` retorna um valor inteiro exclusivo para o objeto em tempo de execução. Por padrão, o valor inteiro é derivado principalmente do endereço de memória do objeto na pilha (mas não é obrigatório sempre).
Esse código *hash* é usado para determinar o local do *bucket*, quando esse objeto precisa ser armazenado em alguma estrutura de dados como o `HashTable`.

Em geral, é necessário substituir o método `hashCode()` sempre que o método `equals()` for sobrescrito, de modo a manter o contrato geral para o método `hashCode()`, que declara que **objetos iguais devem ter códigos hash iguais**.

- Durante a execução de um aplicativo Java, em um mesmo objeto, o *hashCode* deve retornar o mesmo número inteiro, desde que nenhuma informação usada nas comparações de `equals()` seja modificada.

- Se os objetos forem iguais de acordo com o método `equals()`, eles também devem ter o mesmo código *hash*.

- Se os objetos tiverem o mesmo código *hash*, eles **não** terão que ser necessariamente iguais.

```java
package _colecoes.exemplo_equals_hashcode;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Exemplo {

    public static void main(String[] args) {

        List<Livro> livros = new ArrayList<>();

        livros.add(new Livro("Harry Potter", 100.0));
        livros.add(new Livro("Harry Potter", 100.0));
        livros.add(new Livro("Romeu e Julieta", 100.0));
        livros.add(new Livro("Romeu e Julieta", 100.0));
        livros.add(new Livro("Alice", 100.0));
        livros.add(new Livro("Alice", 200.0));
        livros.add(new Livro("Dom Casmurro", 100.0));
        livros.add(new Livro("Dom Casmurro", 200.0));

        Stream<Livro> sDistinct = livros.stream().distinct();
        sDistinct.forEach(System.out::println);
    }
}

class Livro {

    String nome;
    Double preco;

    Livro(){}

    Livro(String nome, Double preco) {
        this.preco = preco;
        this.nome = nome;
    }

    @Override
    public String toString() {
        return this.nome;
    }

    /*
     * Livros com mesmo nome tem o mesmo código hash
     */
    @Override
    public int hashCode() {
        return this.nome.hashCode();
    }

    /*
     * Livros com mesmo nome tem o mesmo código hash, mas somente os que possuem o mesmo
     * preço são considerados iguais.
     */
    @Override
    public boolean equals(Object obj) {
        Livro livro = (Livro) obj;
        return livro.nome.equals(this.nome) && livro.preco.equals(this.preco);
    }


}
```

## Boas práticas

- Evite usar coleções que guardam os elementos pela sua ordem de comparação quando não há necessidade. Um `TreeSet` gasta computacionalmente `O(log(n))` para inserir (ele utiliza uma árvore rubro-negra como implementação), enquanto o `HashSet` gasta apenas `O(1)`.

- Não itere sobre uma `List` utilizando um `for (i=0; i < list.size(); i++)` e `get(i)` para receber os objetos. Enquanto isso parece atraente, algumas implementações de `List` não são de acesso aleatório como a `LinkedList`, fazendo esse código ter uma péssima performance computacional.
