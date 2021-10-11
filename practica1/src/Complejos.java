/**
 *
 * @author ivan0
 */
public class Complejos {
    
    double real, imaginaria;
    
    public Complejos(double real, double imaginaria) {
        this.real = real;
        this.imaginaria = imaginaria;
    }
    
    public void suma(Complejos c) { 
        real += c.real;
        imaginaria += c.imaginaria;
    }
    
    public void resta(Complejos c) {
        real -= c.real;
        imaginaria -= c.imaginaria;
    }
    
    public double modulo() {
        double modulo;
        modulo = Math.sqrt(real*real + imaginaria*imaginaria);
        return modulo;
    }
    
    public void producto(Complejos c) {
        double prod = real * c.real - imaginaria * c.imaginaria;
        imaginaria = real * c.imaginaria + imaginaria * c.real;
        real = prod;
    }

    public void division(Complejos c) {
        Complejos com1 = new Complejos(0, 0);
        Complejos com2 = new Complejos(0, 0);

        com1.real = this.real;
        com2.real = this.real;
        com1.imaginaria = this.imaginaria;
        com2.imaginaria = c.imaginaria;
        com2.imaginaria = (-1) * com2.imaginaria;
        com1.producto(com2);
        c.producto(com2);

        real = com1.real / c.real;
        imaginaria = com1.imaginaria / com2.imaginaria;
    }
}