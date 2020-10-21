package _colecoes.exemplo_hash_set;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;

class Exemplo {

    public static void main(String[] args) {

        HashSet<String> set = new HashSet<>();

        set.addAll(Arrays.asList("A", "B", "C", "D")); // adicionar vários

        /*
         * Conjuntos não permitem valores duplicados, portanto não será
         * adicionado.
         */
        set.add("B");

        /*
         * Remove objeto
         */
        set.remove("C");

        System.out.println(set.size()); // número de elementos no conjunto

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
// > 3

// > A - 65
// > B - 66
// > D - 68