import java.util.Scanner;
public class uniModal {

    public static void main(String[] args ){

        int[] uniModalData = {1,8,5,4,3,2,1,0};
        int len = uniModalData.length;
        int maxElement;
        if(len == 1){
            maxElement = uniModalData[0];
        }else if(uniModalData[0] > uniModalData[1]){
            maxElement = uniModalData[0];
        }else if(uniModalData[len-1] > uniModalData[len-2]) {
            maxElement = uniModalData[len-1];
        }else {
            int index = len / 2;
            int endIndex = len-1;
            int startIndex = 0;
            maxElement = findMax(uniModalData, index,startIndex, endIndex);
        }
        System.out.println("Max element inthe array is -- "+ maxElement);
    }

    private static int max;
    public static int findMax(int [] data, int index,int startIndex, int endIndex){

        if(data[index] > data[index+1] && data[index] > data[index-1]){
            max = data[index];
            return max;
        }else{
            int indexNew;
            if(data[index]<=data[index+1]){
                indexNew = (index + endIndex)/2;
                startIndex = index;
            }else{
               indexNew = (startIndex+index)/2;
                endIndex = index;
            }
            findMax(data, indexNew,startIndex, endIndex);
        }
        return max;
    }
}