import java.util.concurrent.Semaphore;

public class WatekA extends Thread {
    private Semaphore semaphoreB;
    private SequenceHolder sequenceHolder;

    public WatekA(Semaphore semaphoreB, SequenceHolder sequenceHolder) {
        this.semaphoreB = semaphoreB;
        this.sequenceHolder = sequenceHolder;
    }

    @Override
    public synchronized void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                synchronized (sequenceHolder) {
                    if (!sequenceHolder.isNextA() || semaphoreB.availablePermits() != 0) sequenceHolder.wait();
                    else {
                        System.out.println("A");
                        sleep(10);
                        sequenceHolder.setNextA(false);
                        semaphoreB.release(1);
                    }
                }
            }
        } catch (InterruptedException e) {
            System.out.println("Koniec watku A");
        }
    }
}
