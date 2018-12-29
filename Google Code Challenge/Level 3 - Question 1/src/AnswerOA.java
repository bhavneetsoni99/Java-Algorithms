import java.util.ArrayList;

/**
 * Created by bsoni1 on 12/9/2016.
 */
public class AnswerOA {

        //    ArrayList<ArrayList<Integer>> maze = new ArrayList<ArrayList<Integer>>();
//        maze.add([0, 0, 0, 0, 0, 0],[1, 1, 1, 1, 1, 0],[0, 0, 0, 0, 0, 0],[0, 1, 1, 1, 1, 1], [0, 1, 1, 1, 1, 1], [0, 0, 0, 0, 0, 0]);
        public static void main(String[] args) {

            // int [][] maze = {{0, 1, 1, 0}, {0, 0, 0, 1},{1, 5, 0, 0},{1, 1, 1, 0}};
            int[][] maze = {{0, 0, 0, 0, 0, 0}, {1, 1, 1, 1, 1, 0}, {0, 0, 0, 0, 0, 0}, {0, 1, 1, 1, 1, 1}, {0, 1, 1, 1, 1, 1}, {0, 0, 0, 0, 0, 0}};

            //answer(maze);

            Maze = maze;
            int height = maze.length;
            int width = maze[0].length;
            for (int  h = 0 ; h < height; h ++){
                for (int w = 0; w < width; w ++  ){
                    ArrayList <Integer> mIndex =  new ArrayList<Integer>();
                    try {
                        int random = maze[h][w+1];
                        mIndex.add((h)*height+w+1);

                    } catch (ArrayIndexOutOfBoundsException e) {

                    }try {
                        int random = maze[h+1][w];
                        mIndex.add((h+1)*height+w);

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
            countSteps(0, 35);

            System.out.println("**********final answer is  ---" + steps);
        }
        private static int [][] Maze;
        private static int steps = 1;
        private static int nOnes = 0;
        private static ArrayList<ArrayList<Integer>> moves = new ArrayList<ArrayList<Integer>>();
        private static ArrayList<Integer> temp = new ArrayList<Integer>();
        private static ArrayList<Integer> visited = new ArrayList<Integer>();

        private static void countSteps(int src, int dest) {
            visited.add(src);
            nOnes = nOnes+Maze[src/(Maze.length)][src%(Maze[0].length)];
            System.out.println("Ones are ---- " + nOnes + " at  ["+src/(Maze.length)+"],["+src%(Maze[0].length)+"]");
            ArrayList<Integer> a = moves.get(src);
            System.out.println("checking in   ---" + a);
            if(a.contains(dest)){
                System.out.println("found in "+ a);
                return;// steps;
            }else {
                visited.addAll(a);
                System.out.println("allreadyChecked " + visited);
                int aSize = a.size();
                for (int i = 0; i < aSize; i++) {
                    nOnes = nOnes+Maze[a.get(i)/(Maze.length)][a.get(i)%(Maze[0].length)];
                    System.out.println("Ones are ---- " + nOnes + " at  ["+a.get(i)/(Maze.length)+"],["+a.get(i)%(Maze[0].length)+"]");
                    getNextMoves(a.get(i), dest);//prepare next set of temp array for us
                }
                steps = steps + 1;
                ifDestisThere(dest); //search in temp array
                // return steps;
            }

        }
        private static void getNextMoves(int startPt, int dest) {

            ArrayList<Integer> nextMoves = moves.get(startPt);
            System.out.println("next set of moves are    " + nextMoves + " startpoint is  " + startPt + "   src is    " + startPt);
            //nextMoves.remove(new Integer(src)); //removing older to src

            for (int nextM = 0; nextM < nextMoves.size(); nextM++) {
                if (temp.contains(nextMoves.get(nextM))) {

                } else {
                    temp.add(nextMoves.get(nextM));
                }

            }
            System.out.println("temp is   ---" + temp);
            temp.removeAll(visited);
            System.out.println("removed checked   " + temp);


        }

        //check in each of temp array elements
        public static void ifDestisThere(int dest) {
            if (temp.contains(dest)) {
                System.out.println("found in " + temp);
                return;// steps;
            } else {
                steps = steps + 1;
                for (int t = 0; t < temp.size(); t++) {

                    ArrayList<Integer> a = moves.get(temp.get(t));
                    if (a.contains(dest)) {
                        steps++;
                        System.out.println("found in " + temp.get(t));
                        return;// steps;
                    }
                }
                // prepare next temp
                ArrayList<Integer> temp2 = new ArrayList<Integer>(temp);

                System.out.println("*************Cleared old temp**********");
                temp.clear();
                System.out.println("****new temp*****" + temp2);

                System.out.println("***allreadychecked*****" + visited);
                for (int tempitemp = 0; tempitemp < temp2.size(); tempitemp++) {

                    visited.add(temp2.get(tempitemp));
                    getNextMoves(temp2.get(tempitemp), dest);
                }

                ifDestisThere(dest);
            }
            //return steps;
        }
    }

/*
    private static int count = 0;
    public static int answer(int[][] maze) {
        int len = maze.length;
        step [] searchTree = new step [len*len]; //search tree for objects of nodes
        int k = 0;
        for (int  row = 0 ; row < len; row ++){
            for (int col = 0; col<len; col ++  ){
                ArrayList <Integer> mIndex =  new ArrayList<Integer>();
                try {
                    int random = maze[row][col+1];
                    mIndex.add((row)*len+col+1);

                } catch (ArrayIndexOutOfBoundsException e) {

                }try {
                    int random = maze[row+1][col];
                    mIndex.add((row+1)*len+col);

                } catch (ArrayIndexOutOfBoundsException e) {

                }try {
                    int random = maze[row][col-1];
                    mIndex.add((row)*len+col-1);

                } catch (ArrayIndexOutOfBoundsException e) {

                }
                try {
                    int random = maze[row-1][col];
                    mIndex.add((row-1)*len+col);

                } catch (ArrayIndexOutOfBoundsException e) {

                }
                searchTree[k] = new step(k, maze[row][col], mIndex);
                k++;
            }
        }
        for (int i = 0 ; i < searchTree.length; i++) {
            System.out.println(searchTree[i].id  +  "  value is  - " +searchTree[i].value + " possible moves are " + searchTree[i].moves);
        }
        seePath(0,len*len-1,searchTree);
        // Your code goes here.
        return count;
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

    public static void seePath(int start, int end, step [] searchTree) {
        System.out.println("starting from  "+  start + " looking for " + end);
        System.out.println(nOnes);
        //int firstLocation = start;//Members.indexOf(first);
        allreadyCheckedPath.add(start);
        //parents is a list to hold the search point we are at
        parents.add(start); //add the search node to the parents list
        searchTree[start].color = "grey";
        if (searchTree[start].value == 1){
            nOnes++;
//        }
//        if (nOnes>1){
//            parents.remove(start);
//            allreadyCheckedPath.add(start);
//            nOnes = nOnes-searchTree[start].value;
//            return;
        }else {
            int thisNodeParent = start; //searchTree.get(firstLocation).parent;
            ArrayList<Integer> thisNode = searchTree[start].moves;
            thisNode.removeAll(allreadyCheckedPath);

            System.out.println("child are  - " + thisNode);
            if (thisNode.isEmpty()) {
                searchTree[start].color = "Black";
                parents.remove(start);

            } else {
                if (thisNode.contains(end)) {
                    found = true;
                    parents.add(end);
                    printPath();
                } else {
                    allreadyCheckedPath.addAll(thisNode);
                    int interator = thisNode.size();
                    for (int i = 0; i < interator; i++) {
                        if(nOnes<2){
                        if (!found) {
                            seePath(thisNode.get(i), end, searchTree); // keep calling it recursively till you find in the
                            if (!found) {
                                //if the destination was not found  in this step remove the parent node from the search path
                                parents.remove(thisNode.get(i));
                            }
                        }
                        }else{
                            nOnes = nOnes-searchTree[start].value;
                            parents.remove(thisNode.get(i));
                        }
                    }
                }
            }
        }
    }

    private static void printPath(){
        System.out.print(parents);
        for(int i = 0; i <parents.size(); i++){
            System.out.print(parents.get(i) +" -->");
        }
        parents.clear();
    }


}
*/
