
/**
 * @author ivan0
 */

import java.util.Scanner;

public class aleatorios {
    public static void main(String[] args){
        Scanner S = new Scanner(System.in);
        System.out.print("Introduce la longitud de la cadena: ");
        int n = S.nextInt();
        for(int i=0;i<n;i++){
            System.out.print(Math.random()+" ");
        }
    }
}
