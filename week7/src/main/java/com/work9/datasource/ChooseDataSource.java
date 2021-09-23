package com.work9.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import javax.sql.DataSource;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Spring动态配置多数据源，即在大型应用中对数据进行切分，并且采用多个数据库实例进行管理，这样可以有效提高系统的水平伸缩性。
 * 而这样的方案就会不同于常见的单一数据实例的方案，这就要程序在运行时根据当时的请求及系统状态来动态的决定将数据存储在哪个数据库实例中，以及从哪个数据库提取数据。
 * <p>
 * 我们在方案中实现一个虚拟的数据源，并且用它来封装数据源选择逻辑，这样就可以有效地将数据源选择逻辑从Client中分离出来。
 * Client提供选择所需的上下文（因为这是Client所知道的），由虚拟的DataSource根据Client提供的上下文来实现数据源的选择。
 * 具体的实现就是，虚拟的DataSource仅需继承AbstractRoutingDataSource实现determineCurrentLookupKey（）在其中封装数据源的选择逻辑。
 *
 * @author zhangxiao -[Create on 2021/9/23]
 */
public class ChooseDataSource extends AbstractRoutingDataSource {

    /**
     * ThreadLocal 用于提供线程局部变量，在多线程环境可以保证各个线程里的变量独立于其它线程里的变量。
     * 也就是说 ThreadLocal 可以为每个线程创建一个【单独的变量副本】，相当于线程的 private static 类型变量。
     */
    private static final ThreadLocal<String> HOLDER = new ThreadLocal<>();

    /**
     * 读操作的次数
     */
    private AtomicInteger readTimes = new AtomicInteger();

    /**
     * 决定使用哪个数据源之前需要把多个数据源的信息以及默认数据源信息配置好
     *
     * @param defaultTargetDataSource 默认数据源
     * @param targetDataSources       目标数据源
     */
    public ChooseDataSource(DataSource defaultTargetDataSource, Map<Object, Object> targetDataSources) {
        super.setDefaultTargetDataSource(defaultTargetDataSource);
        super.setTargetDataSources(targetDataSources);
        super.afterPropertiesSet();
    }

    public ChooseDataSource() {
    }

    @Override
    protected Object determineCurrentLookupKey() {
        return getDataSource();
    }

    public void readRoutingDataSource() {
        //2下从1  1下从2
        if (readTimes.get() % 3 != 0) {
            HOLDER.set(DataSourceNames.SLAVE_ONE);
        } else {
            HOLDER.set(DataSourceNames.SLAVE_TWO);
        }
        if (HOLDER.get() != null) {
            System.out.println("read dataSource change! now: " + HOLDER.get());
        }else{
            System.out.println("read dataSource used default");
        }
        readTimes.incrementAndGet();
    }

    public static void setDataSource(String dataSource) {
        HOLDER.set(dataSource);
    }

    public static String getDataSource() {
        return HOLDER.get();
    }

    public static void clearDataSource() {
        HOLDER.remove();
    }
}
