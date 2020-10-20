package _modificadores.exemplo_synchronized_statement.exemplo1;
/*
 * Exemplo usando synchronized como statement.
 */

class Sender {

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

    Sender sender;
    private String msg;

    ThreadedSend(String m, Sender obj) {

        msg = m;
        sender = obj;
    }

    public void run() {

        /* Usando synchronized como statement. Somente uma thread pode enviar
         * uma mensagem por vez.
         */
        synchronized(sender) {

            sender.send(msg);
        }
    }
}


class Exemplo {

    public static void main(String[] args) {

        Sender snd = new Sender();

        ThreadedSend S1 = new ThreadedSend("Oi", snd);
        ThreadedSend S2 = new ThreadedSend("Tchau", snd);

        S1.start();
        S2.start();

        try {

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