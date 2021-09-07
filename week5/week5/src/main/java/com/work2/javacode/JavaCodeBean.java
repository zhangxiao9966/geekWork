package com.work2.javacode;

import org.springframework.stereotype.Component;

@Component
public class JavaCodeBean {

    private int id;

    public JavaCodeBean() {
        id = 123;
        System.out.println("调用了JavaCodeBean的构造方法");
    }

    public void getId() {
        System.out.println("Id是" + id);
    }
}
