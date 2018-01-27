package TestThread;

/**
 * 线程安全的单例模式
 */
public class Singleton {
    private Singleton(){

    }
    private static volatile Singleton singleton = null;

    public static Singleton getInstance(){
        if(null == singleton){
            synchronized (Singleton.class){
                if (null==singleton){
                    singleton = new Singleton();

                }
            }

        }
        return singleton;
    }
}
