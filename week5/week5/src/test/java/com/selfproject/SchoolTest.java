package com.selfproject;


import com.work3.school.School;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class SchoolTest {

    @Test
    public void test() {
        ApplicationContext context = new ClassPathXmlApplicationContext("BeanConfig.xml");
        School school = (School) context.getBean("school");
        System.out.println(school.getInfo());
    }
}
