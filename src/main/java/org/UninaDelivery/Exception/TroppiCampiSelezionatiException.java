package org.UninaDelivery.Exception;

import org.UninaDelivery.Controllori.ControlloreFinestre;
import org.UninaDelivery.Starter;

import java.awt.*;

public class TroppiCampiSelezionatiException extends Exception{
    public TroppiCampiSelezionatiException(Component chiamante, ControlloreFinestre controlloreFinestre){
        controlloreFinestre.mostraMessageDialog(chiamante, "Puoi selezionare solo una casella", "Attenzione");
    }
}
