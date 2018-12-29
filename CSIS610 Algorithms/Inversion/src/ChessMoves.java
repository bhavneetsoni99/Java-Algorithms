/**
 * Created by bsoni1 on 10/31/2016.
 */
public class ChessMoves {
    public static void answer(int src, int dest){

        int [][] board = {
                {0, 1, 2, 3, 4, 5, 6, 7},
                {8, 9,10, 11, 12, 13, 14, 15},
                {16, 17, 18, 19, 20, 21, 22, 23},
                {24, 25, 26, 27, 28, 29, 30, 31},
                {32, 33, 34, 35, 36, 37, 38, 39},
                {40, 41, 42, 43, 44, 45, 46, 47},
                {48, 49, 50, 51, 52, 53, 54, 55},
                {56, 57, 58, 59, 60, 61, 62, 63}
        };
        int [][] moves = new int[64][];
        int number;
        int coloumn;

        for (int row=0; row < board.length; row++)
        {
            for (int col=0; col < board[row].length; col++)
            {
                int value = board[row][col];
                System.out.println(value);
            }
        }

//        for (int i = 0; 63; i++){
//            int [] rowNumber = i/8;
//            int [coloumn]
//            moves[0]=
//
//        }
    }
//    System.out.print(board.indexOf(25));
}
