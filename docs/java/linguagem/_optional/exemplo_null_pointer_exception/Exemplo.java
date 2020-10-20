package _optional.exemplo_null_pointer_exception;

import java.util.ArrayList;

class Exemplo {

    public static void main(String[] args) {

        ArrayList<Cidade> cidades = new ArrayList<>();

        Pais p1 = new Pais("Brasil");
        Estado e1 = new Estado(p1);
        Cidade c1 = new Cidade(e1);

        Estado e2 =  new Estado();
        Cidade c2 = new Cidade(e2);

        cidades.add(c1);
        cidades.add(c2);

        for (Cidade c : cidades) {

            if (c.getEstado() != null) {

                Estado estado = c.getEstado();

                /*
                 * A segunda cidade não tem o país, portanto irá retornar
                 * NullPointerException. Portanto a solução é verificar todos objetos
                 * aninhados até o país.
                 */
                // System.out.println(estado.getPais().getNome()); // ERRO!

                if (estado.getPais() != null) {

                    Pais pais = estado.getPais();

                    System.out.println(pais.getNome());

                } else {

                    System.out.println("N/A");
                }
            }
        }
    }
}

class Cidade {

    Estado estado;

    Cidade(Estado estado) { this.estado = estado; }
    Estado getEstado() { return this.estado; }
}

class Estado {

    Pais pais;

    Estado() {}
    Estado(Pais pais) { this.pais = pais; }
    Pais getPais() { return this.pais; }
}

class Pais {

    String nome;

    Pais(String nome) { this.nome = nome; }
    String getNome() { return this.nome; }
}

// Saida:
// > Brasil
// > N/A