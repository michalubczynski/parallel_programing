
public class Main {
    public static void main(String[] args) throws InterruptedException {
        Thread[] t = new Thread[3];
        Synchronizator synchronizator = new SynchronizatorImpl_SEMAPHORE();
        t[0] = new WatekA(synchronizator);
        t[1] = new WatekB(synchronizator);
        t[2] = new WatekC(synchronizator);
        for(Thread w : t){
            w.start();
        }
        Thread.sleep(1500);
        for(Thread w : t){
            w.interrupt();
        }
    }
}
