import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class SeznamiUV {
    private String memoryError = "Error: not enough memory, operation failed";
    Map<String, Seznam<String>> seznami;
    Seznam<String> seznam;

    public SeznamiUV() {
        seznami = new HashMap<>();

    }
    public void addImpl(String key, Seznam<String> seznam) {
        seznami.put(key, seznam);
    }

    public String processInput(String input) {
        Scanner sc = new Scanner(input);
        String token;
        String result = "OK";
        if (sc.hasNext()) {
            token = sc.next();
        } else {
            return "Error: enter command";
        }
        if(token.equals("exit")){
            seznam = null;
            return "Have a nice day.";
        }
        else if (!token.equals("use") && (null == seznam)) {
            return "Error: please specify a data structure (use {pv|sk|bst|23})";
        }

        try {
            switch (token) {
                case "use":
                    if (sc.hasNext()) {
                          seznam = seznami.get(sc.next());
                        if (null == seznam) {
                            result = "Error: please specify a correct data structure type {pv|sk|bst|23}";
                        }
                    } else {
                        result = "Error: please specify a data structure type {pv|sk|bst|23}";
                    }
                    break;
                case "add":
                    if (sc.hasNext()) {
                        seznam.add(sc.next());
                    } else {
                        result = "Error: please specify a string";
                    }
                    break;
                case "remove_first":
                    result = seznam.removeFirst();
                    break;
                case "get_first":
                    result = seznam.getFirst();
                    break;
                case "size":
                    result = seznam.size() + "";
                    break;
                case "depth":
                    result = seznam.depth() + "";
                    break;
                case "is_empty":
                    result = "Data structure is " + (seznam.isEmpty() ? "" : "not ") + "empty.";
                    break;
                case "reset":
                    while (!seznam.isEmpty()) {
                        seznam.removeFirst();
                    }
                    break;
                case "exists":
                    if (sc.hasNext()) {
                        result = "Element " + (seznam.exists(sc.next()) ? "exists " : "doesn't exist ") + "in data structure.";
                    } else {
                        result = "Error: please specify a string";
                    }
                    break;
                case "remove":
                    if (sc.hasNext()) {
                        result = seznam.remove(sc.next());
                    } else {
                        result = "Error: please specify a string";
                    }
                    break;
                case "asList":
                    if(seznam instanceof Drevo23){
                        result = "The list created is: " + seznam.toString() + ".";
                    }
                    else {
                        result = "The list created is: " + seznam.asList().toString() + ".";
                    }
                    break;

                case "print":
                    seznam.print();
                    break;

                case "save":
                    if (sc.hasNext()) {
                        seznam.save(new FileOutputStream(sc.next()));
                    } else {
                        result = "Error: please specify a file name";
                    }
                    break;
                case "restore":
                    if (sc.hasNext()) {
                        seznam.restore(new FileInputStream(sc.next()));
                    } else {
                        result = "Error: please specify a file name";
                    }
                    break;
                default:
                    result = "Error: invalid command";
            }
        } catch (IllegalArgumentException e) {
            result = "Error: Duplicated entry";
        } catch (java.util.NoSuchElementException e) {
            result = "Error: data structure is empty";
        }
        catch (IOException e) {
            result = "Error: IO error " + e.getMessage();
        }
        catch (ClassNotFoundException e) {
            result = "Error: Unknown format";
        }
        catch( OutOfMemoryError e){
            System.err.println(memoryError);
            return memoryError;
        }


        return result;
    }

}
