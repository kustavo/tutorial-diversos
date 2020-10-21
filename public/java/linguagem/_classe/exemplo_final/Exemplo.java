package _classe.exemplo_final;

final class Exemplo {

    void funcaoNAbstrata() {

        System.out.println("Função não abstrata");
    }
}

/*
 * Não pode haver herança de uma classe declarada como "final"
 */
// class Teste extends Exemplo { } // ERRO!