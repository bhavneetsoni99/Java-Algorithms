import java.awt.image.AreaAveragingScaleFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.List;

/**
 * Created by BHAVN on 10/30/2016.
 */
public class Median {
    //private static int capacity;
    private static int[] A={4, 3, 2, 90, 16, 78, 5, 9};
    private static int size = A.length;
    public static void main(String[] args) {
        //int[] A =
        printArray(A);
        int size = A.length;
        sort(A);
        System.out.println("Sorted array is");
        printArray(A);
        double median = findMedian(A);
        System.out.println("Median for the array is " + median);

        //making left and right lists
        // - left containing numbers smaller than median sorted inversely
        // - right containing numbers bigger than median sorted in natural order;
        for (int i = A.length/2-1; i >= 0; i --){
            leftArray.add(A[i]);
        }
        for (int i = A.length/2; i < A.length ; i ++){
            rightArray.add(A[i]);
        }
        System.out.println("Left of Median  " + leftArray);
        System.out.println("Right of Median  " + rightArray);
        getData();
    }
    private static ArrayList<Integer> leftArray = new ArrayList<Integer>();
    private static ArrayList<Integer> rightArray = new ArrayList<Integer>();
    private static void getData() {
        Scanner input = new Scanner(System.in);
        //take input from the user
        System.out.print("Please Enter numbers up to 10 seperated by (,) to find the medians....");
        String s = input.nextLine(); //take input from the user
        // split the input to be stored in an array
        String[] items = s.split(",");
        int p = 0;
        int []tempdata = new int [10];
        for (int i = 0; i < items.length; i++) {
            try {
                //parse the input into an integer value and skipp aplfabets
                tempdata[p] = Integer.parseInt(items[i]);
                p++;
            } catch (Exception e) {
            }
        }
        int [] newData = Arrays.copyOf(tempdata,p);
        printArray(newData);
        updateMedian(newData);
    }
    private static double updateMedian(int A[]) {
        double median= 0;

        for (int i = 0; i < A.length; i ++){
            int leftSize = leftArray.size();
            int rightSize = rightArray.size();

            if (A[i]>= leftArray.get(0) && A[i] <= rightArray.get(0)){
                if (leftSize>rightSize){
                    rightArray.add(0,A[i]);
                    median = (rightArray.get(0)+leftArray.get(0))/2;
                }else if(leftSize == rightSize){
                    median = A[i];
                    leftArray.add(0, A[i]);
                }else{
                    leftArray.add(0, A[i]);
                    median = (rightArray.get(0)+leftArray.get(0))/2;
                }

            }else if(A[i] <= leftArray.get(0)){
                if (leftSize>rightSize){
                    rightArray.add(0,leftArray.get(0));
                    leftArray.remove(0);
                    if (A[i]<leftArray.get(0)){
                        leftArray.add(A[i]);
                    }else{
                        leftArray.add(0,A[i]);
                    }
                    median = (rightArray.get(0)+leftArray.get(0))/2;
                }else if (rightSize>leftSize){
                    leftArray.add(A[i]);
                    median = (rightArray.get(0)+leftArray.get(0))/2;
                }else{
                    leftArray.add(A[i]);
                }

            }else if (A[i]>= rightArray.get(0)){
                if (rightSize>leftSize){
                    leftArray.add(0,rightArray.get(0));
                    rightArray.remove(0);
                    if (A[i]>rightArray.get(0)){
                        rightArray.add(A[i]);

                    }else{
                        rightArray.add(0,A[i]);
                    }
                    median = (rightArray.get(0)+leftArray.get(0))/2;
                }else if (rightSize<leftSize){
                    rightArray.add(A[i]);
                    median = (rightArray.get(0)+leftArray.get(0))/2;
                }else{
                    rightArray.add(A[i]);
                    median = (rightArray.get(0)+leftArray.get(0))/2;

                }
            }

            System.out.println(" Adding element to Array is  " + A[i]);
            int iteration = i+1;
            System.out.println("************* After " + iteration + " iteration *************" );
            System.out.println(" New left Array is  " + leftArray);

            System.out.println(" New right Array is  " + rightArray);

            System.out.println(" median is  " + median);

        }
        return median;
    }
    private static void sort(int A[]) {

        // Build heap (rearrange array)
        for (int i = size / 2 - 1; i >= 0; i--) {
            heapify(A, size, i);
        }

        // One by one extract an element from heap
        for (int i=size-1; i>=0; i--) {
            // Move current root to end
            swap(0, i);
            // call max heapify on the reduced heap
            heapify(A, i, 0);
        }
    }
   private static void heapify(int arr[], int n, int i) {
        int largest = i;
        int l = 2*i + 1;  //left child index
        int r = 2*i + 2;  // right chile index

     //compare children with parent
        if (l < n && arr[l] > arr[largest]) {
            largest = l;
        }
        if (r < n && arr[r] > arr[largest]) {
            largest = r;
        }
        //swap the largest above to maintain max heap property
         if (largest != i) {
            swap(i, largest);
            heapify(arr, n, largest);
        }
    }
    private static double findMedian(int[] data) {
        double median;
        int number = data.length;
        if (number % 2 == 0) {
            System.out.println("numbers are " + data[number / 2]+", "+ data[number / 2 - 1]);
            median = (data[number / 2] + data[number / 2 - 1]) / 2;
        } else {
            median = data[number / 2 + 1];
        }
        return median;
    }

    private static void swap(int indexOne, int indexTwo) {
        int temp = A[indexOne];
        A[indexOne] = A[indexTwo];
        A[indexTwo] = temp;
    }
    private static void ensureCapacity(int itemsAdded){
         A = Arrays.copyOf(A, size+itemsAdded);
    }
    static void printArray(int arr[]) {
        int n = arr.length;
        for (int i=0; i<n; ++i)
            System.out.print(arr[i]+" ");
        System.out.println();
    }

}