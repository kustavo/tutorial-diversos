package _optional.exemplo_optional;

import java.util.ArrayList;
import java.util.Optional;

class Exemplo {

    public static void main(String[] args) {

        ArrayList<Cidade> cidades = new ArrayList<>();

        Pais p1 = new Pais("Brasil");
        Estado e1 = new Estado(p1);
        Cidade c1 = new Cidade(e1);

        Estado e2 = new Estado();
        Cidade c2 = new Cidade(e2);

        cidades.add(c1);
        cidades.add(c2);

        for (Cidade c : cidades) {

            /*
             * map(): aplica o argumento de função ao valor e, em seguida, retorna o
             *      resultado agrupado em um Optional, como ex:
             *      [1,2],[1,3] => [[1,2],[1,3]]
             * flatMap(): aceita um argumento de função que é aplicado a um valor
             *      Optional e retorna o resultado diretamente. Ou seja, "achata" objetos
             *       aninhados como: Optional<Optional<T>> transformado em apenas
             *       Optional<T>, como ex:
             *      [1,2], [1,3] => [1,2,1,3]
             * orElse(): retorna o valor presente se existir, senão retorna o argumento
             */
            System.out.println(c.getEstado().flatMap(Estado::getPais).map(Pais::getNome)
                    .orElse("N/A"));
        }
    }
}

class Cidade {

    Estado estado;

    Cidade(Estado estado) { this.estado = estado; }

    Optional<Estado> getEstado() { return Optional.ofNullable(this.estado); }
}

class Estado {

    Pais pais;

    Estado() { }
    Estado(Pais pais) { this.pais = pais; }

    Optional<Pais> getPais() { return Optional.ofNullable(this.pais); }
}

class Pais {

    String nome;

    Pais(String nome) { this.nome = nome; }

    String getNome() { return this.nome; }
}

// Saida:
// > Brasil
// > N/A