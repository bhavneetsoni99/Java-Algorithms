/**
 * Created by BHAVN on 5/9/2017.

 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Answer2 {
    public static void main(String[] args) {
        List<Integer> banana = new ArrayList<>();
        banana.add(1);
        banana.add(2);
        int guards = answer(banana);
        System.out.println("****************************************");
        System.out.println("Total Number of Steps Possible is  " + guards);
        System.out.println("****************************************");
    }
    //map for infinite loop combinations key (smaller and bigger) value 1
    public static HashMap<String, Integer> infiniteLoopCombinations = new HashMap<String, Integer>();
    private static class Combinations {
        public String combinationString;
        int smaller;
        int bigger;
        public Combinations (int s, int b) {
            this.smaller = s;
            this.bigger = b;
            this.combinationString = s + " and " + b;
            System.out.println(combinationString);
        }
    }

    public static int answer(List <Integer> bananaWithGuardsList) {
        int numberOfGuards = bananaWithGuardsList.size();
        if (numberOfGuards < 2){
            return 1;
        } else{

            //sort the list so that we dont have to worry about duplicate combinations
            //Collections.sort(bananaWithGuardsList);
            Integer [] bananaWithGuardsArray = new Integer [numberOfGuards];
            bananaWithGuardsList.toArray(bananaWithGuardsArray);
            //generate a List of possible combinations nC2 which will (n x(n-1)/2)
            int maxNumberOfPossibleCombinations = numberOfGuards*(numberOfGuards-1)/2;
            int guardsNotInterested = 0;

            // Combinations [] possibleCombinations = new Combinations[];
            //Combinations xyz = new Combinations(1,2);


            for(int i = 0; i <numberOfGuards-1; i++){
                //i is from first to second last
                for (int j = i+1; j < numberOfGuards; j++) {
                    //j from second to last
                    int smaller;
                    int bigger;
                    //set smaller and bigger so we dont have to worry bout combinations 1 and 2 is same as 2 and 1
                    if (bananaWithGuardsArray[i] > bananaWithGuardsArray[j]){
                        smaller = bananaWithGuardsArray[j];
                        bigger = bananaWithGuardsArray[i];
                    }else {
                        bigger = bananaWithGuardsArray[j];
                        smaller = bananaWithGuardsArray[i];
                    }
                        checkCombinations(smaller, bigger);

                    //  if(bananaWithGuardsArray[i])
                }

                //

            }
            return 1;
        }

    }
    private static String checkCombinations (int smaller, int bigger, boolean isItLooping){
        boolean willGoInLoop;
        if(smaller == bigger){
            return "Not Interested";
            //not interested in playing hence invalid combination find another
        }else {
            bigger =
        }

    }
}
