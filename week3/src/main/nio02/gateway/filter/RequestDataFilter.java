package src.main.nio02.gateway.filter;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;

/**
 * @author zhangxiao -[Create on 2021/8/24]
 */
public class RequestDataFilter implements HttpRequestFilter {

    @Override
    public void filter(FullHttpRequest fullRequest, ChannelHandlerContext ctx) {
        int data = Integer.parseInt(fullRequest.uri().split("=")[1]);
        //超过30被拦截
        if (data > 30) {
            fullRequest.headers().set("result", "data is too big");
        }
    }
}
