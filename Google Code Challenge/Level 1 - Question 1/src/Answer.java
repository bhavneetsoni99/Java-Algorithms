
import java.util.*;


public class Answer {
    answer([1, 2, 3], 6);
    public static int[] answer(int[] data, int n) {

        // Your code goes here.
        if(n<=0){
            int [] ans = new int[0];
            return ans;
        } else if(n>=data.length){
            return data;
        }else {

            ArrayList<Integer> dataList= new ArrayList<Integer>();
            for(int b =0;b<data.length;b++){
	         /* We are adding each array's element to the ArrayList*/
                dataList.add(data[b]);
            }
            int length = dataList.size();
            List<Integer> elementsToBeRemoved = new ArrayList<Integer>(0);
            for(int i = 0; i<length;i++){
                int element = dataList.get(i);
                int repeats = Collections.frequency(dataList, element);
                if (repeats>n){
                    elementsToBeRemoved.add(i);
                }
            }
            int elementsToBeRemovedSize = elementsToBeRemoved.size();
            if (elementsToBeRemovedSize>0){
                for(int k = elementsToBeRemovedSize-1; k>=0; k--){
                    dataList.remove(elementsToBeRemoved.get(k));
                }
            }
            int [] dataList2 = new int[dataList.size()];
            for(int j =0;j<dataList.size();j++){
                dataList2[j] = dataList.get(j);
            }
            return dataList2;
        }
    }
}
    answer([1, 2, 3], 0);