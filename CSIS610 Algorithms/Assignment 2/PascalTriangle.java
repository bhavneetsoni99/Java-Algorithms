/**
 * Created by BHAVN on 10/20/2016.
 * This program prints a pascal triangle of the given depth
 */
import java.util.*; //import to get user input

public class PascalTriangle {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int depth = 0;
        while (depth <= 0) { //to prompt user to enter depth until a valid depth has been entered
            System.out.print("Please Enter a valid depth of the triangle....");
            String s = input.next(); //take input from the user
            // making sure input is a valid depth i.e a positive number greater than 0
            try {
                depth = Integer.parseInt(s);
                if (depth >0 ){
                    System.out.println("Entered depth of Pascal Triangle is " + depth + " rows");
                    System.out.println();
                    // We used ArrayList instead of arrays but Arrays can be used as well as we know that
                    // the length of each row is equal to the depth of the row
                    ArrayList<ArrayList<Integer>> updatedTriangle = new ArrayList<ArrayList<Integer>>();
                    ArrayList<Integer> newRow = new ArrayList<Integer>();//will have an array of size equal to number of row

                    newRow.add(1);
                    updatedTriangle.add(newRow);
                    int rowsAtPresent = 1;

                    ArrayList<ArrayList<Integer>> pascalTriangle;
                    pascalTriangle =writeRowToPascalTriangle(updatedTriangle,depth, rowsAtPresent);

                    //following code prints the array on the screen
                    for(int i = 0; i < depth; i++)
                    {
                        String numberAsString = " ";
                        ArrayList<Integer> pascalTriangleRow = pascalTriangle.get(i);
                        for(int j = 0; j < i+1; j++)
                        {
                            numberAsString = numberAsString+"" + Integer.toString(pascalTriangleRow.get(j))+" ";
                        }
                        printer(numberAsString, 80);

                    }
                    //code below is after triangle is for error handling
                } else {
                    System.out.println("Only Positive integer values are allowed....");
                    depth = 0;
                }
            } catch (Exception e) {
               System.out.println("Only integer values are excepted.....");
           }
        }
    }

    public static ArrayList<ArrayList<Integer>>  writeRowToPascalTriangle(ArrayList<ArrayList<Integer>> someTriangle, int depth, int rowsAtPresent) {
        ArrayList<Integer> newRow = new ArrayList<Integer>();//will have an array of size equal to number of row
        ArrayList<Integer> previousRow = someTriangle.get(rowsAtPresent-1);

        if (rowsAtPresent<depth) {
            if (rowsAtPresent ==1){
                newRow.add(1);
                newRow.add(1);
                someTriangle.add(newRow);
                rowsAtPresent = rowsAtPresent + 1;
                writeRowToPascalTriangle(someTriangle, depth, rowsAtPresent);
            }else {
                newRow.add(1);
                for (int i = 0; i < rowsAtPresent-1; i++) {
                   newRow.add(previousRow.get(i) + previousRow.get(i+1));//rowsATPresent-1 gives the index of previous row
                 }
                newRow.add(1);
                someTriangle.add(newRow);
                rowsAtPresent = rowsAtPresent + 1;
                writeRowToPascalTriangle(someTriangle, depth, rowsAtPresent);
            }
        }
        return someTriangle;
    }
// Following method was taken from stackoverflow.com to format the printing of the triangle
    //http://stackoverflow.com/questions/16629476/how-to-center-a-print-statement-text

    static void printer(String str, int size) {
        int left = (size - str.length()) / 2;
        int right = size - left - str.length();
        String repeatedChar = " ";
        StringBuffer buff = new StringBuffer();
        for (int i = 0; i < left; i++) {
            buff.append(repeatedChar);
        }
        buff.append(str);
        for (int i = 0; i < right; i++) {
            buff.append(repeatedChar);
        }
        System.out.println(buff.toString());
    }
}