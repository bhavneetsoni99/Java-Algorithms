import java.util.Scanner;
public class StringReversal {

    public static void main(String[] args ){
        Scanner input = new Scanner(System.in);
        System.out.print("Please Enter a String to be reversed ---- "); //get input from user
        String str = input.nextLine(); //store user input in the str
        int index = 0;
        int len = str.length();
        System.out.println("String Entered is " +str);
        StringBuilder reveresedStr = new StringBuilder();
        String reversedStr = reverseString(str,len,index, reveresedStr );
        System.out.println("Reversed String is " +reversedStr);
    }
    private static String  rStr = new String();

    public static String reverseString(String str,int len, int index, StringBuilder reveresedStr){
        if (index >= len){
            rStr = reveresedStr.toString();
        }
        else {
            reveresedStr.append(str.charAt(len-1 - index));
            index = index+1;
            reverseString(str,len, index, reveresedStr); //call recursively
        }
        return rStr;
    }
}