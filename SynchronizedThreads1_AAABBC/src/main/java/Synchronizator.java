
public interface Synchronizator {

    public void startA() throws InterruptedException;

    public void koniecA() throws InterruptedException;

    public void startB() throws InterruptedException;

    public void koniecB() throws InterruptedException;

    public void startC() throws InterruptedException;

    public void koniecC() throws InterruptedException;

}
