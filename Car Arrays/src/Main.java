import java.util.Scanner;
public class Main {
    public static void main(String[] args) {

       Scanner scan = new Scanner(System.in);

       car[] cars = {
               new car ("Toyota", "Corrola", 2007, 20000, false),
               new car ("Nissan", "NT-800", 2012, 24000, false),
               new car ("Audi", "Q7", 2014, 30000, false),
               new car ("Honda", "Civic", 2003, 10000, false),
       };

       while (true) {
           System.out.println("Enter an option:");
           System.out.println("1  -  Display All Cars");
           System.out.println("2  -  Display Available Cars");
           System.out.println("3  -  Display Most Expensive Car");
           System.out.println("4  -  Display Average Price");
           System.out.println("5  -  Lease A Car");
           int option = scan.nextInt();
           switch (option) {
               case 1:
                   displayAll(cars);
                   break;
               case 2:
                   displayAvailable(cars);
                   break;
               case 3:
                   mostExpensive(cars);
                   break;
               case 4:
                   System.out.println("Average: " + averagePrice(cars) + "$\n");
                   break;
               case 5:
                   lease(cars);
                   break;
           }
       }

    }

    public static void displayAll(car[] car){
        for (car c : car) {
            c.display();
        }
    }

    public static void displayAvailable(car[] car){
        for (car c : car) {
            if (!c.getIsLeased()) {
                c.display();
            }
        }
    }

    public static void mostExpensive (car[] car){

        int index = 0;
        for(int i = 0; i< car.length; i++){
            if(car[i].getPrice() > car[index].getPrice()){
                index = i;
            }
        }
        System.out.println("Most Expensive Car: " + car[index].getMake() + " " + car[index].getModel() + " " + car[index].getYear() + "\n");
    }

    public static double averagePrice (car[] car){
        double sum = 0;
        for(car element : car){
            sum = sum + element.getPrice();
        }
        return sum/car.length;
    }

    public static void lease (car[] car){
        Scanner scan = new Scanner(System.in);
        int index = 0;
        System.out.println("Which car would you like to lease/sell? (1-" + car.length + "):");
        int choice = scan.nextInt();
        if(choice == 0){
            System.exit(0);
        }
        else if(choice > 0 && choice <= car.length){
            index = choice - 1;

        }

        car[index].setIsLeased();
        System.out.println("Successfully leased/sold");
    }



}

class car {

    private String make;
    private String model;
    private int year;
    private double price;
    private boolean isLeased;

    public car(String make, String model, int year, double price, boolean isLeased) {
        this.make = make;
        this.model = model;
        this.year = year;
        this.price = price;
        this.isLeased = isLeased;
    }

    public void display(){
        System.out.println("\nMake: " + make);
        System.out.println("Model: " + model);
        System.out.println("Year: " + year);
        System.out.println("Price: " + price);
        System.out.println("Is it Leased: " + isLeased);
        System.out.println(".................");
    }

    public String getMake() {
        return make;
    }
    public String getModel() {
        return model;
    }
    public int getYear() {
        return year;
    }
    public double getPrice() {
        return price;
    }
    public boolean getIsLeased() {
        return isLeased;
    }

    public void setIsLeased() {
        isLeased = !isLeased;
    }
}