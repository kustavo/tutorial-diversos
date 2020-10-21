package _colecoes.exemplo_iteracao_iterator;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;

class Exemplo {

    public static void main(String[] args) {

        HashSet<String> hashSet = new HashSet<>();
        hashSet.addAll(Arrays.asList("A", "B", "C"));

        Iterator<String> iterator = hashSet.iterator();

        /*
         * hasNext(): retorna true se há mais elementos na iteração
         * next(): retorna o próximo elemento da iteração
         * remove(): remove o atual elemento do iteração
         */
        while (iterator.hasNext()) {

            String valor = iterator.next();
            System.out.print(valor + " ");

            if (valor.equals("B")) {
                iterator.remove();
            }
        }

        iterator = hashSet.iterator(); // reiniciando a iteração
        System.out.println();

        while (iterator.hasNext()) {

            System.out.print(iterator.next() + " ");
        }
    }
}

// Saida:
// > A B C
// > A C