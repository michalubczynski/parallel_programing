import java.util.concurrent.Semaphore;

public class SynchronizatorImpl implements Synchronizator {

    private int count_A = 0;
    private int count_B = 0;

    private boolean watekA = true;
    private boolean watekB = false;
    private boolean watekC = false;


    @Override
    public synchronized void startA() throws InterruptedException {
        while (!watekA) {
            this.wait();
        }
    }

    @Override
    public synchronized void koniecA() throws InterruptedException {
        count_A += 1;
        if (count_A == 3) {
            watekA = false;
            watekB = true;
        }
        notifyAll();
    }

    @Override
    public synchronized void startB() throws InterruptedException {
        while (!watekB) {
            this.wait();
        }
    }

    @Override
    public synchronized void koniecB() throws InterruptedException {
        count_B += 1;
        if (count_B == 2) {
            watekB = false;
            watekC = true;
        }
        notifyAll();
    }

    @Override
    public synchronized void startC() throws InterruptedException {
        while (!watekC) {
            this.wait();
        }
    }

    @Override
    public synchronized void koniecC() throws InterruptedException {
        count_A=0;
        count_B=0;
        watekC = false;
        watekA = true;
        notifyAll();
    }
}
