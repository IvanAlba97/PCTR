
import java.util.Scanner;

public class usaElipse {

    public static void main(String[] args) {

        Scanner S = new Scanner(System.in);
        Elipse E1 = new Elipse(-2, 2, 4, 3);
        Elipse E2 = new Elipse(-3, 3, 6, 5);
        System.out.println("Semieje mayor de E1 = " + E1.getSemiejemayor());
        System.out.println("Semieje menor de E2 = " + E2.getSemiejemenor());

        System.out.print("Introduce la coordenada x de E1: ");
        double x1 = S.nextDouble();
        System.out.print("Introduce la coordenada y de E1: ");
        double y1 = S.nextDouble();
        System.out.print("Introduce la coordenada x de E2: ");
        double x2 = S.nextDouble();
        System.out.print("Introduce la coordenada y de E2: ");
        double y2 = S.nextDouble();

        E1.pertenece(x1, y2);
        E2.pertenece(x2, y2);
    }
}
