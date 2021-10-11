import java.util.Scanner;

public class Usa_hebra {
    public static void main(String[] args) {
        
        Scanner S = new Scanner(System.in);
        
        System.out.print("Introduce el numero de iteraciones: ");
        int nIter = S.nextInt();

        Thread hebra[] = new Thread[10];
        
        for(int i = 0; i < 5; i++) {
            hebra[i] = new hebra(0, nIter);
        }
        for(int i = 5; i < 10; i++) {
            hebra[i] = new hebra(1, nIter);
        }
        
        System.out.println("El valor final de n es " + ((hebra) hebra[0]).getN());

        S.close();
    }
}
