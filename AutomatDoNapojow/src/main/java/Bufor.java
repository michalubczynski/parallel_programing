public class Bufor implements IBufor{
    double cash;
    @Override
    public synchronized void wrzutMonety(double kwota) throws InterruptedException {
        while(cash>=2.5) wait();
        cash+=kwota;
        notifyAll();

    }

    @Override
    public synchronized double wydacTowar() throws InterruptedException {
        while(cash<2.5)wait();
        cash-=2.5;
        notifyAll();
        return cash;
    }

    @Override
    public synchronized double zwrotMonet() throws InterruptedException {
        synchronized (this) {
            double reszta=cash;
            cash=0;
            return reszta;
        }
    }
}
