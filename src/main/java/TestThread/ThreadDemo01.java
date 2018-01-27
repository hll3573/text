package TestThread;


import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *  创建资源类
 */
class Num{
   private  int num = 30;
   private Lock lock = new ReentrantLock();

   public  void run(){
       lock.lock();
       try {
           while (num > 0){

               System.out.println(Thread.currentThread().getName()+"卖出了第"+num +"剩余"+ --num +"张");

           }
       }catch (Exception e){
           e.printStackTrace();
       }finally {
           lock.unlock();
       }
   }

}

public class ThreadDemo01 {

    public static void main(String[] args) {
        Num num = new Num();
        new Thread(() -> {num.run();},"aa").start();
        new Thread(() -> {num.run();},"bb").start();
        new Thread(() -> {num.run();},"cc").start();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
    }
}
