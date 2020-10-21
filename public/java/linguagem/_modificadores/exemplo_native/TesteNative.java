package _modificadores.exemplo_native;

public class TesteNative {

    /*
     * O método será provido por um código nativo
     */
    private native void imprimir();

    public static void main(String args[]) {

        new TesteNative().imprimir();
    }

    static {
        System.loadLibrary("TesteNative");
    }
}