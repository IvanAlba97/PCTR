
/**
 * 
 * @author ivan0
 */

public class algoLamport implements Runnable {

    //Variables globales
    int N;
    int[] Numero = new int[N];
    boolean[] Eligiendo = new boolean[N];
    int id;

    public algoLamport(int i) {
        this.id = i;
    }

    @Override
    public void run() {
        for (int i = 0; i < id; i++) {
            //Calcula el numero de turno
            Eligiendo[i] = true;
            Numero[i] = 1 + max(Numero);
            Eligiendo[i] = false;
            //Compara con todos los hilos
            for (int j = 1; j < N; j++) {
                //Si el hilo j esta calculando su numero, espera a que termine
                while (Eligiendo[j]) {
                    //Nada
                }
                //Si el hilo j tiene mas prioridad, espera a que ponga su numero a 0
                //j tiene mas prioridad si su numero de turno es mas bajo que el
                //de i, o bien si es el mismo numero y ademas j es menor que i
                while (Numero[j] != 0 && (Numero[j] < Numero[i])) {
                    //Nada
                }
            }
            /*INICIO DE LA SECCION CRITICA*/
            /*FIN DE LA SECCION CRITICA*/
            Numero[i] = 0;
            /*Codigo restante*/
        }
    }

    public int max(int[] Numero) {
        int max = Numero[0];
        for (int i = 1; i < Numero.length; i++) {
            if (Numero[i] > max) {
                max = Numero[i];
            }
        }
        return max;
    }

    public static void main(String[] args) {
        Thread[] hilos = new Thread[10];
        for (int i = 0; i < hilos.length; i++) {
            hilos[i] = new Thread(new algoLamport(i));
            hilos[i].start();
        }
        try {
            for (int i = 0; i < hilos.length; i++) {
                hilos[i].join();
            }
        } catch (InterruptedException E) {
            System.err.println(E.getMessage());
        }
    }
}
