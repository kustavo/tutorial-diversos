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