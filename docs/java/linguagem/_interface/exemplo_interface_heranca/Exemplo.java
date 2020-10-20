package _interface.exemplo_interface_heranca;

interface InterfaceA {

    void pensar();
}

interface InterfaceB extends InterfaceA {

    void dizer();
}

class Exemplo implements InterfaceB {

    public void pensar() {

        System.out.println("...");
    }

    public void dizer() {

        System.out.println("Olá");
    }

    public static void main(String[] args) {

        Exemplo minhaInterface = new Exemplo();
        minhaInterface.pensar();
        minhaInterface.dizer();
    }
}

// Saida:
// > ...
// > Olá