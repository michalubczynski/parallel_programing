import java.util.concurrent.atomic.AtomicBoolean;

public class Konto implements IKonto{
    private volatile double cash;
    private AtomicBoolean trwaAkcja = new AtomicBoolean(false);

    public Konto(double cash) {
        this.cash = cash;
    }

    @Override
    public void ustawStanKonta(double stanKonta) {
        cash=stanKonta;
        this.trwaAkcja = new AtomicBoolean(false);
    }

    @Override
    public double stanKonta() {
        this.trwaAkcja = new AtomicBoolean(true);
        return cash;
    }
    @Override
    public AtomicBoolean getTrwaAkcja() {
        return trwaAkcja;
    }
}
