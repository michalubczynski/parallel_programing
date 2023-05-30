

public class WatekA extends Thread {
    Synchronizator synchronizator;
    public WatekA(Synchronizator synchronizator) {
        this.synchronizator = synchronizator;
    }

    public void run(){
        try {
            while (true) {
                //Procedura początkowa zadania
                Thread.sleep((long)(Math.random()*10));
                synchronizator.startA(); //Sekcja krytyczna
                System.out.println("Synchronizowany kod A");
                Thread.sleep((long)(Math.random()*10));
                synchronizator.koniecA(); //Koniec sekcji krytycznej
                //Procedura końcowa zadania
            }
        } catch(InterruptedException e){
            System.out.println("Koniec watku A");
        }
    }
}