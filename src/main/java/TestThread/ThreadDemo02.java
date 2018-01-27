package TestThread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 资源类
 */
class Num1 {
    private  int num = 0 ;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void add(){
        lock.lock();
        try {
            while(num != 0){
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            ++num;
            System.out.println(Thread.currentThread().getName()+"生產了"+ num);
            condition.signalAll();
        }catch (Exception e){

        }finally {
            lock.unlock();
            System.out.println("test pull");
            System.out.println("test pull01");
            System.out.println("test pull01");
            System.out.println("test pull01");
            System.out.println("test pull01");
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println();
        }
    }

    public void down(){
        lock.lock();
        try {
            while(num != 1){
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            --num;
            System.out.println(Thread.currentThread().getName()+"消費后剩餘" + num );
            condition.signalAll();
        }catch (Exception e){

        }finally {
            lock.unlock();
        }
    }


}


public class ThreadDemo02 {
    public static void main(String[] args) {
        Num1 num1 = new Num1();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0 ; i < 10 ; i ++){
                    num1.add();
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        },"aa").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int j = 0 ; j < 10 ; j ++){
                    num1.down();
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        },"bb").start();
    }
}
