public class animal {

    private int nOjos, nPatas;
    private boolean pelo;

    public animal(int nO, int nP, boolean pelo) {
        this.nOjos = nO;
        this.nPatas = nP;
        this.pelo = pelo;
    }

    public void setNOjos(int nO)    { this.nOjos = nO;  }
    public void setNPatas(int nP)   { this.nPatas = nP; }
    public void setPelo(boolean p)  { this.pelo = p;    }
    public int getNOjos()           { return nOjos;     }
    public int getNPatas()          { return nPatas;    }
    public boolean getPelo()        { return pelo;      }
}
