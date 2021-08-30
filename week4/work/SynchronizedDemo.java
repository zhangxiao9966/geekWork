package src.main.work;

public class SynchronizedDemo {
    private int number;

    private synchronized void add(int addNum) throws InterruptedException {
        number += addNum;
        this.notifyAll();
    }

    private synchronized int getNumber() throws InterruptedException {
        if (number == 0) {
            this.wait();
        }
        return number;
    }

    public static void main(String[] args) throws InterruptedException {

        SynchronizedDemo demo = new SynchronizedDemo();

        Thread thread = new Thread(() -> {
            try {
                demo.add(123);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        thread.start();

        System.out.println("结果是：" + demo.getNumber());


    }
}
