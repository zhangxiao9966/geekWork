package com.example.demo.proxy;

import java.lang.reflect.Proxy;

public class RpcClientJdk implements RpcClient {

    @Override
    public <T> T create(Class<T> serviceClass, String url) {
        ClassLoader loader = RpcClientJdk.class.getClassLoader();
        Class[] classes = new Class[]{serviceClass};
        return (T) Proxy.newProxyInstance(loader, classes, new RpcInvocationHandler(serviceClass, url));
    }
}
