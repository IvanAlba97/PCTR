import java.rmi.Naming;
import java.util.Scanner;

public class cPiMonteCarlo {

    public static void main(String[] args) throws Exception {

        Scanner S = new Scanner(System.in);
        int op;
        char c;

        iPiMonteCarlo RefORemoto = (iPiMonteCarlo) Naming.lookup("Servidor");

        do {
            do {
                System.out.println("Elige una de las opciones:");
                System.out.println("1. Anadir puntos al calculo de PI.");
                System.out.println("2. Resetear el calculo de PI");
                System.out.println("3. Obtener el valor de PI actualmente.");
                System.out.print("Opcion: ");
                op = S.nextInt();
            } while (op < 1 || op > 3);

            switch (op) {
                case 1:
                    System.out.print("Introduce el numero de puntos que desea aportar: ");
                    int nPuntos = S.nextInt();
                    RefORemoto.masPuntos(nPuntos);
                    break;
                case 2:
                    RefORemoto.reset();
                    break;
                case 3:
                    System.out.println("Valor de PI en este momento: " + RefORemoto.aproxActual());
                    break;
            }

            System.out.print("Desea realizar alguna otra operacion? (S/N): ");
            c = S.next().charAt(0);
        } while (c == 's' || c == 'S');

        S.close();
    }
}