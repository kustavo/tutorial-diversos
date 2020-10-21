package _polimorfismo.exemplo_polimorfismo_sobrecarga;

class Polimorfismo {

    void metodo(int parA, int parB) {
        System.out.println("int, int");
    }
}

class Exemplo extends Polimorfismo {


    void metodo(String parA, String parB) {
        System.out.println("String, String");
    }

    void metodo(String parA, int parB) {
        System.out.println("String, int");
    }

    void metodo(String parA) {
        System.out.println("String");
    }

    public static void main(String[] args) {

        Exemplo t = new Exemplo();
        t.metodo("teste", "teste");
        t.metodo("teste", 10);
        t.metodo("teste");

        /*
         * Chamando o método da superclasse
         */
        t.metodo(10, 10);
    }
}

// Saida
// > String, String
// > String, int
// > String
// > int, int