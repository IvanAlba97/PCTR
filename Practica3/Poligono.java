
public abstract class Poligono {

    protected Punto P[];

    public Poligono(Punto P[]) {
        super();
        this.P = new Punto[P.length];
        for (int i = 0; i < P.length; i++) {
            this.P[i] = P[i];
        }
    }

    public int getnLados() {
        return P.length;
    }

    @Override
    public String toString() {
        return "Poligono{" + "nLados=" + getnLados() + '}';
    }

    public abstract double area();

    public abstract double perimetro();

    /*
        abstract quiere decir que el metodo debe ser implementado en las clases
        hijas, por ello, la clase Poligono tambien es abstracta
     */
}
