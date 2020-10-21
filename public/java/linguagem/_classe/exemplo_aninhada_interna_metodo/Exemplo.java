package _classe.exemplo_aninhada_interna_metodo;

class Externa {

    String attrExterno = "Campo externo";

    void metodoExterno() {

        class Interna {

            void metodoInterno() {
                System.out.println("método da classe interna ao método");
                System.out.println(attrExterno);
            }
        }

        Interna interna = new Interna();
        interna.metodoInterno();
    }
}

class Exemplo {

    public static void main(String[] args) {

        Externa externa = new Externa();
        externa.metodoExterno();

        /*
         * Não possui acesso fora do método
         */
        // Externa.Interna = externa.new Interna(); //ERRO!

    }
}

// Saida:
// > método da classe interna ao método
// > Campo externo
