import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;


class Element23<Tip extends Comparable>{
    public Tip valOne;
    public Tip valTwo;
    public Element23<Tip> L;
    public Element23<Tip> S;
    public Element23<Tip> R;
    public Element23<Tip> above;



    public Element23(Element23<Tip> above) {
        this.above = above;
    }
    public Element23() {
        above = null;
    }
    public Element23(Element23<Tip> above, Tip val){
        this.above = above;
        this.valOne = val;

    }

    public void switchVals(){
        Tip tmp = valOne;
        valOne = valTwo;
        valTwo = tmp;
    }

    public boolean isLeaf(){
        return L == null && S == null && R == null;
    }


    public Element23[] split(Element23<Tip> above){
        Element23<Tip> levi = new Element23<>(above, this.valOne);
        Element23<Tip> desni = new Element23<>(above, this.valTwo);
        Element23[] ret = new Element23[2];
        ret[0] = levi;
        ret[1] = desni;
        return ret;
    }

    public int has(Tip e){
        if(this.valOne != null){
            if(e.compareTo(this.valOne) == 0){
                return 0;
            }
            else if(e.compareTo(this.valOne) < 0){
                return 1;
            }
            if(this.valTwo != null){
                if(e.compareTo(this.valTwo) == 0){
                    return 0;
                }
                else if(e.compareTo(this.valTwo) > 0) {
                    return 3;
                }
                else{
                    return 2;
                }
            }

        }
        return 2;
    }

    public int numNode(){
        if(this.valTwo != null){
            return 2;
        }
        if(this.valOne != null){
            return 1;
        }
        return 0;
    }

    public void delete(Tip e){
        if(e.compareTo(this.valOne) == 0){
            this.valOne = null;
            if(this.valTwo != null){
                this.switchVals();
            }

        }
        else{
            this.valTwo = null;
        }
    }

    public Element23<Tip> nextInOrder(){
        if(this.L != null){
            return this.L.nextInOrder();
        }
        return this;
    }

}

class SimpleNode<Tip extends Comparable>{
    public Element23<Tip> above;
    public Element23<Tip> left;
    public Element23<Tip> right;
    public Tip value;
}

public class Drevo23<Tip extends Comparable> implements Seznam<Tip> {
    enum SideOfNode{LEFT, MIDDLE, RIGHT}
    public Element23<Tip> rootNode;
    private int size;
    private int depth;

    public Drevo23(){
        rootNode = new Element23<>(null);
        size = 0;
        depth = 0;
    }

    @Override
    public void add(Tip e) {
        insert(rootNode, e);
        size++;

    }

    private void insert(Element23<Tip> currNode, Tip e){
        if(currNode.isLeaf()){
            if(currNode.valOne == null){
                currNode.valOne = e;

            }
            else{
                if(e.compareTo(currNode.valOne) == 0){
                    throw new IllegalArgumentException();
                }
                if(currNode.valTwo != null){
                    Tip sentUp;
                    if(e.compareTo(currNode.valOne) < 0){
                        sentUp = currNode.valOne;
                        currNode.valOne = e;
                    }
                    else if(e.compareTo(currNode.valTwo) > 0){
                        sentUp = currNode.valTwo;
                        currNode.valTwo = e;
                    }
                    else{
                        sentUp = e;
                    }
                    Element23<Tip>[] split = currNode.split(currNode.above);
                    SimpleNode<Tip> node = new SimpleNode<>();
                    node.left = split[0];
                    node.right = split[1];
                    node.above = currNode.above;
                    node.value = sentUp;
                    insertUp(currNode, node);

                }
                else{
                    if(e.compareTo(currNode.valOne) < 0){
                        currNode.switchVals();
                        currNode.valOne = e;
                    }
                    else{
                        currNode.valTwo = e;
                    }
                }
            }
        }
        else{
            if(currNode.valTwo == null){
                if(e.compareTo(currNode.valOne) == 0){
                    throw new IllegalArgumentException();
                }
                if(e.compareTo(currNode.valOne) < 0){
                    insert(currNode.L, e);
                }
                else{
                    insert(currNode.S, e);
                }

            }
            else{
                if(e.compareTo(currNode.valOne) == 0){
                    throw new IllegalArgumentException();
                }
                if(e.compareTo(currNode.valTwo) == 0){
                    throw new IllegalArgumentException();
                }
                if(e.compareTo(currNode.valTwo) > 0){
                    insert(currNode.R, e);
                }
                else if(e.compareTo(currNode.valOne) < 0){
                    insert(currNode.L, e);
                }
                else{
                    insert(currNode.S, e);
                }
            }
        }
    }

    private void insertUp(Element23<Tip> currNode, SimpleNode e){
        Element23<Tip> aboveNode = currNode.above;
        if(aboveNode != null) {
            int parentNode = aboveNode.numNode();
            if(parentNode == 1){
                if(currNode == aboveNode.L){
                    aboveNode.switchVals();
                    aboveNode.valOne = (Tip)e.value;
                    aboveNode.R = aboveNode.S;
                    aboveNode.S = e.right;
                    aboveNode.L = e.left;
                    aboveNode.S.above = aboveNode;
                    aboveNode.L.above = aboveNode;
                }
                else{
                    aboveNode.valTwo = (Tip)e.value;
                    aboveNode.R = e.right;
                    aboveNode.S = e.left;
                    aboveNode.R.above = aboveNode;
                    aboveNode.S.above = aboveNode;
                }

            }
            else{
                if(currNode == aboveNode.L){
                    Element23<Tip> tmpNode = new Element23<>();
                    tmpNode.L = e.left;
                    tmpNode.S = e.right;
                    tmpNode.L.above = tmpNode;
                    tmpNode.S.above = tmpNode;
                    tmpNode.valOne = (Tip)e.value;
                    e.value = aboveNode.valOne;
                    aboveNode.delete(aboveNode.valOne);
                    aboveNode.L = aboveNode.S;
                    aboveNode.S = aboveNode.R;
                    aboveNode.R = null;
                    e.left = tmpNode;
                    e.right = aboveNode;
                    e.above = aboveNode.above;
                    insertUp(aboveNode, e);
                }
                else if(currNode == aboveNode.S){
                    Element23<Tip> tmpNode = new Element23<>();
                    tmpNode.valOne = aboveNode.valTwo;
                    aboveNode.delete(aboveNode.valTwo);
                    aboveNode.S = e.left;
                    aboveNode.S.above = aboveNode;
                    tmpNode.L = e.right;
                    tmpNode.L.above = tmpNode;
                    tmpNode.S = aboveNode.R;
                    tmpNode.S.above = tmpNode;
                    aboveNode.R = null;
                    e.left = aboveNode;
                    e.right = tmpNode;
                    insertUp(aboveNode, e);
                }
                else{
                    Element23<Tip> tmpNode = new Element23<>();
                    tmpNode.valOne = (Tip)e.value;
                    tmpNode.L = e.left;
                    tmpNode.S = e.right;
                    tmpNode.L.above = tmpNode;
                    tmpNode.S.above = tmpNode;

                    e.value = aboveNode.valTwo;
                    aboveNode.delete(aboveNode.valTwo);
                    aboveNode.R = null;
                    e.left = aboveNode;
                    e.right = tmpNode;
                    insertUp(aboveNode, e);

                }
            }

        }
        else{
            aboveNode = new Element23<>();
            aboveNode.valOne = (Tip)e.value;
            rootNode = aboveNode;
            aboveNode.L = e.left;
            aboveNode.S = e.right;
            aboveNode.L.above = aboveNode;
            aboveNode.S.above = aboveNode;
            depth++;
        }
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public Tip remove(Tip e) {
        if(!exists(e)){
            throw new NoSuchElementException();
        }
        Element23<Tip> affectedLeaf = deleteDownPhase(this.rootNode, e);
        while(affectedLeaf != null && affectedLeaf != rootNode) {
            affectedLeaf = deleteUpPhase(affectedLeaf);
        }
        if(affectedLeaf != null && affectedLeaf == rootNode && !affectedLeaf.isLeaf()){
            this.rootNode = rootNode.S;
            this.rootNode.above = null;
            depth--;
        }
        size--;
        return e;
    }

    private Element23<Tip> deleteDownPhase(Element23<Tip> currNode, Tip e){
        int loc = currNode.has(e);
        Element23<Tip> ret = null;
        switch(loc){
            case 0:
                Element23<Tip> nextInOrder = null;
                if(currNode.isLeaf()){
                    currNode.delete(e);
                    if(currNode.numNode() == 0){
                        return currNode;
                    }
                }
                else{

                    if(e.compareTo(currNode.valOne) == 0){
                        nextInOrder = currNode.S.nextInOrder();
                        currNode.valOne = nextInOrder.valOne;
                        nextInOrder.delete(nextInOrder.valOne);

                    }
                    else{
                        nextInOrder = currNode.R.nextInOrder();
                        currNode.valTwo = nextInOrder.valOne;
                        nextInOrder.delete(nextInOrder.valOne);


                    }
                    if(nextInOrder.numNode() != 0){
                        nextInOrder = null;
                    }


                }
                ret = nextInOrder;
                break;

            case 1:
                ret =  deleteDownPhase(currNode.L, e);
                break;

            case 2:
                ret = deleteDownPhase(currNode.S, e);
                break;

            case 3:
                ret = deleteDownPhase(currNode.R, e);
                break;
        }

        return ret;
    }

    private Element23<Tip> deleteUpPhase(Element23<Tip> currNode){
        int parentNode = currNode.above.numNode();
        int siblingNode;
        SideOfNode sideOfNode;
        if(parentNode == 1){
            if(currNode.above.L == currNode){
                siblingNode = currNode.above.S.numNode();
                sideOfNode = SideOfNode.LEFT;
            }
            else{
                siblingNode = currNode.above.L.numNode();
                sideOfNode = SideOfNode.MIDDLE;
            }
            if(siblingNode == 1){
                if(sideOfNode == SideOfNode.LEFT){
                    Element23<Tip> sib = currNode.above.S;
                    sib.switchVals();
                    sib.valOne = sib.above.valOne;
                    sib.above.delete(sib.above.valOne);
                    sib.R = sib.S;
                    sib.S = sib.L;
                    sib.L = currNode.S;
                    sib.above.L = null;
                    if(!sib.isLeaf()){
                        sib.L.above = sib;
                    }
                    return sib.above;
                }
                else{
                    Element23<Tip> sib = currNode.above.L;
                    currNode.valTwo = currNode.above.valOne;
                    currNode.above.delete(currNode.above.valOne);
                    currNode.valOne = sib.valOne;
                    currNode.R = currNode.S;
                    currNode.S = sib.S;
                    currNode.L = sib.L;
                    currNode.above.L = null;
                    if(!currNode.isLeaf()) {
                        currNode.S.above = currNode;
                        currNode.L.above = currNode;
                    }
                    return currNode.above;
                }

            }
            //siblingNode = 2
            else{
                if(sideOfNode == SideOfNode.LEFT){
                    Element23<Tip> sib = currNode.above.S;
                    currNode.valOne = currNode.above.valOne;
                    currNode.above.valOne = sib.valOne;
                    sib.delete(sib.valOne);
                    currNode.L = currNode.S;
                    currNode.S = sib.L;
                    if(!currNode.isLeaf()) {
                        currNode.S.above = currNode;
                    }
                    sib.L = sib.S;
                    sib.S = sib.R;
                    sib.R = null;
                }
                else{
                    Element23<Tip> sib = currNode.above.L;
                    currNode.valOne = currNode.above.valOne;
                    currNode.above.valOne = sib.valTwo;
                    currNode.L = sib.R;
                    if(!currNode.isLeaf()) {
                        currNode.L.above = currNode;
                    }
                    sib.delete(sib.valTwo);
                    sib.R = null;
                }
            }
        }
        //parentNode = 2;
        else{
            if(currNode.above.L == currNode){
                sideOfNode = SideOfNode.LEFT;
                siblingNode = currNode.above.S.numNode();
            }
            else if(currNode.above.S == currNode){
                sideOfNode = SideOfNode.MIDDLE;
                siblingNode = currNode.above.L.numNode();
            }
            else{
                sideOfNode = SideOfNode.RIGHT;
                siblingNode = currNode.above.S.numNode();
            }
            if(siblingNode == 1){
                if(sideOfNode == SideOfNode.LEFT){
                    Element23<Tip> sib = currNode.above.S;
                    currNode.valOne = currNode.above.valOne;
                    currNode.valTwo = sib.valOne;
                    currNode.above.delete(currNode.above.valOne);
                    currNode.L = currNode.S;
                    currNode.S = sib.L;
                    currNode.R = sib.S;
                    currNode.above.S = currNode.above.R;
                    currNode.above.R = null;
                    if(!currNode.isLeaf()) {
                        currNode.S.above = currNode;
                        currNode.R.above = currNode;
                    }
                }
                else if(sideOfNode == SideOfNode.MIDDLE){
                    Element23<Tip> sib = currNode.above.L;
                    currNode.valOne = sib.valOne;
                    currNode.valTwo = currNode.above.valOne;
                    currNode.R = currNode.S;
                    currNode.S = sib.S;
                    currNode.L = sib.L;
                    currNode.above.delete(currNode.above.valOne);
                    currNode.above.L = currNode.above.S;
                    currNode.above.S = currNode.above.R;
                    currNode.above.R = null;
                    if(!currNode.isLeaf()) {
                        currNode.S.above = currNode;
                        currNode.L.above = currNode;
                    }
                }
                else{
                    Element23<Tip> sib = currNode.above.S;
                    sib.valTwo = sib.above.valTwo;
                    sib.above.delete(sib.above.valTwo);
                    sib.R = currNode.S;
                    if(!sib.isLeaf()) {
                        sib.R.above = sib;
                    }
                    sib.above.R = null;
                }
            }
            else if(siblingNode == 2){
                if(sideOfNode == SideOfNode.LEFT){
                    Element23<Tip> sib = currNode.above.S;
                    currNode.valOne = currNode.above.valOne;
                    currNode.above.valOne = sib.valOne;
                    sib.delete(sib.valOne);
                    currNode.L = currNode.S;
                    currNode.S = sib.L;
                    sib.L = sib.S;
                    sib.S = sib.R;
                    sib.R = null;
                    if(!currNode.isLeaf()) {
                        currNode.S.above = currNode;
                    }
                }
                else if(sideOfNode == SideOfNode.MIDDLE){
                    Element23<Tip> sib = currNode.above.L;
                    currNode.valOne = currNode.above.valOne;
                    currNode.above.valOne = sib.valTwo;
                    sib.delete(sib.valTwo);
                    currNode.L = sib.R;
                    if(!currNode.isLeaf()) {
                        currNode.L.above = currNode;
                    }
                    sib.R = null;
                }
                else{
                    Element23<Tip> sib = currNode.above.S;
                    currNode.valOne = currNode.above.valTwo;
                    currNode.above.valTwo = sib.valTwo;
                    sib.delete(sib.valTwo);
                    currNode.L = sib.R;
                    sib.R = null;
                    if(!currNode.isLeaf()) {
                        currNode.L.above = currNode;
                    }
                }
            }
        }
        return null;
    }

    @Override
    public boolean exists(Tip e) {
        Element23<Tip> currNode = rootNode;
        boolean found = false;
        int foundType;
        while(currNode != null && !found){
            foundType = currNode.has(e);
            switch(foundType){
                case 0:
                    found = true;
                    break;
                case 1:
                    currNode = currNode.L;
                    continue;
                case 2:
                    currNode = currNode.S;
                    continue;
                case 3:
                    currNode = currNode.R;
                    break;
            }
        }

        return found;
    }

    @Override
    public List<Tip> asList() {
        List<Tip> ret = new ArrayList<>();
        preOrderAdd(ret, this.rootNode);
        return ret;
    }

    private void preOrderAdd(List<Tip> list, Element23<Tip> currNode){
        if(currNode.isLeaf()){
            if(currNode.numNode() == 2){
                list.add(currNode.valOne);
                list.add(currNode.valTwo);
            }
            else{
                list.add(currNode.valOne);
            }
        }
        else{

            preOrderAdd(list, currNode.L);
            list.add(currNode.valOne);
            preOrderAdd(list, currNode.S);
            if(currNode.numNode() == 2){
                list.add(currNode.valTwo);
                preOrderAdd(list, currNode.R);
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder ret = new StringBuilder();
        preOrderToString(ret, rootNode);
        return ret.toString();
    }

    private void preOrderToString(StringBuilder sb, Element23<Tip> currNode){
        if(currNode.isLeaf()){
            sb.append("(" + currNode.valOne);
            if(currNode.numNode() == 2){
                sb.append("," + currNode.valTwo);
            }
            sb.append(")");
        }
        else{
            if(currNode.numNode() == 2){
                preOrderToString(sb, currNode.R);
                sb.append("(" + currNode.valOne);
                sb.append("," + currNode.valTwo);
                sb.append(")");
                preOrderToString(sb, currNode.S);
                preOrderToString(sb, currNode.L);
            }
            else{
                preOrderToString(sb, currNode.S);
                sb.append("(" + currNode.valOne + ")");
                preOrderToString(sb, currNode.L);
            }
        }

    }

    @Override
    public StudentVpisna get(int vpisna){
        Element23<Tip> currNode = rootNode;
        Tip e = (Tip)new StudentVpisna();
        ((StudentVpisna)e).setId(vpisna);
        boolean found = false;
        int foundType;
        while(currNode != null && !found){
            foundType = currNode.has(e);
            switch(foundType){
                case 0:
                    found = true;
                    break;
                case 1:
                    currNode = currNode.L;
                    continue;
                case 2:
                    currNode = currNode.S;
                    continue;
                case 3:
                    currNode = currNode.R;
                    break;
            }
        }

        if(!found) return null;

        if(currNode.valOne.compareTo(e) == 0) return (StudentVpisna)currNode.valOne;
        return (StudentVpisna)currNode.valTwo;

    }

    @Override
    public StudentImePriimek get(String ime, String priimek){
        Element23<Tip> currNode = rootNode;
        Tip e = (Tip)new StudentImePriimek();
        ((StudentImePriimek)e).setIme(ime);
        ((StudentImePriimek)e).setPriimek(priimek);
        boolean found = false;
        int foundType;
        while(currNode != null && !found){
            foundType = currNode.has(e);
            switch(foundType){
                case 0:
                    found = true;
                    break;
                case 1:
                    currNode = currNode.L;
                    continue;
                case 2:
                    currNode = currNode.S;
                    continue;
                case 3:
                    currNode = currNode.R;
                    break;
            }
        }

        if(!found) return null;

        if(currNode.valOne.compareTo(e) == 0) return (StudentImePriimek)currNode.valOne;
        return (StudentImePriimek)currNode.valTwo;

    }

    @Override
    public String print() {
        StringBuilder ret = new StringBuilder();
        for(Tip i : asList()){
            ret.append(i.toString() + "\n");
        }

        return ret.toString();
    }

    @Override
    public void save(OutputStream outputStream) throws IOException {
        ObjectOutputStream out = new ObjectOutputStream(outputStream);
        out.writeInt(size);
        out.writeInt(depth);
        save(rootNode, out);
    }

    private void save(Element23<Tip> node, ObjectOutputStream out)throws IOException{
        if(node.isLeaf()){
            out.writeBoolean(true);
            out.writeInt(node.numNode());
            out.writeObject(node.valOne);
            if(node.numNode() == 2){
                out.writeObject(node.valTwo);
            }

        }
        else{
            out.writeBoolean(false);
            out.writeInt(node.numNode());
            save(node.L, out);
            save(node.S, out);
            if(node.numNode() == 2){
                save(node.R, out);
            }
            out.writeObject(node.valOne);
            if(node.numNode() == 2){
                out.writeObject(node.valTwo);
            }
        }
    }

    @Override
    public void restore(InputStream inputStream) throws IOException, ClassNotFoundException {
        ObjectInputStream in = new ObjectInputStream(inputStream);
        this.size = in.readInt();
        this.depth = in.readInt();
        rootNode = restore(in, null);
    }

    private Element23<Tip> restore(ObjectInputStream in, Element23<Tip> above) throws IOException, ClassNotFoundException{
        Element23<Tip> node = new Element23<>(above);
        boolean isLeaf = in.readBoolean();
        int numNode = in.readInt();

        if(isLeaf){
            node.valOne = (Tip)in.readObject();
            if(numNode == 2){
                node.valTwo = (Tip)in.readObject();
            }
        }
        else{
            node.L = restore(in, node);
            node.S = restore(in, node);
            if(numNode == 2){
                node.R = restore(in, node);
            }
            node.valOne = (Tip)in.readObject();
            if(numNode == 2){
                node.valTwo = (Tip)in.readObject();
            }
        }

        return node;

    }
}
