package _excecao.exemplo_excecao_throws;

class Exemplo {

    void metodoA() throws Throwable {
        System.out.println("throws Throwable");
    }

    void metodoB() throws Exception {
        System.out.println("throws Exception");
    }

    /*
     * O metodoC deve utilizar "throws Throwable" para passar a resolução das
     * exceções de metodoA e metodoB para acima da pilha. Utilizar
     * "throws Throwable, Exception" não é necessário já que Exception é subclasse
     * de Throwable. Outra solução seria não utilizar "throws Throwable" mas ser
     * obrigado a tratar a exceção.
     */
    void metodoC() throws Throwable {

        metodoA();
        metodoB();
    }

    public static void main(String[] args) throws Throwable {

        Exemplo ex = new Exemplo();

        ex.metodoA();
        ex.metodoB();
        ex.metodoC();

    }
}