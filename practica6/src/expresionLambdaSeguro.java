/**
 * Esta clase contiene los atributos y metodos para modificar una variable con expresiones lambda synchronized
 * @author Iván Alba Gómez
 * @version 3.0
 */
public class expresionLambdaSeguro {
    
    private static int n = 0;
    private static Object cerrojo = new Object();

    /** 
     * Método principal.
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {

        Runnable sum = () -> { for (int i = 0; i < 1000000; i++) synchronized(cerrojo) { n++; } };
        Runnable res = () -> { for (int i = 0; i < 1000000; i++) synchronized(cerrojo) { n--; } };

        Thread h1 = new Thread(sum);
        Thread h2 = new Thread(res);
        h1.start();
        h2.start();
        h1.join();
        h2.join();

        System.out.print("Valor de n: " + n);
    }
}