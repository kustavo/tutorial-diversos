package _generics.exemplo_generics_wildcard_motivacao;

import java.util.ArrayList;
import java.util.List;

class Animal {

    String getEspecies() { return getClass().toString(); }
}

class Cao extends Animal { }

class Gato extends Animal { }

class Exemplo {

    /*
     * Exemplo usando parâmetro de tipo genérico
     */
    static <T extends Animal> void print(List<T> objs) {
        for (T obj : objs) {
            System.out.println(obj.getEspecies());
        }
    }

    /*
     * Exemplo usando wildcard
     */
    static void printWC(List<? extends Animal> objs) {
        for (Animal obj : objs) {
            System.out.println(obj.getEspecies());
        }
    }

    public static void main(String[] args) {

        /*
         * Sem wildcard, não é possível converter superclasse <-> subclasse
         */

        List<Animal> listaAnimal = new ArrayList<Animal>();
        List<Cao> listaCao = new ArrayList<Cao>();
        List<Gato> listaGato = new ArrayList<Gato>();

        // listaAnimal = new ArrayList<Cao>(); // ERRO!
        // listaCao = new ArrayList<Animal>(); // ERRO!
        // listaGato = new ArrayList<Cao>();   // ERRO!


        /*
         * Com wildcard, List<? extends Animal> pode receber atribuição de:
         * List<Animal>, List<Cat>, List<Dog>
         */

        List<? extends Animal> listaBaseAnimal;

        listaBaseAnimal = new ArrayList<Animal>();
        listaBaseAnimal = new ArrayList<Gato>();
        listaBaseAnimal = new ArrayList<Cao>();
    }
}