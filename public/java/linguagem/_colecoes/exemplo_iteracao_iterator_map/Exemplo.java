package _colecoes.exemplo_iteracao_iterator_map;

import java.util.HashMap;
import java.util.Iterator;

class Exemplo {

    public static void main(String[] args) {

        HashMap<Integer,String> hashMap = new HashMap<>();

        hashMap.put(100,"A");
        hashMap.put(101,"B");
        hashMap.put(102,"C");

        Iterator<Integer> keyIterator = hashMap.keySet().iterator();

        while (keyIterator.hasNext()) {

            Integer chave = keyIterator.next();
            String valor = hashMap.get(chave);
            System.out.println(chave + "=" + valor);
        }

    }
}

// Saida:
// > A B C
// > A C