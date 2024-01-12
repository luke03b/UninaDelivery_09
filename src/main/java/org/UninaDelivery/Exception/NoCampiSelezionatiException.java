package org.UninaDelivery.Exception;

import org.UninaDelivery.Controller;

import java.awt.*;

public class NoCampiSelezionatiException extends Exception{
    public NoCampiSelezionatiException(Component chiamante, Controller controller){
        controller.mostraMessageDialog(chiamante, "Selezionare una casella", "Attenzione");
    }
}
