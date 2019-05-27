
/**
 *
 * @author ivan0
 */
import java.util.ArrayList;

public class monitorCadena_2 {

    private static ArrayList<int[][]> buffer2;
    private static int pos2 = -1;

    public monitorCadena_2() {
        buffer2 = new ArrayList<>();
    }

    public synchronized void introducirMatriz(int[][] matriz) {
        while (buffer2.size() == 50) {
            System.out.println("El buffer2 esta lleno.");
            try {
                wait();
            } catch (InterruptedException E) {
                System.err.println(E.getMessage());
            }
        }
        buffer2.add(matriz);
        pos2++;
        System.out.println("Se ha introducido una matriz al buffer2.");
        notifyAll();
    }

    public synchronized int[][] extraerMatriz() {
        while (buffer2.isEmpty()) {
            System.out.println("El buffer2 esta vacio.");
            try {
                wait();
            } catch (InterruptedException E) {
                System.err.println(E.getMessage());
            }
        }
        int[][] matriz = buffer2.get(pos2);
        buffer2.remove(pos2);
        pos2--;
        System.out.println("Se ha extraido una matriz del buffer2.");
        notifyAll();
        return matriz;
    }
}
