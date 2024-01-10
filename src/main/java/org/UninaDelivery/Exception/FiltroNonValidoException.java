package org.UninaDelivery.Exception;

import org.UninaDelivery.GestoreFinestre;

import javax.swing.*;

public class FiltroNonValidoException extends Exception{
    public FiltroNonValidoException(JFrame chiamante, GestoreFinestre gestoreFinestre){
        gestoreFinestre.mostraMessageDialog(chiamante, "Selezionare entrambe le date", "Attenzione");
    }
}
