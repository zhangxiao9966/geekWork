package src.main.work;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author zhangxiao -[Create on 2021/8/30]
 */
public class FutureRunnableDemo implements Runnable {

    private volatile int number = 0;

    private Integer add(int addNum) {
        number += addNum;
        return number;
    }

    public int getNumber() {
        return number;
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        FutureRunnableDemo demo = new FutureRunnableDemo();

        ExecutorService executor = Executors.newSingleThreadExecutor();

        Future<Integer> aa = executor.submit(demo, 666);

        executor.shutdownNow();

        if (aa.get() == 666) {
            System.out.println("结果是：" + demo.getNumber());
        }

    }

    @Override
    public void run() {
        add(123);
    }
}
