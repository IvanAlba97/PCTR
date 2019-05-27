
/**
 * @author ivan0
 */

import java.math.BigDecimal.*;

public class Circulo {
    static final double PI = Math.PI;
    
    public static void main(String[] args){
        double d = 14.2;
        double r = d/2;
        double h = 20;
        double volumen = (h*PI*Math.pow(r, 2))/3;
        System.out.println("El volumen del cono es: "+volumen);
    }
}
