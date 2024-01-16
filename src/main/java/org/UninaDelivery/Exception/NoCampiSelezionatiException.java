package org.UninaDelivery.Exception;

import org.UninaDelivery.Controllori.ControlloreFinestre;

import java.awt.*;

public class NoCampiSelezionatiException extends Exception{
    public NoCampiSelezionatiException(Component chiamante, ControlloreFinestre controlloreFinestre){
        controlloreFinestre.mostraMessageDialogErrore(chiamante, "Selezionare una casella", "Attenzione");
    }
}
