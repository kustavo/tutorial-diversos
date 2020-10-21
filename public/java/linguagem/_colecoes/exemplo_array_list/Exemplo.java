package _colecoes.exemplo_array_list;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

class Exemplo {

    public static void main(String[] args) {

        ArrayList<String> list = new ArrayList<String>();

        list.addAll(Arrays.asList("A", "B", "D")); // adicionar vários
        list.add(2, "C");            // adicionar na posição
        list.add("E");                             // adicionar no final

        System.out.println(list.contains("C"));   // se existe o objeto
        System.out.println(list.indexOf("C"));    // posição do objeto
        System.out.println(list.get(2));          // obter objeto na posição

        list.remove(3);                     // remover na posição

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