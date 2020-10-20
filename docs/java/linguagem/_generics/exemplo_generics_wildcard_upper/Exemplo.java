package _generics.exemplo_generics_wildcard_upper;

import java.util.ArrayList;
import java.util.List;

class SerVivo { }

class Animal extends SerVivo {

    String getEspecies() { return "..."; }
}

class Cao extends Animal { }

class Exemplo {

    /*
     * O limite é superior, Animal é a classe mais alta permitida.
     */
    static void print(List<? extends Animal > objs) {
        for (Animal obj : objs) {
            System.out.println(obj.getEspecies());
        }
    }

    public static void main(String[] args) {

        print(new ArrayList<Animal>());
        print(new ArrayList<Cao>());

        /*
         * SerVivo é uma classe superior a Animal
         */
        // print(new ArrayList<SerVivo>()); // ERRO!

    }
}