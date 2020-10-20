package _heranca.exemplo_heranca_super;

class ClasseAvo {

    ClasseAvo() { System.out.println("Avô"); }

    void diga() { System.out.println("Sou o avô"); }

    void repita() { System.out.println("Sou o avô!!!"); }
}

class ClassePai extends ClasseAvo {

    ClassePai() { System.out.println("Pai"); }

    void diga() { System.out.println("Sou o pai"); }
}

class ClasseFilho extends ClassePai {

    ClasseFilho() { System.out.println("Filho"); }

    ClasseFilho(String msg) { System.out.println(msg); }

    void diga() { System.out.println("Sou o filho"); }
}

class Exemplo extends ClasseFilho {

    /*
     * O super() no construtor não é necessário, mas se for usado deve vir
     * no ínicio do construtor. Não há como chamar dois super(). Se não for
     * especificado qual o construtor da superclasse, será chamado o construtor
     * padrão (sem parâmetros).
     */
    Exemplo() {

        super("Filho malcriado");
        // super(); // ERRO!
        System.out.println("Eu\n");
    }

    /*
     * O super está chamando o método diga() da superclasse
     */
    void diga() {

        System.out.println("Sou eu");
        super.diga();
    }

    public static void main(String[] args) {

        Exemplo t = new Exemplo();

        /*
         * Como o método foi sobrescrito, irá chamar o seu própio
         */
        t.diga();

        /*
         * Através de herança chama o método da ClasseAvo
         */
        t.repita();
    }
}

// Saida
// > Avô
// > Pai
// > Filho malcriado
// > Eu

// > Sou eu
// > Sou o filho
// > Sou o avô!!!