import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

public class Drevo23Mock<Tip extends Comparable> implements Seznam<Tip> {
    @Override
    public void add(Tip e) {

    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public Tip remove(Tip e) {
        return null;
    }

    @Override
    public boolean exists(Tip e) {
        return false;
    }

    @Override
    public List<Tip> asList() {
        return null;
    }

    @Override
    public StudentVpisna get(int vpisna) {
        return null;
    }

    @Override
    public StudentImePriimek get(String ime, String priimek) {
        return null;
    }

    @Override
    public String print() {
        return null;
    }

    @Override
    public void save(OutputStream outputStream) throws IOException {
        throw new OutOfMemoryError();
    }

    @Override
    public void restore(InputStream inputStream) throws IOException, ClassNotFoundException {

    }
}
