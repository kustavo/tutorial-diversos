package _colecoes.exemplo_iteracao_enumeration;

import java.util.*;

class Exemplo {

    public static void main(String[] args) {

        Vector<String> vector = new Vector<>();
        vector.addAll(Arrays.asList("A", "B", "C"));

        Enumeration e = vector.elements();

        while (e.hasMoreElements()) {

            System.out.print(e.nextElement() + " ");
        }
    }
}

// Saida:
// > A B C