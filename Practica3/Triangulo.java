
public class Triangulo extends Poligono {

    public double lado1, lado2, lado3;

    public Triangulo(Punto P[]) {
        super(P);
        lado1 = Math.sqrt(Math.pow(P[1].x - P[0].x, 2) + Math.pow(P[1].y - P[0].y, 2));
        lado2 = Math.sqrt(Math.pow(P[2].x - P[1].x, 2) + Math.pow(P[2].y - P[1].y, 2));
        lado3 = Math.sqrt(Math.pow(P[0].x - P[2].x, 2) + Math.pow(P[0].y - P[2].y, 2));
    }

    @Override
    public double area() {
        double h = (lado1 + lado2 + lado3) / 2;
        return Math.sqrt(h * (h - lado1) * (h - lado2) * (h - lado3));  //Formula de Heron
    }

    @Override
    public double perimetro() {
        return lado1 + lado2 + lado3;
    }

}
