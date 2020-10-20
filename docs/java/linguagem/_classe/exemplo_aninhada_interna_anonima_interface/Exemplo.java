package _classe.exemplo_aninhada_interna_anonima_interface;

interface Evento {

    abstract void clicar();

    abstract void arrastar();
}

class Exemplo {

    static void acao(Evento e) {
        e.clicar();
        e.arrastar();
    }

    public static void main(String[] args) {

        acao(new Evento() {

            public void clicar() {
                System.out.println("clicou");
            }

            public void arrastar() {
                System.out.println("arrastou");
            }
        });
    }

}

// Saida:
// > clicou
// > arrastou