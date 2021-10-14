package PCTR.practica1.src;

public class omnivoro extends animal{
    
    boolean humano;

    public omnivoro(int nO, int nP, boolean pelo, boolean humano) {
        super(nO, nP, pelo);
        this.humano = humano;
    }

    public void setHumano(boolean b)    { this.humano = b;   }
    public boolean getHumano()          { return humano;     }
}
