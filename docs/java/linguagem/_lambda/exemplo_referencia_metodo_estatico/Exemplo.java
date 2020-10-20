package _lambda.exemplo_referencia_metodo_estatico;

public class Exemplo {

    static void dizerAlgo(){
        System.out.println("Hello!");
    }

    public static void main(String[] args) {

        // Referências de método
        Falante falanteR = Exemplo::dizerAlgo;

        // Lambda
        Falante falanteL = () -> Exemplo.dizerAlgo();

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