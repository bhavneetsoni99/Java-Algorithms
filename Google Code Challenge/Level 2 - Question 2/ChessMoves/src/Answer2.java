import java.util.*;

public class Answer2 {
    //private static int steps = 0; //using long as number of inversion is greater than max value of int
    //private static ArrayList<ArrayList<Integer>> moves = new ArrayList<ArrayList<Integer>>();
    private static int[][] board = {
            {0, 1, 2, 3, 4, 5, 6, 7},
            {8, 9, 10, 11, 12, 13, 14, 15},
            {16, 17, 18, 19, 20, 21, 22, 23},
            {24, 25, 26, 27, 28, 29, 30, 31},
            {32, 33, 34, 35, 36, 37, 38, 39},
            {40, 41, 42, 43, 44, 45, 46, 47},
            {48, 49, 50, 51, 52, 53, 54, 55},
            {56, 57, 58, 59, 60, 61, 62, 63}
    };
    private static int steps = 0;
    //public static int answer(int src, int dest) {
    public static void main(String[] args) {


        int src =0;
        int dest = 34;


        if (src == dest) {
            // return steps;
        } else {
            allreadyChecked.add(src);
            steps = steps + 1;
           ArrayList<Integer> posMoves = getPossibleMoves(src);
           // ifContains

            if (posMoves.contains(dest)) {
                //steps = steps + 1;
              return;
            } else {
                int posMoveSize = posMoves.size();
                for (int i = 0; i<posMoveSize; i++){
                    src = posMoves.get(i);

                    posMoves = getPossibleMoves(src);


                }
            }
        }
      System.out.println(steps);
        //  return steps;
    }
    private  static ArrayList<Integer> allreadyChecked = new ArrayList<Integer>();
    private  static ArrayList<Integer> temp = new ArrayList<Integer>();

    private static void ifContains(ArrayList<Integer> posMoves, int dest) {
        if (posMoves.contains(dest)) {
            //steps = steps + 1;
            //  return;
        } else {
            int posMoveSize = posMoves.size();
            for (int i = 0; i < posMoveSize; i++) {
                getPossibleMoves(posMoves.get(i));
            }
        }
    }

    private static ArrayList<Integer> getPossibleMoves(int srcN) {
        int row = srcN / 8;
        int col = srcN - row * 8;
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
        return possibleMoves;
    }

}

//    private static void getNextMoves(int startPt, int src, int dest){
//        ArrayList<Integer> nextMoves = moves.get(startPt);
//        nextMoves.remove(new Integer(src)); //removing older to src
//
//        for (int nextM = 0; nextM < nextMoves.size(); nextM++) {
//            if (temp.contains(nextMoves.get(nextM))) {
//
//            } else {
//                temp.add(nextMoves.get(nextM));
//
//            }
//
//        }
//        System.out.println("temp is   ---" + temp);
//        temp.removeAll(allreadyChecked);
//        System.out.println("removed checked   " + temp);
//
//
//    }
//
//
//}
//
//
