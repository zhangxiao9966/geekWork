package com.work2.auto;

import org.springframework.stereotype.Component;

@Component
public class AutowiredBean {

    public AutowiredBean() {
        System.out.println("调用了AutowiredBean的构造方法");
    }

    public void getInfo() {
        System.out.println("这里是AutowiredBean");
    }
}
