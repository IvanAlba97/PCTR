/**
 *
 * @author ivan0
 */

public class hebra extends Thread {
    
    private static int n = 0;
    private int tipoHilo, nIter;
    
    public hebra(int tipoHilo, int nIter) {
        this.tipoHilo = tipoHilo;
        this.nIter = nIter;
    }
    
    @Override
    public void run() {
        switch(tipoHilo) {
            case 0: for(int i = 0; i < nIter; i++) n++; break;
            case 1: for(int i = 0; i < nIter; i++) n--; break;
            default: System.err.println("Error en el switch.");
        }
    }
    
    public int getN() { return n; }
    
}