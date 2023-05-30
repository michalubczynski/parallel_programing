import java.util.concurrent.Semaphore;

public class WatekA extends Thread {
    private Semaphore semaphoreA;
    private Semaphore semaphoreB;

    public WatekA(Semaphore semaphoreA, Semaphore semaphoreB) {
        this.semaphoreA = semaphoreA;
        this.semaphoreB = semaphoreB;
    }

    @Override
    public void run() {

        try{
            while(!Thread.currentThread().isInterrupted()){
                semaphoreA.acquire();
                System.out.println("A");
                sleep(10);
                if (semaphoreA.availablePermits() == 0) semaphoreB.release(1);
            }
        } catch (InterruptedException e) {
            System.out.println("Koniec watku A");
        }

    }
}
