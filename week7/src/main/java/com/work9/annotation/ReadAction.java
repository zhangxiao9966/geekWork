package com.work9.annotation;

import com.work9.datasource.DataSourceNames;
import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义标签
 */
//指定标记是描述方法的
@Target(ElementType.METHOD)
//定义注解保留的范围，记录在类文件里，被加载到JVM时保留，可以反射的到
@Retention(RetentionPolicy.RUNTIME)
@Component
public @interface ReadAction {
    String name() default DataSourceNames.SLAVE_ONE;
}
