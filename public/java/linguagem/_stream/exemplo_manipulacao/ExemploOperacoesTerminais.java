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