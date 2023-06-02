/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kolothread5;

import java.util.Collection;

/**
 *
 * @author Marcin
 */
public interface Ksiegowosc {
            
    void zamowienie(int idTowaru,double kwota);
    void sprzedaz(int idTowaru,double kwota);        
    void bilans();
    
}
