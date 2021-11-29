public class monitorImpresion {

    private static int impLibres = 3;
    private static boolean[] libres;

    public monitorImpresion() {
        libres = new boolean[impLibres];
        for (int i = 0; i < impLibres; i++) {
            libres[i] = true;
        }
    }

    public synchronized int takePrint(int id) {
        while (impLibres == 0) {
            try {
                wait();
            } catch (InterruptedException E) {
                E.printStackTrace();
            }
            System.out.println("Hilo " + id + " esperando impresora.");
        }
        boolean encontrada = false;
        int imp = -1;
        for (int i = 0; i < libres.length && !encontrada; i++) {
            if (libres[i]) {
                libres[i] = false;
                System.out.println("Hilo " + id + " coge la impresora " + i);
                impLibres--;
                encontrada = true;
                imp = i;
            }
        }
        return imp;
    }

    public synchronized void dropPrint(int id, int imp) {
        libres[imp] = true;
        System.out.println("Hilo " + id + " libera la impresora " + imp);
        impLibres++;
    }
}