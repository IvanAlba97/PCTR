
public class Hilo extends Thread {

    public static int n = 0;
    public int nVueltas, tipoHilo;

    public Hilo(int nVueltas, int tipoHilo) {
        this.nVueltas = nVueltas;
        this.tipoHilo = tipoHilo;
    }

    @Override
    public void run() {
        switch (tipoHilo) {
            case 0:
                for (int i = 0; i < nVueltas; i++) {
                    n++;
                }
                break;
            case 1:
                for (int i = 0; i < nVueltas; i++) {
                    n--;
                }
        }
    }

    public int getN() {
        return n;
    }
}
