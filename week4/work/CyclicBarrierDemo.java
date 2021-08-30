package src.main.work;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierDemo {
    private int number;
    private CyclicBarrier cyclicBarrier;

    public CyclicBarrierDemo(CyclicBarrier cyclicBarrier) {
        this.cyclicBarrier = cyclicBarrier;
    }

    //    1.用barrierAction的方式
//    private int getNumber() {
//        System.out.println(number);
//        return number;
//    }
//    public void setCyclicBarrier(CyclicBarrier cyclicBarrier) {
//        this.cyclicBarrier = cyclicBarrier;
//    }

    private void add(int addNum) throws BrokenBarrierException, InterruptedException {
        number += addNum;
        cyclicBarrier.await();
    }


    private int getNumber() {
        return number;
    }

    public static void main(String[] args) throws InterruptedException, BrokenBarrierException {


//        1.用barrierAction的方式
//        CyclicBarrierDemo demo = new CyclicBarrierDemo();
//        CyclicBarrier cyclicBarrier = new CyclicBarrier(1, demo::getNumber);

        //2.普通的方式
        CyclicBarrier cyclicBarrier = new CyclicBarrier(2);
        CyclicBarrierDemo demo = new CyclicBarrierDemo(cyclicBarrier);
        Thread thread = new Thread(() -> {
            try {
                demo.add(123);
            } catch (BrokenBarrierException | InterruptedException e) {
                e.printStackTrace();
            }
        });

        thread.start();

        cyclicBarrier.await();

        System.out.println("结果是：" + demo.getNumber());
    }

}
