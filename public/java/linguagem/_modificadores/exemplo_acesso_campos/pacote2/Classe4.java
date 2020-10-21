package _modificadores.exemplo_acesso_campos.pacote2;

import _modificadores.exemplo_acesso_campos.pacote1.Classe1;

public class Classe4 extends Classe1 {

    public static void main(String[] args) {

        Classe1 c1 = new Classe1();
        Classe4 c4 = new Classe4();

        // System.out.println("Campo private C1: " + c1.privateC1); // ERRO!
        // System.out.println("Campo private C4: " + c4.privateC1); // ERRO!

        // System.out.println("Campo default C1: " + c1.defaultC1); // ERRO!
        // System.out.println("Campo default C4: " + c4.defaultC1); // ERRO!

        /*
         * Modificador protected permite acessado somente através de hierarquia. Ele não
         * estará acessível na subclasse que está fora do pacote usando a instância de
         * alguma superclasse (classe pai).
         */

        // System.out.println("Campo protected C1: " + c1.protectedC1); // ERRO!
        System.out.println("Campo protected C4: " + c4.protectedC1);

        System.out.println("Campo public C1: " + c1.publicC1);
        System.out.println("Campo public C4: " + c4.publicC1);

        /*
         * A Função da classe C1 tem acesso a todos campos de sua classe
         */
        c4.fncPublicC1();

    }
}

// Saida
// > Campo protected C4: protected
// > Campo public C1: public
// > Campo public C4: public

// > C1 Private: private
// > C1 Default: default
// > C1 Protected: protected
// > C1 Public: public