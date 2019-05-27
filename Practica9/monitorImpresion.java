
/**
 *
 * @author ivan0
 */
public class monitorImpresion {

    private static int nImpresoras = 3;
    private static int[] libres;   // -1 = impresora libre

    public monitorImpresion() {
        libres = new int[nImpresoras];
        for (int i = 0; i < nImpresoras; i++) {
            libres[i] = -1;
        }
    }

    public synchronized void takePrint(int id) {
        while (nImpresoras == 0) {
            try {
                wait();
            } catch (InterruptedException E) {
                System.err.println(E.getMessage());
            }
            System.out.println("Hilo " + id + " esperando impresora.");
        }
        //Ahora hay que buscar que impresora esta libre...
        boolean encontrada = false;
        for (int i = 0; i < libres.length && !encontrada; i++) {
            if (libres[i] == -1) {
                libres[i] = id;
                System.out.println("Hilo " + id + " coge la impresora " + i);
                nImpresoras--;
                encontrada = true;
            }
        }
    }

    public synchronized void dropPrint(int id) {
        boolean encontrada = false;
        for(int i=0;i<libres.length && !encontrada;i++){
            if(libres[i] == id){
                libres[i] = -1;
                System.out.println("Hilo " + id + " suelta la impresora " + i);
                nImpresoras++;
                encontrada = true;
            }
        }
    }
}
