package src.main.work;

import java.util.concurrent.*;

/**
 * @author zhangxiao -[Create on 2021/8/30]
 */
public class FutureTaskDemo {

    private volatile int number = 0;

    public int add(int addNum) {
        number += addNum;
        return number;
    }

    public int getNumber() {
        return number;
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        FutureTaskDemo demo = new FutureTaskDemo();

        ExecutorService executor = Executors.newSingleThreadExecutor();

        FutureTask<Integer> futureTask = new FutureTask<>(() -> demo.add(123), 666);
        executor.submit(futureTask);


        if (futureTask.get() == 666) {
            System.out.println("结果是：" + demo.getNumber());
        }
        executor.shutdownNow();
    }

//    @Override
//    public void run() {
//        add(123);
//    }
}
