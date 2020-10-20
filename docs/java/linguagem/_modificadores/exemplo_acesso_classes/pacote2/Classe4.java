package _modificadores.exemplo_acesso_classes.pacote2;

import _modificadores.exemplo_acesso_classes.pacote1.Classe2;

public class Classe4 extends Classe2 {

    public static void main(String[] args) {

        Classe2 c2 = new Classe2();
        Classe4 c4 = new Classe4();

        // System.out.println("Campo private C2: " + c2.privateC1); // ERRO!
        // System.out.println("Campo private C4: " + c4.privateC1); // ERRO!

        // System.out.println("Campo default C2: " + c2.defaultC1); // ERRO!
        // System.out.println("Campo default C4: " + c4.defaultC1); // ERRO!

        // System.out.println("Campo protected C2: " + c2.protectedC1); // ERRO!
        System.out.println("Campo protected C4: " + c4.protectedC1);

        System.out.println("Campo public C2: " + c2.publicC1);
        System.out.println("Campo public C4: " + c4.publicC1);

    }
}

// Saida
// > Campo protected C4: protected
// > Campo public C2: public
// > Campo public C4: public
