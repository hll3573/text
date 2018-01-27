package TestThread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 资源类
 */
class Num2{
    private  int num = 1;
    private Lock lock = new ReentrantLock();
    private Condition c1 = lock.newCondition();
    private Condition c2 = lock.newCondition();
    private Condition c3 = lock.newCondition();

    public void num5(int demo){
        lock.lock();
        try {
            while (num != 1){
                c1.await();
            }
            for (int i = 1 ; i <= 5 ;i++) {
                System.out.println(Thread.currentThread().getName()+i+"次" + " --五号" + demo);
            }
            num = 2;
            c2.signal();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
    public void num10(int demo){
        lock.lock();
        try {
            while (num != 2){
                c2.await();
            }
            for (int i = 1 ; i <= 10 ;i++){
                System.out.println(Thread.currentThread().getName()+i+"次"+"10号"+ demo);

            }
            num = 3;
            c3.signal();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
    public void num15(int demo){
        lock.lock();
        try {
            while (num != 3){
                c3.await();
            }
            for (int i = 1 ; i <= 15 ;i++) {
                System.out.println(Thread.currentThread().getName()+i+"次" + "十五号" + demo);
            }
            num = 1;
            c1.signal();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }


}


public class ThreadDemo03 {
    public static void main(String[] args) {
        Num2 n = new Num2();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1 ; i <= 10 ; i ++){
                    n.num5(i);
                }
            }
        },"aa").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1 ; i <= 10 ; i ++){
                    n.num10(i);
                }
            }
        },"bb").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1 ; i <= 10 ; i ++){
                    n.num15(i);
                }
            }
        },"cc").start();
    }
}
