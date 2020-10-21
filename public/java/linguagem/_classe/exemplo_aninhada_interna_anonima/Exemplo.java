package _classe.exemplo_aninhada_interna_anonima;

class Funcionario {

    void trabalhar() { System.out.println("trabalhar"); }
}

class Exemplo {

    Funcionario gerente = new Funcionario() {

        /*
         * Sobrescreve o função trabalhar do funcionário
         */
        void trabalhar() { System.out.println("mandar"); }
    };

    Funcionario peao = new Funcionario() {

        /*
         * Sobrescreve o função trabalhar do funcionário
         */
        void trabalhar() { System.out.println("executar"); }

        /*
         * Método descansar não poderá ser usado, pois não foi definido em Funcionario
         */
        void descansar() { System.out.println("descansar"); }
    };

    public static void main(String[] args) {

        Exemplo a = new Exemplo();
        a.gerente.trabalhar();
        a.peao.trabalhar();

        /*
         * Método descansar não existe na classe Funcionario
         */
        // a.peao.descansar(); // ERRO!
    }
}

// Saida:
// > mandar
// > executar