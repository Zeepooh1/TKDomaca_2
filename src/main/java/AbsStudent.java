import java.io.Serializable;

public abstract class AbsStudent implements Student, Serializable {
    protected int id;
    protected String ime;
    protected String priimek;
    protected float povprecje;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPriimek() {
        return priimek;
    }

    public void setPriimek(String priimek) {
        this.priimek = priimek;
    }

    public void setPovprecje(float povprecje) {
        this.povprecje = povprecje;
    }

    public float getPovprecje() {
        return povprecje;
    }

    public String toString(){
        return String.format("\t%d\t| %s, %s\t| %.1f", id, priimek, ime, povprecje);
    }
}
