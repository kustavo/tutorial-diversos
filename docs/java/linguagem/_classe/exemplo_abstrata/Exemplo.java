package _classe.exemplo_abstrata;

abstract class Abstrata {

    String funcaoNaoAbstrata() {
        return "método não abstrato";
    };

    abstract String funcaoAbstrata();
}

class Exemplo extends Abstrata {

    /*
     * Obrigado a implementar os métodos abstratos da classe pai
     */
    String funcaoAbstrata() {
        return "método abstrato implementado";
    }

    public static void main(String[] args) {

        Exemplo t = new Exemplo();
        System.out.println(t.funcaoAbstrata());
        System.out.println(t.funcaoNaoAbstrata());

        /*
         * Não é possível instanciar uma classe abstrata
         */
        // Abstrata a = new Abstrata(); // ERRO!
    }
}

// Saida:
// > método abstrato implementado
// > método não abstrato