/**
 * Esta clase contiene los atributos y metodos manejar un cajero
 * @author Iván Alba Gómez
 * @version 3.0
 * @see redCajeros.java
 * @see cuentaCorriente.java
 */
public class cajero implements Runnable {
    
    private static cuentaCorriente cuenta;
    public int tipo;
    
    /**
     * Método constructor
     * @param cuenta
     * @param tipo
     */
    public cajero(cuentaCorriente cuenta, int tipo) {
        cajero.cuenta = cuenta;
        this.tipo = tipo;
    }
    
    /**
     * Método run
     */
    @Override
    public void run() {
        if (tipo % 2 != 0) {
            cuenta.aumentar(1);
        } else {
            cuenta.decrementar(1);
        }
    }
}