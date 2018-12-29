import java.util.*;

/**
 * Created by BHAVN on 5/9/2017.

 */


public class Answer {
	public static void main(String[] args) {
		////
//            int[] banana_list = new int[2];
//            banana_list[0] = 1;
//            banana_list[1] = 1;
		////////
		int[] banana_list = new int[100];//1, 2, 5, 6, 13, 16, 16, 38, 48, 58, 104
		banana_list[0] = 91;
		banana_list[1] = 91;
		banana_list[2] = 106;
		banana_list[3] = 109;
		banana_list[4] = 149;
		banana_list[5] = 164;
		banana_list[6] = 2345345;
		banana_list[7] = 234235556;
		banana_list[8] = 23423424;
		banana_list[9] = 78787;
		banana_list[10] = 67679;
		banana_list[11] = 5676567;
		banana_list[12] = 564554;
		banana_list[13] = 23524;
		banana_list[14] = 8996;
		banana_list[15] = 988;
		banana_list[16] = 547378;
		banana_list[17] = 152435;
		banana_list[18] = 2344;
		banana_list[19] = 9;
		banana_list[20] = 910;
		banana_list[21] = 914;
		banana_list[22] = 1056;
		banana_list[23] = 1039;
		banana_list[24] = 1492;
		banana_list[25] = 1644;
		banana_list[26] = 25345;
		banana_list[27] = 232556;
		banana_list[28] = 23424;
		banana_list[29] = 787687;
		banana_list[30] = 676679;
		banana_list[31] = 56767;
		banana_list[32] = 5645554;
		banana_list[33] = 235264;
		banana_list[34] = 83996;
		banana_list[35] = 9388;
		banana_list[36] = 5347378;
		banana_list[37] = 1352435;
		banana_list[38] = 23344;
		banana_list[39] = 96656;
		banana_list[40] = 91;
		banana_list[41] = 91;
		banana_list[42] = 106;
		banana_list[43] = 109;
		banana_list[44] = 149;
		banana_list[45] = 164;
		banana_list[46] = 2345345;
		banana_list[47] = 234235556;
		banana_list[48] = 23423424;
		banana_list[49] = 78787;
		banana_list[50] = 67679;
		banana_list[51] = 5676567;
		banana_list[52] = 564554;
		banana_list[53] = 23524;
		banana_list[54] = 8996;
		banana_list[55] = 988;
		banana_list[56] = 547378;
		banana_list[57] = 152435;
		banana_list[58] = 2344;
		banana_list[59] = 9;
		banana_list[60] = 910;
		banana_list[61] = 914;
		banana_list[62] = 1056;
		banana_list[63] = 1039;
		banana_list[64] = 1492;
		banana_list[65] = 1644;
		banana_list[66] = 25345;
		banana_list[67] = 232556;
		banana_list[68] = 23424;
		banana_list[69] = 787687;
		banana_list[70] = 676679;
		banana_list[71] = 56767;
		banana_list[72] = 5645554;
		banana_list[73] = 235264;
		banana_list[74] = 83996;
		banana_list[75] = 9388;
		banana_list[76] = 5347378;
		banana_list[77] = 1352435;
		banana_list[78] = 23344;
		banana_list[79] = 96656;
		banana_list[80] = 91;
		banana_list[81] = 91;
		banana_list[82] = 106;
		banana_list[83] = 109;
		banana_list[84] = 149;
		banana_list[85] = 164;
		banana_list[86] = 2345345;
		banana_list[87] = 234235556;
		banana_list[88] = 23423424;
		banana_list[89] = 78787;
		banana_list[90] = 67679;
		banana_list[91] = 91;
		banana_list[92] = 106;
		banana_list[93] = 109;
		banana_list[94] = 149;
		banana_list[95] = 164;
		banana_list[96] = 2345345;
		banana_list[97] = 234235556;
		banana_list[98] = 23423424;
		banana_list[99] = 78787;


		// Random rand = new Random();
		// for (int i =0; i<2; i++){
//        int n = 20;//rand.nextInt(5) + 1;
//        int[] banana_list = new int[n];
//        for(int k = 0; k<n; k++){
//            banana_list[k] =rand.nextInt(10) + 1;
//        }
		int guards = answer(banana_list);
		System.out.println("****************************************");
		System.out.println("Total Number of guards guarding are  " + guards);
		System.out.println("****************************************");
		System.out.println("****************************************");
		// }


	}
	public static int answer(int[] banana_list) {
		final long startTime = System.nanoTime();
               /*
        to find the solution we need to take the following steps
            1. find the guards each of the guard will go in infinite loop
            2. first match the ones which are pairing with least number of guards
            3. keep removing them
        */
		int numberOfGuards = banana_list.length;
		int guardsNotInterested = 0;
		if (numberOfGuards < 2) {
			guardsNotInterested = numberOfGuards;
		} else {
			for (int i = 0; i < numberOfGuards; i++) {
				HashSet<Integer> infiniteLoopCombinationsLocal = new HashSet<>();
				//make combinations with the remainig remaining guards (since A->B is same as B->A)
				for (int j = i + 1; j < numberOfGuards; j++) {
					//check the numbers to find if they will loop or not
					boolean isLooping = checkCombinations(banana_list[i], banana_list[j]);

					//handle if they will be looping
					if (isLooping) {

						//add  element (j) to loopingcombs
						infiniteLoopCombinationsLocal.add(j);
						//since i and j are looping j and i are also looping
						// add the element(i) to the element(j)'s combinations appropriately


						if (infiniteLoopCombinationsHashMap.containsKey(j)) {
							//add combination to the map entry if its already there
							infiniteLoopCombinationsHashMap.get(j).add(i);
						} else {
							//prepare new set and put it to the hashmap
							HashSet<Integer> addElementI = new HashSet<>();
							addElementI.add(i);
							infiniteLoopCombinationsHashMap.put(j, addElementI);
						}
					}
				}
				//after the inner loop is over push the looping combinations into hashmap
				// check if the element(i) is present in the hashmapArrays
				if (infiniteLoopCombinationsHashMap.containsKey(i)) {
					//get the already present arraylist add the new numbers into it
					//HashSet<Integer> tempI = infiniteLoopCombinationsHashMap.get(i);
					infiniteLoopCombinationsHashMap.get(i).addAll(infiniteLoopCombinationsLocal);
					//infiniteLoopCombinationsHashMap.put(i, tempI);
				} else {
					//put the combination
					infiniteLoopCombinationsHashMap.put(i, infiniteLoopCombinationsLocal);
				}
			}
			//now start finding the combs continue until all of them have been accounted for
			while(!infiniteLoopCombinationsHashMap.isEmpty()) {
				// find the set with least number of combinations
				Integer removeThis = combsTobeRemoved();
				//handle if smallest one is empty means its not looping with anyone
				if (infiniteLoopCombinationsHashMap.get(removeThis).isEmpty()) {
					infiniteLoopCombinationsHashMap.remove(removeThis);
					guardsNotInterested++;
				} else {
					//if the smallest combination set is not empty find the element with
					//smallest set and combine with it
					Integer removeThisIndextoo = pairWith(removeThis);
					//since numbers have been selected they cant be used to make more combinations
					// remove them from all the elements combinations sets
					removeTheseEntries(removeThis, removeThisIndextoo);
				}
			}
		}
		final long endTime = System.nanoTime();

		System.out.println("Total execution time: " + (endTime - startTime) );
		return guardsNotInterested;
	}
	private static HashMap<Integer, HashSet<Integer>> infiniteLoopCombinationsHashMap = new HashMap<>();
	private static Integer combsTobeRemoved(){
		int smallestComb = 1000;//arbitrary number bigger than 100 (max possible combinations)
		Integer smallestKey =1000;
		for (Integer key : infiniteLoopCombinationsHashMap.keySet()) {
			if(infiniteLoopCombinationsHashMap.get(key).size()<smallestComb){
				smallestComb = infiniteLoopCombinationsHashMap.get(key).size();
				smallestKey = key;
			}
			if(smallestComb ==0){
				break;
			}
		}
		return smallestKey;
	}
	private static Integer pairWith(Integer startingKey){
		int smallestComb=1000; //arbitrary number bigger than 100 (max possible combinations)
		Integer pairWith=1000;


		HashSet<Integer> holder =infiniteLoopCombinationsHashMap.get(startingKey);
		infiniteLoopCombinationsHashMap.remove(startingKey);
		for (Integer k : holder) {

			if (infiniteLoopCombinationsHashMap.get(k).size() < smallestComb) {
				smallestComb = infiniteLoopCombinationsHashMap.get(k).size();
				pairWith = k;
				if (smallestComb == 1) {
					break;
				}
			}
		}
		return pairWith;
	}
	private static void removeTheseEntries(Integer a, Integer b){
		infiniteLoopCombinationsHashMap.remove(a);
		infiniteLoopCombinationsHashMap.remove(b);
		//loop thru the map and remove the numbers used for combination
		for(Integer key: infiniteLoopCombinationsHashMap.keySet()){
			infiniteLoopCombinationsHashMap.get(key).remove(a);
			infiniteLoopCombinationsHashMap.get(key).remove(b);
		}
	}
	private static boolean checkCombinations(int valI, int valJ) {
		if(valI == valJ) {//if both are equal wont loop
			return false;
		}
		int total = valI + valJ;
		if (total%2 == 1){//if total is odd they bigger can never be equal
			return true;
		}
		boolean isLooping = false;
		boolean isStopping = false;
		int smaller = Math.min(valI,valJ);
		int remainder;
		int div = total;
		//if the numbers are not looping they have to be equal at some point
		//and the smaller number will have to double up to get there, hence
		//it should be a multiple of 2 i.e. %2 = unless its the starting number or 1
		while(!isLooping && !isStopping) {
			remainder = div%2;
			div = div/2;
			isStopping = div==smaller || div==1;
			isLooping = remainder==1;
		}
		return isLooping;
	}
}
