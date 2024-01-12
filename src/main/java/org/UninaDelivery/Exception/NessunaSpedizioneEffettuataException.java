package org.UninaDelivery.Exception;

import org.UninaDelivery.Controller;

public class NessunaSpedizioneEffettuataException extends Exception{
    public NessunaSpedizioneEffettuataException(Controller controller){
        controller.mostraMessageDialog(null, "Merce Insufficiente in magazzino.\nNessuna spedizione completata", "Attenzione");
    }
}
