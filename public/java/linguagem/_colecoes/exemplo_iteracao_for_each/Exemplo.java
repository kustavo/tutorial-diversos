package _colecoes.exemplo_iteracao_for_each;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

class Exemplo {

    public static void main(String[] args) {

        /*
         * Classes que implementam Collection
         */
        HashSet<String> hashSet = new HashSet<>();
        hashSet.addAll(Arrays.asList("A", "B", "C"));

        hashSet.forEach(name -> System.out.print(name + " "));


        /*
         * Classes que implementam Map
         */
        HashMap<Integer, String> hashMap = new HashMap<>();
        hashMap.put(100, "A");
        hashMap.put(101, "B");
        hashMap.put(102, "C");

        System.out.println();
        hashMap.forEach((key, value) -> System.out.print(key + "=" + value + " "));

    }
}

// Saida:
// > A B C
// > 100=A 101=B 102=C