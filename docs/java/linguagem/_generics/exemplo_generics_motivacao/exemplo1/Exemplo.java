package _generics.exemplo_generics_motivacao.exemplo1;

import java.util.ArrayList;
import java.util.List;

public class Exemplo {

    static void semGenerics() {

        List v = new ArrayList();

        v.add("String");

        /*
         * Erro em tempo de execução
         */
        Integer i = (Integer)v.get(0); // ERRO!
    }

    static void comGenerics() {

        List<String> v = new ArrayList<String>();

        v.add("String");

        /*
         * Erro em tempo de compilação
         */
        //Integer i = v.get(0); // ERRO!

    }

    public static void main(String[] args) {

        semGenerics();
        comGenerics();
    }
}