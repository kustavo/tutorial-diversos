package _interface.exemplo_interface_multipla;

interface InterfaceA {

    void pensar();

    void dizer(String msg);

    default void cantar() {

        System.out.println("Figaro!");
    }
}

interface InterfaceB {

    void pensar();

    void dizer();

    /*
     * Não pode haver dois métodos default com mesmo nome sendo implementados pela
     * classe Exemplo
     */

    // default void cantar() { System.out.println("La la!"); }
}

class Exemplo implements InterfaceA, InterfaceB {

    public void pensar() {

        System.out.println("...");
    }

    public void dizer() {

        System.out.println("Olá");
    }

    public void dizer(String msg) {

        System.out.println(msg);
    }

    public static void main(String[] args) {

        Exemplo minhaInterface = new Exemplo();
        minhaInterface.pensar();
        minhaInterface.dizer();
        minhaInterface.dizer("Oi");

    }
}

// Saida:
// > ...
// > Olá
// > Oi