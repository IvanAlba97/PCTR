/**
 *
 * @author ivan0
 */
import java.util.Scanner;

public class NewtonRaphson {

    public static void main(String[] args) {
        Scanner S = new Scanner(System.in);
        int op, nIter;
        double aproximacion, funcion = 0, derivada = 0;

        do {
            System.out.println("Elige una de las dos funciones: ");
            System.out.println("1. f(x) = cos(x) - x^3 en [0,1]");
            System.out.println("2. f(x) = x^2 - 5 en [2,3]");
            System.out.print("Opcion elegida: ");
            op = S.nextInt();
        } while (op < 1 || op > 2);

        System.out.print("Introduce la aproximacion: ");
        aproximacion = S.nextDouble();

        System.out.print("Introduce el numero de iteraciones: ");
        nIter = S.nextInt();

        for (int i = 0; i < nIter; i++) {
            switch (op) {
                case 1:
                    funcion = Math.cos(aproximacion) - Math.pow(aproximacion, 3);
                    derivada = -Math.sin(aproximacion) - 3 * Math.pow(aproximacion, 2);
                    break;
                case 2:
                    funcion = Math.pow(aproximacion, 2) - 5;
                    derivada = 2 * aproximacion;
                    break;
                default:
                    System.err.println("Error en el switch");
            }
            aproximacion = aproximacion - (funcion / derivada);
            System.out.println("Iteracion: " + (i + 1) + ", aproximacion: " + aproximacion);
        }
        
        S.close();
    }
}