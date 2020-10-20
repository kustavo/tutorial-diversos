package _heranca.exemplo_heranca_multipla;

interface Calcular {

    default double calcular() {

        return 10 * 5.5;
    }
}

interface Cantar {

    default void cantar() {

        System.out.println("La la la!");
    }
}

/*
 * Herança multipla por meio de interfaces
 */
class Pessoa implements Calcular, Cantar {
}

class Exemplo {

    public static void main(String[] args) {

        Pessoa pessoa = new Pessoa();
        pessoa.cantar();
        System.out.println(pessoa.calcular());
    }
}

// Saida:
// > La la la!
// > 55.0