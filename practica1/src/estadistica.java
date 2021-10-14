package PCTR.practica1.src;

import java.util.Scanner;

public class estadistica {

    public static void main(String[] args) {

        Scanner S = new Scanner(System.in);

        int n = Integer.parseInt(args[0]);
        int[] V = new int[n];
        int op = 0;
        char c;

        for (int i = 0; i < n; i++) {
            System.out.print("V[" + i + "]: ");
            V[i] = S.nextInt();
        }
        do {
            do {
                System.out.println("Elige una de las opciones: ");
                System.out.println("1. Media.");
                System.out.println("2. Moda.");
                System.out.println("3. Varianza.");
                System.out.println("4. Desviacion tipica.");
                System.out.print("Opcion: ");
                op = S.nextInt();
            } while (op < 1 || op > 4);

            switch (op) {
                case 1:
                    media(V);
                    break;
                case 2:
                    moda(V);
                    break;
                case 3:
                    varianza(V);
                    break;
                case 4:
                    desviacionTipica(V);
                    break;
                default:
                    System.out.println("Error.");
            }
            System.out.print("Desea realizar alguna otra operacion?(S/N): ");
            c = S.next().charAt(0);
        } while (c == 'S' || c == 's');

        S.close();
    }

    public static void media(int[] V) {
        int suma = 0;
        for (int i = 0; i < V.length; i++) {
            suma += V[i];
        }
        double media = (double) suma / V.length;
        System.out.println("Media = " + media);
    }

    public static void moda(int[] V) {
        int moda = 0, nVeces = 0;
        for (int i = 0; i < V.length; i++) {
            int cont = 0;
            for (int j = 0; j < V.length; j++) {
                if (V[i] == V[j]) {
                    cont++;
                }
            }
            if (cont > nVeces) {
                nVeces = cont;
                moda = V[i];
            }
        }
        System.out.println("Moda = " + moda + ". Se repite " + nVeces + " veces.");
    }

    public static void varianza(int[] V) {
        double varianza, media, sumatorio = 0;
        int suma = 0;
        for (int i = 0; i < V.length; i++) {
            suma += V[i];
        }
        media = suma / V.length;
        for (int i = 0; i < V.length; i++) {
            sumatorio += Math.pow(V[i] - media, 2);
        }
        varianza = sumatorio / (V.length - 1);
        System.out.println("Varianza = " + varianza);
    }

    public static void desviacionTipica(int[] V) {
        double desv, media, sumatorio = 0;
        int suma = 0;
        for (int i = 0; i < V.length; i++) {
            suma += V[i];
        }
        media = suma / V.length;
        for (int i = 0; i < V.length; i++) {
            sumatorio += Math.pow(V[i] - media, 2);
        }
        desv = Math.sqrt(sumatorio / (V.length - 1));
        System.out.println("Desviacion tipica = " + desv);
    }
}