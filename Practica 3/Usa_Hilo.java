
import java.util.Scanner;

public class Usa_Hilo {

    public static void main(String[] args) throws Exception {
        int cont;
        Scanner S = new Scanner(System.in);
        System.out.print("Introduce un numero: ");
        cont = S.nextInt();
        Hilo h1 = new Hilo(cont, 0);
        Hilo h2 = new Hilo(cont, 1);
        h1.start();
        h2.start();
        h1.join();
        h2.join();
        System.out.println("n = " + h1.getN());
    }
}
