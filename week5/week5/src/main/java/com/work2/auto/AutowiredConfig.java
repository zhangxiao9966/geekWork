package com.work2.auto;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


/**
 * 添加自动扫描注解，
 * 标签后直接加(basePackages = "路径"（比如com.work2）)，可指定扫描路径
 * 不加就是默认扫描当前包的配置类
 */
@ComponentScan

//Configuration标注在类上，相当于把该类作为spring的xml配置文件中的<beans>，作用为：配置spring容器(应用上下文)
@Configuration
public class AutowiredConfig {

}
