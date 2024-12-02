package chat.gui;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

import static chat.ChatServer.PORT;

public class ChatClientApp {

	public static void main(String[] args) {
		String chatName = null;
		String nickName = null;
		Scanner scanner = new Scanner(System.in);


		while (true) {
			System.out.println("대화명을 입력하세요.");
			System.out.print(">>> ");
			chatName = scanner.nextLine();

			System.out.print("닉네임>> ");
			nickName = scanner.nextLine();

			if (chatName.isEmpty() == false || nickName.isEmpty()==false) {
				break;
			}

			System.out.println("대화명은 한글자 이상 입력해야 합니다.");
		}

		scanner.close();

		new ChatWindow(chatName,nickName).show();


	}
	public static void log(String message) {
		System.out.println("[Chat Client] " + message);
	}

}
