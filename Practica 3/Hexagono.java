
public class Hexagono extends Poligono{
    
    public double lado;
    
    public Hexagono(Punto P[]){
        super(P);
        lado = Math.sqrt(Math.pow(P[1].x - P[0].x, 2) + Math.pow(P[1].y - P[0].y, 2));
    }
    
    @Override
    public double area(){
        return 2.6 * Math.pow(lado, 2);
    }
    
    @Override
    public double perimetro(){
        return 6 * lado;
    }
}
