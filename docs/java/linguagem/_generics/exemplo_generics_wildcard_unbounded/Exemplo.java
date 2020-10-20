package _generics.exemplo_generics_wildcard_unbounded;

import java.util.ArrayList;
import java.util.List;

class SerVivo { }

class Animal extends SerVivo {

    String getEspecies() { return "..."; }
}

class Cao extends Animal { }

class Exemplo {

    /*
     * Não se sabe o tipo de objeto, portanto não é possível chamar o método
     * getEspecies() de Animal.
     */
    static void print(List<?> objs) {
        for (Object obj : objs) {
            // System.out.println(obj.getEspecies()); // ERRO!
            System.out.println(obj.toString());
        }
    }

    public static void main(String[] args) {

        print(new ArrayList<Animal>());
        print(new ArrayList<Cao>());
        print(new ArrayList<SerVivo>());
    }
}