package _colecoes.exemplo_iteracao_enhanced_for;

import java.util.*;

class Exemplo {

    public static void main(String[] args) {

        /*
         * Classes que implementam Collection
         */
        HashSet<String> hashSet = new HashSet<>();
        hashSet.addAll(Arrays.asList("A", "B", "C"));

        for (String hs : hashSet) {
            System.out.print(hs + " ");
        }

        /*
         * Classes que implementam Map
         */
        HashMap<Integer, String> hashMap = new HashMap<>();
        hashMap.put(100, "A");
        hashMap.put(101, "B");
        hashMap.put(102, "C");

        System.out.println();
        for (Integer key : hashMap.keySet()) {

            System.out.print(key + "=" + hashMap.get(key) + " ");
        }
    }
}

// Saida:
// > A B C
// > 100=A 101=B 102=C