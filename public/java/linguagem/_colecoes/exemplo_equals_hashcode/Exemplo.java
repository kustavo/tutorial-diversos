package _colecoes.exemplo_equals_hashcode;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Exemplo {

    public static void main(String[] args) {

        List<Livro> livros = new ArrayList<>();

        livros.add(new Livro("Harry Potter", 100.0));
        livros.add(new Livro("Harry Potter", 100.0));
        livros.add(new Livro("Romeu e Julieta", 100.0));
        livros.add(new Livro("Romeu e Julieta", 100.0));
        livros.add(new Livro("Alice", 100.0));
        livros.add(new Livro("Alice", 200.0));
        livros.add(new Livro("Dom Casmurro", 100.0));
        livros.add(new Livro("Dom Casmurro", 200.0));

        Stream<Livro> sDistinct = livros.stream().distinct();
        sDistinct.forEach(System.out::println);
    }
}

class Livro {

    String nome;
    Double preco;

    Livro(){}

    Livro(String nome, Double preco) {
        this.preco = preco;
        this.nome = nome;
    }

    @Override
    public String toString() {
        return this.nome;
    }

    /*
     * Livros com mesmo nome tem o mesmo código hash
     */
    @Override
    public int hashCode() {
        return this.nome.hashCode();
    }

    /*
     * Livros com mesmo nome tem o mesmo código hash, mas somente os que possuem o mesmo
     * preço são considerados iguais.
     */
    @Override
    public boolean equals(Object obj) {
        Livro livro = (Livro) obj;
        return livro.nome.equals(this.nome) && livro.preco.equals(this.preco);
    }
}
