package _lambda.exemplo_sem_lambda;

public class Exemplo {

    public static int calcular(OperacaoMatematica om, int num1, int num2) {

        return om.executar(num1, num2);
    }

    public static void main(String[] args) {

        System.out.println("20 + 15 = " + calcular(new Soma(), 20, 15));
        System.out.println("20 - 15 = " + calcular(new Subtracao(), 20, 15));
    }
}

class Soma implements OperacaoMatematica {

    @Override
    public int executar(int a, int b) {
        return a + b;
    }
}

class Subtracao implements OperacaoMatematica {

    @Override
    public int executar(int a, int b) {
        return a - b;
    }
}

interface OperacaoMatematica {

    public int executar(int a, int b);
}

// Saida:
// > 20 + 15 = 35
// > 20 + 15 = 5