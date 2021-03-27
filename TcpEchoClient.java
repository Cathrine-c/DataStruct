import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class TcpEchoClient {
    //1.启动客户端
    //2.进入主循环
    //a.读取用户内容
    //b.构造一个请求发送给服务器
    //c.读取服务器的响应数据
    //d.把相应数据显示到界面上
    private Socket socket = null;

    public TcpEchoClient(String serverIp,int serverPort) throws IOException {
        socket = new Socket(serverIp,serverPort);
    }

    public void start(){
        System.out.println("客户端启动");
        Scanner scanner = new Scanner(System.in);
        try(BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()))) {
        while (true){
            //1.读取用户输入内容
            System.out.println("->");
            String request = scanner.nextLine();
            if("exit".equals(request)){
                break;
            }
            //2.构造请求并发送，此处+\n为了和服务器中的readLine相对应
            bufferedWriter.write(request+"\n");
            //3.读取相应数据
            String response = bufferedReader.readLine();
            //4.把响应数据显示到界面上
            System.out.println(response);
        }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        String abcc = null;

        TcpEchoClient client = new TcpEchoClient(abcc,9090);
    }
}
