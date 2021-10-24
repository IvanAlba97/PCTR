package PCTR.practica2.src;

import java.util.Scanner;

public class redCajeros {
    
    public static void main(String[] args) throws InterruptedException {

        Scanner S = new Scanner(System.in);
        cuentaCorriente cuenta = new cuentaCorriente();
        System.out.print("Introduce el numero de cajeros: ");
        int nCajeros = S.nextInt();
        Thread[] c = new Thread[nCajeros];
        for (int i = 0; i < c.length; i++) {
            c[i] = new Thread(new cajero(cuenta, i));
            c[i].start();
        }
        for (int i = 0; i < c.length; i++) c[i].join();
        System.out.println("Saldo: " + cuenta.getSaldo());
        S.close();
    }
}