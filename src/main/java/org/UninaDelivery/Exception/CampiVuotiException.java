package org.UninaDelivery.Exception;

import org.UninaDelivery.Controller;

import javax.swing.*;

public class CampiVuotiException extends Exception{
    public CampiVuotiException(JFrame chiamante, Controller controller){
        controller.mostraMessageDialog(chiamante, "Inserire tutti i campi", "Attenzione");
    }
}
