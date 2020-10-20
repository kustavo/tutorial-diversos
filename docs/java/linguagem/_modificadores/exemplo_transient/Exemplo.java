package _modificadores.exemplo_transient;

import java.io.*;

class Exemplo implements Serializable {

    // Campos normais
    int i = 10, j = 20;

    // Campos transient
    transient int k = 30;
    transient static int l = 40; // Uso do transient não há impacto
    transient final int m = 50; // Uso do transient não há impacto

    public static void main(String[] args) throws Exception {

        Exemplo input = new Exemplo();

        // Serialização
        FileOutputStream fos = new FileOutputStream("teste.txt");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(input);

        // Deserialização
        FileInputStream fis = new FileInputStream("teste.txt");
        ObjectInputStream ois = new ObjectInputStream(fis);
        Exemplo output = (Exemplo) ois.readObject();

        System.out.println("i = " + output.i);
        System.out.println("j = " + output.j);
        System.out.println("k = " + output.k);
        System.out.println("l = " + output.l);
        System.out.println("m = " + output.m);
    }
}

// Saida
// > i = 10
// > j = 20
// > k = 0
// > l = 40
// > m = 50