package _reflexao.exemplo1_reflexao;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Exemplo {

    public static void main(String[] args) {

        /*
         * O método getClass() da classe Object retorna a classe de um determinado
         * objeto em tempo de execução.
         */
        Class c1 = "minha string".getClass();
        Class c2 = boolean.class;
        Class c3 = java.io.PrintStream.class;

        System.out.println(c1 + " | " + c2 + " |" + c3);

        Class<Pessoa> classe = Pessoa.class;
        for (Field campo : classe.getDeclaredFields()) {
            System.out.println(campo.getName());
        }

        for (Method metodo : classe.getDeclaredMethods()) {
            System.out.println(metodo.getName());
        }
    }
}

class Pessoa {

    String nome;
    int idade;

    Pessoa() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }
}

// Saida
// > class java.lang.String | boolean |class java.io.PrintStream
// > nome
// > idade
// > setNome
// > getIdade
// > setIdade
// > getNome