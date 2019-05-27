
/**
 * @author ivan0
 */

import java.util.Scanner;

public class Cesar {
    public static void main(String[] args){
        Scanner S = new Scanner(System.in);
        String cadena, cifrada = "";
        int desplazamiento, ascii;
        System.out.print("Introduce la frase a cifrar: ");
        cadena = S.nextLine();
        System.out.print("Introduce la clave de desplazamiento: ");
        desplazamiento = S.nextInt();
        for(int i=0;i<cadena.length();i++){
            ascii = ((int)cadena.charAt(i) + desplazamiento) % 27;
            cifrada += (char)ascii;
        }
        System.out.println(cadena);
        System.out.println(cifrada);
    }
}
