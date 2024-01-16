package org.UninaDelivery.Exception;

import org.UninaDelivery.Controllori.ControlloreFinestre;

import java.awt.*;

public class AlcuneSpedizioniNonEffettuateException extends Exception{
    public AlcuneSpedizioniNonEffettuateException(Component parent, ControlloreFinestre controlloreFinestre, int numeroSpedizioniEffettuate){
        controlloreFinestre.mostraMessageDialogErrore(parent, numeroSpedizioniEffettuate + " spedizioni sono state effettuate.\nMotivo: merce insufficiente", "Attenzione");
    }
}
