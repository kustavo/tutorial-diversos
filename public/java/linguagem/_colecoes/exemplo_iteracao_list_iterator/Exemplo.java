package _colecoes.exemplo_iteracao_list_iterator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.ListIterator;

class Exemplo {

    public static void main(String[] args) {

        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.addAll(Arrays.asList("A", "B", "C"));

        ListIterator<String> listIterator = arrayList.listIterator();

        while (listIterator.hasNext()) {

            System.out.print(listIterator.next() + " ");
        }

        /*
         * hasPrevious(): retorna true se há mais elementos anteriores na iteração
         * previous(): retorna o elemento anterior da iteração
         */
        System.out.println();
        while (listIterator.hasPrevious()) {

            System.out.print(listIterator.previous() + " ");
        }
    }
}

// Saida:
// > A B C
// > C B A