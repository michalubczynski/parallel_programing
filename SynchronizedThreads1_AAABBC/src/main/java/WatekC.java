
public class WatekC extends Thread {
    Synchronizator synchronizator;
    public WatekC(Synchronizator synchronizator) {
        this.synchronizator = synchronizator;
    }

    public void run(){
        try {
            while (true) {
                //Procedura początkowa zadania
                Thread.sleep((long)(Math.random()*10));
                synchronizator.startC(); //Sekcja krytyczna
                System.out.println("Synchronizowany kod C");
                Thread.sleep((long)(Math.random()*10));
                synchronizator.koniecC(); //Koniec sekcji krytycznej
                //Procedura końcowa zadania
            }
        } catch(InterruptedException e){
            System.out.println("Koniec watku C");
        }
    }
}