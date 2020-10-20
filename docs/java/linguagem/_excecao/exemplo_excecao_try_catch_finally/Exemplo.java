package _excecao.exemplo_excecao_try_catch_finally;

class Exemplo {

    static void metodoLancaExcecao() throws Exception {

        throw new Exception();
    }

    /*
     * Obrigatório utilizar "throws Exception" na chamada da função.
     */
    static void metodoRepassaExcecao() throws Exception {

        metodoLancaExcecao();
    }

    /*
     * Como a exceção será tratada pelo método, não é necessário utilizar
     * "throws Exception" na chamada do método.
     */
    static void metodoTrataExcecao() {

        try {

            metodoLancaExcecao();

            /*
             * Não será chamado, pois foi redirecionado para o catch()
             */
            System.out.println("Não será executado");

        } catch (RuntimeException r) {

            System.out.println("RuntimeException: " + r);

        } catch (Exception e) {

            /*
             * Como a exceção foi capturada, o programa pode continuar sua execução a partir
             * do try, e não necessariamente após a instrução que lançou a exceção.
             */
            System.out.println("Exception: " + e);

        } finally {

            System.out.println("Finally");
        }

        System.out.println("Fim método");
    }

    public static void main(String[] args) {

        metodoTrataExcecao();

        System.out.println("Fim programa");
    }
}

// Saida
// > Exceção Exception: java.lang.Exception
// > Finally
// > Fim método
// > Fim programa
