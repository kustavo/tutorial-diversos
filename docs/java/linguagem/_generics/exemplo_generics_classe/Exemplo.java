package _generics.exemplo_generics_classe;

import java.util.Arrays;

class Exemplo<T> {

    private Object[] elements = new Object[0];

    public T get(int index) {

        return (T) elements[index];
    }

    public void add(T element) {

        /*
         * Copia o array para o novo tamanho, truncando ou preenchendo com zeros
         */
        elements = Arrays.copyOf(elements, elements.length + 1);

        elements[elements.length - 1] = element;
    }

    public static void main(String[] args) {

        /*
         * Não é necessário repetir os tipos no new, basta usar <>
         */
        Exemplo<String> list = new Exemplo<>();

        /*
         * Espera-se uma string
         */
        // list.add(1); // ERRO!
        list.add("2");

        /*
         * Não há necessidade de cast, pois o compilador sabe que é uma String
         */
        String result = list.get(0);
        System.out.println(result);
    }
}

// Saida
// > 2