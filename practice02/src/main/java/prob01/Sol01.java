package prob01;

import java.util.Scanner;

public class Sol01 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		final int[] MONEYS = {50000, 10000, 5000, 1000, 500, 100, 50, 10, 5, 1};

		System.out.print("금액: ");
		int price = scanner.nextInt();
		int tmp;
		for(int money: MONEYS){
			if(price >= money){
				tmp = price / money;
				price -= tmp*money;
				System.out.println(String.valueOf(money) + " : " + String.valueOf(tmp) + "개");
			}
		}

		scanner.close();
	}
}