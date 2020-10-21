package _colecoes.exemplo_iteracao_for;

import java.util.*;

class Exemplo {

    public static void main(String[] args) {

        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.addAll(Arrays.asList("A", "B", "C"));

        for (int i = 0; i < arrayList.size(); i++) {
            System.out.print(arrayList.get(i) + " ");
        }
    }
}

// Saida:
// > A B C