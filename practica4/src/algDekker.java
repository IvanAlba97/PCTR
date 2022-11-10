package practica4.src;

/**
 * Esta clase contiene los atributos y metodos del algoritmo de Dekker.
 * @author Iván Alba Gómez
 * @version 3.0
 */
public class algDekker extends Thread {

    static final int iteraciones = 10000;
    static volatile int enteroCompartido = 0;
    static volatile boolean wantP = false;
    static volatile boolean wantQ = false;
    static volatile int turn = 1;

    /**
     * Método constructor vacío.
     */
    public algDekker() {
        Thread p = new P();
        Thread q = new Q();
        p.start();
        q.start();
        try {
            p.join();
            q.join();
        } catch(InterruptedException e){
            e.printStackTrace();
        }
        System.out.println("El valor del recurso compartido es " + enteroCompartido);
        System.out.println("Deberia ser 0");
    }

    /**
     * Esta clase representa un proceso dentro del algoritmo de Dekker.
     */
    class P extends Thread {    //Turno 1

        /**
         * Método que encapsula el código a ejecutar concurrentemente.
         */
        @Override
        public void run() {
            for (int i = 0; i < iteraciones; i++) {
                wantP = true;
                while (wantQ) {
                    if (turn != 1) {
                        wantP = false;
                        while (turn != 1) {
                            Thread.yield();
                        }
                        wantP = true;
                    }
                }
                enteroCompartido++;
                System.out.println(enteroCompartido); 
                turn = 2;
                wantP = false;
            }
        }
    }

    /**
     * Esta clase representa un proceso dentro del algoritmo de Dekker.
     */
    class Q extends Thread {    //Turno 2

        /**
         * Método que encapsula el código a ejecutar concurrentemente.
         */
        @Override
        public void run() {
            for (int i = 0; i < iteraciones; i++) {
                wantQ = true;
                while (wantP) {
                    if (turn != 2) {
                        wantQ = false;
                        while (turn != 2) {
                            Thread.yield();
                        }
                        wantQ = true;
                    }
                }
                enteroCompartido--;
                System.out.println(enteroCompartido); 
                turn = 1;
                wantQ = false;
            }
        }
    }

    /** 
     * Método principal.
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        new algDekker();
    }
}