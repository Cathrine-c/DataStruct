import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;

public class HttpResponse {

    private String version = null;
    private int status;//状态码
    private String message; //状态码的描述信息
    private Map<String,String> headers = new HashMap<>();
    private StringBuilder body = new StringBuilder();
    private OutputStream outputStream = null;
    private Object OutputStream;


    public static HttpResponse build(OutputStream outputStream){
        HttpResponse response = new HttpResponse();
        response.outputStream = outputStream;
        return response;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setHeader(String key,String value){
        headers.put(key,value);
    }

    public void writeBody(String content){
        body.append(content);
    }

    //以上的设置属性的操作都在内存中倒腾
    //还需要一个专门的方法，把这些属性按照Http协议都写到scoket
    public void flush() throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter((java.io.OutputStream) OutputStream));
        bufferedWriter.write(version+""+status+""+message+"\n");
        headers.put("Content-Length",body.toString().getBytes().length+"");
        for(Map.Entry<String,String> entry:headers.entrySet()){
            bufferedWriter.write(entry.getKey()+""+entry.getValue()+"\n");
        }
        bufferedWriter.write("\n");
        bufferedWriter.write(body.toString());
        bufferedWriter.flush();
    }
}
