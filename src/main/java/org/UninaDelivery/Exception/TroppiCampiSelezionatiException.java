package org.UninaDelivery.Exception;

import org.UninaDelivery.Controllori.ControlloreFinestre;

import java.awt.*;

public class TroppiCampiSelezionatiException extends Exception{
    public TroppiCampiSelezionatiException(Component chiamante, ControlloreFinestre controlloreFinestre){
        controlloreFinestre.mostraMessageDialogErrore(chiamante, "Puoi selezionare solo una casella", "Attenzione");
    }
}