# Streams

[TOC]

## Introdução

A API *Streams*, introduzida no Java 8, é um recurso que permite manipular conjuntos de forma mais simples e concisa de escrever código que resulta em facilidade de manutenção e paralelização sem efeitos indesejados em tempo de execução. Isso se tornou possível graças à incorporação do paradigma funcional, combinado com as expressões *lambda*. A ideia é iterar sobre essas coleções de objetos e, a cada elemento, realizar alguma ação, seja ela de filtragem, mapeamento, transformação, etc.

De forma sucinta, podemos dizer que *stream* é uma "sequência de elementos de uma fonte de dados que suporta operações de agregação". Podemos dividir a definição da seguinte forma:  

- **Sequência de elementos**: Uma *stream* oferece uma interface para um conjunto de valores sequenciais de um tipo de elemento particular. Apesar disso, as *streams* não armazenam elementos; estes são calculados sob demanda.

- **Fonte de dados**: As *streams* tomam seu insumo de uma fonte de dados, como coleções, matrizes ou recursos de E/S.

- **Operações de agregação**: As *streams* suportam operações do tipo SQL e operações comuns à maioria das linguagens de programação funcionais, como *filter*, *map*, *reduce*, *find*, *match* e *sorted*, entre outras.

Ainda mais, as operações das *streams* têm duas características fundamentais que as diferenciam das operações com coleções:

- **Estrutura de processo**: Muitas operações de *stream* retornam outra *stream*. Assim, é possível encadear operações para formar um processo mais abrangente (*pipeline*). Isto, por sua vez, permite algumas otimizações, por exemplo mediante as noções de *lazy evaluation* e *short-circuiting*.

  - **Lazy evaluation** (avaliação tardia ou "preguiçosa") possibilita que o processamento da cadeia de operações seja executado de forma mais performática, postergando a computação até um ponto em que o resultado seja, de fato, importante, evitando assim cálculos desnecessários.

  - **Short-circuiting** (curto circuito) possibilita que o resultado final seja obtido antes que todos os elementos da *stream* sejam processados. Um exemplo é a chamada ao método `limit(n)` onde apenas os `n` primeiros elementos da *stream* serão processados.

- **Iteração interna**: Diferentemente do trabalho com coleções, em que a iteração é explícita (iteração externa), as operações da *stream* realizam uma iteração é interna (iteração implícita).

O exemplo abaixo de operações com *streams* pode ser representado conforme a figura em seguida.

```java
List<Integer> transactionsIds =
    transactions.stream()
    .filter(t -> t.getType() == Transaction.GROCERY)
    .sorted(comparing(Transaction::getValue).reversed())
    .map(Transaction::getId)
    .collect(toList());
```

<div class='imagem' markdown='1'>

![java-fluxo-stream](_stream/java-fluxo-stream.jpg)

</div>

Em primeiro lugar, é obtido uma *stream* da lista de transações chamando o método `stream()`. A fonte de dados é a lista de transações, que vai fornecer uma listagem de elementos à *stream*. A seguir, vamos aplicar uma série de operações agregadas à *stream*: `filter` (para filtrar elementos segundo um predicado particular), `sorted` (para ordenar os elementos segundo um comparador) e `map` (para obter a informação). Todas as operações, com exceção de `collect`, retornam uma *Stream*, portanto, é possível encadeá-las e formar um processo, que podemos ver como consulta dos dados da fonte.

Na verdade, nenhuma tarefa é realizada até se chamar a operação `collect`. Esta última começará a abordar o processo para retornar um resultado (que não será uma *Stream*; neste caso, trata-se de uma lista `List`).

Em suma, a API *Streams* trabalha convertendo uma fonte de dados em uma *Stream*. Em seguida, realiza o processamento dos dados através das operações intermediárias e, por fim, retorna uma nova coleção ou valor reduzido (*map-reduce*) com a chamada a uma operação terminal.

As **operações intermediárias** são aquelas que retornam um novo Stream para que novas operações intermediárias sejam realizadas de maneira fluente (encadeadas). Podem ser divididas em dois grupos:

- **Stateless**: As operações *stateless*, como `filter()` e `map()`, não armazenam o estado do elemento manipulado anteriormente ao processar um novo elemento. Dessa forma, cada elemento pode ser processado independentemente das operações dos outros elementos.

- **Stateful**: As operações *stateful*, como `distinct()` e `sorted()`, podem incorporar o estado do elemento processado anteriormente no processamento de novos elementos.

As **operações terminais** são operações que juntam os resultados de um *Stream* e retornam um valor ou um objeto. Depois de invocada uma operação terminal, o mesmo Stream não poderá ser alterado por outras operações intermediárias ou executar novas operações terminais. Exemplos: `forEach()`, `sum()`, `min()`, `max()`, `findFirst()`, etc.

<div class='importante' markdown='1'>

A API *Streams* suporta a **paralelização** de operações para processar os dados, bastando trocar o método `stream()` por `parallelStream()`. Dessa forma a API *Streams* irá decompor as ações em várias subtarefas, e as operações serão processadas em paralelo, explorando os recursos oferecidos pelos diversos núcleos do processador.

</div>

## *Streams* x coleções

A noção de coleções já existente em Java e a nova noção de *streams* se referem a interfaces com sequências de elementos. Então, qual a diferença? Resumindo, as coleções se referem a dados enquanto as *streams* se referem a cálculos.

A diferença entre coleções e *streams* tem a ver com quando os cálculos são feitos. As coleções são estruturas de dados armazenados na memória, onde estão todos os valores que a estrutura de dados tem em um momento determinado; cada elemento da coleção deve ser calculado antes de se agregar à coleção. Já as *streams* são estruturas de dados fixas, cujos elementos são calculados sob demanda.

Quando a interface `Collection` é usada, o usuário deve se ocupar da iteração (por exemplo, mediante `foreach`, laço for melhorado); essa abordagem é chamada de iteração externa.

```java
List<String> transactionIds = new ArrayList<>();
    for (Transaction t : transactions) {
        transactionIds.add(t.getId());  
    }
```

Em contrapartida, a biblioteca `Streams` recorre à iteração interna; ela se ocupa da iteração e de armazenar em algum lugar o valor da *stream* resultante; o usuário só fornece uma função dizendo o que deve ser feito.

```java
List<Integer> transactionIds =
    transactions.stream()
    .map(Transaction::getId)
    .collect(toList());
```

## Criação e manipulação

A API *Streams* foi desenvolvida sob o pacote `java.util.stream`. A forma mais comum de criar uma `stream` é através de uma coleção de dados, tendo em vista que o principal propósito dessa API é tornar mais flexível e eficiente o processamento de coleções.

```java
package _stream.exemplo_stream_criacao;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Exemplo {

    public static void main(String[] args) throws IOException {

        /*
         * Stream de uma coleção
         */
        List<String> lista = new ArrayList<String>();
        lista.addAll(Arrays.asList("A", "B", "C"));

        Stream<String> streamLista = lista.stream();


        /*
         * Stream de um map
         */
        Map<Integer, String> map = new HashMap<Integer, String>();

        map.put(0, "A");
        map.put(1, "B");
        map.put(2, "C");

        Stream<String> streamMap = map.values().stream();


        /*
         * Stream de valores, arrays e I/O
         */
        Stream<Integer> valores = Stream.of(1, 2, 3, 4, 5);

        IntStream array = Arrays.stream(new int[] {1, 2, 3, 4, 5});

        Stream <String> io = Files.lines(Paths.get("Exemplo.java"), Charset.defaultCharset());
    }
}
```

Algumas das operações intermediárias mais utilizadas são: `filter()`, `map()`, `sorted()`, `limit()` e `distinct()`. Já para operações terminais, podemos citar: `forEach`, `average()`, `collect()`, `count()` e `allMatch()`.

Classe que define os elementos da lista dos exemplos de operações intermediárias e terminais.

```java
class Pessoa {
    String nome;
    String nacionalidade;
    int idade;

    Pessoa(){}

    Pessoa (String nome, String nacionalidade, int idade) {
        this.nome = nome;
        this.nacionalidade = nacionalidade;
        this.idade = idade;
    }

    List<Pessoa> populaPessoas(){
        Pessoa pessoa1 = new Pessoa("Lucas", "Brasil", 18);
        Pessoa pessoa2 = new Pessoa("Hernandez", "Mexico", 21);
        Pessoa pessoa3 = new Pessoa("Jim","Canada", 22);
        Pessoa pessoa4 = new Pessoa("Amanda", "Brasil", 17);
        Pessoa pessoa5 = new Pessoa("Felipe", "Brasil", 22);

        List<Pessoa> list = new ArrayList<>();
        list.addAll(Arrays.asList(pessoa1, pessoa2, pessoa3, pessoa4, pessoa5));
        return list;
    }

    @Override
    public String toString() {
        return this.nome;
    }

    @Override
    public boolean equals(Object obj) {
        return ((Pessoa) obj).getNacionalidade().equals(this.getNacionalidade());
    }

    @Override
    public int hashCode() {
        return this.getNacionalidade().hashCode();
    }

    String getNome() { return this.nome; }

    String getNacionalidade() { return this.nacionalidade; }

    int getIdade() { return this.idade; }
}
```

Exemplos de operações intermediárias:

```java
package _stream.exemplo_manipulacao;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ExemploOperacoesIntermediarias {

    public static void main(String[] args) {

        List<Pessoa> pessoas = new Pessoa().populaPessoas();

        /*
         * map(): transformações em uma lista de dados de acordo com uma função (Function)
         *
         * Para evitar o overhead de converter um int (tipo primitivo) num Integer(Classe Wrapper),
         * podemos usar IntStream, DoubleStream e LongStream com mapToInt, mapToDouble e mapToLong
         * respectivamente
         */
        Stream<Integer> sMap = pessoas.stream().map(Pessoa::getIdade);
        System.out.println("map: " + sMap.collect(Collectors.toList()));
        // IntStream sMap = pessoas.stream().mapToInt(Pessoa::getIdade);
        // System.out.println(sMap.boxed().collect(Collectors.toList()).toString());

        /*
         * filter(): é usado para filtrar elementos de uma stream de acordo com uma
         * condição (Predicate)
         */
        Stream<Pessoa> sFilter = pessoas.stream()
                .filter(pessoa -> pessoa.nacionalidade.equals("Brasil"));
        System.out.println("filter: " + sFilter.collect(Collectors.toList()));

        /*
         * sorted(): ordenação de elementos em coleções através da função comparing() que recebe
         * uma função (Function)
         */
        Stream<Pessoa> sSorted = pessoas.stream().sorted(Comparator.comparing(Pessoa::getNome));
        System.out.println("sorted: " + sSorted.collect(Collectors.toList()));

        /*
         * distinct(): retorna uma stream contendo apenas elementos que são exclusivos, isto é, que
         * não se repetem. Para comparação de objetos necessita implementar o equals().
         */
        Stream<Pessoa> sDistinct = pessoas.stream().distinct();
        System.out.println("distinct: " + sDistinct.collect(Collectors.toList()));

        /*
         * limit(): limita o número de elementos em uma stream. É uma operação conhecida como
         * curto-circuito devido ao fato de não precisar processar todos os elementos.
         */
        Stream<Pessoa> sLimit = pessoas.stream().limit(2);
        System.out.println("limit: " + sLimit.collect(Collectors.toList()));

        /*
         * As operações intermediárias retornam um novo stream que pode ser utilizado por outras
         * operações intermediárias de forma encadeada.
         */
        Stream<String> sEncadeada = pessoas.stream()
                .filter(pessoa -> !pessoa.nacionalidade.equals("Brasil"))
                .distinct()
                .sorted(Comparator.comparing(Pessoa::getNacionalidade))
                .limit(2)
                .map(Pessoa::getNacionalidade);

        System.out.println("encadeamento: " + sEncadeada.collect(Collectors.toList()));
    }
}

// Saida
// > map: [18, 21, 22, 17, 22]
// > filter: [Lucas, Amanda, Felipe]
// > sorted: [Amanda, Felipe, Hernandez, Jim, Lucas]
// > distinct: [Lucas, Hernandez, Jim]
// > limit: [Lucas, Hernandez]
// > encadeamento: [Canada, Mexico]
```

Exemplos de operações terminais:

```java
package _stream.exemplo_manipulacao;

import java.util.List;
import java.util.stream.Collectors;

public class ExemploOperacoesTerminais {

    public static void main(String[] args) {

        List<Pessoa> pessoas = new Pessoa().populaPessoas();

        /*
         * forEach(): realizar um loop sobre todos os elementos de uma stream e executar algum
         * tipo de processamento.
         */
        System.out.print("forEach: ");
        pessoas.stream().forEach(pessoa -> System.out.print(pessoa.getNome() + " "));
        System.out.println();

        /*
         * collect(): possibilita coletar os elementos de uma stream na forma de coleções,
         * convertendo uma stream para os tipos List, Set ou Map
         */
        List<Pessoa> tCollect = pessoas.stream().collect(Collectors.toList());
        System.out.println("collect: " + tCollect);

        String tCollectJoining = pessoas.stream()
                .map(Pessoa::getNome)
                .collect(Collectors.joining(" / "));
        System.out.println("collect joining: " + tCollectJoining);

        /*
         * average(): permite calcular a média dos valores dos elementos
         *
         * Este é um dos métodos aritméticos disponibilizados para tipos primitivos como IntStream,
         * DoubleStream e LongStream. Podem ser mapeados pelos métodos mapToInt(), mapToDouble() e
         * mapToLong() respectivamente. O método getAsDouble() é utilizada porque average() retorna
         * um um Optional
         */
        double tAverage = pessoas.stream()
                .filter(pessoa -> pessoa.getNacionalidade().equals("Brasil"))
                .mapToInt(pessoa -> pessoa.getIdade())
                .average()
                .getAsDouble();
        System.out.println("average: " + tAverage);

        /*
         * count(): retorna a quantidade de elementos presentes em uma stream.
         */
        long tCount = pessoas.stream().count();
        System.out.println("count: " + tCount);

        /*
         * allMatch(): verifica se todos os elementos de uma stream atendem a um critério passado
         * como parâmetro, através de um Predicate, e retorna um valor booleano.
         */
        boolean tAllMatch = pessoas.stream()
                .allMatch(pessoa -> pessoa.getNacionalidade().equals("Brasil"));
        System.out.println("allMatch: " + tAllMatch);

        /*
         * ifPresent(): se algum valor está presente. Pode ser usado juntamente com filter() mais
         * findAny() ou findFirst() que retornam um objeto Optional.
         */
        pessoas.stream()
                .filter(pessoa -> pessoa.nacionalidade.equals("Mexico"))
                .findAny()
                .ifPresent(p -> System.out.println("ifPresent: " + p.getNome()));
    }
}

// Saida
// > forEach: Lucas Hernandez Jim Amanda Felipe
// > collect: [Lucas, Hernandez, Jim, Amanda, Felipe]
// > collect joining: Lucas / Hernandez / Jim / Amanda / Felipe
// > average: 19.0
// > count: 5
// > allMatch: false
// > ifPresent: Hernandez
```
