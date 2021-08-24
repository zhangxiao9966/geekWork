package src.main.nio02.gateway.inbound;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.epoll.EpollChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import lombok.Data;


import java.util.List;

@Data
public class HttpInboundServer {

    private int port;

    private List<String> proxyServers;

    public HttpInboundServer(int port, List<String> proxyServers) {
        this.port = port;
        this.proxyServers = proxyServers;
    }

    public void run() throws Exception {

        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup(16);

        try {
            ServerBootstrap b = new ServerBootstrap();
            //连接缓存队列大小设置为128
            b.option(ChannelOption.SO_BACKLOG, 128)
                    //禁用Nagle算法，禁止组装数据包，即发即传（与之对应的是）
                    .childOption(ChannelOption.TCP_NODELAY, true)
                    //激活心跳机制，如果超过指定时间连接还在，但是没通信过数据了，就探测一下，对面是不是活着，死了就断了
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    //允许共用监听端口
                    .childOption(ChannelOption.SO_REUSEADDR, true)
                    //设置接收缓冲区的大小
                    .childOption(ChannelOption.SO_RCVBUF, 32 * 1024)
                    //设置发送缓冲区的大小
                    .childOption(ChannelOption.SO_SNDBUF, 32 * 1024)
                    //允许多个进程或线程绑定到同一个端口
                    .childOption(EpollChannelOption.SO_REUSEPORT, true)
//                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    //指定ByteBuf的分配器为PooledByteBufAllocator（）
                    .childOption(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT);

            b.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)
                    .handler(new LoggingHandler(LogLevel.DEBUG))
                    .childHandler(new HttpInboundInitializer(this.proxyServers));

            Channel ch = b.bind(port).sync().channel();
            System.out.println("开启netty http服务器，监听地址和端口为 http://127.0.0.1:" + port + '/');
            ch.closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
