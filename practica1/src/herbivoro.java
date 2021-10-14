package PCTR.practica1.src;

public class herbivoro extends animal{

    private enum tipo { FRUGÍVORO, FOLÍVORO, XILÓFAGO, GRANÍVORO, RIZÓFAGO }
    tipo tipoHerbivoro;

    public herbivoro(int nO, int nP, boolean pelo, tipo t) {
        super(nO, nP, pelo);
        this.tipoHerbivoro = t;
    }

    public void setTipoHerbivoro(tipo t)    { this.tipoHerbivoro = t;   }
    public tipo getTipoHerbivoro()          { return tipoHerbivoro;     }
}
