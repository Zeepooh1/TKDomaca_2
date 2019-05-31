import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Studenti {
    public static void main(String[] args)
    {
        SeznamiUV seznamiUV = new SeznamiUV(new Drevo23<>());
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input;
        String output;

        try
        {
            do
            {
                System.out.print("command> ");
                input = br.readLine();
                output = seznamiUV.processInput(input);
                System.out.println(output);
            }
            while (!input.equalsIgnoreCase("exit"));
        }
        catch (IOException e)
        {
            System.err.println("Failed to retrieve the next command.");
            System.exit(1);
        }
    }
}
