package org.UninaDelivery;

import javax.swing.*;
import java.awt.*;

public class SplashPage extends JWindow{
    private JPanel splashPanel;
    private JLabel logoLabel;
    private Controller controller;
    
    public SplashPage(Controller controller){
        setImpostazioniSplashPage(controller);
        chiusuraAutomatizzata(2500);
    }
    
    private void setImpostazioniSplashPage(Controller controller){
        setLayout(null);
        setContentPane(splashPanel);
        this.controller = controller;
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
        controller.chiudiSplashPage(this);
    }
}
