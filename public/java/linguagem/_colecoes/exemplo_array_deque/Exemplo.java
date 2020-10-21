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