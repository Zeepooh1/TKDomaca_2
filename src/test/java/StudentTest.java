import org.junit.Test;

import static org.junit.Assert.*;

public class StudentTest {

    @Test
    public void testCompareTo(){
        AbsStudent student = new StudentVpisna();
        student.setId(1);
        student.setIme("Ime");
        student.setPriimek("Priimek");
        student.setPovprecje(3.2f);

        boolean caught = false;
        try{
            student.compareTo(new Integer(3));
        }
        catch(IllegalArgumentException e){
            caught = true;
        }

        assertTrue(caught);

        caught = false;
        student = ((StudentVpisna) student).toImePriimek();

        try{
            student.compareTo(new Integer(3));
        }
        catch(IllegalArgumentException e){
            caught = true;
        }

        assertTrue(caught);

    }

}