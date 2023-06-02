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
class Zarzad implements Runnable {

    Ksiegowosc ksiegowosc;

    public Zarzad(Ksiegowosc ksiegowosc) {
        this.ksiegowosc = ksiegowosc;
    }

    @Override
    public void run() {
        try {
            do {
                Thread.sleep(333);
                ksiegowosc.bilans();
            } while (!Thread.interrupted());
        } catch (InterruptedException ex) {  }
    }

}