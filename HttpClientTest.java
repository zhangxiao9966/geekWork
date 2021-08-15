package src.main;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class HttpClientTest {

    public static void main(String[] args) throws IOException {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
            HttpGet httpget = new HttpGet("http://localhost:8801/");
            System.out.println("Executing request " + httpget.getRequestLine());

            //重写自己的接受方法
            ResponseHandler<String> responseHandler = response -> {
                int status = response.getStatusLine().getStatusCode();
                //状态在200到206代表成功处理了请求
                if (status >= 200 && status < 206) {
                    HttpEntity entity = response.getEntity();
                    return entity != null ? EntityUtils.toString(entity,"UTF-8") : null;
                } else {
                    throw new ClientProtocolException("Unexpected response status: " + status);
                }
            };
            String responseBody = httpclient.execute(httpget, responseHandler);
            System.out.println(responseBody);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            httpclient.close();
        }
    }

}
