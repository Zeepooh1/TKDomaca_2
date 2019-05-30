import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

public interface Seznam<Tip> {

    // dodajanje elementa v podatkovno strukturo
    void add(Tip e);


    // število elementov v podatkovni strukturi
    int size();

    // odstranjevanje (in vracanje) dolocenega elementa iz strukture
    Tip remove(Tip e);
   
    // ali element obstaja v strukturi
    boolean exists(Tip e);

    // vrne elemente kot List<Tip>
    List<Tip> asList();

    StudentVpisna get(int vpisna);

    StudentImePriimek get(String ime, String priimek);

        //Izpiše podatkovno strukturo
    String print();

    //shrani podatkovno strukturo v datoteko
    void save(OutputStream outputStream) throws IOException;

    //prebere podatkovno strukturo v datoteko
    void restore(InputStream inputStream) throws  IOException, ClassNotFoundException;
}
