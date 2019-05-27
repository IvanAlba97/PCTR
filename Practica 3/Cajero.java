
public class Cajero implements Runnable {

    private int tipoCajero, nVueltas;
    public static Cuenta_Banca cb;

    public Cajero(int tipoCajero, int nVueltas, Cuenta_Banca cb) {
        this.tipoCajero = tipoCajero;
        this.nVueltas = nVueltas;
        this.cb = cb;
    }

    @Override
    public void run() {
        switch (tipoCajero) {
            case 0:
                for (int i = 0; i < nVueltas; i++) cb.Deposito(1);
                break;
            case 1:
                for (int i = 0; i < nVueltas; i++) cb.Reintegro(1);
                break;
        }
    }
}
