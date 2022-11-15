import java.util.Scanner;

/**
 * Esta clase contiene los atributos y metodos para la resolución del método MonteCarlo
 * @author Iván Alba Gómez
 * @version 3.0
 */
public class intDefinidaMonteCarlo {

    /** Método principal
     * @param args
     */
    public static void main(String[] args) {

        Scanner S = new Scanner(System.in);
        int op;

        do {
            System.out.println("Elige una de las dos funciones: ");
            System.out.println("1. f(x) = sin(x)");
            System.out.println("2. f(x) = x");
            System.out.print("Opcion elegida: ");
            op = S.nextInt();
        } while (op < 1 || op > 2);

        System.out.print("Introduce el numero de puntos: ");
        int nPuntos = S.nextInt();

        int aciertos = 0;
        switch(op) {
            case 1:
                for (int i = 0; i < nPuntos; i++) {
                    double x = Math.random();
                    double y = Math.random();
                    if(y < Math.sin(x)) aciertos++;
                }
                break;
            case 2:
                for (int i = 0; i < nPuntos; i++) {
                    double x = Math.random();
                    double y = Math.random();
                    if(y < x) aciertos++;
                }
                break;
            default:
                System.err.println("Error en el switch"); 
                break;
        }
        
        double integral = (double)aciertos / nPuntos;
        System.out.println("El valor de la integral es: " + integral);

        S.close();
    }
}