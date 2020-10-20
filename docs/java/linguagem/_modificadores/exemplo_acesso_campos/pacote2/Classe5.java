package _modificadores.exemplo_acesso_campos.pacote2;

public class Classe5 extends Classe4 {

    public static void main(String[] args) {

        Classe4 c4 = new Classe4();
        Classe5 c5 = new Classe5();

        // System.out.println("Campo private C4: " + c4.privateC1); // ERRO!
        // System.out.println("Campo private C5: " + c5.privateC1); // ERRO!

        // System.out.println("Campo default C4: " + c4.defaultC1); // ERRO!
        // System.out.println("Campo default C5: " + c5.defaultC1); // ERRO!

        /*
         * Modificador protected não estará acessível na subclasse que está fora do
         * pacote usando a instância de alguma superclasse intermediária.
         */

        // System.out.println("Campo protected C4: " + c4.protectedC1); // ERRO!
        System.out.println("Campo protected C5: " + c5.protectedC1);

        System.out.println("Campo public C4: " + c4.publicC1);
        System.out.println("Campo public C5: " + c5.publicC1);
    }
}

// Saida
// > Campo protected C5: protected
// > Campo public C4: public
// > Campo public C5: public