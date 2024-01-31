import java.util.concurrent.atomic.AtomicInteger;

public class MultiThread implements Runnable {
    static AtomicInteger counter = new AtomicInteger(1); // counter

    static int normalCounter = 0;

    private static final Object lock = new Object();
    public MultiThread() {
    }

    static void incrementCounter() {
        System.out.println(Thread.currentThread().getName() + ": Atomic counter: " + counter.getAndIncrement() + "\n");
        System.out.println(Thread.currentThread().getName() + ": normal Counter: " + normalCounter + "\n");
        normalCounter++;
    }

    static void incrementCounterSynchronized() {
        synchronized (lock) {
            System.out.println(Thread.currentThread().getName() + ": normal Counter: " + normalCounter + "\n");
            normalCounter++;
        }
    }

    @Override
    public void run() {
        /*while(counter.get() < 3){
            incrementCounter();
        }*/

        while(normalCounter < 5){
            incrementCounterSynchronized();
        }
    }

    public static void main(String[] args) {
        MultiThread t = new MultiThread();
        Thread thread1 = new Thread(t);
        Thread thread2 = new Thread(t);

        thread1.start();
        thread2.start();
    }
}