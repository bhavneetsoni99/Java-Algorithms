/**
 * Created by BHAVN on 11/6/2016.
 */
public class SelectionSort{
    private static int [] data = {4, 3, 2, 90, 16, 78, 5, 9};
    public static void main(String[] args){

        int smaller;
        int n = data.length;
        for (int i = 0; i < n - 1; i++) {
            smaller = i;
            for (int j = i + 1; j < n; j++)
                if (data[j] < data[smaller])
                    smaller = j;
            if (smaller != i) {
                swap(i, smaller);
            }
        }
        printArray(data);
    }
    private static void swap(int indexOne, int indexTwo) {
        int temp = data[indexOne];
        data[indexOne] = data[indexTwo];
        data[indexTwo] = temp;
    }
    static void printArray(int arr[]) {
        int n = arr.length;
        for (int i=0; i<n; ++i)
            System.out.print(arr[i]+" ");
        System.out.println();
    }
}
