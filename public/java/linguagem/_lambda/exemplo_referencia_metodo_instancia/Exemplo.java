package _lambda.exemplo_referencia_metodo_instancia;

public class Exemplo {

    void dizerAlgo() {
        System.out.println("Hello!");
    }

    public static void main(String[] args) {

        Exemplo objeto = new Exemplo();

        // Referências de método
        Falante falanteR = objeto::dizerAlgo;

        // Lambda
        Falante falanteL = () -> objeto.dizerAlgo();

        falanteR.dizer();
        falanteL.dizer();
    }
}

interface Falante {
    void dizer();
}

// Saida:
// > Hello!
// > Hello!