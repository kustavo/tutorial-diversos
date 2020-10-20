package _lambda.exemplo1_lambda;

public class Exemplo {

    static int calcular(OperacaoMatematica om, int num1, int num2) {
        return om.executar(num1, num2);
    }

    public static void main(String[] args) {
        System.out.println("20 + 15 = " + calcular((a,b) -> a + b, 20, 15));
        System.out.println("20 - 15 = " + calcular((a,b) -> a - b, 20, 15));
    }
}

@FunctionalInterface
interface OperacaoMatematica {

    int executar(int a, int b);

    /*
     * A expressão lambda funciona se a interface possui apenas um método abstrato.
     */
    // int imprimir (int a, int b); // ERRO!
}

// Saida
// > 20 + 15 = 35
// > 20 - 15 = 5