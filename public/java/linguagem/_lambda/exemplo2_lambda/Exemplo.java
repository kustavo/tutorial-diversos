package _lambda.exemplo2_lambda;

public class Exemplo {

    public static void main(String[] args) {

        /*
         * Criação de uma classe anônima se lambda
         */
        Validador semLambda = new Validador() {
            public boolean isPositivo(int valor) {
                return valor > 0;
            }
        };

        /*
         * Criação de uma classe anônima com lambda
         */
        Validador comLambda = (v) -> v > 0;

        System.out.println(semLambda.isPositivo(-10));
        System.out.println(comLambda.isPositivo(-10));
    }
}

interface Validador {
    boolean isPositivo(int valor);
}

// Saida:
// > false
// > false