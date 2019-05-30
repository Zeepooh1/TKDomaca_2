import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class SeznamiUV {
    private String memoryError = "Error: not enough memory, operation failed";
    Seznam<StudentVpisna> seznam_vpisne;
    Seznam<StudentImePriimek> seznam_imePriimek;

    public SeznamiUV() {
        seznam_vpisne = new Drevo23<>();
        seznam_imePriimek = new Drevo23<>();
    }

    public String processInput(String input) {
        Scanner sc = new Scanner(input);
        Scanner stdin = new Scanner(System.in);
        String token;
        String result = "OK";
        if (sc.hasNext()) {
            token = sc.next();
        } else {
            return "Error: enter command";
        }
        if(token.equals("exit")){
            seznam_vpisne = null;
            seznam_imePriimek = null;
            return "Goodbye";
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
                        result = "Error: Invalid input data";
                    }
                    break;

//                case "reset":
//                    while (!seznam.isEmpty()) {
//                        seznam.removeFirst();
//                    }
//                    break;
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
                                result = "Student does not exists";
                                break;
                            }
                            seznam_vpisne.remove((StudentVpisna)a);
                            seznam_imePriimek.remove(((StudentVpisna) a).toImePriimek());
                        }

                    } else {
                        a = new StudentImePriimek();
                        System.out.print(token + "> First name: ");
                        a.setIme(stdin.nextLine());
                        System.out.print(token + "> Last name: ");
                        a.setPriimek(stdin.nextLine());

                        a = seznam_imePriimek.get(a.getIme(), a.getPriimek());

                        if("remove".equals(token)){
                            if(a == null){
                                result = "Student does not exists";
                                break;
                            }
                            seznam_vpisne.remove(((StudentImePriimek)a).toVpisna());
                            seznam_imePriimek.remove(((StudentImePriimek)a));
                        }
                    }
                    if("search".equals(token)) {
                        if(a == null){
                            result = "Student does not exists";
                            break;
                        }
                        result = String.format("%d | %s, %s | %.1f", a.getId(), a.getPriimek(), a.getIme(), a.getPovprecje());
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

//                case "save":
//                    if (sc.hasNext()) {
//                        seznam.save(new FileOutputStream(sc.next()));
//                    } else {
//                        result = "Error: please specify a file name";
//                    }
//                    break;
//                case "restore":
//                    if (sc.hasNext()) {
//                        seznam.restore(new FileInputStream(sc.next()));
//                    } else {
//                        result = "Error: please specify a file name";
//                    }
//                    break;
                default:
                    result = "Invalid command";
            }
        } catch (IllegalArgumentException e) {
            result = "Error: Duplicated entry";
        } catch (java.util.NoSuchElementException e) {
            result = "Error: data structure is empty";
        }
//        catch (IOException e) {
//            result = "Error: IO error " + e.getMessage();
//        }
//        catch (ClassNotFoundException e) {
//            result = "Error: Unknown format";
//        }
        catch( OutOfMemoryError e){
            System.err.println(memoryError);
            return memoryError;
        }


        return ">> " + result;
    }

}
