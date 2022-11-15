/**
 * Esta clase contiene los atributos y metodos de una hebra
 * @author Iván Alba Gómez
 * @version 3.0
 * @see Usa_hebra
 */
public class hebra extends Thread {
    
    private static int n = 0;
    private int tipoHilo, nIter;
    
    /**
     * Método constructor parametrizado
     * @param tipoHilo Indica si el hilo incrementa o decrementa
     * @param nIter Número de iteraciones
     */
    public hebra(int tipoHilo, int nIter) {
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
     * Metodo para obtener el valor de n
     * @return Regresa el valor de n
     */
    public int getN() { return n; }
    
}