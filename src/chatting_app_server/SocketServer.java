package chatting_app_server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.ParseException;
import org.json.simple.parser.JSONParser; //JSON 객체 파싱
import org.json.simple.JSONObject; //JSONObject 객체 

public class SocketServer {
	public static void main(String[] args) throws ParseException {
        int portNumber = 5555;

        try {
            System.out.println("서버를 시작합니다...");
            ServerSocket serverSocket = new ServerSocket(portNumber); //포트번호를 매개변수로 전달하면서 서버 소켓 열기
            System.out.println("포트 " + portNumber + "에서 요청 대기중...");

            while(true) {
                Socket socket = serverSocket.accept(); //클라이언트가 접근했을 때 accept() 메소드를 통해 클라이언트 소켓 객체 참조
                InetAddress clientHost = socket.getLocalAddress();
                int clientPort = socket.getPort();
                System.out.println("클라이언트 연결됨. 호스트 : " + clientHost + ", 포트 : " + clientPort);

                
                ObjectInputStream instream = new ObjectInputStream(socket.getInputStream()); //소켓의 입력 스트림 객체 참조
                Object obj = instream.readObject(); // 입력 스트림으로부터 Object 객체 가져오기
                System.out.println("클라이언트로부터 받은 데이터 : " + obj); // 가져온 객체 출력
              
                //json 파싱
                JSONAdd jsonadd=new JSONAdd();
                jsonadd.jsonadd(); //생성자 불러오기
                
                JSONParser parser=new JSONParser(); //파싱 작업을 위한 객체생성
                JSONObject jobj=(JSONObject)parser.parse(jsonadd.json); //String json을 파싱받은 뒤, JSONObject 형태로 저장
                JSONObject inf=(JSONObject)jobj.get("inf");
                
                String str=(String)inf.get(obj); //파싱
                
                ObjectOutputStream outstream = new ObjectOutputStream(socket.getOutputStream()); //소켓의 출력 스트림 객체 참조
                outstream.writeObject(str); //출력 스트림에 응답 넣기
                outstream.flush(); // 출력
                socket.close(); //소켓 해제 
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
    }
	
}



