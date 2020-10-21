package _generics.exemplo_generics_interface;

interface Generics<T, V> {

    T metodoA(V valor);
}

class Exemplo implements Generics<String, Integer> {

    /*
     * Implementando o método conforme informado no Generics
     */
    public String metodoA(Integer valor) {

        return Integer.toString(valor);
    }

    public static void main(String[] args) {

        Exemplo t = new Exemplo();

        System.out.println(t.metodoA(1));
    }
}

// Saida
// > 1