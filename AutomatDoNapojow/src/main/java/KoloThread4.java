/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Marcin
 */
public class KoloThread4 {

    /**
     * Masz do oprogramowania automat do napojów. Działa on w dwóch wątkach. Pierwszy wątek odpowiada za obsługę systemu wrzutu monet (nazywany wątkiem klienta). 
     * Drugi wątek odpowiada za wydawanie napojów (nazywany wątkiem sterowania). Pomiędzy wątkami Klienta i Sterowania jest bufor który zapewnia wymianę informacji między obydwoma wątkami
     * System powinien działać tak, zę wątek Sterowania czeka aż w wątku klienta nie zostanie dodana do bufora odpowiednia kwota umożliwiająca wydanie napoju. 
     * Jeśli wątek Klienta wrzuci do automatu wymaganą kwotę to wątek klienta powinien przejść w stan oczekiwania, czyli próba dodania kolejnej monety powinna zostać zablokowana aż do momentu 
     * gdy wątek sterowania nie wyda napoju. Jeśli wątek Sterowania wyda napój odblokowuje on wątek Klienta umożliwiając mu wrzucenie nowych monet.
     * Zadanie na ocenę 3 wymaga podstawowego działania.
     * Program powinien wydać towar zaraz po wrzuceniu jakiejkolwiek kwoty. Interrupt mogą nie działać oraz zwrot reszty też może nie działać (przykładowa implementacja - return 0;)
     * Wyższa ocena wymaga by:
     * Wątki zatrzymywały się po wydaniu polecenia interrupt()
     * Istniała możliwość wydania polecenia zwrocMonety(), które to polecenie powinno umożliwić zwrot monet bez wydawania towaru
     * Domyślna cena napoju to 2.5 zł
     * Dla ułatwienia możesz korzystać z klasy double jako typu opisującego kwotą (w praktyce nie wolno tak robić z uwagi na zaokrąglanie)
     * Do realizacji zadania skorzystaj z klasy IBufor która definiuje odpowiednie metody dla wątku Klienta, Sterowania, oraz zwrotu monet
     * Przeanalizuj metodę main która jest już stworzona i powinna zapewnić działanie programu bez modyfikacji
     * Dla ułatwienia wątek klienta został już zaimplementowany i nie należy go modyfikować. (na ocenę 3 można go zmodyfikować jeśli będziesz uważał/uważała to za konieczne)
	 * Z założenia klient wywołując polecenie wrzutMonety(kwota) powinien wrzucić do bufora odpowiednią kwotę. Jeśli odpowiednia kwota jest większa od ceny to wątek klienta powinien zostać zablokowany, jeśli nie to wątek klienta powinien iść dalej.
	 * Podobnie powinien działać watek Sterowania. Wywołanie metody reszta = bufor.wydacTowar(); powinno zablokować wątek na czas aż klient nie wrzyci odpowiedniej kwoty. Jesli zostanie wrzucona odpowiednia kwota to wątek Sterowania powinien się ucuchomić wydać towar i resztę (wypisać na ekranie "wydaję towar, reszta = " + reszta) a następnie ponownie wejść w tryb oczekiwania
	 * Całość logiki powinna być zaimplementowana w klasie Bufor
	 * Wydanie polecenia zrotMonet(); powinno zwrócić monety bez wydawania reszty.
     */

    public static void main(String[] args) throws InterruptedException {
        IBufor bufor = new Bufor();
        Klient klient = new Klient(bufor);
        Sterowanie sterowanie = new Sterowanie(bufor);
        klient.start();
        sterowanie.start();
        
        //Działanie programu
        Thread.sleep(1500);
        
        //Symulacja zwrotu reszty
        double reszta = bufor.zwrotMonet();
        System.out.println("Wydano polecenie zwrotu monet. Otrzymano: " + reszta + " zł");
        
        //Działanie programu
        Thread.sleep(1500);
        
        //Kończenie programu
        klient.interrupt();
        sterowanie.interrupt();
        klient.join();
        sterowanie.join();
        System.out.println("Koniec programu");
    }
    
}
