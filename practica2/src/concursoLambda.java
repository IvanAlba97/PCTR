package practica2.src;

/**
 * Esta clase genera una condición de concurso sobre la variable n mediante expresiones lambda
 * @author Iván Alba Gómez
 * @version 3.0
 */
public class concursoLambda {
    
    private static int n = 0;
    
    /** 
     * Método principal
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {

        Runnable sum = () -> { for (int i = 0; i < 1000000; i++) n++; };
        Runnable res = () -> { for (int i = 0; i < 1000000; i++) n--; };

        Thread h1 = new Thread(sum);
        Thread h2 = new Thread(res);
        h1.start();
        h2.start();
        h1.join();
        h2.join();

        System.out.print("Valor de n: " + n);
    }
}