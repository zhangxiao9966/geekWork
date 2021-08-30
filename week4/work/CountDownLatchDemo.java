package src.main.work;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchDemo {
    private int number;
    private CountDownLatch countDownLatch;

    public CountDownLatchDemo(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    private void add(int addNum) {
        number += addNum;
        countDownLatch.countDown();
    }

    private int getNumber() {
        return number;
    }

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(1);

        CountDownLatchDemo demo = new CountDownLatchDemo(countDownLatch);
        Thread thread = new Thread(() -> {
            demo.add(123);
        });
        thread.start();

        countDownLatch.await();
        System.out.println("结果是：" + demo.getNumber());
    }
}
