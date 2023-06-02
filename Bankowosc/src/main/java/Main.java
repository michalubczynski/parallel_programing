/**
 * Mamy dwa konta bankowe konto1 oraz konto2, zadanie polega na stworzeniu wątku, który umożliwi bezpieczną realizację funkcji przelania
 * po 1zł pieniędzy z jednego konta na drugie.
 * W programie jednocześnie będzie działało 10 wątków.
 * Twoje zadanie polega na:
 * Implementacji klasy Konto. Zwróć uwagę na zależności w metodzie main()
 * Implementacji metody run() w klasie MojWatek (patrz poniżej), tak aby:
 * - bezpiecznie pobrać z Konto1 1zł i dodać 1zł pobrany z Konto1 do aktualnego stanu Konto2
 * - w ramach jednego wątku pomiędzy kolejnymi iteracjami cyklu pobranie / zapis na konto musi być przerwa minimum 10ms
 * - jeśli na Konto1 nie ma już pieniędzy wątek powinien się zakończyć
 * - Wątki powinny prawidłowo reagować na polecenie interrupt.
 * - program powinien gwarantować możliwość działania współbieżnego, czyli należy zapewnić żywotność i bezpieczeństwo. Rozwiązanie w którym jeden wątek blokuje dostę innym na cały czas działania wątku będzie skutkowało oceną niedostateczną
 * <p>
 * UWAGA: Interfejs IKonto składa sie z dwóch metod - pozwalającej na odczyt stanu okta, oraz na zapis stanu konta
 * <p>
 * Zwróć uwagę na komentarze poniżej.
 * UWAGA:
 * 1) docelowo metod main powinna pozostać nie zmieniona (choć można w niej robić drobne modyfikacje na potrzeby debugowania)
 * 2) konstruktora klasy MojWatek nie wolno modyfikować,
 * 3) Można i należy dodać implementacji metody run()
 * 4) Dla ułatwienia zacznij od 1 wątku.
 * 5) Resztę programu uzupełnić na podstawie kodu metody main() oraz gotowych fragmentów klasy MyWatek()
 */


//Klasa wątku
public class Main {

    public static void main(String[] args) throws InterruptedException {
        //Inicjalizacja zmiennych
        double początkowyStanKonta = 10000.0;
        int liczbaWatkow = 10;
        IKonto konto1 = new Konto(początkowyStanKonta);
        IKonto konto2 = new Konto(0);
        //Inicjalizacja wątków i przekazanie wątkom referencji do kont
        Thread[] watki = new Thread[liczbaWatkow];
        for (int i = 0; i < liczbaWatkow; i++) {
            watki[i] = new MojWatek(konto1, konto2);
        }
        //Uruchomienie wątków
        for (int i = 0; i < liczbaWatkow; i++) {
            watki[i].start();
        }
        //Wątki pracują przez 0.5s
        Thread.sleep(2000);
        //Po 0.5 sekundzie wysyłamy polecenie zatrzymania do wątków i wątki powinny się zakończyć
        for (int i = 0; i < liczbaWatkow; i++) {
            watki[i].interrupt();
        }
        //Czekamy aż wszystkie wątki się zamkną
        for (int i = 0; i < liczbaWatkow; i++) {
            watki[i].join();
        }
        //Wypisanie stanu kont
        System.out.println("Koncowy stan konta zrodlowego = " + konto1.stanKonta());
        System.out.println("Koncowy stan konta docelowego = " + konto2.stanKonta());
        System.out.println("Suma zgromadzonych srodkow = " + (konto1.stanKonta() + konto2.stanKonta()));
    }
}

//Klasa wątku
class MojWatek extends Thread {

    IKonto konto1;
    IKonto konto2;

    //Konstruktor
    public MojWatek(IKonto konto1, IKonto konto2) {
        this.konto1 = konto1;
        this.konto2 = konto2;
    }

    //Główna metod wątku
    @Override
    public void run() {
        try {
            while (!currentThread().isInterrupted()) {
                synchronized (konto1){
                    double cash1 = konto1.stanKonta();
                    konto1.ustawStanKonta(cash1 - 1);
                }
                synchronized (konto2){
                    double cash2 = konto2.stanKonta();
                    konto2.ustawStanKonta(cash2 + 1);
                }
                System.out.println("+1");
                Thread.sleep((int) (Math.random() * 5));
            }
        } catch (Exception e) {
            System.out.println("Koniec przelewania");
        }
    }
}
