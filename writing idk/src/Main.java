import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int i = 1;
        while (i>0) {
            String messa = scanner.nextLine();


            fileWriter("\n" + messa, "Algebraic Questions");

            i++;
        }
    }

    static void fileWriter (String message, String name) {
        String nameInput = name + ".txt";
        try(FileWriter outstream = new FileWriter(nameInput, true)){
            outstream.write(message);
            System.out.println("fileWriterTest method is successful!");
        }
            catch (IOException e) {
                System.out.println("Whoops: " + e);
        }

    }

}