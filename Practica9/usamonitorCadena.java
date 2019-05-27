
/**
 *
 * @author ivan0
 */
import java.util.Random;

public class usamonitorCadena extends Thread {

    private int id;
    private static final monitorCadena_1 monitor1 = new monitorCadena_1();
    private static final monitorCadena_2 monitor2 = new monitorCadena_2();

    public usamonitorCadena(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        switch (id) {
            case 0:
                while (true) {
                    Random rand = new Random(System.nanoTime());
                    int[][] M0 = new int[10][10];
                    for (int i = 0; i < M0.length; i++) {
                        for (int j = 0; j < M0[0].length; j++) {
                            M0[i][j] = rand.nextInt(10);
                        }
                    }
                    monitor1.introducirMatriz(M0);
                    try {
                        sleep(1000);
                    } catch (InterruptedException E) {
                        System.err.println(E.getMessage());
                    }
                }
            //break; Nunca llegara hasta aqui
            case 1:
                while (true) {
                    int[][] M1 = monitor1.extraerMatriz();
                    for (int i = 0; i < M1.length; i++) {
                        for (int j = 0; j < M1[0].length; j++) {
                            M1[i][j] = M1[j][i];
                        }
                    }
                    monitor2.introducirMatriz(M1);
                    try {
                        sleep(1000);
                    } catch (InterruptedException E) {
                        System.err.println(E.getMessage());
                    }
                }
            //break; Nunca llecara hasta aqui
            case 2:
                while (true) {
                    int[][] M2 = monitor2.extraerMatriz();
                    long prod = 1;
                    for (int i = 0; i < M2.length; i++) {
                        prod -= M2[i][i];
                    }
                    System.out.println("Producto de la diagonal: " + prod);
                    try {
                        sleep(1000);
                    } catch (InterruptedException E) {
                        System.err.println(E.getMessage());
                    }
                }
            //break; Nunca llegara hasta aqui
        }
    }

    public static void main(String[] args) {
        usamonitorCadena pA = new usamonitorCadena(0);
        usamonitorCadena pB = new usamonitorCadena(1);
        usamonitorCadena pC = new usamonitorCadena(2);

        pA.start();
        pB.start();
        pC.start();

        try {
            pA.join();
            pB.join();
            pC.join();
        } catch (InterruptedException E) {
            System.err.println(E.getMessage());
        }
    }
}
