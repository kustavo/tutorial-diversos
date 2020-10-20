package _modificadores.exemplo_static;

class Static {

    String atrNaoEstatico = "Campo Não estático";
    static String atrEstatico = "Campo estático";

    static void metodoEstatico() {

        System.out.println("Método estático");

        /*
         * Não é necesseráio instância para chamar um campo ou método estático
         */
        System.out.println(atrEstatico);

        /*
         * Um método estático não pode acessar uma variável de instância, já que o
         * método pertence à classe. Somente uma instância pode acessar uma variável de
         * instância.
         */
        // System.out.println(atrNaoEstatico); // ERRO!
        Static i = new Static();
        System.out.println(i.atrNaoEstatico);
    }

    void metodoNaoEstatico() {

        System.out.println("Método Não estático");

        /*
         * Um método não estático pode acessar uma variável de instância, já que o
         * método pertence à instância.
         */
        System.out.println(atrEstatico);
        System.out.println(atrNaoEstatico);
    }

    public static void main(String[] args) {

        metodoEstatico(); // ou Static.metodoEstatico()

        /*
         * O método main é estático portanto não pode acessar um campo de instância, seu
         * escopo é de classe, portanto também não pode acessar a instância com this.
         */
        // System.out.println(atrNaoEstatico); // ERRO!
        // System.out.println(this.metodoNaoEstatico()); // ERRO!

        Static i = new Static();
        i.metodoNaoEstatico();

    }
}

class Exemplo {

    public static void main(String[] args) {

        /*
         * Chamando um método estático de Static em outra classe
         */
        Static.metodoEstatico();

        Static i = new Static();
        i.metodoNaoEstatico();
    }
}

// Saida:
// > Método estático
// > Campo estático
// > Campo Não estático
// > Método Não estático
// > Campo estático
// > Campo Não estático