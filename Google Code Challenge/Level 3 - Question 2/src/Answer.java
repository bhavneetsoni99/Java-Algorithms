import java.util.ArrayList;
import java.util.HashMap;

public class Answer {
    public static void main(String[] args) {
        int Steps = Answer.answer(200);
        System.out.println("Total Number of Steps Possible is  " + Steps);//487067745
    }

    public static int answer(int n) {
        int mxPosSteps = (int) Math.sqrt(2 * n);
        int perfectStaircase = mxPosSteps * (mxPosSteps + 1) / 2;
        int extraBricksfromPerfectStaircase = n - perfectStaircase;
        // to handle when the min number of bricks needs to be more in the first step than max width
        if (perfectStaircase > n) {
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
                ArrayList<Integer> bricksInStep = new ArrayList<Integer>();
                for (int j = 1; j <= maxHeightofStep; j++) {
                    bricksInStep.add(j);
                }
                brickCombinations.add(bricksInStep);
            }
        }

        int possibleStairCases = 0;
        HashMap<String, Integer> computed = new HashMap<String, Integer>();
        for (int i =bricksInFirstStep.size()-1;i >=0; i--) {
            int remainingBricks = n-bricksInFirstStep.get(i);
            int maxHt;
            if(remainingBricks >= bricksInFirstStep.get(i)){
                maxHt = bricksInFirstStep.get(i)-1;
            }else{
                maxHt = remainingBricks;
            }
            possibleStairCases += Answer.getCombs(bricksInFirstStep.get(i),maxHt, brickCombinations, n, 0, bricksInFirstStep.get(i), mxPosSteps, computed);
        }
        return possibleStairCases;

    }
//method to get the possible steps based on the
    private static int getCombs(int startingStepHt,int maxHt, ArrayList<ArrayList<Integer>> brickCombinations, int n, int previousStepIndex, int sum, int mxPosSteps, HashMap<String, Integer> computed) {
        int possibleStairCases = 0;
        int remainingBricks = n - sum;
//if available bricks are more than the number of bricks in previous step start with max possible ht for this step
        if (remainingBricks >= startingStepHt) {
            maxHt = startingStepHt - 1;
        } else {
            maxHt = remainingBricks;
        }
        String key = remainingBricks + " - " + maxHt;
        if (computed.containsKey(key)) {
            return computed.get(key);
        }else{

        int oldSum = sum;
        int nextStepIndex = previousStepIndex + 1;
        if (nextStepIndex <= mxPosSteps - 1) {
            ArrayList<Integer> nextStep2 = brickCombinations.get(nextStepIndex);
            Integer nextStep[] = nextStep2.toArray(new Integer[nextStep2.size()]);

            for (int bIndex = 0; bIndex < maxHt; bIndex++) {
                int bricks = nextStep[bIndex];
                if (bricks >= startingStepHt) {
                } else {
                    sum = sum + bricks;
                    if (sum > n) {
                        sum = oldSum;
                        continue;

                    } else if (sum == n) {
                        possibleStairCases++;
                        computed.put(key, possibleStairCases);
                        break;
                    } else {
                        possibleStairCases += getCombs(bricks, maxHt, brickCombinations, n, nextStepIndex, sum, mxPosSteps, computed);
                        sum = oldSum;
                        computed.put(key, possibleStairCases);
                    }
                }
            }
            }
        }
       return possibleStairCases;
    }
}
