/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Marcin
 */
public class Klient extends Thread {

    IBufor bufor;

    public Klient(IBufor bufor) {
        this.bufor = bufor;
    }

    public void run() {
        try {
            while (true) {
                Thread.sleep((int)(Math.random()*100));
                System.out.println("Wrzucam 1zł");
                bufor.wrzutMonety(1);
            }
        } catch (InterruptedException e) {

        }

    }

}
