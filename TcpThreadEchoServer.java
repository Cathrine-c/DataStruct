
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpThreadEchoServer {
    private ServerSocket serverSocket = null;

    public TcpThreadEchoServer(int port) throws IOException {
        serverSocket = new ServerSocket(port);
    }

    public void start() throws IOException {
        System.out.println("服务器启动");
        while (true){
            Socket clientSocket = serverSocket.accept();
            Thread t = new Thread(){
                @Override
                public void run() {
                    try {
                        processConnection(clientSocket);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            };
            t.start();
        }
    }

    public void processConnection(Socket clientSocket) throws IOException {
        System.out.printf("[%s:%d] 客户端上线\n",clientSocket.getInetAddress().toString(),
                clientSocket.getPort());
        try(BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()))){
            while (true){
                String request = bufferedReader.readLine();
                String response = process(request);
                bufferedWriter.write(response+"\n");
                bufferedWriter.flush();
                System.out.printf("[%s:%d] req:%s resq:%s\n",clientSocket.getInetAddress().toString(),
                        clientSocket.getPort(),request,response);

            }
        }catch(IOException e){
                //e.printStackTrace();
            System.out.printf("[%s:%d] 客户端下线\n",clientSocket.getInetAddress().toString(),
                    clientSocket.getPort());

        }
        }

    private String process(String request) {
        return request;
    }

    public static void main(String[] args) throws IOException {
        TcpThreadEchoServer server = new TcpThreadEchoServer(9090);
        server.start();
    }
}

