
public class Cuadrado extends Poligono {

    public double lado;

    public Cuadrado(Punto P[]) {
        super(P);
        lado = Math.sqrt(Math.pow(P[1].x - P[0].x, 2) + Math.pow(P[1].y - P[0].y, 2));
    }

    @Override
    public double area() {
        return lado * lado;
    }

    @Override
    public double perimetro() {
        return 4 * lado;
    }
}
