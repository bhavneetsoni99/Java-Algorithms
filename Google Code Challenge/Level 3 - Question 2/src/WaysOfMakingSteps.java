
/**
 * <b>IDeserve <br>
 * <a href="https://www.youtube.com/c/IDeserve">https://www.youtube.com/c/IDeserve</a>
 * Total number of ways to make change using an infinite supply of coins of denomination set {20,10,5,1}
 * @author Nilesh
 */
import java.util.HashMap;
import java.util.Objects;

public class WaysOfMakingSteps {
    class AmountDenom {
        int bricks;
        int denom;

        public AmountDenom(int bricks, int denom)
        {
            this.bricks = bricks;
            this.denom = denom;
        }

        // we need to override hashCode and equals method for user defined objects when these objects are used as keys
        @Override
        public int hashCode()
        {
            // since this code uses jdk 7
            return Objects.hash(this.bricks, this.denom);
        }

        @Override
        public boolean equals(Object obj){
            if (obj instanceof AmountDenom) {
                AmountDenom keyObj = (AmountDenom) obj;
                return (keyObj.bricks == this.bricks && keyObj.denom == this.denom);
            } else {
                return false;
            }
        }
    }
    // denominations we can use : 20,10,5 and 1
    public int countNumberOfWays(int bricks, int denom, HashMap<AmountDenom, Integer> numberOfWays)
    {
        // if only denominations available is 1 then number of ways to make change = 1
        if (denom == 1)
        {
            // store the computed result for re-use
            numberOfWays.put(new AmountDenom(bricks, denom), 1);
            return 1;
        }

        int nextDenom = 0;

        if (denom == 20) // if current denom is 25 then next denom to be used is 10
        {
            nextDenom = 10;
        }
        else if (denom == 10) // if current denom is 10 then next denom to be used is 5
        {
            nextDenom = 5;
        }
        else if (denom == 5) // if current denom is 5 then next denom to be used is 1
        {
            nextDenom = 1;
        }

        // try all possible number of coins of current denomination
        int numberOfCoins = 0, ways = 0, modifiedAmount;
        while ((numberOfCoins*denom) <= bricks)
        {
            modifiedAmount = bricks - (numberOfCoins*denom);

            // check if we have already computed the number of ways for this amount and denom set
            if (numberOfWays.get(new AmountDenom(modifiedAmount, denom)) != null)
            {
                ways += numberOfWays.get(new AmountDenom(modifiedAmount, denom));
            }
            else
            {
                ways += countNumberOfWays(modifiedAmount, nextDenom, numberOfWays);
            }
            numberOfCoins += 1;
        }

        // store the computed result for re-use
        numberOfWays.put(new AmountDenom(bricks, denom), ways);
        return ways;
    }


    // denominations we can use : 20,10,5 and 1
    public int countNumberOfWays(int bricks, int denom)
    {
        // if only denominations available is 1 then number of ways to make change = 1
        if (denom == 1)
        {
            return 1;
        }

        int nextDenom = 0;

        if (denom == 20) // if current denom is 25 then next denom to be used is 10
        {
            nextDenom = 10;
        }
        else if (denom == 10) // if current denom is 10 then next denom to be used is 5
        {
            nextDenom = 5;
        }
        else if (denom == 5) // if current denom is 5 then next denom to be used is 1
        {
            nextDenom = 1;
        }

        // try all possible number of coins of current denomination
        int numberOfCoins = 0, ways = 0;
        while ((numberOfCoins*denom) <= bricks)
        {
            ways += countNumberOfWays(bricks - (numberOfCoins*denom), nextDenom);
            numberOfCoins += 1;
        }

        return ways;
    }


    public static void main(String[] args)
    {
        WaysOfMakingSteps solution = new WaysOfMakingSteps();

        int bricks = 200;
        HashMap<AmountDenom, Integer> numberOfWays = new HashMap();
        System.out.println("Number of ways of making change for 20 using denominations of 20,10,5 and 1 are:\n" +solution.countNumberOfWays(bricks, 4, numberOfWays));
    }
}
