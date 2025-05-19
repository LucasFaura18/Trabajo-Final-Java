package participa;

public class Participa {
    long CodPel;
    long CodPer;
    String Rol;

    public Participa(){
        this( 0, 0, "");
    }
    
    public Participa(Participa par) {
        this.CodPel = par.CodPel;
        this.CodPer = par.CodPer;
        this.Rol = par.Rol;
    }

    public Participa(long CodPel, long CodPer, String Rol){
        this.CodPel = CodPel;
        this.CodPer = CodPer;
        this.Rol = Rol;
    }

    public long getCodPel() {
        return CodPel;
    }
    public void setCodPel(long codPel) {
        CodPel = codPel;
    }

    public long getCodPer() {
        return CodPer;
    }
    public void setCodPer(long codPer) {
        CodPer = codPer;
    }

    public String getRol() {
        return Rol;
    }
    public void setRol(String rol) {
        Rol = rol;
    }

    @Override
    public String toString() {
        return String.format("|CodPel: %2d, CodPer: %2d, Rol: %-12s|", this.CodPel, this.CodPer, this.Rol);
    }
}
