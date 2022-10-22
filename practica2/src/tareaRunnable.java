package practica2.src;

/**
 * Esta clase contiene todos los atributos y métodos para modificar una variable de manera concurrente
 * @author Iván Alba Gómez
 * @version 3.0
 * @see Usa_tareaRunnable
 */
public class tareaRunnable implements Runnable{
    
    private static int n = 0;
    private int tipoHilo, nIter;

    /**
     * Método constructor
     * @param tipoHilo
     * @param nIter
     */
    public tareaRunnable(int tipoHilo, int nIter) {
        this.tipoHilo = tipoHilo;
        this.nIter = nIter;
    }

    /**
     * Método run
     */
    @Override
    public void run() {
        switch(tipoHilo) {
            case 0: for(int i = 0; i < nIter; i++) n++; break;
            case 1: for(int i = 0; i < nIter; i++) n--; break;
            default: System.err.println("Error en el switch.");
        }
    }

    /**
     * Método observador
     * @return Valor de n
     */
    public int getN() { return n; }
}