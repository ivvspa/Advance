import java.util.*;

public class Main {
    public static int countDigit(int number, int digit, int x) {
        //implement logic here
        if ((number-1)<0){
            return x;
        }

        if (number%10 == digit){
            return countDigit(number/10, digit, x+1);
        }
        else {
            return countDigit(number/10, digit, x);
        }
    }
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n1 = scan.nextInt();
        int n2 = scan.nextInt();
        System.out.println(countDigit(n1, n2, 0));
    }
}