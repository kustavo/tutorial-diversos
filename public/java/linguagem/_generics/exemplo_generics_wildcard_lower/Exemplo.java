package _generics.exemplo_generics_wildcard_lower;

import java.util.ArrayList;
import java.util.List;

class SerVivo { }

class Animal extends SerVivo {

    String getEspecies() { return "..."; }
}

class Cao extends Animal { }

class Exemplo {

    /*
     * O limite é inferior, Animal é a classe mais baixa permitida.
     */
    static void print(List<? super Animal> objs) {
        for (Animal obj : (List<Animal>) objs) {
            System.out.println(obj.getEspecies());
        }
    }

    public static void main(String[] args) {

        print(new ArrayList<Animal>());
        print(new ArrayList<SerVivo>());

        /*
         * Cao é uma classe inferior a Animal
         */
        // print(new ArrayList<Cao>()); // ERRO!
    }
}