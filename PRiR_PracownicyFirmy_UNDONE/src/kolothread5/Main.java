/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kolothread5;

/**
 *
 * @author Marcin
 */
public class Main {

    /*
    Zadanie polega na implementacji obsługi systemu płatności w firmie. 
    System składa się z k pracowników - k-wątków klasy Pracownik (patrz metoda main())
    i jednego wątku w postaci klasy Zarząd. Całość systemu łączy obiekt implementujący 
    interfejs Księgowosc.
    Każdy z pracowników odpowiedzialny jest za sprzedaż produktu lub zamówienie produktu. 
    Odpowiadają temu wywołania jednej z dwóch metod w klasie Ksiegowosc - odpowiednio 
    zamówienie(...), sprzedaz(...). Zamawiając lub sprzedając produkt podajemy id 
    produktu oraz kwotę odpowiednio zamówienia produktu i cena sprzedaży - patrz 
    klasa Pracownik()i metoda run() - w nieskończonej pętli losowane jest idProduktu, 
    kwota oraz informacja czy mamy do czynienia ze sprzedażą czy zakupem (zmienna 
    boolean zamowienieSprzedaz). Następnie w zależności od wartości 
    zamowienieSprzedaz wywoływane są metody zamowienie / sprzedaz.
    
    W przypadku klasy Zarzad również występuje nieskończona pętla, w której
    co 1/3 [s] wywoływana jest metoda bilans() która wyświetla informacje o tym jaki 
    jest stan sprzedaży/zamówienia każdego z produktów. Dodatkowo wyświetlane jest 
    podsumowanie w postaci Bilans: [sumaryczna_kwota_sprzedazy_zamówień]
    
    Wyświetlany wynik powinien wyglądać jak poniżej
    Towar: 0 Kwota: 968.940983422113
    Towar: 1 Kwota: 866.6658010860291
    Towar: 2 Kwota: 264.87893260369043
    Towar: 3 Kwota: -751.9884609439541
    Towar: 4 Kwota: -616.3194051511049
    Towar: 5 Kwota: -982.2226241569036
    Towar: 6 Kwota: 737.6498936109775
    Towar: 7 Kwota: -359.14094751521395
    Towar: 8 Kwota: 224.72618042224556
    Towar: 9 Kwota: 240.67209502081866
    ================================
    Bilans: 593.8624483986976
    
    Twoje zadanie polega na impementacji klasy KsiegowoscImpl implementującej interfejsc
    Ksiegowosc. Poszczególne metody powinny być wątkobezpieczne i zapewniać prawidłowe
    działanie oraz pozwalać na:
    w przypadku metody zamowienie(id,kwota) - dla produktu o identyfikatorze id
    odjąć od aktualnej kwoty podaną kwotę. Uuwaga zakładamy że zamawiając coś my sami
    musimy wydać pieniądze - stąd minus - na dany produkt
    w przypadku metody sprzedaz(id, kwota) - dla produktu o identyfikatorze id
    dodać do aktualnej kwoty podaną kwotą. Uwaga zakładamy że sprzedając produkt 
    zarabiamy na nim, stąd dodawanie kwoty
    Liczba produktów wynosi 10, chociaż możesz przyjąć nieskończoną liczbę produktów
    Początkowe wartości kwot przypisanych do poszczególnych produktów = 0 
    
    Metoda bilans() powinna wyświetlić kompletną ifnromację o wartościach przypisanych
    do poszczególnych produktów oraz wyświetlić ostateczną kwotę zgodnie z opisem i przykładem powyżej.            
    */
    
    public static void main(String[] args) throws InterruptedException {
        int k = 10;
        Ksiegowosc ksiegowosc = new KsiegowoscImpl(k); //Tworzenie instancji klasy księgowość
        Thread[] ok = new Thread[k]; //Tablica wątków
        //Tworzenie i uruchomienie wątków pracowników
        for (int i = 0; i < k; i++) {
            ok[i] = new Thread(new Pracownik(ksiegowosc));
            ok[i].start();
        }
        //Tworzenie i uruchomienie wątku zarządu
        Thread zarzad  = new Thread(new Zarzad(ksiegowosc));
        zarzad.start();
        
        //Czekamy 5 sekund
        Thread.sleep(5000);
        
        //Zakończenie wątków pracowników
        for(int i=0; i<k; i++){
            ok[i].interrupt();            
        }
        //Zakończenie wątku zarządu
        zarzad.interrupt();
    }

}




