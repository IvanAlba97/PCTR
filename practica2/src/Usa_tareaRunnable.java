package PCTR.practica2.src;

import java.util.Scanner;

public class Usa_tareaRunnable implements Runnable {
    
    private tareaRunnable TR;
    private int tipoHilo, nIter;
    
    public Usa_tareaRunnable(tareaRunnable TR, int tipoHilo, int nIter) {
        this.TR = TR;
        this.tipoHilo = tipoHilo;
        this.nIter = nIter;
    }
    
    @Override
    public void run() {
       switch(tipoHilo) {
           case 0: for(int i = 0; i < nIter; i++) TR.inc(); break;
           case 1: for(int i = 0; i < nIter; i++) TR.dec(); break;
           default: System.err.println("Error en el switch.");
       }
    }
    
    public static void main(String[] args) {
        
        Scanner S = new Scanner(System.in);
        
        System.out.print("Introduce el numero de iteraciones: ");
        int nIter = S.nextInt();
        
        tareaRunnable TR = new tareaRunnable();
        Thread h1 = new Thread(new Usa_tareaRunnable(TR, 0, nIter));
        Thread h2 = new Thread(new Usa_tareaRunnable(TR, 1, nIter));
        h1.start(); h2.start();
        try { h1.join(); h2.join(); } catch(InterruptedException E) {
            System.err.println(E.getMessage());
        }
        System.out.println("El valor final de n es " + TR.getN());
        S.close();
    }
}