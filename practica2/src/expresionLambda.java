package PCTR.practica2.src;

public class expresionLambda {
    
    private static int n = 0;

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