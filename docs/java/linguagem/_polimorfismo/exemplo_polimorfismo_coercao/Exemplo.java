package _polimorfismo.exemplo_polimorfismo_coercao;

class Polimorfismo {

    void metodo() {
        System.out.println("pai");
    }
}

class Exemplo extends Polimorfismo {

    String campo;

    Exemplo(String campo) {

        this.campo = campo;
    }

    void metodo() {
        System.out.println("filho");
    }

    void apresentar() {
        System.out.println("olá");
    }

    public static void main(String[] args) {

        /*
         * Coerção implícita
         */

        /*
         * O objeto p é uma instância de Exemplo mas somente com os métodos e campos
         * presente em Polimorfismo. Portanto não há como chamar o método apresentar de
         * Exemplo.
         */
        Polimorfismo p = new Exemplo("campo");
        p.metodo();
        // p.apresentar(); // ERRO!
        // p.campo; // ERRO!

        /*
         * Coerção explícita
         */

        /*
         * Após a coerção do objeto p novamente para uma instâmcia de Exemplo, o campo
         * pode ser acessado novamente. O valor dos campos não foi perdido nesse
         * processo.
         */
        Exemplo t = (Exemplo) p;
        System.out.println(t.campo);

        /*
         * Tipos primitivos e Strings
         */
        int i = (int) 10.2;
        String s = Double.toString(10.2); // ou String.valueOf(10.2);
    }
}

// Saida:
// > filho
// > campo