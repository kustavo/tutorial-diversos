package _modificadores.exemplo_acesso_campos.pacote2;

import _modificadores.exemplo_acesso_campos.pacote1.Classe1;

public class Classe3 {

    public static void main(String[] args) {

        Classe1 c1 = new Classe1();
        // System.out.println("Campo private: " + c1.privateC1); // ERRO!
        // System.out.println("Campo default: " + c1.defaultC1); // ERRO!
        // System.out.println("Campo protected: " + c1.protectedC1); // ERRO!
        System.out.println("Campo public: " + c1.publicC1);
    }
}

// Saida
// > Campo public: public