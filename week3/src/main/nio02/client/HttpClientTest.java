package src.main.nio02.client;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.Random;

/**
 * @author zhangxiao -[Create on 2021/8/24]
 */
public class HttpClientTest {

    public static void main(String[] args) throws IOException {
        CloseableHttpClient httpclient = HttpClients.createDefault();

        try {


            //重写一个response的handler
            ResponseHandler<String> responseHandler = response -> {
                int status = response.getStatusLine().getStatusCode();
                //状态在200到206代表成功处理了请求
                if (status >= 200 && status < 206) {
                    HttpEntity entity = response.getEntity();
                    return entity != null ? EntityUtils.toString(entity, "UTF-8") : null;
                } else {
                    throw new ClientProtocolException("Unexpected response status: " + status);
                }
            };
            Random random = new Random();
            HttpGet httpget;
            for (int i = 0; i < 50; i++) {
                //循环50次 发送不同的data数据给网关绑定的端口出去
                httpget = new HttpGet("http://127.0.0.1:8888/data=" + random.nextInt(50));
                System.out.println("Executing request " + httpget.getRequestLine());
                String responseBody = httpclient.execute(httpget, responseHandler);
                System.out.println(responseBody);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            httpclient.close();
        }
    }

}
