import java.util.concurrent.Semaphore;

public class WatekB extends Thread {
    private Semaphore semaphoreB;
    private SequenceHolder sequenceHolder;

    public WatekB(Semaphore semaphoreB, SequenceHolder sequenceHolder) {
        this.semaphoreB = semaphoreB;
        this.sequenceHolder = sequenceHolder;
    }

    @Override
    public synchronized void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                semaphoreB.acquire(1);
                System.out.println("B");
                sleep(10);
                synchronized (sequenceHolder){
                    sequenceHolder.notifyAll();
                }
            }
        } catch (InterruptedException e) {
            System.out.println("Koniec watku B");
        }
    }
}
