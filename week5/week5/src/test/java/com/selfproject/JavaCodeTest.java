package com.selfproject;


import com.work2.javacode.JavaCodeConfig;
import com.work2.javacode.JavaCodeBean;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class JavaCodeTest {

    @Test
    public void test() {
        AnnotationConfigApplicationContext configApplicationContext =
                new AnnotationConfigApplicationContext(JavaCodeConfig.class);
        JavaCodeBean demo = (JavaCodeBean) configApplicationContext.getBean("javaCodeBean");
        demo.getId();
    }
}
