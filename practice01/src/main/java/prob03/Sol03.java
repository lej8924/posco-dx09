package prob03;

import java.util.Scanner;

public class Sol03 {

	public static void main(String[] args) {

		/* 코드 작성 */
		Scanner sc = new Scanner(System.in);
		System.out.print("수를 입력하세요: ");

		int num = sc.nextInt();

		while(true) {
			int sum = 0;
			if(num %2 == 0){
				for(int i=0;i<=num;i+=2){
					sum += i;
				}
			} else{
				for(int i=1;i<=num;i+=2){
					sum += i;
				}
			}
			System.out.println("결과값: " + String.valueOf(sum));

			System.out.print("수를 입력하세요: ");
			num = sc.nextInt();

		}
	}
}
