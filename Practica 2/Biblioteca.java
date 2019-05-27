
import java.util.Scanner;

public class Biblioteca {

    public static int cont = 0;

    public static void main(String[] args) {
        Scanner S = new Scanner(System.in);
        int op1;
        char op2;
        Paciente Pac[] = new Paciente[10];

        do {
            do {
                System.out.println("Elige una de las opciones: ");
                System.out.println("1. Insertar paciente.");
                System.out.println("2. Borrar paciente.");
                System.out.println("3. Consultar paciente.");
                System.out.print("Opcion: ");
                op1 = S.nextInt();
            } while (op1 < 1 || op1 > 3);

            switch (op1) {
                case 1:
                    insertar(Pac);
                    break;
                case 2:
                    borrar(Pac);
                    break;
                case 3:
                    consultar(Pac);
                    break;
            }

            System.out.println("Desea realizar alguna otra operacion [S/N]?");
            op2 = S.next().charAt(0);
        } while (op2 == 's' || op2 == 'S');
    }

    public static void insertar(Paciente Pac[]) {
        int telefono, edad;
        String nombre, dni, direccion, compania, sexo;
        Scanner S = new Scanner(System.in);

        if (cont >= Pac.length) {
            System.out.println("No se pueden introducir mas pacientes.");
        } else {
            System.out.print("Introduce el nombre: ");
            nombre = S.nextLine();
            System.out.print("Introduce el dni: ");
            dni = S.nextLine();
            System.out.print("Introduce la direccion: ");
            direccion = S.nextLine();
            System.out.print("Introduce la compania: ");
            compania = S.nextLine();
            System.out.print("Introduce el sexo: ");
            sexo = S.nextLine();
            System.out.print("Introduce el telefono: ");
            telefono = S.nextInt();
            System.out.print("Introduce la edad: ");
            edad = S.nextInt();
            Pac[cont] = new Paciente(telefono, edad, nombre, dni, direccion, compania, sexo);
            cont++;
            System.out.println("Paciente registrado.");
            //System.out.println("cont = " + cont);
        }
    }

    public static void borrar(Paciente Pac[]) {
        Scanner S = new Scanner(System.in);
        String dni;
        boolean encontrado = false;
        System.out.print("Introduce el dni del paciente a eliminar: ");
        dni = S.nextLine();
        for (int i = 0; Pac[i] != null && cont != 0 && i < Pac.length && !encontrado; i++) {
            if (Pac[i].getDni().equals(dni)) {
                Pac[i] = null;
                for (int j = i; j < Pac.length - 1; j++) {  //Cuando elimino un paciente,desplazo
                    Pac[j] = Pac[j + 1];                    //hacia la izquierda los de la derecha
                }
                encontrado = true;
                cont--;
                System.out.println("Paciente eliminado.");
                //System.out.println("cont = " + cont);
            }
        }
        if (!encontrado) {
            System.out.println("Paciente no encontrado.");
        }
    }

    public static void consultar(Paciente Pac[]) {
        Scanner S = new Scanner(System.in);
        String dni;
        boolean encontrado = false;
        System.out.print("Intoduce el dni del paciente a consultar: ");
        dni = S.nextLine();
        for (int i = 0; Pac[i] != null && cont != 0 && i < Pac.length && !encontrado; i++) {
            if (Pac[i].getDni().equals(dni)) {
                encontrado = true;
                //System.out.println("El paciente esta en la posicion " + i + " del vector.");
                System.out.println("DNI: " + Pac[i].getDni());
                System.out.println("Nombre: " + Pac[i].getNombre());
                System.out.println("Edad: " + Pac[i].getEdad());
                System.out.println("Sexo: " + Pac[i].getSexo());
                System.out.println("Telefono: " + Pac[i].getTelefono());
                System.out.println("Compania: " + Pac[i].getCompania());
                System.out.println("Direccion: " + Pac[i].getDireccion());
            }
        }
        if (!encontrado) {
            System.out.println("Paciente no encontrado.");
        }
    }
}
