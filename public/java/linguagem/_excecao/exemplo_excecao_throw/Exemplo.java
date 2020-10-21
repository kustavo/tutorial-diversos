package _excecao.exemplo_excecao_throw;

import java.io.IOException;

class Excecao {

    void metodoA() throws Exception {

        /*
         * Covariantes. Exception é superclasse de IOException.
         */
        throw new IOException();
    }

    void metodoB() throws Error {

        /*
         * Não Covariantes. RuntimeException não é subclasse de Error. Entretanto é uma
         * exceção NÃO checada.
         */
        throw new RuntimeException(); // ERRO!
    }

    void metodoC() throws IOException {

        /*
         * Não Covariantes. Exception não é subclasse de IOException e sim superclasse.
         * É uma exceção checada.
         */
        // throw new Exception(); // ERRO!
    }

}

class Exemplo {

    public static void main(String[] args) throws Exception {

        Excecao ex = new Excecao();

        ex.metodoA();
        ex.metodoB();
        ex.metodoC();
    }
}
