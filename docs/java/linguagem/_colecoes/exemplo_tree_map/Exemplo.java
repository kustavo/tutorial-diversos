package _colecoes.exemplo_tree_map;

import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

class Exemplo {

    public static void main(String[] args) {

        TreeMap<Integer,String> map = new TreeMap<>();

        map.put(101,"B");
        map.put(103,"A");
        map.put(102,"C");

        System.out.println(map.entrySet());

        /*
         * Classes que implementam Map não deriva de Collection, portanto não possuem
         * o método iterator().
         */
        Iterator<Integer> keyIterator = map.keySet().iterator();

        while (keyIterator.hasNext()) {

            Integer chave = keyIterator.next();
            String valor = map.get(chave);
            System.out.print(chave + "=" + valor + " ");
        }

        System.out.println("\n" + map.higherKey(101)); // a maior chave na sequencia
        System.out.println(map.lowerKey(103));         // a menor chave na sequencia

        System.out.println(map.firstEntry());          // primeiro par chave-valor
        System.out.println(map.lastEntry());           // último par chave-valor

        System.out.println(map.firstKey());            // primeiro chave
        System.out.println(map.lastKey());             // última chave

        System.out.println(map.containsKey(101));      // se contém a chave
        System.out.println(map.containsValue("C"));    // se contém o valor
    }
}

// Saida:
// > [101=B, 102=C, 103=A]
// > 101=B 102=C 103=A
// > 102
// > 102
// > 101=B
// > 103=A
// > 101
// > 103
// > true
// > true