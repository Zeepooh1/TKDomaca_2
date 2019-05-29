public class StudentImePriimek extends AbsStudent {
    @Override
    public int compareTo(Object o) {
        if(!(o instanceof StudentImePriimek)){
            throw new IllegalArgumentException();
        }
        return (this.priimek + this.ime).compareTo(((StudentImePriimek)o).getPriimek() + ((StudentImePriimek)o).getIme());
    }

    public StudentVpisna toVpisna(){
        StudentVpisna a = new StudentVpisna();
        a.setId(this.id);
        a.setIme(this.ime);
        a.setPovprecje(this.povprecje);
        a.setPriimek(this.priimek);
        return a;
    }
}
