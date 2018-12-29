import java.util.*;

public class Answer {

    public static void main(){
        answer(0,63);
    }

    public static int answer(int src, int dest) {
        if (src == dest) {
            return 0;
        } else {
            steps = steps+1;
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


            countSteps(src, dest);
            return steps;
        }
    }
    private static int steps = 0;
    // public static int count = 0;
    private static ArrayList<ArrayList<Integer>> moves = new ArrayList<ArrayList<Integer>>();
    private static ArrayList<Integer> temp = new ArrayList<Integer>();
    private static ArrayList<Integer> allreadyChecked = new ArrayList<Integer>();

    private static void countSteps( int src, int dest) {
        ArrayList<Integer> a = moves.get(src);


        if(a.contains(dest)){

            return;
        }else{
            int aSize = a.size();
            for(int i = 0; i<aSize; i++) {
                allreadyChecked.add(a.get(i));
                getNextMoves(a.get(i), src,dest);//prepare next set of temp array for us
            }
            steps = steps+1;
            ifDestisThere(dest,src); //search in temp array
            return;
        }

    }
    //prepare temp array
    private static void getNextMoves(int startPt, int src, int dest){
        ArrayList<Integer> nextMoves = moves.get(startPt);
        nextMoves.remove(new Integer(src)); //removing older to src
        int nextMovesSize = nextMoves.size();
        for (int nextM = 0; nextM < nextMovesSize; nextM++) {
            if (temp.contains(nextMoves.get(nextM))) {

            } else {
                temp.add(nextMoves.get(nextM));

            }

        }

        temp.removeAll(allreadyChecked);



    }

    //check in each of temp array elements
    public static void ifDestisThere(int dest, int src){
        //check in each of moves in temp
        // count = count +1;
        if (temp.contains(dest)){

            return;
        }else {
            steps=steps+1;
            int tempSize = temp.size();
            for (int t = 0; t <tempSize; t++) {
                ArrayList<Integer> a = moves.get(temp.get(t));
                if (a.contains(dest)) {

                    return;
                }
            }
            // prepare next temp
            ArrayList<Integer> temp2 = (ArrayList<Integer>) temp.clone();

            temp.clear();

            int temp2Size =temp2.size();
            for (int tempitemp = 0; tempitemp < temp2Size; tempitemp++) {

                allreadyChecked.add(temp2.get(tempitemp));
                getNextMoves(temp2.get(tempitemp), src,dest);
            }

            ifDestisThere(dest, src);


        }

    }


}