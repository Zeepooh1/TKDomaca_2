import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SeznamiUVTest {

    private SeznamiUV uv;
    private List<String> values;
    private List<String> imena;
    private List<String> priimki;
    private List<Integer> ids;
    public SeznamiUVTest() {
    }

    @Before
    public void setUp() {
        uv = new SeznamiUV();

        values = new ArrayList<>(Arrays.asList("LF","OL","SJ","MS","FZ","ZE","NO","UN","TG","QH","WY","HI","XR","AU","CC","DA","EB","GQ","RT","IP","JW","PM","VK","BX","YD","KV","VC","WN","YP","XM","UT","AW","DU","TX","RB","GI","FS","MY","IJ","BG","OV","JD","CZ","KE","LR","NF","HH","ZA","PO","SL","QQ","EK"));
        imena = new ArrayList<>(values);
        Collections.shuffle(imena);
        priimki = new ArrayList<>(values);
        Collections.shuffle(priimki);
        ids = new ArrayList<>();
        for(int i = 0; i < values.size(); i++){
            ids.add(i);
        }
    }


    // TO DO
    // napišite teste za sklad, prioritetno vrsto in BS drevo
    // testi kličejo (zaporedoma) vse operacije nad določeno strukturo 
    // glej POMOZNE METODE

    
    @Test
    public void testUse23(){
        List<ByteArrayInputStream> in = readyInput();

        for(int i = 0; i < values.size(); i++) {
            System.setIn(in.get(i));
            assertEquals(">> OK", uv.processInput("add"));
        }

        assertEquals(">> Invalid argument", uv.processInput("add ams"));

        System.setIn(new ByteArrayInputStream("I3me".getBytes()));
        assertEquals(">> Invalid input data", uv.processInput("add"));
        System.setIn(new ByteArrayInputStream("234\nI3me".getBytes()));
        assertEquals(">> Invalid input data", uv.processInput("add"));
        System.setIn(new ByteArrayInputStream("234\nIme\nPr2mek".getBytes()));
        assertEquals(">> Invalid input data", uv.processInput("add"));
        System.setIn(new ByteArrayInputStream("234\nIme\nPrimek\npvop".getBytes()));
        assertEquals(">> Invalid input data", uv.processInput("add"));
        assertEquals(">> Invalid argument", uv.processInput("add something"));

        assertEquals(">> Invalid command", uv.processInput("sadfsd"));

        assertEquals(">> OK", uv.processInput(String.format("remove %d",ids.get(20))));
        assertEquals(">> Student does not exist", uv.processInput("remove 444"));

        System.setIn(new ByteArrayInputStream(String.format("%s\n%s", imena.get(10), priimki.get(10)).getBytes()));
        assertEquals(">> OK", uv.processInput("remove"));
        System.setIn(new ByteArrayInputStream("Ime\nPriimek".getBytes()));
        assertEquals(">> Student does not exist", uv.processInput("remove"));
        System.setIn(new ByteArrayInputStream("12me\nPriimek".getBytes()));
        assertEquals(">> Invalid input data", uv.processInput("remove"));
        System.setIn(new ByteArrayInputStream("Ime\nPr21imek".getBytes()));
        assertEquals(">> Invalid input data", uv.processInput("remove"));

        assertEquals(String.format(">> \t%d\t| %s, %s\t| 5.2", ids.get(30), priimki.get(30), imena.get(30)), uv.processInput(String.format("search %d", ids.get(30))));

        assertEquals(">> Invalid input data", uv.processInput("search test"));

        System.setIn(new ByteArrayInputStream(String.format("%s\n%s", imena.get(45), priimki.get(45)).getBytes()));
        assertEquals(String.format(">> \t%d\t| %s, %s\t| 5.2", ids.get(45), priimki.get(45), imena.get(45)), uv.processInput("search"));
        assertEquals(">> Student does not exist", uv.processInput("search 123"));

        String p = uv.processInput("print");
        System.setIn(new ByteArrayInputStream("y".getBytes()));
        assertEquals(">> OK", uv.processInput("save test.bin"));
        assertEquals(">> Error: please specify a file name", uv.processInput("save"));
        assertEquals(">> OK", uv.processInput("reset"));
        assertEquals(">> Error: please specify a file name", uv.processInput("restore"));
        assertEquals(">> OK", uv.processInput("restore test.bin"));
        assertEquals(p, uv.processInput("print"));

        System.setIn(new ByteArrayInputStream("y".getBytes()));
        assertEquals(">> OK", uv.processInput("reset"));
        assertEquals(">> No. of students: 0", uv.processInput("count"));
        assertEquals(">> No. of students: 0", uv.processInput("print"));

        assertEquals(">> OK", uv.processInput("restore test.bin"));
        assertEquals(">> No. of students: 50", uv.processInput("count"));
        System.setIn(new ByteArrayInputStream("n".getBytes()));
        assertEquals(">> OK", uv.processInput("reset"));
        assertEquals(">> No. of students: 50", uv.processInput("count"));

        assertEquals(">> Invalid argument", uv.processInput(""));
        assertEquals(">> Invalid argument", uv.processInput("reset all"));

        System.setIn(new ByteArrayInputStream("y".getBytes()));
        assertEquals(">> OK", uv.processInput("reset"));

        assertTrue(uv.processInput("restore wrong_tree.bin").matches(">> Error: IO error.*"));


        System.setIn(new ByteArrayInputStream("2\nIme\nPrimek\n2.2".getBytes()));
        assertEquals(">> OK", uv.processInput("add"));

        System.setIn(new ByteArrayInputStream("2\nIme\nPrimek\n2.2".getBytes()));
        assertEquals(">> Error: Duplicated entry", uv.processInput("add"));

        assertEquals(">> Goodbye", uv.processInput("exit"));




    }

    private List<ByteArrayInputStream> readyInput(){
        StringBuilder sb = new StringBuilder();

        Collections.shuffle(ids);
        List<ByteArrayInputStream> ret = new ArrayList<>();
        for(int i= 0; i < values.size(); i++){
            sb.append(String.format("%d\n%s\n%s\n5.2", ids.get(i), imena.get(i), priimki.get(i)));
            ret.add(new ByteArrayInputStream(sb.toString().getBytes()));
            sb = new StringBuilder();
        }
        return ret;
    }

    // *****************
    // POMOZNE METODE
    // *****************


}
