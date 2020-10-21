package _generics.exemplo_generics_metodo;

class GenericsA {

    @Override
    public String toString() {
        return this.getClass().toString();
    }
}
class  GenericsB extends GenericsA { }

class Exemplo  {

    static <T, K> T converte(K par) {

        return (T) par;
    }

    static <T> void print(T par) {

        System.out.println(par.toString());
    }

    public static void main(String[] args) {

        GenericsA g = converte(new GenericsB());
        print(g);
    }
}

// Saida
// > cclass exemplo_generics_metodo.GenericsB