public class drakkarVikingo {

    private int anguilas;
    private final int nVikingos;

    public drakkarVikingo(int nVikingos) {
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
