package prob03;

import static prob04.Sol04.printCharArray;

public class Sol03 {
	public static void main(String args[]) {
		char c[] = { 'T', 'h', 'i', 's', ' ', 'i', 's', ' ', 'a', ' ', 'p', 'e', 'n', 'c', 'i', 'l', '.' };

		// 원래 배열 원소 출력
		printCharArray(c);

		// 공백 문자 바꾸기
		replaceSpace(c);

		// 수정된 배열 원소 출력
		printCharArray(c);
	}
	private static void printCharArray(char[] array){
		/* 코드를 완성합니다 */

		System.out.println(String.valueOf(array));
	}
	private static void replaceSpace(char[] array){
		/* 코드를 완성합니다 */
		String res = String.valueOf(array).replace(" ",",");
		System.out.println(res);
	}
}