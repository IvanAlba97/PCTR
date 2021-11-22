public class drakkarVikingo {

    private static monitorDrakkar monitor;

    private static class monitorDrakkar {

        private int anguilas;
        private final int nVikingos;

        public monitorDrakkar(int nVikingos) {
            this.anguilas = nVikingos;
            this.nVikingos = nVikingos;
        }

        public synchronized void comer(int hilo) {
            while (anguilas == 0) {
                try {
                    wait();
                    System.out.println("Marmita vacia, el vikingo " + hilo + " esta esperando para comer.");
                } catch (InterruptedException e) {
                    System.err.println(e.getMessage());
                }
            }
            anguilas--;
            System.out.println("El vikingo " + hilo + " ha comido.");
            if (anguilas == 0) {
                notifyAll();
            }
        }

        public synchronized void cocinar() {
            while (anguilas > 0) {
                try {
                    wait();
                    System.out.println("Marmita no vacia, el cocinero esta esperanco a que se vacie.");
                } catch (InterruptedException e) {
                    System.err.println(e.getMessage());
                }
            }
            anguilas = nVikingos;
            System.out.println("El cocinero ha llenado la marmita.");
            notifyAll();
        }
    }

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
