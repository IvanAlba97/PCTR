
/**
 * @author ivan0
 */

import java.util.Scanner;

public class NewtonRaphson {
    public static void main(String[] args){
        Scanner S = new Scanner(System.in);
        int op;
        double pos = 0, aprox;
        
        System.out.print("Numero de iteraciones: ");
        int nIter = S.nextInt();
        
        do{
            System.out.println("Elige una de las opciones:");
            System.out.println("1. f(x) = cos(x) - x^3 en [0,1]");
            System.out.println("2. f(x) = x^2 - 5 en [2,3]");
            System.out.print("Opcion: ");
            op = S.nextInt();
        }while(op<1 || op>2);
        
        System.out.print("Introduce la aproximacion: ");
        aprox = S.nextDouble();
        
        switch(op){
            case 1: pos = f1(nIter, aprox); break;
            case 2: pos = f2(nIter, aprox); break;
        }
        
        System.out.println("La posicion del cero es: "+pos);
    }
    
    public static double f1(int nIter, double aprox){
        double funcion, derivada;
        for(int i=0;i<nIter;i++){
            funcion = Math.cos(aprox) - Math.pow(aprox, 3);
            derivada = -Math.sin(aprox) - 3 * Math.pow(aprox, 2);
            aprox -= (funcion / derivada);
            System.out.println("Iteracion: "+i+"; Aproximacion: "+aprox);
        }
        return aprox;
    }
    
    public static double f2(int nIter, double aprox){
        double funcion, derivada;
        for(int i=0;i<nIter;i++){
            funcion = Math.pow(aprox, 2) - 5;
            derivada = 2 * aprox;
            aprox -= (funcion / derivada);
            System.out.println("Iteracion: "+i+"; Aproximacion: "+aprox);
        }
        return aprox;
    }
}
