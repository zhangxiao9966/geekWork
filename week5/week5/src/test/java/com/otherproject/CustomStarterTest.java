package com.otherproject;

import com.custom.starter.School;
import com.custom.starter.SchoolAutoConfiguration;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SchoolAutoConfiguration.class)
public class CustomStarterTest {

    @Autowired
    School mySchool;

    @Test
    public void test() {
        System.out.println(mySchool.getInfo());
    }
}
