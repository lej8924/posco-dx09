package prob06;

import java.util.Random;
import java.util.Scanner;

public class Sol06 {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int idx = 1;
		while(true) {

			/* 게임 작성 */

			// 정답 램덤하게 만들기
			Random random = new Random();
			int correctNumber = random.nextInt(100) + 1;
//			System.out.println(correctNumber);
			System.out.println("수를 결정하였습니다. 맞추어 보세요.");
			System.out.println("1-100");

			int inin = scanner.nextInt();
			while(true){
				inin = scanner.nextInt();
				if(inin > correctNumber){
					System.out.println("더 낮게");
				}else if(inin < correctNumber){
					System.out.println("더 높게");
				}else{
					break;
				}
			}
			System.out.println("맞췄습니다.");

			//새 게임 여부 확인하기
			System.out.print("다시 하겠습니까(y/n)>>");
			String answer = scanner.next();
			if("y".equals(answer) == false) {
				break;
			}
		}

		scanner.close();
	}
}