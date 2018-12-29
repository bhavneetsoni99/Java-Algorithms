/**
 * Created by bsoni1 on 10/25/2016.
 */

/**
 * Created by BHAVN on 10/23/2016.
 */

import java.util.*;
import java.io.*;

public class Inversion {
    private static int inversionCount =0;
    private static int[] sortedArray = new int [5];
    public static void main(String[] args) {
        String dataFile = "src/IntegerArray2.txt";

        // Prepare to read from the file, using a Scanner object
        File data = new File(dataFile);
        Scanner scanner = null;
        try {
            scanner = new Scanner(data);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        int[] integerArray = new int[5];
        int i = 0;
        while (scanner.hasNextInt()) {
            integerArray[i++] = scanner.nextInt();

        }
        mergeSort(integerArray, 0, integerArray.length - 1);
        //System.out.println("inversions" + inversionCount);
        System.out.println("inversions" + inversionCount);
        for(int c =0; i<sortedArray.length;c++){
            System.out.println(sortedArray[c]);
        }

    }

    public static void mergeSort(int[] someArray, int startIndex, int endIndex) {
        if (startIndex < endIndex) {
            //System.out.println("hihihihihi");
            int mid = (startIndex + endIndex) / 2;
            mergeSort(someArray, startIndex, mid);
            mergeSort(someArray, mid + 1, endIndex);
            merge(someArray, startIndex, mid, endIndex);
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
                inversionCount = inversionCount+mid-left;
            }
                k=k+1;

        }
        if (left<=mid){
            while (left <= mid) {
                temp[k] = someArray[left];
                left = left+1;
                k=k+1;
                //inversionCount= inversionCount+1;
            }

        }else if(right<=endIndex){
            while(right<=endIndex){
                temp[k] = someArray[right];
                right = right+1;
                k= k+1;
            }
        }
        for(int i = 0;i<temp.length; i++){
            sortedArray[startIndex+i]=temp[i];
        }

    }
}
