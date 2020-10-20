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

        System.out.println(list.empty());     // verifica se lista vazia

        list.push("A");                  // adiciona elemento no inicio

        System.out.println(list.peek());      // obtém primeiro elemento

        list.pop();                           // remove primeiro elemento

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