package org.UninaDelivery.Exception;

import org.UninaDelivery.GestoreFinestre;

import javax.swing.*;
import java.awt.*;

public class TroppiCampiSelezionatiException extends Exception{
    public TroppiCampiSelezionatiException(Component chiamante, GestoreFinestre gestoreFinestre){
        gestoreFinestre.mostraMessageDialog(chiamante, "Puoi selezionare solo una casella", "Attenzione");
    }
}
