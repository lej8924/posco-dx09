package prob02;

import java.util.ArrayList;
import java.util.List;

public class Sol02 {
	public static void main(String[] args) {

		/* 코드 작성 */
		List<Integer> nums = new ArrayList<>();
		for(int i =1;i<=18;i++){
			nums.add(i);
			if(i < 10){
				continue;
			}else{
				printArray(nums);
				System.out.println();
			}
		}
	}

	public static void printArray(List<Integer> list){
		for(int i=0;i<list.size();i++){
			System.out.print(list.get(i) + " ");
		}
	}
}