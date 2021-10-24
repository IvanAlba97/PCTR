package PCTR.practica2.src;

public class cajero implements Runnable {
    
    private static cuentaCorriente cuenta;
    public int tipo;
    
    public cajero(cuentaCorriente cuenta, int tipo) {
        cajero.cuenta = cuenta;
        this.tipo = tipo;
    }
    
    @Override
    public void run() {
        if (tipo % 2 != 0) {
            cuenta.aumentar(1);
        } else {
            cuenta.decrementar(1);
        }
    }
}