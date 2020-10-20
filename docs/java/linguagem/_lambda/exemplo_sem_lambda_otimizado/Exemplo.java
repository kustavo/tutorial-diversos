package _lambda.exemplo_sem_lambda_otimizado;

public class Exemplo {

    static int calcular(OperacaoMatematica om, int num1, int num2) {

        return om.executar(num1, num2);
    }

    public static void main(String[] args) {

        System.out.println("20 + 15 = " + calcular(new OperacaoMatematica() {
            public int executar(int a, int b) {
                return a + b;
            }
        },  20, 15));

        System.out.println("20 + 15 = " + calcular(new OperacaoMatematica() {
            public int executar(int a, int b) {
                return a - b;
            }
        }, 20, 15));
    }
}

interface OperacaoMatematica {

    int executar(int a, int b);
}