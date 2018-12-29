import java.util.*;

public class Answer {
    private static int steps = 0; //using long as number of inversion is greater than max value of int
    private static ArrayList<ArrayList<Integer>> moves = new ArrayList<ArrayList<Integer>>();
    //public static int answer(int src, int dest) {
    public static void main(String[] args) {


        int src = 0;
        int dest =63;


        if (src == dest) {
            // return steps;
        } else {
            steps = steps+1;
            allreadyChecked.add(src);
            int[][] board = {
                    {0,   1,  2,  3,  4,  5,  6,  7},
                    {8,   9, 10, 11, 12, 13, 14, 15},
                    {16, 17, 18, 19, 20, 21, 22, 23},
                    {24, 25, 26, 27, 28, 29, 30, 31},
                    {32, 33, 34, 35, 36, 37, 38, 39},
                    {40, 41, 42, 43, 44, 45, 46, 47},
                    {48, 49, 50, 51, 52, 53, 54, 55},
                    {56, 57, 58, 59, 60, 61, 62, 63}
            };

            for (int row = 0; row < board.length; row++) {

                for (int col = 0; col < board[row].length; col++) {
                    ArrayList<Integer> possibleMoves = new ArrayList<Integer>();

                    try {
                        possibleMoves.add(board[row + 2][col + 1]);

                    } catch (ArrayIndexOutOfBoundsException e) {

                    }
                    try {
                        possibleMoves.add(board[row + 2][col - 1]);

                    } catch (ArrayIndexOutOfBoundsException e) {

                    }
                    try {
                        possibleMoves.add(board[row - 2][col + 1]);

                    } catch (ArrayIndexOutOfBoundsException e) {

                    }
                    try {
                        possibleMoves.add(board[row - 2][col - 1]);

                    } catch (ArrayIndexOutOfBoundsException e) {

                    }
                    try {
                        possibleMoves.add(board[row + 1][col + 2]);

                    } catch (ArrayIndexOutOfBoundsException e) {

                    }
                    try {
                        possibleMoves.add(board[row + 1][col - 2]);

                    } catch (ArrayIndexOutOfBoundsException e) {

                    }
                    try {
                        possibleMoves.add(board[row - 1][col + 2]);

                    } catch (ArrayIndexOutOfBoundsException e) {

                    }
                    try {
                        possibleMoves.add(board[row - 1][col - 2]);

                    } catch (ArrayIndexOutOfBoundsException e) {

                    }
                    moves.add(possibleMoves);
                }

            }

//            for (int row = 0; row < 64; row++) {
//                //for (int col = 0; col < 8; col++) {
//                List<Integer> value = moves.get(row);
//                System.out.print(value);
//                //}
//                System.out.println();
//            }
            //  System.out.print(moves.size());


            System.out.println("printout  --- " + src);

           countSteps(src, dest);
            System.out.println("**********final answer is  ---" + steps);


        }
    }

    private static ArrayList<Integer> temp = new ArrayList<Integer>();
    private  static ArrayList<Integer> allreadyChecked = new ArrayList<Integer>();

    private static void countSteps( int src, int dest) {
        ArrayList<Integer> a = moves.get(src);
        System.out.println("checking in   ---" + a);

        if(a.contains(dest)){
            System.out.println("found in "+ a);
            return;// steps;
        }else{
            allreadyChecked.addAll(a);
            System.out.println("allreadyChecked "+ allreadyChecked);
            int aSize = a.size();
            for(int i = 0; i<aSize; i++) {
              //  allreadyChecked.add(a.get(i));
                getNextMoves(a.get(i),dest);//prepare next set of temp array for us
            }
            steps = steps+1;
            ifDestisThere(dest); //search in temp array
           // return steps;
        }

    }
    //prepare temp array
    private static void getNextMoves(int startPt, int dest){
        ArrayList<Integer> nextMoves = moves.get(startPt);
        System.out.println("next set of movers are    "+nextMoves +" startpoint is  "+ startPt+ "   src is   " );
        //nextMoves.remove(new Integer(src)); //removing older to src

            for (int nextM = 0; nextM < nextMoves.size(); nextM++) {
                if (temp.contains(nextMoves.get(nextM))) {

                } else {
                    temp.add(nextMoves.get(nextM));

                }

            }
            System.out.println("temp is   ---" + temp);
            temp.removeAll(allreadyChecked);
            System.out.println("removed checked   " + temp);


    }
    //check in each of temp array elements
    public static void ifDestisThere(int dest){
        //check in each of moves in temp

        if (temp.contains(dest)){
            System.out.println("found in "+ temp);
            return;// steps;
        }else {
            steps=steps+1;
            for (int t = 0; t < temp.size(); t++) {
                ArrayList<Integer> a = moves.get(temp.get(t));
                if (a.contains(dest)) {
                    System.out.println("found in " + temp.get(t));
                    return;// steps;
                }
            }
            // prepare next temp
            ArrayList<Integer> temp2 =  new ArrayList<Integer>(temp);

            System.out.println("*************Cleared old temp**********");
            temp.clear();
            System.out.println("****new temp*****" + temp2);

            System.out.println("***allreadychecked*****" + allreadyChecked);
            for (int tempitemp = 0; tempitemp < temp2.size(); tempitemp++) {

                allreadyChecked.add(temp2.get(tempitemp));
                getNextMoves(temp2.get(tempitemp),dest);
            }

            ifDestisThere(dest);
        }
    //return steps;
    }


}


