public class heterogenea extends Thread {

    public static int n = 0, m = 0;
    public int tipo;

    public heterogenea(int tipo) {
        this.tipo = tipo;
    }

    public void run() {
        switch(tipo) {
            case 0: incN();
            case 1: incM();
        }
    }

    public synchronized void incN() { n++; }
    public void incM() { m++; }

    public static void main(String[] args) throws Exception {
        int nHebras = 1000;
        Thread[] hilos = new Thread[nHebras];

        for (int i = 0; i < hilos.length; i++) {
            hilos[i] = new Thread(new heterogenea(i % 2));      // Par: tipo 0     Impar: tipo 1
            hilos[i].start();
        }

        for (int i = 0; i < hilos.length; i++) {
            hilos[i].join();
        }

        System.out.println("n = " + n);
        System.out.println("m = " + m);
    }
}
