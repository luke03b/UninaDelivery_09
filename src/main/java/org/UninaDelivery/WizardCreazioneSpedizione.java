package org.UninaDelivery;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WizardCreazioneSpedizione extends JDialog{
    private JPanel cards;
    private JPanel card1;
    private JPanel card2;
    private JPanel card3;
    private JButton avantiButton;
    private JButton avantiButton1;
    private JButton confermaButton;
    private JButton annullaButton;
    private JButton indietroButton;
    private JButton indietroButton1;
    private GestoreFinestre gestoreFinestre;
    ImageIcon imageIcon = new ImageIcon("src/main/java/org/UninaDelivery/Icon/logoSenzaScritte.png");
    CardLayout cardLayout = (CardLayout) cards.getLayout();
    
    public WizardCreazioneSpedizione(JFrame parent, GestoreFinestre gestoreFinestre){
        setImpostazioniWizardPage(parent, gestoreFinestre);
        
        Listeners();
    }
    
    private void setImpostazioniWizardPage(JFrame parent, GestoreFinestre gestoreFinestre){
        setIconImage(imageIcon.getImage());
        setLayout(null);
        setResizable(true);
        this.gestoreFinestre = gestoreFinestre;
        setTitle("Creazione Spedizione");
        setContentPane(cards);
        setMinimumSize(new Dimension(800, 400));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        cards.add(card1, "Scelta Corriere");
        cards.add(card2, "Scelta Mezzo Trasporto");
        cards.add(card3, "Riepilogo");
    }
    
    private void Listeners(){
        avantiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.next(cards);
            }
        });
        
        annullaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        avantiButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.next(cards);
            }
        });
        indietroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.previous(cards);
            }
        });
        indietroButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.previous(cards);
            }
        });
    }
}
