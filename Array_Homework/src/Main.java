import java.util.Arrays;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {

        String[] title = {"Percy Jackson", "Tinker Bell", "Circe", "Ariadne", "Winnie the Pooh"};
        double[] price = {15.99, 29.99, 34.99, 37.99, 15.49};
        int[] quantity = {7, 1, 2, 5, 12};

        for (int i = 1; i > 0; i++) {
            display(title, price, quantity);
            search(title, price, quantity);
        }
    }


    static void display(String[] title, double[] price, int[] quantity) {

        System.out.println("What are you searching for today? Press 0 to exit");
        System.out.println("   Title   " + "Quantity  " + "Price");
        for (int i = 0; i < title.length; i++) {
            System.out.println(i+1 + "  " + title[i] + "  " + quantity[i] + "  " + price[i]);
        }
    }

    static void search(String[] title, double[] price, int[] quantity) {
        Scanner scan = new Scanner(System.in);
        String search = scan.nextLine();

        if (search.equals("0")) {
            System.exit(0);
        }

        for (int i = 0; i < title.length; i++) {


            char[] searchChar = (search.toUpperCase()).toCharArray();
            char[] titleChar = (title[i].toUpperCase()).toCharArray();



            if (Arrays.equals(searchChar, titleChar)){
                    System.out.println("Is this what you are searching for?: " + title[i] + "   (yes/no)");

                String confirm = scan.next();
                if (confirm.equalsIgnoreCase("yes")) {
                    if (quantity[i] > 0) {
                        System.out.println("This will be " + price[i] + "\nPlease put your credit card on the reader. (Balance in $:) ");
                        double balance = scan.nextDouble();
                        if (balance >= price[i]) {
                            quantity[i]--;
                            System.out.println("Successful transaction! Thank you for your business.");
                            System.out.printf("New Balance: %.2f" , (balance - price[i]));
                            System.out.print("$\n");
                        } else if (0 < balance && balance < price[i]){
                            System.out.println("Invalid amount of money. Please contact your bank if you foresee any problems.");
                        }
                        else {
                            System.out.println("Invalid operation. Please type a positive number.");
                        }
                    } else {
                        System.out.println("Not in stock");
                    }
                    } else if (confirm.equalsIgnoreCase("no")) {

                    System.out.println("Try searching again for your desired product");
                }
                else {
                    System.out.println("Invalid input");
                }
            }
        }
    }
}


