package _colecoes.exemplo_priority_queue;

import java.util.Arrays;
import java.util.PriorityQueue;

class Exemplo {

    public static void main(String[] args) {

        PriorityQueue<String> fila = new PriorityQueue<>();

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