import java.io.*;

public class Main {
    public static void main(String[] args) {
        Employee e1 = new Employee (1, "jorge", 10000.0);
        e1.writeToFile("employee.txt");
        e1.readFromFile("employee.txt");
    }
}

class Employee implements Serializable {

    private int employeeId;
    private String employeeName;
    private double salary;

    public Employee(int employeeId, String employeeName, double salary) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.salary = salary;
    }

    public void writeToFile(String path) {

        try (ObjectOutputStream dio = new ObjectOutputStream(new FileOutputStream(path))) {
            dio.writeObject(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readFromFile(String path) {
        try (ObjectInputStream dio = new ObjectInputStream(new FileInputStream(path))) {

            try {
                while (true) {
                    Employee e = (Employee) dio.readObject();
                    System.out.println("Employee ID: " + e.employeeId + "\nEmployee Name: " + e.employeeName + "\nSalary: " + e.salary);
                }
            }
            catch (EOFException e) {}
        }
        catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
        }
    }

}