import java.util.ArrayList;
public class Trie {
    public static void main(String[] args) {
        int Steps = Answer4.answer(12);
        System.out.println("Total Number of Steps Possible is  " + Steps);//487067745
    }

    public static int answer(int n) {

        //max width possible is if each step is one smaller than previous one
        int mxPosSteps = (int) Math.sqrt(2 * n);
        //
        // System.out.println("mxPosSteps from initial calc ==   "+mxPosSteps);

        int perfectStaircase = mxPosSteps * (mxPosSteps + 1) / 2;
        //System.out.println("perfectStaircase based on initial calc ==   "+perfectStaircase);

        int extraBricksfromPerfectStaircase = n - perfectStaircase;
        //System.out.println("perfectStaircase  ==   "+extraBricksfromPerfectStaircase);

        // to handle when the min number of bricks needs to be more in the first step than max width
        if (perfectStaircase > n) {
            //  System.out.println("fixing the maxWidth");
            mxPosSteps = mxPosSteps - 1;
            extraBricksfromPerfectStaircase = n - (mxPosSteps * (mxPosSteps + 1) / 2);
        }

        int excessiveBricksinFirstStep = 0;
        if (extraBricksfromPerfectStaircase > 0) {
            excessiveBricksinFirstStep = 1;
        }

        ArrayList<ArrayList<Integer>> brickCombinations = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> bricksInFirstStep = new ArrayList<Integer>();
//first step might contain more than
        for (int i = (mxPosSteps + excessiveBricksinFirstStep); i < n; i++) {
            bricksInFirstStep.add(i);
        }
        brickCombinations.add(bricksInFirstStep);

//remaining steps to be as follows
        if (mxPosSteps >= 2) {
            for (int i = 2; i <= mxPosSteps; i++) {
                int maxHeightofStep = (n - (i * (i - 1) / 2)) / i;
                System.out.println("maxHeight of step  ==   " + i + " is " + maxHeightofStep);
                ArrayList<Integer> bricksInStep = new ArrayList<Integer>();
                for (int j = 1; j <= maxHeightofStep; j++) {
                    bricksInStep.add(j);
                }
                brickCombinations.add(bricksInStep);
            }
        }
//print Arraylists
        for (ArrayList<Integer> l1 : brickCombinations) {
            for (int le : l1) {
                System.out.print(le + " ");
                //countSteps=countSteps+le;
            }

            System.out.println();
        }

        return possibleStairCases;

    }

    private static int possibleStairCases = 0;

    public class Node{
        int parent;
        int value;
        int sumOfParents;
        public  void makeChildNode(){

        }
        public Node(int parent, int sumOfParents, int value, int totalBricks){

            this.parent = parent;
            this.value = value;

        }
        //public static
    }

    private static void getCombs(int startingStepHt, ArrayList<ArrayList<Integer>> brickCombinations, int n, int previousStepIndex, int sum, int mxPosSteps) {
        //System.out.println("function      "+possibleStairCases);
        // System.out.println("function called with ht  "+startingStepHt+" Previous stepIndex  "+ previousStepIndex+ "   Sum is ---"+sum);
        int oldSum = sum;
        int nextStepIndex = previousStepIndex + 1;
        if (nextStepIndex <= mxPosSteps - 1) {
            ArrayList<Integer> nextStep2 = brickCombinations.get(nextStepIndex);
            Integer nextStep[] = nextStep2.toArray(new Integer[nextStep2.size()]);


            int nextStepSize = nextStep.length;//size();
            int possibleValuesToCheck = nextStep2.indexOf(startingStepHt);
            if (possibleValuesToCheck == -1) {
                possibleValuesToCheck = nextStepSize - 1;
            }
            for (int bIndex = 0; bIndex <= possibleValuesToCheck; bIndex++) {
                int bricks = nextStep[bIndex];//.get(bIndex);
                if (bricks >= startingStepHt) {
                } else {

                    sum = sum + bricks;
                    if (sum > n) {
                        sum = oldSum;
                        continue;
                        //getCombs(bricks, brickCombinations, n, nextStepIndex, sum, mxPosSteps);
                    } else if (sum == n) {
                        sum = oldSum;
                        possibleStairCases++;
                        System.out.println("function      " + possibleStairCases);
                        break;
                    } else {
                        getCombs(bricks, brickCombinations, n, nextStepIndex, sum, mxPosSteps);
                        sum = oldSum;
                    }
                }
            }
        }
        // System.out.println("xxxxxxxxxxx");
        // System.out.println("at the end of function sum is"+ sum + " step index is " + nextStepIndex);
    }
}

