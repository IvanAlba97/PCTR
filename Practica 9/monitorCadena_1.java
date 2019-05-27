
/**
 *
 * @author ivan0
 */
import java.util.ArrayList;

public class monitorCadena_1 {

    private static ArrayList<int[][]> buffer1;
    private static int pos1 = -1;

    public monitorCadena_1() {
        buffer1 = new ArrayList<>();
    }

    public synchronized void introducirMatriz(int[][] matriz) {
        while (buffer1.size() == 100) {
            System.out.println("El buffer1 esta lleno.");
            try {
                wait();
            } catch (InterruptedException E) {
                System.err.println(E.getMessage());
            }
        }
        buffer1.add(matriz);
        pos1++;
        System.out.println("Se ha introducido una matriz al buffer1.");
        notifyAll();
    }

    public synchronized int[][] extraerMatriz() {
        while (buffer1.isEmpty()) {
            System.out.println("El buffer1 esta vacio.");
            try {
                wait();
            } catch (InterruptedException E) {
                System.err.println(E.getMessage());
            }
        }
        int[][] matriz = buffer1.get(pos1);
        buffer1.remove(pos1);
        pos1--;
        System.out.println("Se ha extraido una matriz del buffer1.");
        notifyAll();
        return matriz;
    }
}
