import java.util.HashMap;

/**
 * Created by BHAVN on 2/19/2017.
 */
public class Answer4 {

    public static void main(String[] args) {
        int Steps = Answer4.answer(8);
        System.out.println("Total Number of Steps Possible is  " + Steps);//487067745
    }

    public static int answer(int n) {
        int possibleSteps =0;
        if(n<=4){
            return 1;
        }
        int minHeight = Answer4.minHt(n);
        HashMap<String, Integer> computed = new HashMap<String, Integer>();


        int firstStep = n-1;

        while(firstStep>=minHeight) {
            int sum = firstStep;
            int remainingBricks = n-firstStep;
            System.out.println("Remaining Bricks   " + remainingBricks + "      First Step  " + firstStep);
            String key = remainingBricks +" - " ;
            if(remainingBricks<=2){
                possibleSteps ++;
                computed.put(key, 1);
            }else {

                possibleSteps += Answer4.findSteps(firstStep,remainingBricks,computed, true); //called on all possible first step
                System.out.println("steps after first OutsodeCall  " + firstStep + "     possibleSteps    " + possibleSteps);
                computed.put(key, possibleSteps);
            }
            firstStep--;

        }
        return possibleSteps;
    }

    public static int findSteps(int previousStep,int remainingBricks,HashMap<String, Integer> computed, boolean isSecond){
        if(remainingBricks<=0){
            return 0;
        }
        int possibleSteps = 0;
        String key = remainingBricks +" - " ;
        if (computed.containsKey(key)) {
            return computed.get(key);
        }
        int minHeight= Answer4.minHt(remainingBricks);
        int firstStep;
        if(remainingBricks>=previousStep) {
            firstStep = previousStep - 1;
        }else {
            firstStep=remainingBricks;
        }
            while(firstStep>=minHeight) {
                remainingBricks = remainingBricks-firstStep;

                System.out.println("Remaining Bricks   " + remainingBricks + "      First Step  " + firstStep);
                key = remainingBricks +" - ";
                if (computed.containsKey(key)) {
                    return computed.get(key);
                }
                if(remainingBricks == 0){

                    return possibleSteps;
                }else {
                    possibleSteps += Answer4.findSteps(firstStep, remainingBricks, computed, false);
                    computed.put(key, possibleSteps);
                }
                firstStep--;

            }
        return possibleSteps;
    }

    public static int minHt(int bricks){
        int mxPosSteps = (int) Math.sqrt(2 * bricks);
        int perfectStaircase = mxPosSteps * (mxPosSteps + 1) / 2;
        int extraBricksfromPerfectStaircase = bricks - perfectStaircase;
        if (perfectStaircase > bricks) {
            mxPosSteps = mxPosSteps - 1;
            extraBricksfromPerfectStaircase = bricks - (mxPosSteps * (mxPosSteps + 1) / 2);
        }

        int excessiveBricksinFirstStep = 0;
        if (extraBricksfromPerfectStaircase > 0) {
            excessiveBricksinFirstStep = 1;
        }
        int height = mxPosSteps+excessiveBricksinFirstStep;
        System.out.println("min ht of tep with  " + bricks + " is  "+  height);
        return height;
    }

}
