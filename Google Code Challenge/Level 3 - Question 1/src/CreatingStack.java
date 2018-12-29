import java.util.ArrayList;

/**
 * Created by bsoni1 on 12/9/2016.
 */
public class CreatingStack {
    public static void main(String[] args) {
        int[][] maze = {{0, 0, 0, 0, 0, 0}, {1, 1, 1, 1, 1, 0}, {0, 0, 0, 0, 0, 0}, {0, 1, 1, 1, 1, 1}, {0, 1, 1, 1, 1, 1}, {0, 0, 0, 0, 0, 0}};
        Maze = maze;
        int height = maze.length;
        int width = maze[0].length;
        for (int h = 0; h < height; h++) {
            for (int w = 0; w < width; w++) {
                ArrayList<Integer> mIndex = new ArrayList<Integer>();
                try {
                    int random = maze[h][w + 1];
                    mIndex.add((h) * height + w + 1);

                } catch (ArrayIndexOutOfBoundsException e) {

                }
                try {
                    int random = maze[h + 1][w];
                    mIndex.add((h + 1) * height + w);

                } catch (ArrayIndexOutOfBoundsException e) {

                }
                try {
                    int random = maze[h][w - 1];
                    mIndex.add((h) * height + w - 1);

                } catch (ArrayIndexOutOfBoundsException e) {

                }
                try {
                    int random = maze[h - 1][w];
                    mIndex.add((h - 1) * height + w);

                } catch (ArrayIndexOutOfBoundsException e) {

                }
                moves.add(mIndex);

            }
        }
        ArrayList<Integer> r = new ArrayList<>();
        r.add(0);
        visited.add(0);
        stack.add(r);
        levelHeight = 1;
        buildStack(height*width-1);
        System.out.println(stack);
    }
    private static int levelHeight;
    private static int[][] Maze;
    private static ArrayList<ArrayList<Integer>> moves = new ArrayList<ArrayList<Integer>>();

    private static ArrayList<Integer> visited = new ArrayList<Integer>();
    private static ArrayList<ArrayList<Integer>> stack = new ArrayList<ArrayList<Integer>>();

    private static void buildStack(int exitPoint) {
    int size = stack.size();
    int pushPoint = size;
    ArrayList<Integer> level = stack.get(size-1);
    for(int i = 0; i<level.size(); i++){
        ArrayList<Integer> stackLevel = moves.get(level.get(i));
        stackLevel.removeAll(visited);
        visited.addAll(stackLevel);
        //if stacklevel exist add to it else add a new stacklevel
        try{
        stack.get(pushPoint).addAll(stackLevel);
        }catch (IndexOutOfBoundsException e){
            stack.add(stackLevel);
        }
    }
        while(!visited.contains(exitPoint)){
            buildStack(exitPoint);}
    }
}
