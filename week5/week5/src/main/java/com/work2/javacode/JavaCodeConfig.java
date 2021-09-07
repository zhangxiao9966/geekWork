package com.work2.javacode;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JavaCodeConfig {

    //@Bean标注在方法上(返回某个实例的方法)，等价于spring的xml配置文件中的<bean>，作用为：注册bean对象
    @Bean
    public JavaCodeBean javaCodeBean() {
        return new JavaCodeBean();
    }
}
