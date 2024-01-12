package org.UninaDelivery.Exception;

import org.UninaDelivery.Controller;

public class SpedizioniNonEffettuateException extends Exception{
    public SpedizioniNonEffettuateException(int numeroSpedizioniNonEffettuate, Controller controller){
        if (numeroSpedizioniNonEffettuate == 0)
            controller.mostraMessageDialog(null, "Merce Insufficiente in magazzino.\nNessuna spedizione completata", "Attenzione");
        
        if (numeroSpedizioniNonEffettuate == 1)
            controller.mostraMessageDialog(null, "Merce Insufficiente in magazzino.\n" + numeroSpedizioniNonEffettuate + " spedizione non completata", "Attenzione");
        
        if (numeroSpedizioniNonEffettuate > 1)
            controller.mostraMessageDialog(null, "Merce Insufficiente in magazzino.\n" + numeroSpedizioniNonEffettuate + " spedizioni non completate", "Attenzione");
    }
}
