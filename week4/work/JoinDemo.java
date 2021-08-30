package src.main.work;

public class JoinDemo {
    private volatile int number = 0;

    private void add(int addNum) {
        number += addNum;
    }

    public int getNumber() {
        return number;
    }

    public static void main(String[] args) throws InterruptedException {

        JoinDemo demo = new JoinDemo();

        Thread thread = new Thread(() -> {
            demo.add(123);
        });

        thread.start();
        thread.join();

        System.out.println("结果是：" + demo.getNumber());

    }
}
