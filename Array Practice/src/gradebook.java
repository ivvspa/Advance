import java.util.Scanner;

public class gradebook {

    public static void main (String[] args){


        Scanner scan = new Scanner(System.in);

        int i = 0;
        System.out.println("how many items?:");
        i = scan.nextInt();

        int[] array = new int[i];
        newNumber(array);
        sumAverage(array);

    }


    static void newNumber (int[] arr){

        Scanner scan = new Scanner(System.in);

        for(int i = 0; i<arr.length; i++){

            System.out.println("enter item " + i);
            arr[i] = scan.nextInt();

        }
    }

    static void sumAverage (int[] arr){
        int sum = 0;
        for (int i = 0; i<arr.length; i++){
            sum += arr[i];
        }

        int average = sum/arr.length;

        System.out.print("sum is: " + sum + " average is; " + average);

    }


}