package TestThread;


import sun.awt.windows.ThemeReader;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class test01 {
    private int num = 1;
    private Lock lock = new ReentrantLock();
    private Condition c1 = lock.newCondition();
    private Condition c2 = lock.newCondition();
    private Condition c3 = lock.newCondition();

    public void run5(int x ){
        try {
            lock.lock();
            while (num !=1){
                c1.await();
            }
            for (int i = 1 ; i <= 5; i++ ){
                System.out.println(Thread.currentThread().getName()+ i +"轮数"+x);
                num = 2;
                c2.signal();
            }
        }catch (Exception e){

        }finally {
            lock.unlock();
        }
    }
    public void run10(int x ){
        try {
            lock.lock();
            while (num !=2){
                c2.await();
            }
            for (int i = 1 ; i <= 10; i++ ){
                System.out.println(Thread.currentThread().getName()+ i +"轮数"+x);
                num = 3;
                c3.signal();
            }
        }catch (Exception e){

        }finally {
            lock.unlock();
        }
    }
    public void run15(int x ){
        try {
            lock.lock();
            while (num !=3){
                c3.await();
            }
            for (int i = 1 ; i <= 15; i++ ){
                System.out.println(Thread.currentThread().getName()+ i +"轮数"+x);
                num = 1;
                c1.signal();
            }
        }catch (Exception e){

        }finally {
            lock.unlock();
        }
    }
}


public class ThreadDemo04 {
    public static void main(String[] args) {
        test01 test01 = new test01();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1 ; i <= 10 ; i++){
                    test01.run5(i);

                }
            }
        },"aa").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1 ; i <= 10 ; i++){
                    test01.run10(i);
                }
            }
        },"bb").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1 ; i <= 10 ; i++){
                    test01.run15(i);
                }
            }
        },"cc").start();
    }
}
