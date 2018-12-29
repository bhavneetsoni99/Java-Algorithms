import java.util.Stack;

/**
 * Created by BHAVN on 11/6/2016.
 */
public class SelectionSort{
    private static int [] data = {4, 3, 2, 90, 16, 78, 5, 9};
    public static void main(String[] args){
/*
International Journal of Advanced Science and Technology Vol. 56, July, 2013 - Author Md. Khairullah
        1. Repeat steps 2 to 9 until length=1
        2. If stack is empty
        Push 0 in the stack
        End if
        3. Pop stack and put in max
        4. Set count=max+1
        5. Repeat steps 6 and 7 until count<length
        6. If (a[count]>a[max])
        a. Push count-1 on stack
        b. Interchange data at location count-1 and max
        c. Set max=count
        End if
        7. Set count=count+1
        8. Interchange data at location length-1 and max
        A[n]




*/
        int smaller;
        int n = data.length;

        //International Journal of Advanced Science and Technology Vol. 56, July, 2013 - Author Md. Khairullah
        Stack<Integer> stack = new Stack<Integer>();
        int count = 0;
        int max = 0;

        for (int i = 0; i < n - 1; i++) {

            smaller = i;
            for (int j = i + 1; j < n; j++) {
                if (data[j] < data[smaller])
                    smaller = j;

                if (stack.size() == 0) {
                    stack.push(data[i]);
                } else {
                    stack.pop();
                    stack.push(j);
                }
            }

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
