
public class Pentagono extends Poligono{
    
    public double lado;
    
    public Pentagono(Punto P[]){
        super(P);
        lado = Math.sqrt(Math.pow(P[1].x - P[0].x, 2) + Math.pow(P[1].y - P[0].y, 2));
    }
    
    @Override
    public double area(){
        return 1.72 * Math.pow(lado, 2);
    }
    
    @Override
    public double perimetro(){
        return 5 * lado;
    }
    
}
