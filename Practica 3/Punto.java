
public class Punto {

    double x, y; // Variables de instancia

    public Punto(double x, double y) { // El constructor
        this.x = x;
        this.y = y;
    }

    public void moverEn(double dx, double dy) { // Un metodo
        this.x += dx;
        this.y += dy;
    }

    @Override
    public String toString() { // Otro metodo
        return "(" + this.x + "," + this.y + ")";
    }
}
