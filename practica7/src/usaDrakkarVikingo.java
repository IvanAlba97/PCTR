public class usaDrakkarVikingo {
    private static drakkarVikingo monitor;

    public static class vikingo implements Runnable {

        private int id;

        public vikingo(int hilo) {
            this.id = hilo;
        }

        @Override
        public void run() {
            while (true) {
                monitor.comer(id);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.err.println(e.getMessage());
                }
            }
        }
    }

    public static class cocinero implements Runnable {

        @Override
        public void run() {
            while (true) {
                monitor.cocinar();
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    System.err.println(e.getMessage());
                }
            }
        }
    }

    public static void main(String[] args) {
        int nVikingos = 10;
        cocinero C = new cocinero();
        monitor = new drakkarVikingo(nVikingos);
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
