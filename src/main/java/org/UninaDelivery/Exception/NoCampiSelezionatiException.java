package org.UninaDelivery.Exception;

import org.UninaDelivery.GestoreFinestre;

import javax.swing.*;
import java.awt.*;

public class NoCampiSelezionatiException extends Exception{
    public NoCampiSelezionatiException(Component chiamante, GestoreFinestre gestoreFinestre){
        gestoreFinestre.mostraMessageDialog(chiamante, "Selezionare una casella", "Attenzione");
    }
}
