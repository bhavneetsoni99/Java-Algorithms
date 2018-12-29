/**
 * Created by bsoni1 on 10/25/2016.
 */

/**
 * Created by BHAVN on 10/23/2016.
 */

import java.util.*;
import java.io.*;

public class Inversion {
    private static long inversionCount =0L; //using long as number of inversion is greater than max value of int
    //private static int[] sortedArray = new int [100000];
    public static void main(String[] args) {
        String dataFile = "src/IntegerArray.txt"; // file should be place inside the source folder for this path to work

        // Prepare to read from the file, using a Scanner object
        File data = new File(dataFile);
        Scanner scanner = null;
        try {  //to handle error situations
            scanner = new Scanner(data);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        int[] integerArray = new int[100000];
        int i = 0;
        while (scanner.hasNextInt()) {
            integerArray[i++] = scanner.nextInt();

        }
        mergeSort(integerArray, 0, integerArray.length - 1);


        System.out.println("sortedArray  is ");
//        for(int c =0; c<integerArray.length;c++){
//            System.out.print(integerArray[c] +", ");
//        }
        System.out.println("inversions in this Array is  --- " + inversionCount);
    }

    public static void mergeSort(int[] someArray, int startIndex, int endIndex) {
        if (startIndex < endIndex) {
           int mid = (startIndex + endIndex) / 2;
            mergeSort(someArray, startIndex, mid);//call recursively on the left half of the array
            mergeSort(someArray, mid + 1, endIndex);//call recursively on the right half of the array
            merge(someArray, startIndex, mid, endIndex);//merge left and right arrays
                    }
    }

    public static void merge(int[] someArray, int startIndex, int mid, int endIndex) {

        int [] temp = new int[endIndex-startIndex+1];
        int left = startIndex;
        int right = mid+1;
        int k = 0;
        while (left <= mid && right <= endIndex) {
            if (someArray[left] < someArray[right]) {
                temp[k] = someArray[left];
                left = left+1;
            } else {
                temp[k] = someArray[right];
                right = right+1;
                inversionCount = inversionCount +mid+1-left;
                //here we calculate the actual inversion when
                //the left most element of Right Array is smaller
                //than Left Most element of Left Array(L[left]) we can infer
                //It is smaller than all the elements to the right of L[left] i.e left to mid
            }
            k=k+1;

        }
        if (left<=mid){
            while (left <= mid) {
                temp[k] = someArray[left];
                left = left+1;
                k=k+1;
               }

        }else if(right<=endIndex){
            while(right<=endIndex){
                temp[k] = someArray[right];
                right = right+1;
                k= k+1;
            }
        }
        for(int i = 0;i<temp.length; i++){
            someArray[startIndex+i]=temp[i];
        }

    }
}