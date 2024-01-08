package org.UninaDelivery.Exception;

import org.UninaDelivery.GestoreFinestre;

import javax.swing.*;

public class CampiVuotiException extends Exception{
    public CampiVuotiException(JFrame chiamante, GestoreFinestre gestoreFinestre){
        gestoreFinestre.mostraMessageDialog(chiamante, "Inserire tutti i campi", "Attenzione");
    }
}
