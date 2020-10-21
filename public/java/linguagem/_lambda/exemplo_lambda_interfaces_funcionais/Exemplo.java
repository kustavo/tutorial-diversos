package _lambda.exemplo_lambda_interfaces_funcionais;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class Exemplo {

    public static void main(String[] args) {

        ArrayList<String> lista = new ArrayList<>();

        lista.addAll(Arrays.asList("HAA!", "HAAAAA!", "HA!"));

        /*
         * Sem o uso de lambda
         */
        Collections.sort(lista, new Comparator<String>(){
            public int compare(String valor1, String valor2){
                return valor1.length() - valor2.length();
            }
        });

        /*
         * Com o uso de lambda
         */
        Collections.sort(lista, (v1, v2) -> v1.length() - v2.length());

        System.out.println(lista);
    }
}

// Saida:
// > [HA!, HAA!, HAAAAA!]