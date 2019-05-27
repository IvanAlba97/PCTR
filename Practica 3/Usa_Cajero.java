
public class Usa_Cajero {

    public static void main(String[] args) {
        
        Cuenta_Banca cuenta = new Cuenta_Banca(1234, 100.0);
        
        Thread cajero1 = new Thread(new Cajero(0, 1000, cuenta));
        Thread cajero2 = new Thread(new Cajero(1, 1000, cuenta));
        
        cajero1.start();
        cajero2.start();

        try {
            cajero1.join();
        } catch (InterruptedException E) {
            System.err.println("Error.");
        }
        try {
            cajero2.join();
        } catch (InterruptedException E) {
            System.err.println("Error.");
        }

        System.out.println(cuenta.Saldo());
    }
}
