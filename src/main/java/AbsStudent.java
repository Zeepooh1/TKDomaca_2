public abstract class AbsStudent implements Student{
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

    public float getPovprecje() {
        return povprecje;
    }

    public void setPovprecje(float povprecje) {
        this.povprecje = povprecje;
    }

    public String toString(){
        return String.format("\t%d\t| %s, %s\t| %.1f", id, priimek, ime, povprecje);
    }
}
