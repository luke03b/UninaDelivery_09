package org.UninaDelivery.Exception;

import org.UninaDelivery.Controller;

import java.awt.*;

public class TroppiCampiSelezionatiException extends Exception{
    public TroppiCampiSelezionatiException(Component chiamante, Controller controller){
        controller.mostraMessageDialog(chiamante, "Puoi selezionare solo una casella", "Attenzione");
    }
}
