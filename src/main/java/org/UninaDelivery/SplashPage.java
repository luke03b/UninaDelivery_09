package org.UninaDelivery;

import org.UninaDelivery.Controllori.ControlloreFinestre;

import javax.swing.*;
import java.awt.*;

public class SplashPage extends JWindow{
    private JPanel splashPanel;
    private JLabel logoLabel;
    private ControlloreFinestre controlloreFinestre;
    
    public SplashPage(ControlloreFinestre controlloreFinestre){
        setImpostazioniSplashPage(controlloreFinestre);
        chiusuraAutomatizzata(2500);
    }
    
    private void setImpostazioniSplashPage(ControlloreFinestre controlloreFinestre){
        setLayout(null);
        setContentPane(splashPanel);
        this.controlloreFinestre = controlloreFinestre;
        logoLabel.setIcon(new ImageIcon("src/main/java/org/UninaDelivery/Icon/logoUninaDeliveryGrande.jpg"));
        setMinimumSize(new Dimension(1024, 682));
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    private void chiusuraAutomatizzata(long millisecondi){
        try{
            Thread.sleep(millisecondi);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        controlloreFinestre.chiudiSplashPage(this);
    }
}