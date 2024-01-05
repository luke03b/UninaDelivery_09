package org.example;

import javax.swing.*;
import java.awt.*;

public class HomePage extends JFrame{
    private JPanel panel1;
    public GestoreFinestre gestoreFinestre;
    
    public HomePage(JFrame parent, GestoreFinestre gf){
        setImpostazioniHomePage(parent, gf);
    }
    
    private void setImpostazioniHomePage(JFrame parent, GestoreFinestre gf){
        setLayout(null);
        setResizable(false);
        gestoreFinestre = gf;
        setTitle("Home");
        setContentPane(panel1);
        setMinimumSize(new Dimension(540, 320));
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
}
