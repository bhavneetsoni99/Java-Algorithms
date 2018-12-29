import java.util.ArrayList;

/**
 * Created by bsoni1 on 12/9/2016.
 */
public class AnswerTree {
    private static int [][] Maze;
    private static int steps = 1;
    private static int nOnes = 0;
    private static ArrayList<ArrayList<Integer>> moves = new ArrayList<ArrayList<Integer>>();
    private static ArrayList<Integer> lookingIn = new ArrayList<Integer>();
    private static ArrayList<Integer> visited = new ArrayList<Integer>();
    private static ArrayList<ArrayList<Integer>> paths = new ArrayList<ArrayList<Integer>>();
    public static void main(String[] args) {

        // int [][] maze = {{0, 1, 1, 0}, {0, 0, 0, 1},{1, 5, 0, 0},{1, 1, 1, 0}};
        int[][] maze = {{0, 0, 0, 0, 0, 0}, {1, 1, 1, 1, 1, 0}, {0, 0, 0, 0, 0, 0}, {0, 1, 1, 1, 1, 1}, {0, 1, 1, 1, 1, 1}, {0, 0, 0, 0, 0, 0}};

        //answer(maze);
        Maze = maze;
        int height = maze.length;
        int width = maze[0].length;
        for (int  h = 0 ; h < height; h ++){
            for (int w = 0; w < width; w ++  ){
                ArrayList<Integer> mIndex =  new ArrayList<Integer>();
                try {
                    int random = maze[h+1][w];
                    mIndex.add((h+1)*height+w);

                } catch (ArrayIndexOutOfBoundsException e) {

                }try {
                    int random = maze[h][w+1];
                    mIndex.add((h)*height+w+1);

                } catch (ArrayIndexOutOfBoundsException e) {

                }try {
                    int random = maze[h][w-1];
                    mIndex.add((h)*height+w-1);

                } catch (ArrayIndexOutOfBoundsException e) {

                }
                try {
                    int random = maze[h-1][w];
                    mIndex.add((h-1)*height+w);

                } catch (ArrayIndexOutOfBoundsException e) {

                }
                moves.add(mIndex);

            }
        }
        System.out.println(moves);
        queue.add(0);
        while (queue.size()<height*width) {
            buildqueue(0);
        }
        System.out.println("**********final answer is  ---" + queue);
        //find(0,35);
        printNetwork();

        System.out.println("**********final answer is  ---" + steps);
    }
    private static ArrayList<Integer> queue = new ArrayList<Integer>();
    private static void buildqueue(int src){
        ArrayList<Integer> level = moves.get(src);
        level.removeAll(queue);
        queue.addAll(level);

        for (int i = 0; i < level.size(); i++){
            buildqueue(level.get(i));
           }
    }


    private static void find(int src, int dest){

        if (src == dest){
            ArrayList<Integer> addtopath = new ArrayList<Integer>();
            addtopath.addAll(lookingIn);
            paths.add(addtopath);
            lookingIn.clear();
        }else {
            ArrayList<Integer> level = new ArrayList<Integer>();
            level = moves.get(src);
            lookingIn.add(src);
            visited.add(src);
            level.removeAll(visited);

            int iterator = level.size();
            for (int i = 0; i < iterator; i++) {
                if (moves.get(i).contains(dest)) {
                    ArrayList<Integer> addtopath = new ArrayList<Integer>();
                    addtopath = lookingIn;
                    paths.add(addtopath);
                    lookingIn.clear();
                } else {
                    queue.add(level.get(i));
                    find(level.get(i), dest);
                }
            }
        }
    }
    public static void printNetwork(){
        int len = paths.size();
        System.out.println(len);
        for(int i = 0; i <len; i++){
            //System.out.print(Members.get(i)+ "  ----->  ");
            System.out.print(paths.get(i));
            System.out.println();

        }
    }


}
