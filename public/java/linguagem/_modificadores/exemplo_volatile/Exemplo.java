package _modificadores.exemplo_volatile;

public class Exemplo {

    private static volatile int valor = 0;

    static class Escuta extends Thread {

        public void run() {

            int captura = valor;

            while (captura < 5) {

                if (captura != valor) {

                    System.out.println("Escutou: " + valor);

                    captura = valor;
                }
            }
        }
    }

    static class Altera extends Thread{

        public void run() {

            while (valor < 5) {

                System.out.println("Alterou: " + (valor + 1));

                valor++;

                try {

                    Thread.sleep(500);

                } catch (InterruptedException e) { e.printStackTrace(); }
            }
        }
    }

    public static void main(String[] args) {

        new Escuta().start();
        new Altera().start();
    }
}

// Saida
// > Alterou: 1
// > Escutou: 1
// > Alterou: 2
// > Escutou: 2
// > Alterou: 3
// > Escutou: 3
// > Alterou: 4
// > Escutou: 4
// > Alterou: 5
// > Escutou: 5