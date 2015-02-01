package ben.algorithm;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CountInversions {
	
	public static void main(String[]args){
		CountInversions ci = new CountInversions();
		List<Integer> data = ci.readData("E:/IntegerArray.txt");
		Integer[] arr = new Integer[data.size()];
		data.toArray(arr);
		long count = ci.execute(arr);
		System.out.print(count);
	}
	
	public long execute(Integer[] arr){
		if(arr == null || arr.length == 0 || arr.length == 1){
			return 0;
		}else{
			//计算数组中间元素的索引
			int endIdx = arr.length - 1;
			int middleIdx = endIdx/2;
			//将原数组分成左右两个数组
			Integer[]leftArray = Arrays.copyOfRange(arr, 0, middleIdx+1);
			Integer[]rightArray = Arrays.copyOfRange(arr, middleIdx+1, endIdx+1);
			//递归调用计算左右子数组的inversion数
			long leftCount = execute(leftArray);
			long rightCount = execute(rightArray);
			//计算split inversion
			long splitCount = 0;
			int i = 0;
			int j = 0;
			for(int k = 0;k <= endIdx;k++){
				if(j >= rightArray.length || 
						(i < leftArray.length && leftArray[i] <= rightArray[j])){
					arr[k] = leftArray[i];
					i++;
				}else{
					arr[k] = rightArray[j];
					splitCount += leftArray.length - i;
					j++;
				}
			}
			return leftCount+rightCount+splitCount;
		}
	}
	
	/**
	 * 从文件中读取数据
	 * @param fileName 文件名
	 */
	public List<Integer> readData(String fileName){
		List<Integer> data = new ArrayList<Integer>();
		File file = new File(fileName);
		BufferedReader reader = null;
		try{
			reader = new BufferedReader(new FileReader(file));
			String tempString = null;
			//一次读入一行，直到读入null为文件结束
			while((tempString = reader.readLine()) != null){
				data.add(Integer.parseInt(tempString));
			}
			reader.close();
		}catch(IOException e){
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
