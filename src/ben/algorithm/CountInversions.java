package algorithm;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * count inversions of an input array
 * @author Ben
 *
 */
public class CountInversions {

	public static void main(String[]args){
		CountInversions ci = new CountInversions();
		List<Integer>data = ci.readData("E:/IntegerArray.txt");
		Integer[]arr = new Integer[data.size()];
		data.toArray(arr);
		System.out.println("inversions:"+ci.execute(arr, 0, arr.length-1));
	}
	
	/**
	 * count inversions of arr from beginIndex, to endIndex inclusive
	 * @param arr
	 * @param beginIndex
	 * @param endIndex
	 * @return
	 */
	public long execute(Integer[]arr, int beginIndex, int endIndex){
		if(arr == null || beginIndex < 0 
				|| endIndex >= arr.length 
				|| beginIndex >= endIndex){
			return 0;
		}
		//calculate the middleIndex
		int middleIndex = (beginIndex+endIndex)/2;
		//do the recursion twice
		long leftCount = execute(arr, beginIndex, middleIndex);
		long rightCount = execute(arr, middleIndex+1, endIndex);
		//generate copies of left and right part
		Integer[]leftArray = Arrays.copyOfRange(arr, beginIndex, middleIndex+1);
		Integer[]rightArray = Arrays.copyOfRange(arr, middleIndex+1, endIndex+1);
		//calculate the split inversion
		long splitCount = 0;
		for(int k = beginIndex, i = 0, j = 0;k <= endIndex;k++){
			if(j >= rightArray.length 
					|| (i < leftArray.length 
							&& leftArray[i] <= rightArray[j])){
				arr[k] = leftArray[i];
				i++;
			}else{
				arr[k] = rightArray[j];
				splitCount += leftArray.length - i;
				j++;
			}
		}
		return leftCount + rightCount + splitCount;
	}
	
	/**
	 * read input array from file
	 * @param fileName
	 * @return
	 */
	public List<Integer> readData(String fileName){
		List<Integer> data = new ArrayList<Integer>();
		File file = new File(fileName);
		BufferedReader reader = null;
		try{
			reader = new BufferedReader(new FileReader(file));
			String line;
			//read data line by line, until null
			while((line = reader.readLine()) != null){
				data.add(Integer.parseInt(line));
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(reader != null){
				try{
					reader.close();
				}catch(IOException e1){	
				}
			}
		}
		return data;
	}
}
