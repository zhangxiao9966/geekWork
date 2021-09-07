package com.selfproject;

import com.work2.xml.XmlBean;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class XmlTest {

    @Test
    public void test() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("BeanConfig.xml");
        XmlBean bean = (XmlBean) context.getBean("XmlBean");
        bean.info();
    }
}
