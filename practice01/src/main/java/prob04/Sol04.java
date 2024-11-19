package prob04;

import java.util.Scanner;

public class Sol04 {

	public static void main(String[] args) {

		/* 코드 작성 */

		Scanner scanner = new Scanner(System.in);
		System.out.print("문자열을 입력하세요: ");
		String str = scanner.nextLine();
		int idx = 1;

		while(true){
			if(idx > str.length()){
				break;
			}
			for(int i=0;i<idx;i++){
				System.out.print(str.charAt(i));
			}
			System.out.println();
			idx++;
		}

		scanner.close();
	}
}