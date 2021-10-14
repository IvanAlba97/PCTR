package PCTR.practica1.src;

public class carnivoro extends animal {

    private enum tipo { ESTRICTO, PISCÍVORO, INSECTÍVORO, MIRMECÓFAGO }
    tipo tipoCarnivoro;

    public carnivoro(int nO, int nP, boolean pelo, tipo t) {
        super(nO, nP, pelo);
        this.tipoCarnivoro = t;
    }

    public void setTipoCarnivoro(tipo t)    { this.tipoCarnivoro = t;   }
    public tipo getTipoCarnivoro()          { return tipoCarnivoro;     }
}
