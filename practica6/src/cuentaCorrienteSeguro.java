public class cuentaCorrienteSeguro {
    private double saldo;

    public cuentaCorrienteSeguro() { this.saldo = 0.0; }

    public double getSaldo() { return saldo; }

    public synchronized void aumentar(double cantidad) {
        saldo += cantidad;
        System.out.println("Se ha aumentado el saldo de la cuenta en " + cantidad + " unidades.");
        System.out.println("Saldo actual: " + saldo);
    }

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