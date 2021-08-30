package src.main.work;

import java.util.concurrent.*;

/**
 * @author zhangxiao -[Create on 2021/8/30]
 */
public class FutureCallableDemo implements Callable {

    private volatile int number = 0;

    private Integer add(int addNum) {
        number += addNum;
        return number;
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        FutureCallableDemo demo = new FutureCallableDemo();

        ExecutorService executor = Executors.newSingleThreadExecutor();

        Future<Integer> aa = (Future<Integer>) executor.submit(demo);

        executor.shutdownNow();

        System.out.println("结果是：" + aa.get());

    }

    @Override
    public Object call() {
        return add(123);
    }
}
