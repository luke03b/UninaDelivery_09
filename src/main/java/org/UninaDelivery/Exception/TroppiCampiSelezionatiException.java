package org.UninaDelivery.Exception;

import org.UninaDelivery.GestoreFinestre;

import javax.swing.*;

public class TroppiCampiSelezionatiException extends Exception{
    public TroppiCampiSelezionatiException(JFrame chiamante, GestoreFinestre gestoreFinestre){
        gestoreFinestre.mostraMessageDialog(chiamante, "Puoi visualizzare un solo ordine per volta", "Attenzione");
    }
}
