package com.selfproject;

import com.work2.auto.AutowiredBean;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AutowiredBean.class)
public class AutowiredTest {

    @Autowired
    private AutowiredBean example;

    @Test
    public void test() {
        example.getInfo();
    }
}
