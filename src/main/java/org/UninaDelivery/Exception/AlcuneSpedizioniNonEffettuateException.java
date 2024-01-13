package org.UninaDelivery.Exception;

import org.UninaDelivery.Controllori.ControlloreFinestre;

import javax.swing.*;
import java.awt.*;

public class AlcuneSpedizioniNonEffettuateException extends Exception{
    public AlcuneSpedizioniNonEffettuateException(Component parent, ControlloreFinestre controlloreFinestre, int numeroSpedizioniEffettuate){
        controlloreFinestre.mostraMessageDialog(parent, numeroSpedizioniEffettuate + " spedizioni sono state effettuate.\nMotivo: merce insufficiente", "Attenzione");
    }
}
