public class StudentVpisna extends AbsStudent{
    @Override
    public int compareTo(Object o) {
        if(!(o instanceof StudentVpisna)){
            throw new IllegalArgumentException();
        }
        return this.id - ((StudentVpisna)o).getId();
    }

    public StudentImePriimek toImePriimek(){
        StudentImePriimek a = new StudentImePriimek();
        a.setId(this.id);
        a.setIme(this.ime);
        a.setPovprecje(this.povprecje);
        a.setPriimek(this.priimek);
        return a;
    }
}
