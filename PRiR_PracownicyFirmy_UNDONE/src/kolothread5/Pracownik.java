/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kolothread5;

import java.util.Random;

/**
 *
 * @author Marcin
 */
class Pracownik implements Runnable {

    Ksiegowosc ksiegowosc;

    public Pracownik(Ksiegowosc ksiegowosc) {
        this.ksiegowosc = ksiegowosc;
    }

    @Override
    public void run() {
        Random r = new Random();
        try {
            do {
                int idTowaru = r.nextInt(10); //Generowanie losowego nr id klienta
                double kwota = r.nextDouble() * 1000; //Generowanie wpłaty lub wypłaty zależnie od znaku
                boolean zamowienieSprzedaz = r.nextBoolean(); //Losujemy czy bedziemy skladali zamowienie czy sprzedaz
                if (zamowienieSprzedaz) {
                    ksiegowosc.zamowienie(idTowaru, kwota);
                } else {
                    ksiegowosc.sprzedaz(idTowaru, kwota);
                }
                Thread.sleep(20);
            } while (!Thread.interrupted());
        } catch (InterruptedException ex) {
        }
    }

}
