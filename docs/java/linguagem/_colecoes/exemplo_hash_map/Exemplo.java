package _colecoes.exemplo_hash_map;

import java.util.HashMap;

class Exemplo {

    public static void main(String[] args) {

        HashMap<Integer,String> map = new HashMap<>();

        map.put(100,"A"); // adiciona o par chave-valor
        map.put(101,"B");
        map.put(102,"C");
        map.put(100,"X"); // sobrescreve o valor correspondente a chave

        map.putIfAbsent(100, "Z"); // adiciona somente se chave não existe

        System.out.println(map.get(100));   // obtém o valor correspondente a chave

        /*
         * Substitui o valor correspondente a chave
         */
        map.replace(100, "A");

        /*
         * Substitui o valor correspondente a chave somente se o valor corresponde
         */
        map.replace(100, "W", "Z");

        System.out.println(map.containsValue("C")); // se possui o valor
        System.out.println(map.containsKey(102));   // se possui a chave

        System.out.println(map.entrySet()); // retorna a visão do mapa
        System.out.println(map.keySet());   // retorna a visão das chaves

        System.out.println(map.size());     // retorna a quantidade de elementos
    }
}

// Saida:
// > X
// > true
// > true
// > [100=A, 101=B, 102=C]
// > [100, 101, 102]
// > 3