import java.util.concurrent.Semaphore;

public class WatekB extends Thread{
    private Semaphore semaphoreA;
    private Semaphore semaphoreB;

    public WatekB(Semaphore semaphoreA, Semaphore semaphoreB) {
        this.semaphoreA = semaphoreA;
        this.semaphoreB = semaphoreB;
    }

    @Override
    public void run() {

        try{
            while(!Thread.currentThread().isInterrupted()){
                semaphoreB.acquire();
                System.out.println("B");
                sleep(10);
                semaphoreA.release(2);
            }
        } catch (InterruptedException e) {
            System.out.println("Koniec watku B");
        }

    }
}
