package com.example.demo.proxy;

public interface RpcClient {

    /**
     * 创建代理
     */
    <T> T create(final Class<T> serviceClass, final String url);
}
