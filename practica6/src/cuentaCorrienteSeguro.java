/**
 * Esta clase contiene los atributos y metodos para el manejo de cuentas corrientes.
 * @author Iván Alba Gómez
 * @version 3.0
 */
public class cuentaCorrienteSeguro {
    private double saldo;

    /**
     * Método constructor.
     */
    public cuentaCorrienteSeguro() { this.saldo = 0.0; }

    /**
     * Método observador.
     * @return saldo
     */
    public double getSaldo() { return saldo; }

    
    /** 
     * Método para aumentar el saldo.
     * @param cantidad
     */
    public synchronized void aumentar(double cantidad) {
        saldo += cantidad;
        System.out.println("Se ha aumentado el saldo de la cuenta en " + cantidad + " unidades.");
        System.out.println("Saldo actual: " + saldo);
    }

    
    /** 
     * Método para decrementar el saldo.
     * @param cantidad
     */
    public synchronized void decrementar(double cantidad) {
        if (saldo >= cantidad) {
            saldo -= cantidad;
            System.out.println("Se ha decrementado el saldo de la cuenta en " + cantidad + " unidades.");
            System.out.println("Saldo actual: " + saldo);
        } else {
            System.out.println("No tiene saldo suficiente.");
            System.out.println("Saldo disponible: " + saldo);
        }
    }
}