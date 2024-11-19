package prob05;

public class Sol05 {
	public static void main(String[] args) {

		/* 코드 작성 */
		for(int i=1;i<=100;i++){
			String tmp = String.valueOf(i);
			if(tmp.contains("3") || tmp.contains("6") || tmp.contains("9")){
				System.out.println(String.valueOf(i) + " 짝" );
			}
		}

	}
}
