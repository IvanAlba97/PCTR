import java.util.Scanner;

/**
 * Esta clase contiene los atributos y metodos para el cálculo de números perfectos de manera secuencial.
 * @author Iván Alba Gómez
 * @version 3.0
 * @see numPerfectosParalelo.java
 */
public class numPerfectos {

    
    /** 
     * @param n
     * @return boolean
     */
    public static boolean esPerfecto(long n) {
        int suma = 0;
        for(long j = 1; j < n; j++) {
            if(n % j == 0) suma += j;
        }
        if(n == suma) return true;
        else return false;
    }

    
    /** 
     * Método principal.
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        Scanner S = new Scanner(System.in);
        System.out.print("Introduce la longitud del intervalo: 1 - ");
        int intervalo = S.nextInt();	  
        int total = 0;
        long inicTiempo = System.nanoTime();
        for(long i = 0; i <= intervalo; i++) if(esPerfecto(i)) total++;
        long tiempoTotal = (System.nanoTime()-inicTiempo)/(long)1.0e6;
        System.out.println("Encontrados " + total + " numeros perfectos" + " en " + tiempoTotal + " milisegundos.");
    }
}
