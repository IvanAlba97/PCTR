/**
 * Esta clase contiene los atributos y metodos para manejar una cuenta corriente
 * @author Iván Alba Gómez
 * @version 3.0
 * @see redCajeros.java
 * @see cajero.java
 */
public class cuentaCorriente {
    
    private double saldo/*, numCuenta*/;

    /**
     * Método constructor
     */
    public cuentaCorriente() { this.saldo = 0.0; }

    /**
     * Método observador
     * @return Valor de saldo
     */
    public double getSaldo() { return saldo; }

    /** 
     * Método modificador
     * @param cantidad
     */
    public void aumentar(double cantidad) {
        saldo += cantidad;
        System.out.println("Se ha aumentado el saldo de la cuenta en " + cantidad + " unidades.");
        System.out.println("Saldo actual: " + saldo);
    }

    
    /** 
     * Método modificador
     * @param cantidad
     */
    public void decrementar(double cantidad) {
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