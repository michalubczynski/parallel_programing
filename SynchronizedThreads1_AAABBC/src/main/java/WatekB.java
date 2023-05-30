
public class WatekB extends Thread {
    Synchronizator synchronizator;
    public WatekB(Synchronizator synchronizator) {
        this.synchronizator = synchronizator;
    }

    public void run(){
        try {
            while (true) {
                //Procedura początkowa zadania
                Thread.sleep((long)(Math.random()*10));
                synchronizator.startB(); //Sekcja krytyczna
                System.out.println("Synchronizowany kod B");
                Thread.sleep((long)(Math.random()*10));
                synchronizator.koniecB(); //Koniec sekcji krytycznej
                //Procedura końcowa zadania
            }
        } catch(InterruptedException e){
            System.out.println("Koniec watku B");
        }
    }
}
