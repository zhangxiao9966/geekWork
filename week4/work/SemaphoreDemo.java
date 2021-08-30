package src.main.work;

import java.util.concurrent.Semaphore;

public class SemaphoreDemo {
    private int number;
    private Semaphore semaphore;

    public SemaphoreDemo(Semaphore semaphore) {
        this.semaphore = semaphore;
    }

    private void add(int addNum) {
        number += addNum;
        semaphore.release();
    }

    private int getNumber() throws InterruptedException {
        semaphore.acquire();
        return number;
    }

    public static void main(String[] args) throws InterruptedException {
        Semaphore semaphore = new Semaphore(1);
        //上来就把信号量消耗光
        semaphore.acquire();
        SemaphoreDemo demo = new SemaphoreDemo(semaphore);
        Thread thread = new Thread(() -> {
            demo.add(123);
        });
        thread.start();

        System.out.println("结果是：" + demo.getNumber());
    }
}
