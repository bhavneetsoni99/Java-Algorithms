import java.util.Scanner;

/**
 * Created by BHAVN on 11/3/2016.
 */
public class StringReversal {

    public static void main(String[] args ){

        Scanner input = new Scanner(System.in);
        System.out.print("Please Enter a String to be reversed ---- ");
        String str = input.next();
        System.out.println("String Entered is " +str);
        char reversedStr = reverseString(str);
        System.out.println("Reversed String is " +reversedStr);


    }
    private static int index;
    public static char reverseString(String str){
        index = str.length();
        char reversedStr = str.charAt(0);
        return reversedStr;
    }
}
