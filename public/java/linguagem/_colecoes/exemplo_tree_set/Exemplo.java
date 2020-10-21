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