
import java.util.Scanner;
import java.util.Random;

public class matVector {

    private static int fa, ca, fb;

    public static void main(String[] args) {
        Scanner S = new Scanner(System.in);
        int op;
        char salida;

        do {
            do {
                System.out.println("Elige una de las opciones: ");
                System.out.println("1. Rellenar manualmente.");
                System.out.println("2. Rellenar aleatoriamente.");
                System.out.print("Opcion: ");
                op = S.nextInt();
            } while (op < 1 || op > 2);

            do {
                System.out.println("(El numero de columnas de A debe ser igual al numero de filas de B)");
                System.out.print("Introduce el numero de filas de la matriz A: ");
                fa = S.nextInt();
                System.out.print("Introduce el numero de columnas de la matriz A: ");
                ca = S.nextInt();
                System.out.print("Introduce el numero de filas del vector B: ");
                fb = S.nextInt();
            } while (ca != fb);

            int[][] A = new int[fa][ca];
            int[] B = new int[fb];
            int[] C = new int[fa];

            switch (op) {
                case 1:
                    inicializar(A, B);
                    break;
                case 2:
                    rellenar(A, B);
                    break;
            }

            producto(A, B, C);
            //mostrar(A, B, C);

            System.out.print("Desea realizar algun otro producto (S/N)?: ");
            salida = S.next().charAt(0);
        } while(salida == 'S' || salida == 's');
    
    }

    public static void rellenar(int[][] A, int[] B) {
        Random rand = new Random(System.nanoTime());

        for (int i = 0; i < fa; i++) {
            for (int j = 0; j < ca; j++) {
                A[i][j] = rand.nextInt(10);
            }
        }

        for (int i = 0; i < fb; i++) {
            B[i] = rand.nextInt(10);
        }
    }

    public static void inicializar(int[][] A, int[] B) {
        Scanner S = new Scanner(System.in);

        System.out.println("Rellenando Matriz A...");
        for (int i = 0; i < fa; i++) {
            for (int j = 0; j < ca; j++) {
                System.out.print("A[" + i + "][" + j + "] = ");
                A[i][j] = S.nextInt();
            }
        }

        System.out.println("Rellenando Vector B...");
        for (int i = 0; i < fb; i++) {
            System.out.print("B[" + i + "] = ");
            B[i] = S.nextInt();
        }
    }

    public static void producto(int[][] A, int[] B, int[] C) {
        double tiempoInicio = System.currentTimeMillis();
        for (int i = 0; i < ca; i++) {
            for (int j = 0; j < fa; j++) {
                C[i] += A[i][j] * B[j];
            }
        }
        double tiempoFin = System.currentTimeMillis() - tiempoInicio;
        System.out.println("Tiempo = " + tiempoFin + " milisegundos.");
    }

    public static void mostrar(int[][] A, int[] B, int[] C) {

        System.out.println("MATRIZ A");
        for (int i = 0; i < fa; i++) {
            for (int j = 0; j < ca; j++) {
                System.out.print(A[i][j] + "  ");
            }
            System.out.println();
        }

        System.out.println("VECTOR B");
        for (int i = 0; i < fb; i++) {
            System.out.print(B[i] + "  ");
        }
        System.out.println();

        System.out.println("VECTOR C");
        for (int i = 0; i < fa; i++) {
            System.out.print(C[i] + "  ");
        }
        System.out.println();
    }
}
