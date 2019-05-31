import org.codehaus.plexus.personality.plexus.lifecycle.phase.InitializationException;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class SeznamiUV {
    private String memoryError = "Error: not enough memory, operation failed";
    Seznam<StudentVpisna> seznam_vpisne;
    Seznam<StudentImePriimek> seznam_imePriimek;

    public SeznamiUV(Object o) {
        Class<? extends Object> a = o.getClass();
        try {
            seznam_vpisne = (Seznam<StudentVpisna>)a.newInstance();
            seznam_imePriimek = (Seznam<StudentImePriimek>)a.newInstance();
        }
        catch(IllegalAccessException e){

        }
        catch(InstantiationException e){

        }
    }

    public String processInput(String input) {
        Scanner sc = new Scanner(input);
        Scanner stdin = new Scanner(System.in);
        String token;
        String result = "OK";
        if (sc.hasNext()) {
            token = sc.next();
        } else {
            return ">> Invalid argument";
        }
        if(token.equals("exit")){
            seznam_vpisne = null;
            seznam_imePriimek = null;
            return ">> Goodbye";
        }

        try {
            switch (token) {
                case "add":
                    if (!sc.hasNext()) {
                        AbsStudent a = new StudentVpisna();
                        System.out.print("add> Student ID: ");
                        String ime;
                        String priimek;
                        try{
                            a.setId(stdin.nextInt());
                        }catch(InputMismatchException e){
                            result = "Invalid input data";
                            break;
                        }
                        stdin.nextLine();
                        System.out.print("add> First name: ");
                        ime = stdin.nextLine();
                        if(!ime.matches("[A-Za-z]+([ ]*[A-Za-z]*)*")){
                            result = "Invalid input data";
                            break;
                        }
                        a.setIme(ime);

                        System.out.print("add> Last name: ");
                        priimek = stdin.nextLine();
                        if(!priimek.matches("[A-Za-z]+([ ]*[A-Za-z]*)*")){
                            result = "Invalid input data";
                            break;
                        }
                        a.setPriimek(priimek);
                        System.out.print("add> Avg. grade: ");
                        try{
                            a.setPovprecje(stdin.nextFloat());
                        }catch(InputMismatchException e){
                            result = "Invalid input data";
                            break;
                        }

                        seznam_vpisne.add((StudentVpisna)a);
                        a = ((StudentVpisna) a).toImePriimek();
                        seznam_imePriimek.add((StudentImePriimek)a);
                    } else {
                        result = "Invalid argument";
                    }
                    break;

                case "reset":
                    if(!sc.hasNext()) {
                        System.out.print("reset> Are you sure (y|n): ");
                        if ("y".equals(stdin.next())) {
                            seznam_imePriimek = new Drevo23<>();
                            seznam_vpisne = new Drevo23<>();
                        }

                    }
                    else{
                        result = "Invalid argument";
                    }
                    break;

                case "count":
                    result = String.format("No. of students: %d", seznam_imePriimek.size());
                    break;

                case "remove":
                case "search":
                    AbsStudent a;
                    if (sc.hasNext()) {
                        a = new StudentVpisna();
                        try{
                            a.setId(sc.nextInt());
                        }catch(InputMismatchException e){
                            result = "Invalid input data";
                            break;
                        }
                        a = seznam_vpisne.get(a.getId());
                        if("remove".equals(token)){
                            if(a == null){
                                result = "Student does not exist";
                                break;
                            }
                            seznam_vpisne.remove((StudentVpisna)a);
                            seznam_imePriimek.remove(((StudentVpisna) a).toImePriimek());
                        }

                    } else {
                        a = new StudentImePriimek();
                        String ime;
                        String priimek;
                        System.out.print("search> First name: ");
                        ime = stdin.nextLine();
                        if(!ime.matches("[A-Za-z]+([ ]*[A-Za-z]*)*")){
                            result = "Invalid input data";
                            break;
                        }
                        a.setIme(ime);

                        System.out.print("search> Last name: ");
                        priimek = stdin.nextLine();
                        if(!priimek.matches("[A-Za-z]+([ ]*[A-Za-z]*)*")){
                            result = "Invalid input data";
                            break;
                        }
                        a.setPriimek(priimek);
                        a = seznam_imePriimek.get(a.getIme(), a.getPriimek());

                        if("remove".equals(token)){
                            if(a == null){
                                result = "Student does not exist";
                                break;
                            }
                            seznam_vpisne.remove(((StudentImePriimek)a).toVpisna());
                            seznam_imePriimek.remove(((StudentImePriimek)a));
                        }
                    }
                    if("search".equals(token)) {
                        if(a == null){
                            result = "Student does not exist";
                            break;
                        }
                        result = a.toString();
                    }
                    break;

                case "print":
                    int size = seznam_imePriimek.size();
                    if(size > 0){
                        result = String.format("No. of students: %d\n%s", size, seznam_imePriimek.print());

                    }
                    else{
                        result = String.format("No. of students: %d", size);
                    }
                    break;

                case "save":
                    if (sc.hasNext()) {
                        FileOutputStream fout = new FileOutputStream(sc.next());
                        seznam_vpisne.save(fout);
                        seznam_imePriimek.save(fout);
                    } else {
                        result = "Error: please specify a file name";
                    }
                    break;
                case "restore":
                    if (sc.hasNext()) {
                        FileInputStream fin = new FileInputStream(sc.next());
                        seznam_vpisne.restore(fin);
                        seznam_imePriimek.restore(fin);                    } else {
                        result = "Error: please specify a file name";
                    }
                    break;
                default:
                    result = "Invalid command";
            }
        } catch (IllegalArgumentException e) {
            result = "Error: Duplicated entry";
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


        return ">> " + result;
    }
}
