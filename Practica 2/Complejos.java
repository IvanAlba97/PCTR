
public class Complejos {

    double real, imaginaria;

    public Complejos(double real, double imaginaria) {
        this.real = real;
        this.imaginaria = imaginaria;
    }

    public void suma(Complejos c2) {
        real += c2.real;
        imaginaria += c2.imaginaria;
    }

    public void resta(Complejos c2) {
        real -= c2.real;
        imaginaria -= c2.imaginaria;
    }

    public double modulo() {
        double modulo;
        modulo = Math.sqrt(real * real + imaginaria * imaginaria);
        return modulo;
    }

    public void producto(Complejos c2) {
        double prod = real * c2.real - imaginaria * c2.imaginaria;
        imaginaria = real * c2.imaginaria + imaginaria * c2.real;
        real = prod;
    }

    public void division(Complejos c2) {
        Complejos com1 = new Complejos(0, 0);
        Complejos com2 = new Complejos(0, 0);

        com1.real = this.real;
        com2.real = this.real;
        com1.imaginaria = this.imaginaria;
        com2.imaginaria = c2.imaginaria;
        com2.imaginaria = (-1) * com2.imaginaria;
        com1.producto(com2);
        c2.producto(com2);

        real = com1.real / c2.real;
        imaginaria = com1.imaginaria / com2.imaginaria;
    }
}
