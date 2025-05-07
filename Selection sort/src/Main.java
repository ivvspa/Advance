public class Main { //its insertion now
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 5, 4};
        insertionSort(arr);
        for (int i : arr){
            System.out.print(i + " ");
        }
    }

    static void insertionSort(int[] arr) {
        int count = 0;
        for (int i = 1; i < arr.length; i++) {
            int temp = arr[i];
            int possibleIndex = i;

            while (possibleIndex > 0 && arr[possibleIndex-1] > temp) {
                arr[possibleIndex] = arr[possibleIndex - 1];
                possibleIndex--;
                count++;
            }

            arr[possibleIndex] = temp;

        }
        System.out.println(count);
    }
}
