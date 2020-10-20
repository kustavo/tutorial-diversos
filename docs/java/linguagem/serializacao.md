# Serialização

[TOC]

## Introdução

Serialização é a técnica que permite transformar o estado de um objeto em uma sequência *bytes*. Depois que um objeto for serializado ele pode ser gravado (ou persistido) em um arquivo de dados e recuperado do arquivo e desserializado para recriar o objeto na memória.

O processo de serialização de objetos é bastante utilizado em sistemas distribuídos (coleção de computadores independentes conectados por uma rede e equipados com um sistema de software distribuído) e na persistência de dados (manter dados além da duração da execução do programa). Com a transformação do objeto em *bytes* é possível enviar o objeto por uma rede, ou salvá-lo em um arquivo ou em um banco de dados.

As classe envolvidas na serialização deve implementar a interface `Serializable`.

```java
package _serializacao.exemplo_serializacao_desserializacao;

import java.io.*;
import java.util.ArrayList;

public class Exemplo {

    static String path = System.getProperty("user.dir")
            + "/_serializacao/exemplo_serializacao_desserializacao/";

    static void serializar(ArrayList<Object> lista, String nomeArq) {

        try {

            /*
             * Cria o fluxo de saída em arquivo
             */
            FileOutputStream arquivo = new FileOutputStream(path + nomeArq);

            /*
             * Cria o objeto que escreve no fluxo de saída
             */
            ObjectOutputStream objOutput = new ObjectOutputStream(arquivo);

            /*
             * Grava a lista no objeto que escreve no fluxo de saída
             */
            objOutput.writeObject(lista);
            objOutput.close();
            arquivo.close();

        } catch(IOException erro) {
            System.out.printf("Erro: %s", erro.getMessage());
        }
    }

    static ArrayList<Object> desserializar(String nomeArq) {

        ArrayList<Object> lista = new ArrayList();

        try {

            /*
             * Cria o fluxo de entrada a partir do arquivo
             */
            FileInputStream arquivo = new FileInputStream(path + nomeArq);

            /*
             * Cria o objeto que lê o fluxo de entrada
             */
            ObjectInputStream objLeitura = new ObjectInputStream(arquivo);

            lista = (ArrayList<Object>)objLeitura.readObject();

            objLeitura.close();
            arquivo.close();

        } catch(Exception erro) {
            System.out.printf("Erro: %s", erro.getMessage());
        }

        return lista;
    }

    public static void main(String[] args) {

        ArrayList<Object> listaIn = new ArrayList<>();
        listaIn.add(new Pesssoa("Maria", 43, new Endereco("Rua Dois", 12)));
        listaIn.add(new Pesssoa("João", 56, new Endereco("Rua Nove", 19)));

        serializar(listaIn, "saida.txt");

        ArrayList<Object> listaOut = desserializar("saida.txt");

        listaOut.forEach(l -> System.out.println(Pesssoa.class.cast(l)));
    }
}

class Pesssoa implements Serializable {

    String nome;
    int idade;
    Endereco endereco;

    Pesssoa(String nome, int idade, Endereco endereco) {
        this.nome = nome;
        this.idade = idade;
        this.endereco = endereco;
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

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    @Override
    public String toString() {
        return "nome: " + this.getNome() + ", idade: " + this.getIdade()
                + ", enderco: " + this.endereco.getRua() + ", " + this.endereco.getNumero();
    }
}

class Endereco implements Serializable {

    String rua;
    int numero;

    Endereco(String rua, int numero) {
        this.rua = rua;
        this.numero = numero;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }
}

// Saida:
// > nome: Maria, idade: 43, enderco: Rua Dois, 12
// > nome: João, idade: 56, enderco: Rua Nove, 19
```
