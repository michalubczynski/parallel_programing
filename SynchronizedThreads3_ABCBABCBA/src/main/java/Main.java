import java.util.concurrent.Semaphore;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        SequenceHolder sequenceHolder = new SequenceHolder();
        Semaphore semaphoreB = new Semaphore(0);

        Thread[] t = new Thread[3];
        t[0] = new WatekA(semaphoreB, sequenceHolder);
        t[1] = new WatekB(semaphoreB, sequenceHolder);
        t[2] = new WatekC(semaphoreB, sequenceHolder);
        for (Thread w : t) {
            w.start();
        }
        Thread.sleep(500);
        for (Thread w : t) {
            w.interrupt();
        }
        for (Thread w : t) {
            w.join();
        }
        System.out.println("Koniec Programu");
    }
}
