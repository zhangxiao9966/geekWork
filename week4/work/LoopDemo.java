package src.main.work;

public class LoopDemo {

    private volatile int number = 0;

    private void add(int addNum) {
        number += addNum;
    }

    public int getNumber() {
        return number;
    }

    public static void main(String[] args) throws InterruptedException {

        LoopDemo demo = new LoopDemo();

        Thread thread = new Thread(() -> {
            demo.add(123);
        });

        thread.start();

        while (demo.getNumber() == 0) {
        }

        System.out.println("结果是：" + demo.getNumber());

    }
}
