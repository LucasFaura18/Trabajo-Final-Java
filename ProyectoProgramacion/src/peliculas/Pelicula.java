package peliculas; 

public class Pelicula {
    long CodPel;
    String TitPel;
    String GenPel;
    long duracion;

    public Pelicula(){
        this( 0, "", "", 0);
    }
    
    public Pelicula(Pelicula pel) {
        this.CodPel = pel.CodPel;
        this.TitPel = pel.TitPel;
        this.GenPel = pel.GenPel;
        this.duracion = pel.duracion;
    }

    public Pelicula(long CodPel, String NomPel, String GenPel, long duracion){
        this.CodPel = CodPel;
        this.TitPel = NomPel;
        this.GenPel = GenPel;
        this.duracion = duracion;
    }

    public long getCodPel() {
        return CodPel;
    }
    public void setCodPel(long codPel) {
        CodPel = codPel;
    }

    public String getTitPel() {
        return TitPel;
    }
    public void setTitPel(String nomPel) {
        TitPel = nomPel;
    }

    public String getGenPel() {
        return GenPel;
    }
    public void setGenPel(String genPel) {
        GenPel = genPel;
    }

    public long getDuracion() {
        return duracion;
    }
    public void setDuracion(long duracion) {
        this.duracion = duracion;
    }

    @Override
    public String toString() {
        return String.format("|CodPel: %2d, Título: %-20s, Género: %-15s, Duracion: %3d|", this.CodPel, this.TitPel, this.GenPel, this.duracion);
    }
}
