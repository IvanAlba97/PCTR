
import java.util.*;

public class usaComplejos {

    public static void main(String[] args) {
        double a1, a2, b1, b2;
        char s;
        Scanner S = new Scanner(System.in);
        do {
            System.out.print("Introduce la parte imaginaria del complejo A: ");
            a1 = S.nextDouble();
            System.out.print("Introduce la parte real del complejo A: ");
            a2 = S.nextDouble();
            System.out.print("Introduce la parte imaginaria del complejo B: ");
            b1 = S.nextDouble();
            System.out.print("Introduce la parte real del complejo B: ");
            b2 = S.nextDouble();

            Complejos c1 = new Complejos(a1, a2);
            Complejos c2 = new Complejos(b1, b2);

            System.out.println("Seleccione una opcion: ");
            System.out.println("1. Suma.");
            System.out.println("2. Resta.");
            System.out.println("3. Modulo.");
            System.out.println("4. Producto.");
            System.out.println("5. Division.");
            int op = S.nextInt();

            switch (op) {
                case 1:
                    c1.suma(c2);
                    System.out.println("Suma = " + c1.real + " + " + c1.imaginaria + "i");
                    break;
                case 2:
                    c1.resta(c2);
                    System.out.println("Resta = " + c1.real + " + " + c1.imaginaria + "i");
                    break;
                case 3:
                    double mod = c1.modulo();
                    System.out.println("Modulo = " + mod);
                    break;
                case 4:
                    c1.producto(c2);
                    System.out.println("Producto = " + c1.real + " + " + c1.imaginaria + "i");
                    break;
                case 5:
                    c1.division(c2);
                    System.out.println("Division = " + c1.real + " + " + c1.imaginaria + "i");
                    break;
                default:
                    System.out.println("Error.");
            }
            System.out.print("¿Desea realizar alguna otra operacion [S/N]?: ");
            s = S.next().charAt(0);
        } while (s == 's' || s == 'S');
    }
}
