package com.work2.xml;

import org.springframework.stereotype.Component;

@Component
public class XmlBean {
    public XmlBean() {
        System.out.println("Construct Example");
    }

    public void info() {
        System.out.println("Auto wiring example");
    }
}
