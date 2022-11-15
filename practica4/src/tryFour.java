/**
 * Esta clase contiene los atributos y metodos para el cuarto intento del algoritmo de Dekker.
 * @author Iván Alba Gómez
 * @version 3.0
 * @see tryThree.java
 */
public class tryFour extends Thread{
	private int tipoHilo;
	private static volatile int nVueltas = 10000;
	private static volatile int n = 0;
	private static volatile boolean C1 = false;
	private static volatile boolean C2 = false;

    /**
     * Método constructor.
     * @param tipoHilo
     */
    public tryFour(int tipoHilo) { this.tipoHilo = tipoHilo; }

    /**
     * Método que encapsula el código a ejecutar concurrentemente.
     */
    @Override
    public void run() {
        switch(tipoHilo){
            case 1: 
                for(int i = 0; i < nVueltas; i++) {
                    C1 = true;
                    while(C2 == true);
                    C1 = false;
                    C1 = true;
                    n++;
                }
                break;
            case 2: 
                for(int i = 0; i < nVueltas; i++) {
                    C2 = true;
                    while(C1 == true);
                    C2 = false;
                    C2 = true;
                    n--;
                }
                break;
            }
    }

    
    /** 
     * Método principal.
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException{
        tryFour h1 = new tryFour(1);
        tryFour h2 = new tryFour(2);
        h1.start(); h2.start();
        h1.join(); h2.join();
        System.out.println(n);
    }
}
