import java.util.ArrayList;

/**
 * Created by bsoni1 on 12/8/2016.
 */

public class Answer {
//    ArrayList<ArrayList<Integer>> maze = new ArrayList<ArrayList<Integer>>();
//        maze.add([0, 0, 0, 0, 0, 0],[1, 1, 1, 1, 1, 0],[0, 0, 0, 0, 0, 0],[0, 1, 1, 1, 1, 1], [0, 1, 1, 1, 1, 1], [0, 0, 0, 0, 0, 0]);
    public static void main (String[] args) {

    int [][] maze4 = {{0, 1, 1, 0}, {0, 0, 0, 1},{1, 1, 0, 0},{1, 1, 1, 0}};
        int [][] maze6 = {{0, 0, 1, 0, 0, 0}, {0, 1, 1, 0, 1, 0}, {0,1,1,0,1,0}, {0,1,1,0,1,0}, {0,1,1,0,1,0}, {0, 0, 0, 1,1, 0}};
   int steps = answer(maze6);
   System.out.println("result of maze6 is >>>   "+ steps);
       // steps= answer(maze4);
        //System.out.println("result of second is >>>   "+ steps);

    }

    public static int answer(int[][] maze) {
        int height = maze.length;
        int width = maze[0].length;
        step [] searchTree = new step [height*width]; //search tree for objects of nodes
        int k = 0;
        for (int  h = 0 ; h < height; h ++){
            for (int w = 0; w<width; w ++  ){
                ArrayList <Integer> mIndex =  new ArrayList<Integer>();
                try {
                    int random = maze[h][w+1];
                    mIndex.add((h)*width+w+1);

                } catch (ArrayIndexOutOfBoundsException e) {

                }try {
                    int random = maze[h+1][w];
                    mIndex.add((h+1)*width+w);

                } catch (ArrayIndexOutOfBoundsException e) {

                }try {
                    int random = maze[h][w-1];
                    mIndex.add((h)*width+w-1);

                } catch (ArrayIndexOutOfBoundsException e) {

                }
                try {
                    int random = maze[h-1][w];
                    mIndex.add((h-1)*width+w);

                } catch (ArrayIndexOutOfBoundsException e) {
                }
                searchTree[k] = new step(k, maze[h][w], mIndex);
                k++;
            }
        }
        // for (int i = 0 ; i < searchTree.length; i++) {
        //    System.out.println(searchTree[i].id  +  "  value is  - " +searchTree[i].value + " possible moves are " + searchTree[i].moves);
        // }
        seePath(0,height*width-1,searchTree);
        //System.out.println(parents.size());
        int steps = parents.size();
        parents.clear();
        allreadyCheckedPath.clear();
        nOnes=0;
        found = false;
        return steps;
    }

    private static class step{
        int id;
        int value ;
        ArrayList <Integer> moves;
        String color;
        private step(int i, int v, ArrayList<Integer> m) {
            id = i;
            value = v;
            moves = m;
            color = "white";
        }
    }
    private  static boolean found = false;
    private  static int nOnes = 0;
    private  static ArrayList<Integer> allreadyCheckedPath = new ArrayList<Integer>();
    private static ArrayList<Integer> parents=  new ArrayList<Integer>();
    private static ArrayList<ArrayList<Integer>> paths = new ArrayList<ArrayList<Integer>>();

    public static void seePath(int start, int end, step [] searchTree) {
        //System.out.println("starting from  "+  start + " looking for " + end);
        //System.out.println(nOnes);
        allreadyCheckedPath.add(start);
        //parents is a list to hold the search point we are at
        //add the search node to the parents list
        searchTree[start].color = "grey";
        //System.out.println(parents);
        nOnes = nOnes+ searchTree[start].value;
        if(nOnes>1){
            nOnes = nOnes-searchTree[start].value;
            //parents.remove(start);
        }else {
            parents.add(start);
            // System.out.println(parents);

            ArrayList<Integer> thisNode = searchTree[start].moves;
            thisNode.removeAll(allreadyCheckedPath);

            //System.out.println("child are  - " + thisNode);
            if (thisNode.contains(end)) {
                found = true;
                parents.add(end);
                //paths.add(parents);
                //printPath();
            } else {
                if (nOnes > 1) {
                    parents.remove(start);
                    allreadyCheckedPath.remove(start);
                    nOnes = nOnes - searchTree[start].value;
                } else {
                    allreadyCheckedPath.addAll(thisNode);
                    int interator = thisNode.size();
                    for (int i = 0; i < interator; i++) {

                        if (!found) {
                            // System.out.println("Calling seePath for -"+thisNode.get(i));
                            //System.out.println("Value subtracted is -" +searchTree[thisNode.get(i)].value);
                            seePath(thisNode.get(i), end, searchTree); // keep calling it recursively till you find in the
                            if (!found) {
                                //if the destination was not found  in this step remove the parent node from the search path
                                parents.remove(thisNode.get(i));

                            }
                        }
                    }
                    allreadyCheckedPath.removeAll(thisNode);
                    nOnes=nOnes-searchTree[start].value;

                }
            }
        }
    }


    private static void printPath(){
        System.out.println();
        System.out.println(parents);
        for(int i = 0; i <parents.size(); i++){
            System.out.print(parents.get(i) +" -->");
        }
        System.out.println();
        parents.clear();
    }


}

