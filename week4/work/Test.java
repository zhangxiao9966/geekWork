package src.main.work;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public class Test {
    public static void main(String[] args) throws Exception {
        //lockAwait();


        // syncWait();
        //
        final AtomicBoolean flag = new AtomicBoolean(false);

        //
        Thread t1 = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("执行结束:" + Thread.currentThread().getName());
            flag.set(true);
        });
        t1.start();

        //
        try {
            // t1.join();
        } catch (Exception e) {
            e.printStackTrace();
        }

        while (!flag.get()) {
            try {
                System.out.println("=======" + Thread.currentThread().getName());
                TimeUnit.MILLISECONDS.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //
        System.out.println("执行结束:" + Thread.currentThread().getName());

        // Lock
        // Condition
        //
        Future future = null;
        future.get();
        future.get(10, TimeUnit.SECONDS);
    }

}
