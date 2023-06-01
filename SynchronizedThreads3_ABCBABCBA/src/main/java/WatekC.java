import java.util.concurrent.Semaphore;

public class WatekC extends Thread {
    private Semaphore semaphoreB;
    private SequenceHolder sequenceHolder;

    public WatekC(Semaphore semaphoreB, SequenceHolder sequenceHolder) {
        this.semaphoreB = semaphoreB;
        this.sequenceHolder = sequenceHolder;
    }

    @Override
    public synchronized void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                synchronized (sequenceHolder) {
                    if (sequenceHolder.isNextA() || semaphoreB.availablePermits() != 0) sequenceHolder.wait();
                    else {
                        System.out.println("C");
                        sleep(10);
                        sequenceHolder.setNextA(true);
                        semaphoreB.release(1);
                    }
                }
            }
        } catch (InterruptedException e) {
            System.out.println("Koniec watku C");
        }
    }
}
