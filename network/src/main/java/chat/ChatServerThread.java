package chat;

import echo.EchoServer;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;
import java.util.List;


public class ChatServerThread extends Thread{

    private String nickName;
    private Socket socket;
    private List<Writer> listWriters;

    public ChatServerThread(Socket socket,List<Writer> listWriters) throws IOException{
        this.socket = socket;
        this.listWriters = listWriters;
    }
    @Override
    public void run(){
        // 1. Remote Host Information
        BufferedReader br = null;
        PrintWriter pw = null;
        // 2. 스트림 얻기
        try{
            InetSocketAddress inetRemoteSocketAddress = (InetSocketAddress)socket.getRemoteSocketAddress();
            String remoteHostAddress = inetRemoteSocketAddress.getAddress().getHostAddress();
            int remotePort = inetRemoteSocketAddress.getPort();
            log("connected by client[" + remoteHostAddress + ":" + remotePort + "]");

            br = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
            pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(),StandardCharsets.UTF_8), true);

            while(true){
                String request = br.readLine();
                if(request == null){
                    log("클라이언트로부터 연결 끊김");
                    doQuit(pw);
                    break;
                }
                log("received:" + request);
                // 4. 프로토콜 분석
                String[] tokens = request.split(":");
                if("join".equals(tokens[0])) {
                    doJoin(tokens[1],pw);
                }else if("message".equals(tokens[0])){
                    doMessage(tokens[1],pw);
                }else if("quit".equals(tokens[0])){
                    doQuit(pw);
                }else{
                    log("에러: 알 수 없는 요청(" + tokens[0] + ")");
                }
            }
        }catch (SocketException e){
            log("[Error] " + e);
        }catch (IOException e){
            log("[Error] " + e);
        }finally {
            try{
                if(socket != null && !socket.isClosed()){
                    socket.close();
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    private void doJoin(String nickName, Writer writer){
        this.nickName = nickName;

        String data = nickName + "님이 참여하였습니다.";
        broadcast(data);

        addWriter(writer);

        //ack
        PrintWriter pw = (PrintWriter)writer;
        pw.println("join:ok");
        pw.flush();
    }

    private void doMessage(String message, Writer writer){
        PrintWriter pw = (PrintWriter) writer;
        String data = this.nickName + ":" + message;
        broadcast(data);
    }

    private void doQuit(Writer writer){
        removeWriter(writer);

        String data = this.nickName + "님이 퇴장하였습니다.";
        broadcast(data);
    }

    private void removeWriter(Writer writer){
        synchronized (listWriters){
            listWriters.remove(writer);
        }
    }

    private void addWriter(Writer writer){
        synchronized (listWriters){
            listWriters.add(writer);
        }
    }

    private void broadcast(String data){
        synchronized (listWriters){
            for(Writer writer: listWriters){
                PrintWriter pw = (PrintWriter) writer;
                pw.println(data);
                pw.flush();
            }
        }
    }

    public static void log(String message) {
        System.out.println("[Chat Server] " + message);
    }




}
