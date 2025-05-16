import java.io.*;

public class Main {
    public static void main(String[] args) {

        writeEmp("Student_List.txt");
        readEmp("Student_List.txt");

    }

    public static void writeEmp(String path){
        try(DataOutputStream out = new DataOutputStream(new FileOutputStream(path))){

            for(int i = 0; i<5; i++) {
                out.writeInt(i);
                out.writeUTF("" + (i+1));
                out.writeInt((int)(Math.random()*12));
                out.writeDouble(Math.random() * 4);
                out.writeInt((int) (Math.random() * 30));
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    public static void readEmp(String path) {
        try (DataInputStream inputStream = new DataInputStream(new FileInputStream(path))) {

            try {
                while (true) {

                    System.out.println("ID: " + inputStream.readInt());
                    System.out.println("Name: " + inputStream.readUTF());
                    System.out.println("Grade Level: " + inputStream.readInt());
                    System.out.println("GPA: " + inputStream.readDouble());
                    System.out.println("Absences: "  + inputStream.readInt() + "\n");

                }
            }
                catch (EOFException e) {}

            }
        catch (IOException e) {
            e.printStackTrace();
        }

        }
    }
