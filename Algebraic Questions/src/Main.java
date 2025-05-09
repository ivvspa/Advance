import java.io.*;
import java.util.Scanner;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random rand = new Random();
        int i = 0;
        while (i<5) {

            int n1 = rand.nextInt(100);
            int n2 = rand.nextInt(100);
            System.out.println(n1 + " + " + n2 + " = ?");
            String messa = scanner.nextLine();

            int intMessa = 0;

            try {
                intMessa = Integer.parseInt(messa);
            }
            catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }

                if (n1 + n2 == intMessa) {

                    fileWriter(n1 + " + " + n2 + " = " + messa + "   Correct!\n", "Algebraic Questions");

                } else {

                    fileWriter(n1 + " + " + n2 + " = " + messa + "   Incorrect.\n", "Algebraic Questions");

                }

                i++;


        }
    }

    static void fileWriter (String message, String name) {
        String nameInput = name + ".txt";
        try(FileWriter outstream = new FileWriter(nameInput, true)){
            outstream.write(message);
        }
        catch (IOException e) {
            System.out.println("Whoops: " + e);
        }

    }

}