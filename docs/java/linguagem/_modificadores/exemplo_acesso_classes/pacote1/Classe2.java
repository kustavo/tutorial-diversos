package _modificadores.exemplo_acesso_classes.pacote1;

public class Classe2 extends Classe1 {

    public static void main(String[] args) {

        Classe1 c1 = new Classe1();
        // System.out.println("Campo private: " + c1.privateC1); // ERRO!
        System.out.println("Campo default: " + c1.defaultC1);
        System.out.println("Campo protected: " + c1.protectedC1);
        System.out.println("Campo public: " + c1.publicC1);
    }
}

// Saida:
// > Campo default: default
// > Campo protected: protected
// > Campo public: public