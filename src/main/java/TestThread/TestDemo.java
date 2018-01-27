package TestThread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 资源类 一个加 一个减
 */

class Number1 {
    private int num = 0;
    private Lock lock = new ReentrantLock();

    private Condition condition = lock.newCondition();

    public void add() {
        lock.lock();
        try {
            while (num != 0) {
                condition.await();
            }
            System.out.println(Thread.currentThread().getName() + "剩余" + ++num);
            condition.signalAll();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void down() {
        try {
            lock.lock();
            while (num != 1) {
                condition.await();
            }
            System.out.println(Thread.currentThread().getName() + "剩余" + --num);
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}


public class TestDemo {
    public static void main(String[] args) {
        Number1 number1 = new Number1();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0 ; i < 10 ; i ++){

                    number1.add();
                }
            }
        }, "aa").start();

        System.out.println("123123123");
        System.out.println("123123123");
        System.out.println("123123123");
        System.out.println("123123123");

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0 ; i < 10 ; i++){

                    number1.down();
                }
            }
        }, "bb").start();
    }
}
