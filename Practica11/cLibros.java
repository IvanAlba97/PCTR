
/**
 *
 * @author ivan0
 */
import java.rmi.*;
import java.util.Scanner;

public class cLibros {

    //static Scanner S = new Scanner(System.in);
    //static String titulo, autor;
    //static int anno;

    public static void main(String[] args) throws Exception {

        Scanner S = new Scanner(System.in);
        int op;
        char c;

        do {
            do {
                System.out.println("Seleccione una de las opciones: ");
                System.out.println("1. Consultar.");
                System.out.println("2. Insertar.");
                System.out.println("3. Extraer.");
                System.out.print("Opcion: ");
                op = S.nextInt();
            } while (op < 1 || op > 3);

            switch (op) {
                case 1:
                    consultar();
                    break;
                case 2:
                    insertar();
                    break;
                case 3:
                    extraer();
                    break;
            }

            System.out.print("Desea realizar alguna otra accion? (S/N): ");
            c = S.next().charAt(0);
        } while (c == 's' || c == 'S');

    }

    private static void consultar() throws Exception {
        Scanner S = new Scanner(System.in);
        iLibros RefORemoto = (iLibros) Naming.lookup("Servidor");
        String titulo;
        System.out.print("Introduce el titulo del libro: ");
        titulo = S.nextLine();
        System.out.println(RefORemoto.consultar(titulo));
    }

    private static void insertar() throws Exception {
        Scanner S = new Scanner(System.in);
        iLibros RefORemoto = (iLibros) Naming.lookup("Servidor");
        String titulo, autor;
        int anno;
        System.out.print("Introduce el titulo del libro: ");
        titulo = S.nextLine();
        System.out.print("Introduce el autor del libro: ");
        autor = S.nextLine();
        System.out.print("Introduce el anno del libro: ");
        anno = S.nextInt();
        RefORemoto.insertar(titulo, autor, anno);
    }

    private static void extraer() throws Exception {
        Scanner S = new Scanner(System.in);
        iLibros RefORemoto = (iLibros) Naming.lookup("Servidor");
        String titulo;
        System.out.print("Introduce el titulo del libro: ");
        titulo = S.nextLine();
        Libro L = RefORemoto.extraer(titulo);
        if(L.getTitulo().equals("No encontrado")){
            System.out.println("El libro introducido no se encuentra en nuestra Base de Datos.");
        }else{
            System.out.println(L.toString());
        }
    }
}
