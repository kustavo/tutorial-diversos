package _lambda.exemplo_referencia_metodo_instancia_anonima;

public class Exemplo {

    public static void main(String[] args) {

        // Referências de método
        Falante falanteR = Mensageiro::new;

        // Lambda
        Falante falanteL = (s) -> new Mensageiro(s);

        falanteR.dizer("Hello!");
        falanteL.dizer("Hello!");
    }
}

class Mensageiro {

    Mensageiro(String s) {
        System.out.println(s);
    }
}

interface Falante {
    void dizer(String s);
}

// Saida:
// > Hello!
// > Hello!