package _colecoes.exemplo_linked_list;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;

class Exemplo {

    public static void main(String[] args) {

        /*
         * Além dos métodos do ArrayList, LinkedList possui outros métodos
         */
        LinkedList<String> list = new LinkedList<>();

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