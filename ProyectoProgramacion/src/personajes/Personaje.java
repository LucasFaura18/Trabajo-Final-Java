package personajes;

public class Personaje implements Serializer{
    long CodPer;
    String NomPer;
    String GenPer;
    Long CodAct;

    public Personaje(){
        this(0,"","", null);
    }

    public Personaje(Personaje per){
        this.CodPer = per.CodPer;
        this.NomPer = per.NomPer;
        this.GenPer = per.GenPer;
        this.CodAct = per.CodAct;
    }

    public Personaje(String data){
        deserialize(data);
    }

    public Personaje(long CodPer, String NomPer, String GenPer, Long CodAct){
        this.CodPer = CodPer;
        this.NomPer = NomPer;
        this.GenPer = GenPer;
        this.CodAct = CodAct;
    }

    public long getCodPer() {
        return CodPer;
    }
    public void setCodPer(long codPer) {
        CodPer = codPer;
    }

    public String getNomPer() {
        return NomPer;
    }
    public void setNomPer(String nomPer) {
        NomPer = nomPer;
    }

    public String getGenPer() {
        return GenPer;
    }
    public void setGenPer(String genPer) {
        GenPer = genPer;
    }

    public Long getCodAct() {
        return CodAct;
    }
    public void setCodAct(long codAct) {
        CodAct = codAct;
    }

    @Override
    public String toString() {
        return String.format("|CodPer: %2d, Nombre: %-16s, Genero: %-9s, CodAct: %2d|", this.CodPer, this.NomPer, this.GenPer, this.CodAct);
    }

    @Override
    public String serialize() {
        return String.format("\"%d\";\"%s\";\"%s;%s", this.CodPer, this.NomPer, this.GenPer, this.CodAct!=null?"\""+this.CodAct.toString()+"\"":"NULL");
    }

    private String substractQuotes(String data){
        return data.substring(1, data.length()-1);
    }

    @Override
    public void deserialize(String data) {
        String[] datos = data.split(";");
        
        this.CodPer = Integer.parseInt(datos[0].substring(1, datos[0].length()-1));
        this.NomPer = this.substractQuotes(datos[1]);
        this.GenPer = this.substractQuotes(datos[2]);
        
        if(datos[3].equals("NULL"))
            this.CodAct = null;
        else
            this.CodAct = Long.parseLong(this.substractQuotes(datos[3]));
        }


}
