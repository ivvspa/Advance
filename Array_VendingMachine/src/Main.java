import java.util.Scanner;
public class Main {
    public static void main(String[] args) {

        String[] name = {"Cola", "Pepsi", "FuzeTea", "Balkan", "Sprite", "Schweppes", "Fanta", "Nestea"};
        double[] price = {2.99, 2.99, 2.49, 3.99, 1.49, 1.59, 2.99, 4.99};
        int[] quantity = {3, 3, 3, 3, 3, 3, 3, 3};

        for (int i = 1; i > 0; i++) {
            Scanner scan = new Scanner(System.in);

            System.out.println("Vending Machine Products:");
            System.out.println("Number    Product    Quantity    Price");
            display(name, price, quantity);
            System.out.println("Choose an item from 1-8 (press anything else to exit)");

            int choice = scan.nextInt();
            if (choice <= 8 && choice >= 1) {
                choice--;
            } else {
                System.out.println("Exiting...");
                System.exit(0);
            }

            if (quantity[choice] > 0) {
                quantity[choice]--;
                System.out.println("Insert money: " + price[choice]);
                double moneyGiven = scan.nextDouble();
                if (moneyGiven >= price[choice]) {

                    System.out.println("Dispensing...");
                    System.out.println("Change: " + (moneyGiven - price[choice]));

                } else {
                    double sum = moneyGiven;
                    while (price[choice] >= sum) {
                        double remaining = price[choice] - sum;
                        System.out.printf("Not enough money inserted. Remaining: %.2f" , remaining);
                        double newMoney = scan.nextDouble();
                        sum = sum + newMoney;
                        if (newMoney == 0) {
                            System.exit(0);
                        }
                    }
                    System.out.println("Dispensing...");
                    double remaining = (sum - price[choice]);
                    System.out.printf("Change: %.2f" + remaining);
                }
            } else {
                System.out.println("No more product in stock");
            }
        }
    }


    static void display (String[] name, double[] price, int[] quantity) {

        for(int i = 0; i<name.length; i++){
            System.out.println(i + 1 + "         " + name[i] + "         " + quantity[i] + "        " + price[i]);
        }

    }

}

