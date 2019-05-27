
import java.util.Scanner;

public class UsaTodo {

    public static void main(String[] args) {
        Scanner S = new Scanner(System.in);
        int op;
        char op2;

        do {
            do {
                System.out.println("Seleccione su poligono: ");
                System.out.println("1. Triangulo.");
                System.out.println("2. Cuadrado.");
                System.out.println("3. Pentagono.");
                System.out.println("4. Hexagono.");
                System.out.print("Opcion: ");
                op = S.nextInt();
            } while (op < 1 || op > 4);

            switch (op) {
                case 1:
                    Punto PuntosTriangulo[] = {new Punto(0, 2), new Punto(2, 2), new Punto(1, 4)};
                    Triangulo T = new Triangulo(PuntosTriangulo);
                    System.out.println("Area = " + T.area());
                    System.out.println("Perimetro = " + T.perimetro());
                    break;
                case 2:
                    Punto PuntosCuadrado[] = {new Punto(0, 2), new Punto(2, 2)};
                    Cuadrado C = new Cuadrado(PuntosCuadrado);
                    System.out.println("Area = " + C.area());
                    System.out.println("Perimetro = " + C.perimetro());
                    break;
                case 3:
                    Punto PuntosPentagono[] = {new Punto(0, 2), new Punto(2, 2)};
                    Pentagono P = new Pentagono(PuntosPentagono);
                    System.out.println("Area = " + P.area());
                    System.out.println("Perimetro = " + P.perimetro());
                    break;
                case 4:
                    Punto PuntosHexagono[] = {new Punto(0, 2), new Punto(2, 2)};
                    Hexagono H = new Hexagono(PuntosHexagono);
                    System.out.println("Area = " + H.area());
                    System.out.println("Perimetro = " + H.perimetro());
                    break;
            }
            System.out.print("Desea realizar alguna otra operacion?: ");
            op2 = S.next().charAt(0);
        } while (op2 == 'S' || op2 == 's');
    }
}
