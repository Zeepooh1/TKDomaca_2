import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

public interface Seznam<Tip> {

    // dodajanje elementa v podatkovno strukturo
    void add(Tip e);

    // odstranjevanje (in vračanje) prvega elementa iz pod. strukture
    Tip removeFirst();

    // vracanje prvega elementa iz podatkovne strukture
    Tip getFirst();

    // število elementov v podatkovni strukturi
    int size();

    // globina podatkovne strukture
    int depth();

    // ali je podakovna struktura prazna
    boolean isEmpty();
    
    // odstranjevanje (in vracanje) dolocenega elementa iz strukture
    Tip remove(Tip e);
   
    // ali element obstaja v strukturi
    boolean exists(Tip e);

    // vrne elemente kot List<Tip>
    List<Tip> asList();

    //Izpiše podatkovno strukturo
    void print();

    //shrani podatkovno strukturo v datoteko
    void save(OutputStream outputStream) throws IOException;

    //prebere podatkovno strukturo v datoteko
    void restore(InputStream inputStream) throws  IOException, ClassNotFoundException;
}
