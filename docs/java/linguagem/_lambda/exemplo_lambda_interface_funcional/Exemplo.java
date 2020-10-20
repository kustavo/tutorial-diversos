package _lambda.exemplo_lambda_interface_funcional;

import java.util.function.BiFunction;

public class Exemplo {

    public static int calcular(BiFunction<Integer, Integer, Integer> bf, int num1, int num2) {
        return bf.apply(num1, num2);
    }

    public static void main(String[] args) {
        System.out.println("20 + 15 = " + calcular((a, b) -> a + b, 20, 15));
        System.out.println("20 - 15 = " + calcular((a, b) -> a - b, 20, 15));
    }
}

// Saida
// > 20 + 15 = 35
// > 20 - 15 = 5