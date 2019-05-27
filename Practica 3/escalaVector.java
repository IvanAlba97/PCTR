
import java.util.Scanner;
import java.util.Random;

public class escalaVector {

    public static void main(String[] args) {

        Scanner S = new Scanner(System.in);
        Random rand = new Random(System.nanoTime());
        int escalar;
        int[] v = new int[1000000/*00*/]; //10^6

        System.out.print("Introduce el escalado: ");
        escalar = S.nextInt();
        
        for(int i=0;i<v.length;i++){
            v[i] = rand.nextInt(10);
            //System.out.println("v[" + i + "] = " + v[i]*escalar);
        }
    }
}
