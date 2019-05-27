
/**
 *
 * @author ivan0
 */

import java.util.Random;
import java.util.Scanner;

public class resImagen {

    public static void main(String[] args) {
        
        Scanner S = new Scanner(System.in);
        
        System.out.print("Introduce el orden de la matriz: ");
        int n = S.nextInt();
        n += 2;
        
        int[][] M = new int[n][n];
        int[][] M2 = new int[n][n];
        
        rellenar(M, n);
        resaltar(M, M2, n);
        mostrar(M, M2, n);
        
    }
    
    public static void rellenar(int[][] M, int n){
        
        Random rand = new Random(System.nanoTime());
        
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                M[i][j] = 0;
            }
        }
        
        for(int i=1;i<n-1;i++){
            for(int j=1;j<n-1;j++){
                M[i][j] = rand.nextInt(20) + 1;
            }
        }
    }
    
    public static void resaltar(int[][] M, int[][] M2, int n){
        
        for(int i=1;i<n-1;i++){
            for(int j=1;j<n-1;j++){
                M2[i][j] =(4*M[i][j]-M[i][j-1]-M[i][j+1]-M[i-1][j]-M[i+1][j]) / 8;
            }
        }
    }
    
    public static void mostrar(int[][] M, int[][] M2, int n){
        
        System.out.println("    .:MATRIZ NORMAL:.");
        for(int i=1;i<n-1;i++){
            for(int j=1;j<n-1;j++){
                System.out.print(M[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
        
        System.out.println("    .:MATRIZ RESALTADA:.");
        for(int i=1;i<n-1;i++){
            for(int j=1;j<n-1;j++){
                System.out.print(M2[i][j] + " ");
            }
            System.out.println();
        }
    }
}
