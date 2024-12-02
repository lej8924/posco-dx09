package chat.gui;
import chat.ChatClientThread;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.*;
import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

import static chat.ChatServer.PORT;
import static chat.ChatServer.log;

public class ChatWindow {
	private static final String SERVER_IP = "192.168.0.37";

	private Frame frame;

	private String nickName;
	private Panel pannel;
	private Button buttonSend;
	private TextField textField;
	private TextArea textArea;

	private PrintWriter pw;


	public ChatWindow(String name,String nickName) {
		this.nickName = nickName;
		frame = new Frame(name);
		pannel = new Panel();
		buttonSend = new Button("Send");
		textField = new TextField();
		textArea = new TextArea(30, 80);
	}

	public void show() {
		// Button
		buttonSend.setBackground(Color.GRAY);
		buttonSend.setForeground(Color.WHITE);

		//
		buttonSend.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
//				System.out.println("clicked!");
				sendMessage();
			}
		});
		// Textfield
		textField.setColumns(80);
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				char keyChar = e.getKeyChar();
				if(keyChar == KeyEvent.VK_ENTER) {
					sendMessage();
				}
			}
		});
		// Pannel
		pannel.setBackground(Color.LIGHT_GRAY);
		pannel.add(textField);
		pannel.add(buttonSend);
		frame.add(BorderLayout.SOUTH, pannel);
		// TextArea
		textArea.setEditable(false);
		frame.add(BorderLayout.CENTER, textArea);
		// Frame
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				finish();
			}
		});
		frame.setVisible(true);
		frame.pack();

		try{
			// 1. 서버 연결 작업
			Socket socket = new Socket();
			socket.connect(new InetSocketAddress(SERVER_IP,PORT));
			// 2. IO Stream Set
			Scanner sc = new Scanner(System.in);
			this.pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "utf-8"));
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream(),"utf-8"));

			// 3. JOIN Protocol
			pw.println("join:"+nickName);
			pw.flush();

			// 4. ChatClientTread 생성
			new ChatClientThread(br).start();


		}catch(IOException e){
			log("error" + e);
		}
	}

	private void sendMessage() {
		String message = textField.getText();
//		System.out.println("message:" + message);
		pw.println("message:"+message);
		pw.flush();

		textField.setText("");
		textField.requestFocus();

	}

	private void updateTextArea(String message){
		textArea.append(message);
		textArea.append("\n");
	}

	private void finish(){
		//quit 프로토콜 구현
		pw.println("quit");
		pw.flush();
		// exit java application
		System.exit(0);
	}

	private class ChatClientThread extends Thread{

		private BufferedReader br;

		public ChatClientThread(BufferedReader br){
			this.br = br;
		}

		@Override
		public void run(){

			try{
				while(true){
					String data = br.readLine();
//					System.out.println("test"+data);
					if(data == null){
						log("closed by server");
						finish();
					}
					updateTextArea(data);
				}
			}catch(IOException e){
				log("[Error]" + e);
			}
		}

		public static void log(String message) {
			System.out.println("[Chat Client] " + message);
		}
	}

}
