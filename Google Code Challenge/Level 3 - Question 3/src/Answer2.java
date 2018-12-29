/**
 * Created by BHAVN on 5/9/2017.

 */
import java.math.BigInteger;


public class Answer2 {
    public static void main(String[] args) {
        String Steps = Answer2.answer("1000000000000000005120000000000000000000000000000000000000000000000", "1");
        //1000000000000000005119999999999999999999999999999999999999999999999
        System.out.println("****************************************");
        System.out.println("Total Number of Steps Possible is  " + Steps);
        System.out.println("****************************************");
    }
    private final static BigInteger biggestLong = new BigInteger(Long.toString(Long.MAX_VALUE));
    private final static int biggestInt = Integer.MAX_VALUE;
    private final static BigInteger biggestIntBigInt = new BigInteger(Integer.toString(biggestInt));

    private static boolean genIsInt;
    private static boolean genIsLong;
    private static boolean genIsBig;
    private static boolean isImpossible;
    private static int generationsInt;
    private static long generationsLong;
    private static BigInteger generationsBig;

    public static String answer(String M, String F) {
        if (M =="0" || F =="0"){
            return "impossible";
        } else if (M == F){
            if ( M =="1"){
                return "0";
            }else {
                return "impossible";
            }
        }else {

            Answer2.setDefaults();
            BigInteger m = new BigInteger(M);
            BigInteger f = new BigInteger(F);
            BigInteger smaller = m.min(f);
            //get the bigger of two
            BigInteger bigger = m.max(f);

            if (bigger.compareTo(biggestLong) == 1) {
                //numbers are bigInteger transfer to BigInt handler
                Answer2.findGenerationsBigInt(bigger, smaller);
            } else {
                //none of the number is a a bigInteger
                if (bigger.compareTo(biggestIntBigInt) == 1) {
                    //transfer code to long handler
                    long bL = bigger.longValue();
                    long sL = smaller.longValue();
                    Answer2.findGenerationsLong(bL, sL);
                } else {
                    //transfer to int handler
                    int bI = bigger.intValue();
                    int sI = smaller.intValue();
                    Answer2.findGenerationsInt(bI, sI);
                }
            }
        }
        String generationString;
        if (isImpossible) {
            generationString = "impossible";
        } else {
            if (genIsInt) {
                generationString = Integer.toString(generationsInt);
            } else if (genIsLong) {
                generationString = Long.toString(generationsLong);

            } else {
                generationString = generationsBig.toString();
            }
        }
        return generationString;

    }
    private static void findGenerationsBigInt(BigInteger bigger, BigInteger smaller){
        if (smaller.compareTo(new BigInteger("1")) == 0) {
            //handle when you get 1 as smaller number
            trackGenerations(bigger.subtract(smaller));
        } else {
            if (bigger.compareTo(biggestLong) == 1 ){
                BigInteger iteration[] = bigger.divideAndRemainder(smaller);


                //remainder is zero - not possible means at some point they will be equal
                if (iteration[1].compareTo(new BigInteger("0")) == 0 ){
                    Answer2.isImpossible = true;
                    return;
                } else {
                    //quotient will be the generations
                    Answer2.trackGenerations(iteration[0]);
                    //remainder will be smaller than the divisor so smaller becomes the bigger
                    bigger = smaller;
                    smaller = iteration[1];
                    if (bigger.compareTo(biggestLong) == 1) {
                        Answer2.findGenerationsBigInt(bigger, smaller);
                    } else if (bigger.compareTo(biggestIntBigInt) == 1){
                        Answer2.findGenerationsLong(bigger.longValue(), smaller.longValue());
                    } else {
                        Answer2.findGenerationsLong(bigger.intValue(), smaller.intValue());
                    }
                }

            }
        }
    }
    private static void findGenerationsLong(long biggerL, long smallerL) {
        if (biggerL == smallerL) {
            Answer2.isImpossible = true;
            return;
        } else {
            if (smallerL == 1L) {
                //handle when you get 1 as smaller number
                Answer2.trackGenerations(biggerL - smallerL);
            } else {
                long iterationsL = biggerL / smallerL;
                long remainderL = biggerL % smallerL;

                if (remainderL == 0) {
                    Answer2.isImpossible = true;
                    return;
                } else if (remainderL == 1L) {
                    Answer2.trackGenerations(iterationsL - 1L);
                } else
                    Answer2.trackGenerations(iterationsL);
                biggerL = smallerL;
                smallerL = remainderL;
                if (biggerL > biggestInt) {
                    Answer2.findGenerationsLong(biggerL, smallerL);
                } else {
                    Answer2.findGenerationsInt((int) biggerL, (int) smallerL);
                }
            }
        }
    }
    private static void findGenerationsInt(int biggerI, int smallerI){
        if (biggerI == 1 && smallerI == 1) {
            System.out.println("number of Steps will be   " + "found" );
            return;
        } else if (biggerI == smallerI) {
            Answer2.isImpossible = true;
            return;
        } else {
            if (smallerI == 1) {
                Answer2.trackGenerations(biggerI - 1);
            } else {
                int iterationsI = biggerI / smallerI;
                int remainderI = biggerI % smallerI;
                if (remainderI == 0) {
                    Answer2.isImpossible = true;
                    return;
                } else {
                    Answer2.trackGenerations(iterationsI);
                    biggerI = smallerI;
                    smallerI = remainderI;
                    findGenerationsInt(biggerI, smallerI);
                }
            }
        }
    }

    private static void trackGenerations(BigInteger iterations) {
        if (Answer2.genIsBig){
            Answer2.generationsBig = Answer2.generationsBig.add(iterations);
        } else {
            if (iterations.compareTo(biggestLong) == 1) {
                Answer2.genIsBig = true;
                Answer2.genIsLong = false;
                Answer2.genIsInt = false;
                if (Answer2.generationsInt != 0) {
                    generationsBig = Answer2.generationsBig.add(new BigInteger(Integer.toString(Answer2.generationsInt)));
                    Answer2.generationsInt = 0;
                }
                if (Answer2.generationsLong != 0) {
                    generationsBig = Answer2.generationsBig.add(new BigInteger(Long.toString(Answer2.generationsLong)));
                    Answer2.generationsLong = 0L;
                }
                generationsBig = Answer2.generationsBig.add(iterations);
            } else {
                trackGenerations(iterations.longValue());
            }
        }
    }
    private static void trackGenerations(long iterationsL) {
        if (Answer2.genIsBig) {
            Answer2.generationsBig = Answer2.generationsBig.add(new BigInteger(Long.toString(iterationsL)));
        } else if (Answer2.genIsInt){
            if (iterationsL + generationsInt >biggestInt){
                genIsInt = false;
                genIsLong = true;
                trackGenerations(iterationsL);
            }else{
                generationsInt = generationsInt + (int) iterationsL;
            }
        }else if (Answer2.genIsLong) {
            if (Long.MAX_VALUE - iterationsL > Answer2.generationsLong) {
                if (Answer2.generationsInt != 0) {
                    Answer2.generationsLong = Answer2.generationsLong + Answer2.generationsInt;
                    Answer2.generationsInt = 0;
                }
                Answer2.generationsLong = Answer2.generationsLong + iterationsL;
            } else {
                Answer2.genIsInt = false;
                Answer2.genIsLong = false;
                Answer2.genIsBig = true;
                Answer2.generationsBig = Answer2.generationsBig.add(new BigInteger(Long.toString(iterationsL)));
                if (Answer2.generationsLong != 0L) {
                    Answer2.generationsBig = Answer2.generationsBig.add(new BigInteger(Long.toString(generationsLong)));
                    Answer2.generationsLong = 0L;
                }
                if (Answer2.generationsInt != 0) {
                    Answer2.generationsBig = Answer2.generationsBig.add(new BigInteger(Integer.toString(Answer2.generationsInt)));
                    Answer2.generationsInt = 0;
                }
            }
        }
    }

    private static void trackGenerations(int iterationsI) {
        if (genIsBig) {
            Answer2.generationsBig = Answer2.generationsBig.add(new BigInteger(Integer.toString(iterationsI)));
        } else if (genIsLong) {
            if (Long.MAX_VALUE - iterationsI > generationsLong) {
                Answer2.generationsLong = Answer2.generationsLong + iterationsI;
            } else {
                Answer2.genIsInt = false;
                Answer2.genIsLong = false;
                Answer2.genIsBig = true;
                Answer2.generationsBig = Answer2.generationsBig.add(new BigInteger(Integer.toString(iterationsI)));
                if (Answer2.generationsLong != 0L) {
                    Answer2.generationsBig = Answer2.generationsBig.add(new BigInteger(Long.toString(generationsLong)));
                    Answer2.generationsLong = 0L;
                }
                if (Answer2.generationsInt != 0) {
                    Answer2.generationsBig.add(new BigInteger(Integer.toString(Answer2.generationsInt)));
                    Answer2.generationsInt = 0;
                }

            }
        } else {
            if (biggestInt - iterationsI > generationsInt) {
                generationsInt = generationsInt + iterationsI;
            } else {
                genIsInt = false;
                genIsLong = true;
                generationsLong = generationsLong + generationsInt;
                generationsInt = 0;
                trackGenerations(iterationsI);
            }
        }
    }

    private static void setDefaults(){
        genIsInt = true;
        genIsLong = false;
        genIsBig = false;
        generationsInt = 0;
        generationsLong = 0L;
        generationsBig = new BigInteger("0");
        isImpossible = false;

    }
}
