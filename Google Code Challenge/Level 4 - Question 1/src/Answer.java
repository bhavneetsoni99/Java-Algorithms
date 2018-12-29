//submission code

package com.google.challenges;
import java.math.BigInteger;
public class Answer {
    private static final BigInteger biggestLong = new BigInteger(Long.toString(Long.MAX_VALUE));
    private static final int biggestInt = Integer.MAX_VALUE;
    private static final BigInteger biggestIntBigInt = new BigInteger(Integer.toString(biggestInt));
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
            Answer.setDefaults();
            BigInteger m = new BigInteger(M);
            BigInteger f = new BigInteger(F);
            BigInteger smaller = m.min(f);
            //get the bigger of two
            BigInteger bigger = m.max(f);

            if (bigger.compareTo(Answer.biggestLong) == 1) {
                //numbers are bigInteger transfer to BigInt handler
                Answer.findGenerationsBigInt(bigger, smaller);
            } else {
                //none of the number is a a bigInteger
                if (bigger.compareTo(Answer.biggestIntBigInt) == 1) {
                    //transfer code to long handler
                    long bL = bigger.longValue();
                    long sL = smaller.longValue();
                    Answer.findGenerationsLong(bL, sL);
                } else {
                    //transfer code to int handler
                    int bI = bigger.intValue();
                    int sI = smaller.intValue();
                    Answer.findGenerationsInt(bI, sI);
                }
            }
        }
        String generationString;
        if (isImpossible) {
            generationString = "impossible";
        } else {
            if (Answer.genIsInt) {
                generationString = Integer.toString(Answer.generationsInt);
            } else if (Answer.genIsLong) {
                generationString = Long.toString(Answer.generationsLong);

            } else {
                generationString = Answer.generationsBig.toString();
            }
        }
        return generationString;

    }
    private static void findGenerationsBigInt(BigInteger bigger, BigInteger smaller){
        if (smaller.compareTo(new BigInteger("1")) == 0) {
            //handle when you get 1 as smaller number
            Answer.trackGenerations(bigger.subtract(smaller));
        } else {
            if (bigger.compareTo(Answer.biggestLong) == 1 ){
                BigInteger iteration[] = bigger.divideAndRemainder(smaller);


                //remainder is zero - not possible means at some point they will be equal
                if (iteration[1].compareTo(new BigInteger("0")) == 0 ){
                    Answer.isImpossible = true;
                    return;
                } else {
                    //quotient will be the generations
                    Answer.trackGenerations(iteration[0]);
                    //remainder will be smaller than the divisor so smaller becomes the bigger
                    bigger = smaller;
                    smaller = iteration[1];
                    if (bigger.compareTo(Answer.biggestLong) == 1) {
                        Answer.findGenerationsBigInt(bigger, smaller);
                    } else if (bigger.compareTo(Answer.biggestIntBigInt) == 1){
                        Answer.findGenerationsLong(bigger.longValue(), smaller.longValue());
                    } else {
                        Answer.findGenerationsLong(bigger.intValue(), smaller.intValue());
                    }
                }

            }
        }
    }
    private static void findGenerationsLong(long biggerL, long smallerL) {
        if (biggerL == smallerL) {
            Answer.isImpossible = true;
            return;
        } else {
            if (smallerL == 1L) {
                //handle when you get 1 as smaller number
                Answer.trackGenerations(biggerL - smallerL);
            } else {
                long iterationsL = biggerL / smallerL;
                long remainderL = biggerL % smallerL;

                if (remainderL == 0) {
                    Answer.isImpossible = true;
                    return;
                } else if (remainderL == 1L) {
                    Answer.trackGenerations(iterationsL - 1L);
                } else
                    Answer.trackGenerations(iterationsL);
                biggerL = smallerL;
                smallerL = remainderL;
                if (biggerL > biggestInt) {
                    Answer.findGenerationsLong(biggerL, smallerL);
                } else {
                    Answer.findGenerationsInt((int) biggerL, (int) smallerL);
                }
            }
        }
    }
    private static void findGenerationsInt(int biggerI, int smallerI){
        if (biggerI == 1 && smallerI == 1) {
            return;
        } else if (biggerI == smallerI) {
            Answer.isImpossible = true;
            return;
        } else {
            if (smallerI == 1) {
                Answer.trackGenerations(biggerI - 1);
            } else {
                int iterationsI = biggerI / smallerI;
                int remainderI = biggerI % smallerI;
                if (remainderI == 0) {
                    Answer.isImpossible = true;
                    return;
                } else {
                    Answer.trackGenerations(iterationsI);
                    biggerI = smallerI;
                    smallerI = remainderI;
                    Answer.findGenerationsInt(biggerI, smallerI);
                }
            }
        }
    }

    private static void trackGenerations(BigInteger iterations) {
        if (Answer.genIsBig){
            Answer.generationsBig = Answer.generationsBig.add(iterations);
        } else {
            if (iterations.compareTo(Answer.biggestLong) == 1) {
                Answer.genIsBig = true;
                Answer.genIsLong = false;
                Answer.genIsInt = false;
                if (Answer.generationsInt != 0) {
                    Answer.generationsBig = Answer.generationsBig.add(new BigInteger(Integer.toString(Answer.generationsInt)));
                    Answer.generationsInt = 0;
                }
                if (Answer.generationsLong != 0) {
                    Answer.generationsBig = Answer.generationsBig.add(new BigInteger(Long.toString(Answer.generationsLong)));
                    Answer.generationsLong = 0L;
                }
                Answer.generationsBig = Answer.generationsBig.add(iterations);
            } else {
                Answer.trackGenerations(iterations.longValue());
            }
        }
    }
    private static void trackGenerations(long iterationsL) {
        if (Answer.genIsBig) {
            Answer.generationsBig = Answer.generationsBig.add(new BigInteger(Long.toString(iterationsL)));
        } else if (Answer.genIsInt){
            if (iterationsL + generationsInt >biggestInt){
                Answer.genIsInt = false;
                Answer.genIsLong = true;
                Answer.trackGenerations(iterationsL);
            }else{
                Answer.generationsInt = Answer.generationsInt + (int) iterationsL;
            }
        }else if (Answer.genIsLong) {
            if (Long.MAX_VALUE - iterationsL > Answer.generationsLong) {
                if (Answer.generationsInt != 0) {
                    Answer.generationsLong = Answer.generationsLong + Answer.generationsInt;
                    Answer.generationsInt = 0;
                }
                Answer.generationsLong = Answer.generationsLong + iterationsL;
            } else {
                Answer.genIsInt = false;
                Answer.genIsLong = false;
                Answer.genIsBig = true;
                Answer.generationsBig = Answer.generationsBig.add(new BigInteger(Long.toString(iterationsL)));
                if (Answer.generationsLong != 0L) {
                    Answer.generationsBig = Answer.generationsBig.add(new BigInteger(Long.toString(Answer.generationsLong)));
                    Answer.generationsLong = 0L;
                }
                if (Answer.generationsInt != 0) {
                    Answer.generationsBig = Answer.generationsBig.add(new BigInteger(Integer.toString(Answer.generationsInt)));
                    Answer.generationsInt = 0;
                }
            }
        }
    }

    private static void trackGenerations(int iterationsI) {
        if (genIsBig) {
            Answer.generationsBig = Answer.generationsBig.add(new BigInteger(Integer.toString(iterationsI)));
        } else if (genIsLong) {
            if (Long.MAX_VALUE - iterationsI > Answer.generationsLong) {
                Answer.generationsLong = Answer.generationsLong + iterationsI;
            } else {
                Answer.genIsInt = false;
                Answer.genIsLong = false;
                Answer.genIsBig = true;
                Answer.generationsBig = Answer.generationsBig.add(new BigInteger(Integer.toString(iterationsI)));
                if (Answer.generationsLong != 0L) {
                    Answer.generationsBig = Answer.generationsBig.add(new BigInteger(Long.toString(Answer.generationsLong)));
                    Answer.generationsLong = 0L;
                }
                if (Answer.generationsInt != 0) {
                    Answer.generationsBig.add(new BigInteger(Integer.toString(Answer.generationsInt)));
                    Answer.generationsInt = 0;
                }

            }
        } else {
            if (Answer.biggestInt - iterationsI > Answer.generationsInt) {
                Answer.generationsInt = Answer.generationsInt + iterationsI;
            } else {
                Answer.genIsInt = false;
                Answer.genIsLong = true;
                Answer.generationsLong = Answer.generationsLong + Answer.generationsInt;
                Answer.generationsInt = 0;
                Answer.trackGenerations(iterationsI);
            }
        }
    }
    private static void setDefaults(){
        Answer.genIsInt = true;
        Answer.genIsLong = false;
        Answer.genIsBig = false;
        Answer.generationsInt = 0;
        Answer.generationsLong = 0L;
        Answer.generationsBig = new BigInteger("0");
        Answer.isImpossible = false;
    }

}