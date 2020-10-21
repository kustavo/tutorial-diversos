package _stream.exemplo_manipulacao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Pessoa {
    String nome;
    String nacionalidade;
    int idade;

    Pessoa(){}

    Pessoa (String nome, String nacionalidade, int idade) {
        this.nome = nome;
        this.nacionalidade = nacionalidade;
        this.idade = idade;
    }

    List<Pessoa> populaPessoas(){
        Pessoa pessoa1 = new Pessoa("Lucas", "Brasil", 18);
        Pessoa pessoa2 = new Pessoa("Hernandez", "Mexico", 21);
        Pessoa pessoa3 = new Pessoa("Jim","Canada", 22);
        Pessoa pessoa4 = new Pessoa("Amanda", "Brasil", 17);
        Pessoa pessoa5 = new Pessoa("Felipe", "Brasil", 22);

        List<Pessoa> list = new ArrayList<>();
        list.addAll(Arrays.asList(pessoa1, pessoa2, pessoa3, pessoa4, pessoa5));

        return list;
    }

    @Override
    public String toString() {
        return this.nome;
    }

    @Override
    public boolean equals(Object obj) {
        return ((Pessoa) obj).getNacionalidade().equals(this.getNacionalidade());
    }

    @Override
    public int hashCode() {
        return this.getNacionalidade().hashCode();
    }

    String getNome() { return this.nome; }

    String getNacionalidade() { return this.nacionalidade; }

    int getIdade() { return this.idade; }
}