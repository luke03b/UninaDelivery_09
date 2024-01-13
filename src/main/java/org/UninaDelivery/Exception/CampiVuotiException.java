package org.UninaDelivery.Exception;

import org.UninaDelivery.Controllori.ControlloreFinestre;

import javax.swing.*;

public class CampiVuotiException extends Exception{
    public CampiVuotiException(JFrame chiamante, ControlloreFinestre controlloreFinestre){
        controlloreFinestre.mostraMessageDialog(chiamante, "Inserire tutti i campi", "Attenzione");
    }
}
