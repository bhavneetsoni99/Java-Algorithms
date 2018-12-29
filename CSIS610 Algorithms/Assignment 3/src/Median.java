import java.util.Scanner;
import java.util.List;

/**
 * Created by BHAVN on 10/30/2016.
 */
public class Median {
    public static void main(String[] args){

        ArrayList<Integer>  A = new ArrayList<Integer>();


        Scanner input = new Scanner(System.in);
        int depth = 0;

            System.out.print("Please Enter a numnber to be added to the array....");
            String s = input.next(); //take input from the user
            // making sure input is a valid depth i.e a positive number greater than 0
            try {
                depth = Integer.parseInt(s);

                System.out.println("Entered depth of Pascal Triangle is " + depth + " rows");
                System.out.println();

            } catch (Exception e) {//handle error situation
                System.out.println("Only integer values are accepted.....");
            }


        int i = 1;
        int length = A.length;
        double median;
        //build heap
        // sort it (min heapify)
        //
        //split the whole array in to two heaps of eaqual size
        int [] upperHalf;
        int [] lowerHalf;


        while (i <= length){
            parent(i);
            left(i);
            right(i);
            i = i++;
        }
//        def parent(i):
//        return i/2
//
//        def left(i):
//        try:
//        return 2*i
//        except:
//        pass
//
//        def right(i):
//        try:
//        return 2 *i + 1
//        except:
//        pass

    }

    public static int parent(int index){
        return index/2;
    }
    public static int left(int index){
        return index*2;
    }
    public static  int right(int index){
        return index*2 +1;
    }
    static void MaxHeapify(){

    }
    static void MinHeapify(){

    }
}
