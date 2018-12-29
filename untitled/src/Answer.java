import java.math.*;
public class Answer {
    public static void main(String[] args) {
        int[] xs = new int[3];
        xs[0] = 1;
        xs[1] = -0;
        xs[2] = 1;
//        xs[3] = -1000;
//        xs[4] = 1000;
//        xs[5] = -1000;
//        xs[6] = -1000;
//        xs[7] = -1000;
//        xs[8] = -1000;
//        xs[9] = -1000;
//        xs[10] = -1000;
//        xs[11] = -1000;
//        xs[12] = -1000;
//        xs[13] = -1000;
//        xs[14] = -1000;
//        xs[15] = -1000;
//        xs[16] = -1000;

        String powerString;
        int length = xs.length;

        if (length >= 1) {
            int power = 1;
            long powerLong = 0L;
            BigInteger powerBig = new BigInteger("1");
            int negCount = 0;
            int posCount = 0;
            int leastNeg = -1001;
            int maxInt = Integer.MAX_VALUE;
            long maxLong = Long.MAX_VALUE;
            int i;
            int l;
            boolean isBig = false;
            boolean isLong = false;

            for (i = 0; i < length; i++) {
                if (StrictMath.abs(maxInt / power) < StrictMath.abs(xs[i])) { //Integer.MAX_VALUE/power
                    powerLong = power;
                    isLong = true;
                    break;
                }
                if (xs[i] > 1) {
                    posCount = posCount + 1;
                    power = power * xs[i];
                } else if (xs[i] < 0) {
                    negCount = negCount + 1;
                    if (xs[i] > leastNeg) {
                        leastNeg = xs[i];
                    }
                    power = power * xs[i];
                }
            }
            for (l = i; l < length; l++) {
                if (StrictMath.abs(maxLong / powerLong) < StrictMath.abs(xs[l])) {
                    powerBig = new BigInteger(Long.toString(powerLong));
                    isBig = true;
                    break;
                }
                if (xs[l] > 1) {
                    posCount = posCount + 1;
                    powerLong = powerLong * xs[l];//convert to biginteger
                } else if (xs[l] < 0) {
                    negCount = negCount + 1;
                    if (xs[l] > leastNeg) {
                        leastNeg = xs[l];
                    }
                    powerLong = powerLong * xs[l];
                }
            }
            for (int b = l; b < length; b++) {
                if (xs[b] > 1) {
                    posCount = posCount + 1;
                    BigInteger xsElement = new BigInteger(Integer.toString(xs[b]));
                    powerBig = powerBig.multiply(xsElement);
                } else if (xs[b] < 0) {
                    negCount = negCount + 1;
                    if (xs[b] > leastNeg) {
                        leastNeg = xs[b];
                    }
                    BigInteger xsElement = new BigInteger(Integer.toString(xs[b]));
                    powerBig = powerBig.multiply(xsElement);
                }
            }

            if (isBig) {
                if(negCount%2 !=0){
                    BigInteger leastNegBig = new BigInteger(Integer.toString(leastNeg));
                    powerBig = powerBig.divide(leastNegBig);
                }
                powerString = powerBig.toString();
            } else if (isLong) {
                if(powerLong<0){
                    powerLong = powerLong/leastNeg;
                }
                powerString = Long.toString(powerLong);

            } else {
                if(power < 0) {
                    power = power/leastNeg;

                    if (power ==1 && negCount==1&& posCount==0) {
                        power = 0;
                    }
                    powerString = Integer.toString(power);
                }else {
                    if (power ==1 &&  posCount==0 && negCount==0) {
                        power = 0;
                    }
                    powerString = Integer.toString(power);
                }

            }
        } else {
            powerString = "0";
        }

        // return powerString;
        System.out.println(powerString);
    }
}