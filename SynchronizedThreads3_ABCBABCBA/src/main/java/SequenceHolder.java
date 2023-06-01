public class SequenceHolder {
    private boolean nextA = true;

    public synchronized boolean isNextA() {
        return nextA;
    }

    public synchronized void setNextA(boolean nextA) {
        this.nextA = nextA;
    }
}
