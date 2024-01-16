package org.UninaDelivery.Exception;

import org.UninaDelivery.Controllori.ControlloreFinestre;

import java.awt.*;

public class NessunaSpedizioneEffettuataException extends Exception{
    public NessunaSpedizioneEffettuataException(Component parent, ControlloreFinestre controlloreFinestre){
        controlloreFinestre.mostraMessageDialogErrore(parent, "Nessuna spedizione è stata effettuata.\nMotivo: merce insufficiente nel magazzino", "Attenzione");
    }
}
