import java.util.concurrent.Semaphore;

public class Main {
    static Semaphore semaphoreA = new Semaphore(2);
    static Semaphore semaphoreB = new Semaphore(0);
    public static void main(String[] args) throws InterruptedException {

        Thread[] t = new Thread[2];
        t[0] = new WatekA(semaphoreA, semaphoreB);
        t[1] = new WatekB(semaphoreA, semaphoreB);
        for(Thread w : t){
            w.start();
        }
        Thread.sleep(500);
        for(Thread w : t){
            w.interrupt();
        }
        for(Thread w : t){
            w.join();
        }
        System.out.println("Koniec Programu");

    }
}
