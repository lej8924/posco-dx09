package chat;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import static chat.ChatServer.PORT;

public class ChatClient {

    private static final String SERVER_IP = "192.168.0.37";

    public static void main(String[]args){
        // 1. 키보드 연결 + 2. socket 생성
        Scanner sc = null;
        Socket socket = null;

        // 3. 연결
        try{
            sc = new Scanner(System.in, "utf-8");
            socket = new Socket();

            socket.connect(new InetSocketAddress(SERVER_IP,PORT));

            // 4. reader/writer 생성
            PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "utf-8"));
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream(),"utf-8"));

            // 5. join 프로토콜
            System.out.print("닉네임>> ");
            String nickName = sc.nextLine();
            pw.println("join:"+nickName);
            pw.flush();

            // 6. ChatClientReceiveThread 시작
            new ChatClientThread(br).start();

            // 7. 키보드 입력 처리
            while(true){

                String line = sc.nextLine();
                if("quit".equals(line)){
                    // quit 프로토콜 처리
                    break;
                }else{
                    // 메세지 처리
                    pw.println("message:"+line);
                    pw.flush();
                }

            }
        }catch (IOException e){
            log("[Error]" + e);

        }finally {
            try {
                if (sc != null) {
                    sc.close();
                }
                if (socket != null && !socket.isClosed()) {
                    socket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }




    }
    public static void log(String message) {
        System.out.println("[Chat Client] " + message);
    }

}
