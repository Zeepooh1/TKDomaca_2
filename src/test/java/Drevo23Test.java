import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;

public class Drevo23Test {

    @Test
    public void testAdd() {
        Drevo23<Integer> tree = new Drevo23<>();
        tree.add(15);
        tree.add(6);
        tree.add(30);
        tree.add(17);
        tree.add(12);
        tree.add(13);
        tree.add(16);
        tree.add(0);
        tree.add(7);
        tree.add(8);
        tree.add(9);
        tree.add(18);
        tree.add(25);
        tree.add(19);
        tree.add(11);
        tree.add(45);
        tree.add(33);

        Integer rightVal = 25;
        assertEquals(rightVal, tree.rootNode.S.valOne);
        rightVal = 13;
        assertEquals(rightVal, tree.rootNode.L.S.S.valOne);
        rightVal = 15;
        assertEquals(rightVal, tree.rootNode.valOne);
        rightVal = 19;
        assertEquals(rightVal, tree.rootNode.S.L.S.valTwo);

        boolean caught = false;
        try{
            tree.add(30);
        }
        catch(IllegalArgumentException e){
            caught = true;
        }
        assertTrue(caught);

    }

    @Test
    public void testSize(){
        Drevo23<Integer> tree = new Drevo23<>();
        tree.add(15);
        tree.add(6);
        tree.add(30);
        tree.add(17);
        tree.add(12);
        tree.add(13);

        assertEquals(6, tree.size());

        tree.add(16);
        tree.add(0);
        tree.add(7);
        tree.add(8);
        tree.add(9);
        tree.add(18);

        assertEquals(12, tree.size());

        tree.add(25);
        tree.add(19);
        tree.add(11);
        tree.add(45);
        tree.add(33);

        assertEquals(17, tree.size());
    }

    @Test
    public void testExists(){
        Drevo23<Integer> tree = new Drevo23<>();
        tree.add(15);
        tree.add(6);
        tree.add(30);
        tree.add(17);
        tree.add(12);
        tree.add(13);
        tree.add(16);
        tree.add(0);
        tree.add(7);
        tree.add(8);
        tree.add(9);
        tree.add(18);
        tree.add(25);
        tree.add(19);
        tree.add(11);
        tree.add(45);
        tree.add(33);

        Integer find = 18;
        assertTrue(tree.exists(find));

        find = 0;
        assertTrue(tree.exists(find));

        find = 10;
        assertFalse(tree.exists(find));

        find = 13;
        assertTrue(tree.exists(find));
    }

    @Test
    public void testRemove(){
        Drevo23<Integer> tree = new Drevo23<>();
        tree.add(15);
        tree.add(6);
        tree.add(30);
        tree.add(17);
        tree.add(12);
        tree.add(13);
        tree.add(16);

        tree.remove(12);

        Integer res = 16;
        assertEquals(res, tree.rootNode.S.valOne);

        tree.add(10);
        tree.remove(16);

        res = 30;
        assertEquals(res, tree.rootNode.R.valTwo);


    }

    @Test
    public void testAsList(){
        Drevo23<Integer> tree = new Drevo23<>();
        tree.add(15);
        tree.add(6);
        tree.add(30);
        tree.add(17);
        tree.add(12);
        tree.add(13);
        tree.add(16);
        tree.add(0);
        tree.add(7);
        tree.add(8);
        List<Integer> values = new ArrayList<>(Arrays.asList(0, 6, 7, 8, 12, 13, 15, 16, 17, 30));
        assertEquals(values, tree.asList());
    }

    @Test
    public void testToString(){
        Drevo23<Integer> tree = new Drevo23<>();
        tree.add(15);
        tree.add(6);
        tree.add(30);
        tree.add(17);
        tree.add(12);
        tree.add(13);
        tree.add(16);
        tree.add(0);
        tree.add(7);
        tree.add(8);
        String v = "(30)(17)(16)(15)(13)(6,12)(7,8)(0)";
        assertEquals(v, tree.toString());
    }
}