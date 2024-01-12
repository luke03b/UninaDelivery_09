package org.UninaDelivery.Exception;

import org.UninaDelivery.Controller;

import javax.swing.*;

public class FiltroNonValidoException extends Exception{
    public FiltroNonValidoException(JFrame chiamante, Controller controller){
        controller.mostraMessageDialog(chiamante, "Selezionare entrambe le date", "Attenzione");
    }
}
