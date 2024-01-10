package org.UninaDelivery.Exception;

import org.UninaDelivery.GestoreFinestre;

import javax.swing.*;

public class NoCampiSelezionatiException extends Exception{
    public NoCampiSelezionatiException(JFrame chiamante, GestoreFinestre gestoreFinestre){
        gestoreFinestre.mostraMessageDialog(chiamante, "Selezionare un ordine", "Attenzione");
    }
}
