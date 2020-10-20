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