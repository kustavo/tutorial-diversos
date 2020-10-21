package _classe.exemplo_aninhada_estatica;

class Externa {

    static String attrEstatico = "campo estático";
    String attrNaoEstatico = "campo NÃO estático";

    static class Estatica {

        void imprime() {
            System.out.println("método da classe aninhada estática");
            System.out.println(attrEstatico);

            /*
             * Uma classe estática não tem acesso aos membros da instância da classe
             * externa. Possui acesso somente aos membros estáticos.
             */
            // System.out.println(attrNaoEstatico); //ERRO!
        }
    }

    class NaoEstatica {

        void imprime() {
            System.out.println("método da classe aninhada NÃO estática");
            System.out.println(attrEstatico);
            System.out.println(attrNaoEstatico);
        }
    }
}

class Exemplo {

    public static void main(String[] args) {

        /*
         * Não é necessário uma instância da Classe Externa
         */
        Externa.Estatica estatica = new Externa.Estatica();
        estatica.imprime();

        /*
         * Necessário uma instância da Classe Externa
         */
        // Externa.NaoEstatica naoEstatica = new Externa.NaoEstatica(); // ERRO!
        Externa.NaoEstatica naoEstatica = new Externa().new NaoEstatica();
        naoEstatica.imprime();

    }
}

// Saida:
// > método da classe aninhada estática
// > campo estático
// > método da classe aninhada NÃO estática
// > campo estático
// > campo NÃO estático