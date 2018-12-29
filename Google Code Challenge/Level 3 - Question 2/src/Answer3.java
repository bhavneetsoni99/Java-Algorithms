import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by BHAVN on 2/19/2017.
 */
public class Answer3 {

    public static void main(String[] args) {
        int Steps = Answer3.answer(7);
        System.out.println("Total Number of Steps Possible is  " + Steps);//487067745
    }

    public static int answer(int n) {
        int possibleSteps=0;
        if(n<=4){
            return 1;
        }else{
            Integer [] firstStep = Answer3.makeStep(n,n);
            int firstStepHt = firstStep.length-1;

            for(int i =firstStepHt ; i>=0;  i--) {
                int remainingBricks = n-firstStep[i]; //bricksInFirstStep.get(i);
                int maxHt;
                if(remainingBricks >= firstStep[i]){
                    maxHt = firstStep[i]-1;
                }else{
                    maxHt = remainingBricks;
                }
                String key = remainingBricks +" - " + maxHt;
                if(Answer3.computed.containsKey(key)){
                    return computed.get(key);
                }else if (remainingBricks<=2){
                    possibleSteps=1;
                    Answer3.computed.put(key, possibleSteps);
                    //return possibleSteps;
                }else {
                    possibleSteps += Answer3.remainingPossible(remainingBricks, maxHt);

                    Answer3.computed.put(key,possibleSteps);
                }
            }

        }
        isFirstStep=true;
        return possibleSteps;
    }
    private static HashMap<String, Integer> computed = new HashMap<String, Integer>();
    private static int remainingPossible(int remainingBricks, int maxHt){
        int possibleSteps = 0;
        String key = remainingBricks +" - " + maxHt;
        if(computed.containsKey(key)){
            return computed.get(key);
        }else{

            Integer [] nextStep = makeStep(remainingBricks,maxHt);
            int nextStepHt = nextStep.length-1;

            for(int i =nextStepHt ; i>=0;  i--) {
                remainingBricks = remainingBricks-nextStep[i]; //bricksInFirstStep.get(i);

                if(remainingBricks >= nextStep[i]){
                    maxHt = nextStep[i]-1;
                }else{
                    maxHt = remainingBricks;
                }
                    possibleSteps += Answer3.remainingPossible(remainingBricks, maxHt);
                remainingBricks=remainingBricks+nextStep[i];
            }
        }
        return possibleSteps;
    }
    private static boolean isFirstStep = true;
    private static boolean isSecondStep = true;
    private static Integer [] makeStep(int n, int maxHt){
        ArrayList<Integer> bricksInStepList = new ArrayList<Integer>();
        if (Answer3.isFirstStep){
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
//first step might contain more than
            for (int i = (mxPosSteps + excessiveBricksinFirstStep); i < n; i++) {
                bricksInStepList.add(i);
            }
            isFirstStep = false;

        }else{
            for (int i = 1; i <= maxHt; i++) {
                bricksInStepList.add(i);
            }
            }
        Integer bricksInStep[] = bricksInStepList.toArray(new Integer[bricksInStepList.size()]);
        //System.out.print(bricksInStep);
        return bricksInStep;
    }
    private static void getCombs(int startingStepHt, ArrayList<ArrayList<Integer>> brickCombinations, int n, int previousStepIndex, int sum, int mxPosSteps) {
        //System.out.println("function      "+possibleStairCases);
        // System.out.println("function called with ht  "+startingStepHt+" Previous stepIndex  "+ previousStepIndex+ "   Sum is ---"+sum);
        int oldSum = sum;
        int nextStepIndex = previousStepIndex+1;
        if (nextStepIndex <= mxPosSteps - 1) {
            ArrayList<Integer> nextStep2 = brickCombinations.get(nextStepIndex);
            Integer nextStep[]=nextStep2.toArray(new Integer[nextStep2.size()]);


            int nextStepSize = nextStep.length;//size();
            int possibleValuesToCheck = nextStep2.indexOf(startingStepHt);
            if(possibleValuesToCheck ==-1){
                possibleValuesToCheck= nextStepSize-1;
            }
            for (int bIndex =0 ; bIndex <=possibleValuesToCheck; bIndex++) {
                int bricks = nextStep[bIndex];//.get(bIndex);
                if (bricks >= startingStepHt) {
                } else {

                    sum = sum + bricks;
                    if(sum  > n){
                        sum = oldSum;
                        continue;
                        //getCombs(bricks, brickCombinations, n, nextStepIndex, sum, mxPosSteps);
                    }else if(sum == n) {
                        sum = oldSum;
                        //possibleStairCases++;
                       // System.out.println("function      "+possibleStairCases);
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
