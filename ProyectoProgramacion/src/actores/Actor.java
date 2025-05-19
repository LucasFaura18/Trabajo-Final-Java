package actores;

public class Actor {
    long CodAct;
    String NomAct;
    String ApeAct;

    public Actor(){
        this( 0, "", "");
    }
    
    public Actor(Actor act) {
        this.CodAct = act.CodAct;
        this.NomAct = act.NomAct;
        this.ApeAct = act.ApeAct;
    }

    public Actor(long CodAct, String NomAct, String ApeAct){
        this.CodAct = CodAct;
        this.NomAct = NomAct;
        this.ApeAct = ApeAct;
    }

    public long getCodAct() {
        return CodAct;
    }
    public void setCodAct(long codAct) {
        CodAct = codAct;
    }

    public String getNomAct() {
        return NomAct;
    }
    public void setNomAct(String nomAct) {
        NomAct = nomAct;
    }

    public String getApeAct() {
        return ApeAct;
    }
    public void setApeAct(String apeAct) {
        ApeAct = apeAct;
    }   

    @Override
    public String toString() {
        return String.format("|CodAct: %2d, Nombre: %-10s, Apellidos: %-10s|", this.CodAct, this.NomAct, this.ApeAct);
    }
}
