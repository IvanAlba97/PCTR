
import java.util.Scanner;
import java.util.Random;

public class escalaVectorParalelo extends Thread {

    int nHilo, escalar;
    int[] v = new int[1000000/*00*/];

    public escalaVectorParalelo(int nHilo, int escalar) {
        this.nHilo = nHilo;
        this.escalar = escalar;
    }

    @Override
    public void run() {
        Random rand = new Random(System.nanoTime());
        switch (nHilo) {
            case 0:
                for (int i = 0; i < v.length / 2; i++) {
                    v[i] = rand.nextInt(10);
                    //System.out.println("v[" + i + "] = " + v[i] * escalar);
                }
            case 1:
                for (int i = v.length / 2; i < v.length; i++) {
                    v[i] = rand.nextInt(10);
                    //System.out.println("v[" + i + "] = " + v[i] * escalar);
                }
        }
    }

    public static void main(String[] args) throws Exception {
        Scanner S = new Scanner(System.in);
        int escalar;
        System.out.print("Introduce el escalar: ");
        escalar = S.nextInt();

        escalaVectorParalelo h1 = new escalaVectorParalelo(0, escalar);
        escalaVectorParalelo h2 = new escalaVectorParalelo(1, escalar);

        h1.start();
        h2.start();
        h1.join();
        h2.join();
    }
}
