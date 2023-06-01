/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Marcin
 */
public interface IBufor {
    void wrzutMonety(double kwota) throws InterruptedException;
    double wydacTowar() throws InterruptedException;
    double zwrotMonet() throws InterruptedException;
}
