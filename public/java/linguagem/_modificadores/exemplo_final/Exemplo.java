package _modificadores.exemplo_final;

class Final {

    final String atrFinal; // Poderia ser atribuído o valor aqui

    Final() {

        this.atrFinal = "Final";

        /*
         * Um campo final não pode ser alterado
         */
        // this.atrFinal = "Novo Final"; // ERRO!
    }

    final void metodoFinal() {

        System.out.println("Método final");
    }

    public static void main(String[] args) {

        Final t = new Final();

        /*
         * Um campo final não pode ser alterado
         */
        // t.atrFinal = "Novo final"; // ERRO!
        System.out.println(t.atrFinal);
        t.metodoFinal();
    }

}

class Exemplo extends Final {

    /*
     * Um método final não pode ser sobrescrito
     */
    // void metodoFinal() { } // ERRO!

    public static void main(String[] args) {

        Final t = new Final();

        /*
         * Um campo final não pode ser alterado
         */
        // t.atrFinal = "Novo final"; // ERRO!
        System.out.println(t.atrFinal);
        t.metodoFinal();
    }
}

// Final:
// > Final
// > Método final