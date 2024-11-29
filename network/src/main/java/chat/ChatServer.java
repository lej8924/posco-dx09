package chat;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.net.*;
import java.util.ArrayList;
import java.util.List;


public class ChatServer{
    public static final int PORT = 9999;

    public static void main(String[]args){
        ServerSocket serverSocket = null;

        try{
            List<Writer> listWriters = new ArrayList<Writer>();

           serverSocket = new ServerSocket();
           String hostAddress = InetAddress.getLocalHost().getHostAddress();
           serverSocket.bind(new InetSocketAddress(hostAddress, PORT));
           log("연결 기다림 " + hostAddress + ":" + PORT);

           while(true){
               // 클라이언트와 연결된 후 클라이언트와 채팅 데이터 통신은 ChatServerThread에서 실행
               Socket socket = serverSocket.accept();
               new ChatServerThread(socket, listWriters).start();
           }
        }catch(IOException e){
            log("error" + e);
        }finally {
            try{
                if(serverSocket != null && !serverSocket.isClosed()){
                    serverSocket.close();
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }

    }

    public static void log(String message) {
        System.out.println("[Chat Server] " + message);
    }

}
