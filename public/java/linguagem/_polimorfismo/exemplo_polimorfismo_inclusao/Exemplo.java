package _polimorfismo.exemplo_polimorfismo_inclusao;

abstract class Operacao {

    abstract double calcular(double x, double y);
}

class Soma extends Operacao {

    double calcular(double x, double y) { return x + y; }

    void apresentar() { System.out.println("Função soma"); }
}

class Subtracao extends Operacao {

    double calcular(double x, double y) { return x - y; }

    void apresentar() { System.out.println("Função subtração"); }
}

public class Exemplo {

    /*
     * O Objeto do tipo Operacao, poderá ser tanto de Soma quanto de Subtracao
     */

    public static void mostrarCalculo(Operacao operacao, double x, double y) {

        System.out.println("O resultado é: " + operacao.calcular(x, y));

        /*
         * Somente métodos presentes em Operacao poderão ser chamados
         */
        // operacao.apresentar(); // ERRO!
    }

    public static void main(String[] args) {

        Exemplo.mostrarCalculo(new Soma(), 5, 5);      // 10
        Exemplo.mostrarCalculo(new Subtracao(), 5, 5); // 0
    }
}

// Saida
// > O resultado é: 10.0
// > O resultado é: 0.0