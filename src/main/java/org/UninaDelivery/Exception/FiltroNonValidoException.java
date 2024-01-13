package org.UninaDelivery.Exception;

import org.UninaDelivery.Controllori.ControlloreFinestre;
import org.UninaDelivery.Starter;

import javax.swing.*;

public class FiltroNonValidoException extends Exception{
    public FiltroNonValidoException(JFrame chiamante, ControlloreFinestre controlloreFinestre){
        controlloreFinestre.mostraMessageDialog(chiamante, "Selezionare entrambe le date", "Attenzione");
    }
}
