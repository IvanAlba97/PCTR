package PCTR.practica1.src;

public class omnivoro extends animal{
    
    public enum tipo { TERRESTRE, ACUÁTICO, VOLADOR }
    tipo tipoOmnivoro;

    public omnivoro(int nO, int nP, boolean pelo, tipo t) {
        super(nO, nP, pelo);
        this.tipoOmnivoro = t;
    }

    public void setTipoomnivoro(tipo t)    { this.tipoOmnivoro = t;   }
    public tipo getTipoomnivoro()          { return tipoOmnivoro;     }
}
