import java.util.concurrent.Semaphore;

public class SynchronizatorImpl_SEMAPHORE implements Synchronizator{
    private Semaphore semaphoreA = new Semaphore(3);
    private Semaphore semaphoreB = new Semaphore(0);
    private Semaphore semaphoreC = new Semaphore(0);
    @Override
    public void startA() throws InterruptedException {
        semaphoreA.acquire();
    }

    @Override
    public void koniecA() throws InterruptedException {
        if(semaphoreA.availablePermits() == 0) semaphoreB.release(2);
    }

    @Override
    public void startB() throws InterruptedException {
        semaphoreB.acquire();

    }

    @Override
    public void koniecB() throws InterruptedException {
        if(semaphoreB.availablePermits() == 0) semaphoreC.release(1);

    }

    @Override
    public void startC() throws InterruptedException {
        semaphoreC.acquire();
    }

    @Override
    public void koniecC() throws InterruptedException {
        semaphoreA.release(3);
    }
}
