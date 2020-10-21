package _metodo.exemplo_metodo;

class Valor {

    private int valor;

    public Valor(int valor) {
        this.valor = valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public int getValor() {
        return valor;
    }
}

class Exemplo {

    static void metodo(Valor valorObjeto, int valorLocal) {

        valorObjeto.setValor(200);
        valorLocal = 20;

        System.out.println("\nDentro do metodo");
        System.out.println("Valor Objeto: " + valorObjeto.getValor()); // 200
        System.out.println("Valor Local: " + valorLocal);              // 20
    }

    static void metodoMudancaReferencia(Valor valorObjeto, int valorLocal) {

        /* Atribuição por valor */
        valorObjeto = new Valor(300);
        valorLocal = 30;

        System.out.println("\nDentro do metodo");
        System.out.println("Valor Objeto: " + valorObjeto.getValor()); // 300
        System.out.println("Valor Local: " + valorLocal);              // 30
    }

    public static void main(String[] args) {

        Valor valorObjeto = new Valor(100);
        int valorLocal = 10;

        System.out.println("\nAntes do metodo");
        System.out.println("Valor Objeto: " + valorObjeto.getValor()); // 100
        System.out.println("Valor Local: " + valorLocal);              // 10

        metodo(valorObjeto, valorLocal);

        System.out.println("\nDepois do metodo");
        System.out.println("Valor Objeto: " + valorObjeto.getValor()); // 200
        System.out.println("Valor Local: " + valorLocal);              // 10

        metodoMudancaReferencia(valorObjeto, valorLocal);

        System.out.println("\nDepois do metodo");
        System.out.println("Valor Objeto: " + valorObjeto.getValor()); // 200
        System.out.println("Valor Local: " + valorLocal);              // 10
    }
}

// Saida
// > Antes do metodo
// > Valor Objeto: 100
// > Valor Local: 10
//
// > Dentro do metodo
// > Valor Objeto: 200
// > Valor Local: 20
//
// > Depois do metodo
// > Valor Objeto: 200
// > Valor Local: 10
//
// > Dentro do metodo
// > Valor Objeto: 300
// > Valor Local: 30
//
// > Depois do metodo
// > Valor Objeto: 200
// > Valor Local: 10