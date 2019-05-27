
import java.util.Scanner;

public class Estadistica {

    public static void main(String[] args) {
        int N, op;
        Scanner S = new Scanner(System.in);
        System.out.print("¿Cuantos elementos tiene la secuencia?: ");
        N = S.nextInt();
        int[] v = new int[N];

        for (int i = 0; i < v.length; i++) {
            System.out.print("Introduce un valor entero para v[" + i + "]: ");
            v[i] = S.nextInt();
        }

        do {
            System.out.println("Elige una opcion: ");
            System.out.println("1. Media.");
            System.out.println("2. Moda.");
            System.out.println("3. Varianza.");
            System.out.println("4. Desviacion tipica.");
            op = S.nextInt();
        } while (op < 1 || op > 4);

        switch (op) {
            case 1:
                media(v);
                break;
            case 2:
                moda(v);
                break;
            case 3:
                varianza(v);
                break;
            case 4:
                desv(v);
                break;
            default:
                System.out.println("Error.");
        }
    }

    static void media(int v[]) {
        int suma = 0;
        for (int i = 0; i < v.length; i++) {
            suma += v[i];
        }
        System.out.println("La media de los elementos introducidos es: " + (double) suma / v.length);
    }

    static void moda(int v[]) {
        int nVecesMax = 0, moda = 0;
        for (int i = 0; i < v.length; i++) {
            int nVeces = 0;
            for (int j = 0; j < v.length; j++) {
                if (v[i] == v[j]) {
                    nVeces++;
                }
                if (nVeces > nVecesMax) {
                    moda = v[i];
                    nVecesMax = nVeces;
                }
            }
        }
        if (nVecesMax == 1) {
            System.out.println("No hay moda en la secuencia de numeros introducidos.");
        } else {
            System.out.println("La moda es: " + moda + " y se repite: " + nVecesMax + " veces.");
        }
    }

    static void varianza(int v[]) {
        double var, media, sumatorio = 0;

        int suma = 0;
        for (int i = 0; i < v.length; i++) {
            suma += v[i];
        }
        media = suma / v.length;
        for (int i = 0; i < v.length; i++) {
            sumatorio += Math.pow(v[i] - media, 2);
        }
        var = sumatorio / (v.length - 1);
        System.out.println("La varianza de los numeros introducidos es: " + var);
    }

    static void desv(int v[]) {
        double desv, media, sumatorio = 0;
        int suma = 0;
        for (int i = 0; i < v.length; i++) {
            suma += v[i];
        }
        media = suma / v.length;
        for (int i = 0; i < v.length; i++) {
            sumatorio += Math.pow(v[i] - media, 2);
        }
        desv = Math.sqrt(sumatorio / (v.length - 1));
        System.out.println("La desviacion tipica de los numeros introducidos es: " + desv);
    }
}
