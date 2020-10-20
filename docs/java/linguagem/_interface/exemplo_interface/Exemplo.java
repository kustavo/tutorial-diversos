package _interface.exemplo_interface;

interface Interface {

    /*
     * Os métodos aceitam somente o modificador de acesso public ou default.
     * Entretanto, em uma interface, todos os métodos são public, e as variáveis são
     * public e static, mesmo quando não declarados.
     */

    public abstract boolean feliz(); // public abstract são desnecessários

    String hello = "Variável estática";

    String dizer(String msg);

    /*
     * A partir do Java 8, é possível criar métodos com corpo padrão, caso não seja
     * implementado.
     */

    default void cantar(String msg) { // public implícito

        System.out.println(msg);
    }

    /*
     * Métodos estáticos sempre possuem corpos e NÃO são abstratos, ou seja, não
     * obriga a sua implatação.
     */
    static void estatico(String msg) {

        System.out.println(msg);
    }
}

class Exemplo implements Interface {

    /*
     * Todos métodos implementados devem usar o modificador de acesso public
     */

    public boolean feliz() {

        return true;
    }

    public String dizer(String msg) {

        return msg;
    }

    /*
     * Não é obrigatório declarar métodos default, pois possuem um corpo padrão.
     */
    // public void cantar(String msg) {}

    public static void main(String[] args) {

        /*
         * As variáveis são sempre estáticas
         */
        System.out.println(Interface.hello);

        Interface.estatico("Método estático");

        /*
         * Depois que uma classe Java implementa uma interface Java, é possível usar uma
         * instância dessa classe como uma instância dessa interface.
         */
        Interface minhaInterface = new Exemplo();
        String dito = minhaInterface.dizer("Implementado");
        System.out.println(dito);

    }
}

// Saida:
// > Variável estática
// > Método estático
// > Implementado