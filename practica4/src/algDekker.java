package PCTR.practica4.src;

public class algDekker extends Thread {

    static final int iteraciones = 1000000;
    static volatile int enteroCompartido = 0;
    static volatile boolean wantP = false;
    static volatile boolean wantQ = false;
    static volatile boolean wantR = false;
    static volatile int turn = 1;

    public algDekker() {
        Thread p = new P();
        Thread q = new Q();
        Thread r = new R();
        p.start();
        q.start();
        r.start();
        try {
            p.join();
            q.join();
            r.join();
        } catch(InterruptedException e){
            System.err.println(e.getMessage());
        }
        System.out.println("El valor del recurso compartido es " + enteroCompartido);
        System.out.println("Deberia se 0");
    }

    class P extends Thread {    //Turno 1

        @Override
        public void run() {
            for (int i = 0; i < iteraciones; i++) {
                wantP = true;
                while (wantQ || wantR) {
                    if (turn != 1) {
                        wantP = false;
                        while (turn != 1) {
                            Thread.yield();
                        }
                        wantP = true;
                    }
                }
                enteroCompartido++;
                turn = 2;
                wantP = false;
            }
        }
    }

    class Q extends Thread {    //Turno 2

        @Override
        public void run() {
            for (int i = 0; i < iteraciones; i++) {
                wantQ = true;
                while (wantP || wantR) {
                    if (turn != 2) {
                        wantQ = false;
                        while (turn != 2) {
                            Thread.yield();
                        }
                        wantQ = true;
                    }
                }
                enteroCompartido++;
                turn = 3;
                wantQ = false;
            }
        }
    }

    class R extends Thread {    //Turno 3

        @Override
        public void run() {
            for (int i = 0; i < iteraciones; i++) {
                wantR = true;
                while (wantP || wantQ) {
                    if (turn != 3) {
                        wantR = false;
                        while (turn != 3) {
                            Thread.yield();
                        }
                        wantR = true;
                    }
                }
                enteroCompartido++;
                turn = 1;
                wantR = false;
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new algDekker();
    }
}