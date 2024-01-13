package org.UninaDelivery.Exception;

import org.UninaDelivery.Controllori.ControlloreFinestre;
import org.UninaDelivery.Starter;

import java.awt.*;

public class NoCampiSelezionatiException extends Exception{
    public NoCampiSelezionatiException(Component chiamante, ControlloreFinestre controlloreFinestre){
        controlloreFinestre.mostraMessageDialog(chiamante, "Selezionare una casella", "Attenzione");
    }
}
