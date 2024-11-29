package chat;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class ChatClientThread extends Thread{

    private BufferedReader br;

    public ChatClientThread(BufferedReader br){
        this.br = br;
    }

    @Override
    public void run(){
        // reader를 통해 읽은 데이터 콘솔에 출력하기(message 처리)
        try{
            while(true){
                System.out.print(">>");
                String data = br.readLine();
                if(data == null){
                    log("closed by server");
                }
                System.out.println("<<" + data);

            }
        }catch(IOException e){
            log("[Error]" + e);
        }
    }

    public static void log(String message) {
        System.out.println("[Chat Client] " + message);
    }

}
