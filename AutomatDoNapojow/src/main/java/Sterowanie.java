/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Marcin
 */
public class Sterowanie extends Thread {

    IBufor bufor;

    public Sterowanie(IBufor bufor) {
        this.bufor = bufor;
    }

    public void run() {
        try {
            while (true) {
                Thread.sleep((int)(Math.random()*100));                
                double reszta = bufor.wydacTowar();                
                System.out.println("Wydanie towaru");
                System.out.println("Reszta: " + reszta + " zł");
            }
        } catch (InterruptedException e) {

        }

    }

}
