public class usaDrakkarVikingo {
    private static monitorDrakkar monitor;

    public static void main(String[] args) {
        int nVikingos = 10;
        cocinero C = new cocinero();
        monitor = new monitorDrakkar(nVikingos);
        vikingo[] V = new vikingo[nVikingos];
        Thread[] hilos = new Thread[nVikingos];
        Thread cocinero = new Thread(C);
        for (int i = 0; i < hilos.length; i++) {
            V[i] = new vikingo(i);
            hilos[i] = new Thread(V[i]);
            hilos[i].start();
        }
        cocinero.start();
    }
}
