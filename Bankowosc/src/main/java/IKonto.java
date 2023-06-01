/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.concurrent.atomic.AtomicBoolean;

/**
 *
 * @author Marcin
 */
public interface IKonto {
    /** 
     * Metoda pozweala na zapisanie/ustawienie stanu konta
     */
    void ustawStanKonta(double stanKonta);

    /**
     * Metoda zwraca stanKonta
     * @return 
     */
    double stanKonta();

    AtomicBoolean getTrwaAkcja();
}
