package _modificadores.exemplo_synchronized_metodo;
/*
 * Exemplo usando synchronized no método.
 */

class Sender {

    /*
     * Método sincronizado, apenas uma thread irá executá-lo por vez
     */
    public synchronized void send(String msg) {

        System.out.println("Enviando: " + msg);

        try {

            Thread.sleep(1000);

        } catch (Exception e) {

            System.out.println("Thread interrompida.");
        }

        System.out.println("Enviada: " + msg);
    }
}

class ThreadedSend extends Thread {

    Sender obj;
    private String msg;

    // Recebe o objeto que irá enviar a msg e a msg
    ThreadedSend(String msg, Sender obj) {

        this.msg = msg;
        this.obj = obj;
    }

    // Função chamada ao start() da thread
    public void run() {

        obj.send(msg);
    }
}


class Exemplo {

    public static void main(String[] args) {

        Sender obj = new Sender();

        ThreadedSend S1 = new ThreadedSend("Oi", obj);
        ThreadedSend S2 = new ThreadedSend("Tchau", obj);

        // Iniciando duas threads do tipo ThreadedSend
        S1.start();
        S2.start();

        try {

            // Esperando as threads terminarem
            S1.join();
            S2.join();

        } catch (Exception e) {

            System.out.println("Interrompido");
        }
    }
}

// Saida
// > Enviando: Oi
// > Enviada: Oi
// > Enviando: Tchau
// > Enviada: Tchau
