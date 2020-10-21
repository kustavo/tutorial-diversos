package _classe.exemplo_aninhada_interna;

class Externa {

    private String attrExternoPrv = "campo externo privado";
    private String attrExternoPub = "campo externo publico";

    class InternaA {

        /*
         * Classes aninhadas não podem ter campos ou métodos estáticos.
         */
        // static String atr = "valor"; // ERRO!

        String attrInternoA = "campo interno A";

        void imprime() {

            System.out.println("método da classe aninhada A");
            System.out.println(attrExternoPrv);
            System.out.println(attrExternoPub);
        }
    }

    class InternaB {

        void imprime() {

            System.out.println("método da classe aninhada B");
            /*
             * Necessário uma instância da Classe InternaA
             */
            System.out.println(new InternaA().attrInternoA);
        }
    }
}

class Exemplo {

    public static void main(String[] args) {

        /*
         * Necessário uma instância da Classe Externa
         */
        // Externa.InternaA internaA = new Externa.InternaA(); // ERRO!
        Externa externa = new Externa();
        Externa.InternaA internaA = externa.new InternaA();
        internaA.imprime();

        Externa.InternaB internaB = externa.new InternaB();
        internaB.imprime();

    }
}

// Saida:
// > método da classe aninhada A
// > campo externo privado
// > campo externo publico
// > método da classe aninhada B
// > campo interno A
