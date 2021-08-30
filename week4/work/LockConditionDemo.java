package src.main.work;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author zhangxiao -[Create on 2021/8/30]
 */
public class LockConditionDemo {
    private int number;

    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    private void add(int addNum) {
        lock.lock();
        try {
            number += addNum;
            condition.signal();
        } finally {
            lock.unlock();
        }
    }

    private int getNumber() {
        lock.lock();
        try {
            while (number == 0) {
                condition.await();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return number;
    }

    public static void main(String[] args) {
        LockConditionDemo demo = new LockConditionDemo();

        Thread thread = new Thread(() -> {
            demo.add(123);
        });

        thread.start();

        System.out.println("结果是：" + demo.getNumber());
    }

}
