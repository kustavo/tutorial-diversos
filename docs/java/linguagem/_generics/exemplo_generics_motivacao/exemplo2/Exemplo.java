package _generics.exemplo_generics_motivacao.exemplo2;

import java.util.ArrayList;
import java.util.List;

class Animal { }

class Cao extends Animal { }

class Gato extends Animal { }

class Exemplo {

    static void semGenerics() {

        Gato[] gatos = new Gato[2];
        Animal[] animais = gatos;

        /*
         * Compila pois Cao é um Animal, mas ocorrerá erro em tempo de execução
         */
        // animais[0] = new Cao(); // ERRO!
    }

    static void comGenerics() {

        List<Gato> gatosList = new ArrayList<>();

        /*
         * Erro em tempo de compilação
         */
        // List<Animal> animaisList = gatosList; // ERRO!
    }

    public static void main(String[] args) {

        semGenerics();
        comGenerics();
    }
}