import java.math.*;
import java.util.*;
public class Answer {
    public static void main(String[] args) {
        int[] xs = {-2};
        answer(xs);
    }
    public static String answer(int [] xs) {

        String powerString;
        int length = xs.length;
        int power;
        if (length ==1){
            power =  xs[0];
            powerString = Integer.toString(power);
            return powerString;
        }
        ArrayList<Integer> posPan = new ArrayList<Integer>();
        ArrayList<Integer> negPan = new ArrayList<Integer>();
        int leastNeg = -1001;
        for(int a = 0; a<length;a++  ){
            if (xs[a]>0){
                posPan.add(xs[a]);
            }else if(xs[a]<0){
                negPan.add(xs[a]);
                if (xs[a] > leastNeg) {
                    leastNeg = xs[a];
                }
            }
        }
        int negCount = negPan.size();
        if(negCount % 2 == 1){
            negPan.remove(Integer.valueOf(leastNeg));
        }
        posPan.addAll(negPan);
        int posCount = posPan.size();
        if(posCount>0){
            int maxInt = Integer.MAX_VALUE/1000;
            long maxLong = Long.MAX_VALUE/1000;
            long powerLong = 1L;
            BigInteger powerBig = new BigInteger("1");
            int i;
            int l;
            boolean isBig = false;
            boolean isLong = false;
            power =StrictMath.abs(posPan.get(0));
            for(i = 1; i<posCount; i++){
                int nextElement = StrictMath.abs(posPan.get(i));
                if (power > maxInt) { //Integer.MAX_VALUE/power
                    powerLong = power;
                    isLong = true;
                    break;
                }
                power = power*nextElement;
            }
            for (l = i; l < posCount; l++) {
                int nextElement = StrictMath.abs(posPan.get(l));
                if (powerLong > maxLong) {
                    powerBig = new BigInteger(Long.toString(powerLong));
                    isBig = true;
                    break;
                }
                powerLong = powerLong*nextElement;
            }
            for (int b = l; b < posCount; b++) {
                int nextElement = StrictMath.abs(posPan.get(b));
                BigInteger xsElement =new BigInteger(Integer.toString(nextElement));
                powerBig = powerBig.multiply(xsElement);
            }
            if (isBig) {
                powerString = powerBig.toString();
            } else if (isLong) {
                powerString = Long.toString(powerLong);
            } else {
                powerString = Integer.toString(power);
            }
        } else {
            powerString ="0";
        }
        return powerString;
    }
}
