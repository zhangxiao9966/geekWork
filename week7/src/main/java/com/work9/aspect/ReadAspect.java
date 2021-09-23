package com.work9.aspect;

import com.work9.datasource.ChooseDataSource;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Aspect
@Component
public class ReadAspect {

    @Autowired
    ChooseDataSource dataSource;

    @Pointcut("@annotation(com.work9.annotation.ReadAction)")
    public void read() {
    }

    /**
     * 获取参数，改变DataSource为slave节点
     */
    @Around("read()")
    public List<Map<String, Object>> setDatabaseSource(ProceedingJoinPoint point) throws Throwable {
        Object[] argv = point.getArgs();
        //谁到这都路由一下，按规则选择数据源
        dataSource.readRoutingDataSource();
        return (List<Map<String, Object>>) point.proceed(argv);
    }
}
