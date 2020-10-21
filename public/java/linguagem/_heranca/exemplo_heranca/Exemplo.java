package _heranca.exemplo_heranca;

class Pessoa {

    String nome;
    int idade;

    Pessoa(String nome, int idade) {

        this.nome = nome;
        this.idade = idade;
    }

    double tirarCopias(int quantidade) {
        return 0.10 * (double) quantidade;
    }

    /*
     * A palavra especial "final" bloqueia sobrescrita do método
     */
    final String estudar(String disciplina) {
        return "Estudando " + disciplina;
    }
}

class Aluno extends Pessoa {

    String matricula;

    Aluno(String nome, int idade) {
        super(nome, idade);
    }

    /*
     * A notação @Override não é obrigatória, mas caso um método esteja anotado, ele
     * necessariamente precisa estar reescrevendo um método da classe mãe.
     */
    @Override
    double tirarCopias(int quantidade) {
        return 0.07 * (double) quantidade;
    }

    /*
     * Se o método abaixo for usado ocorrerá erro, pois o método não pode ser
     * sobrescrito.
     */

    // @Override
    // public final String estudar(String disciplina) {}

    // public String estudar(String disciplina) {}
}

public class Exemplo {

    public static void main(String[] args) {

        Aluno a = new Aluno("Jose Francisco", 32);
        System.out.println("Nome: " + a.nome);
        System.out.println("Idade: " + a.idade);
        System.out.println("Valor copias: R$" + a.tirarCopias(100));
        System.out.println("Estudando: " + a.estudar("Geografia"));
    }
}

// Saida:
// > Nome: Jose Francisco
// > Idade: 32
// > Valor copias: R$7.000000000000001
// > Estudando: Estudando Geografia