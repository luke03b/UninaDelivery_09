package org.UninaDelivery.Exception;

import org.UninaDelivery.Controllori.ControlloreFinestre;

import javax.swing.*;

public class FiltroNonValidoException extends Exception{
    public FiltroNonValidoException(JFrame chiamante, ControlloreFinestre controlloreFinestre){
        controlloreFinestre.mostraMessageDialogErrore(chiamante, "Selezionare entrambe le date", "Attenzione");
    }
}
