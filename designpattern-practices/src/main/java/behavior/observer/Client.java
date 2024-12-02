package behavior.observer;

import java.util.Scanner;

public class Client {

    public static void main(String[] args) {
        Subject<Integer> integerSubject = new Subject<>();
        //익명 함수 스타일
        integerSubject.registerObserver(new Observer<Integer>() {
            @Override
            public void update(Integer val) {
                System.out.println("Oberser01: " + val);
            }
        });

        // 람다 스타일
        integerSubject.registerObserver((val) -> {
            System.out.println("Observer02: " + val);
        });

        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println(">>");
            Integer val = sc.nextInt();

        }
    }
}
