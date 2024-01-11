package org.UninaDelivery;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WizardCreazioneSpedizione extends JDialog{
    private JPanel cards;
    private JPanel paginaSceltaCorriere;
    private JPanel paginaSceltaMezzo;
    private JPanel paginaRiepilogo;
    private JButton avantiButton1;
    private JButton avantiButton2;
    private JButton annullaButton;
    private JButton indietroButton2;
    private JTable table1;
    private JScrollPane panelContenenteJTable1;
    private JLabel iconaSceltaCorriere;
    private JLabel iconaMezzo;
    private JLabel iconaRiepilogo;
    private JLabel iconaSceltaCorriere2;
    private JLabel iconaMezzo2;
    private JLabel iconaRiepilogo2;
    private JLabel iconaSceltaCorriere3;
    private JLabel iconaRiepilogo3;
    private JLabel iconaMezzo3;
    private JButton indietroButton3;
    private JScrollPane panelContenenteJTable2;
    private JButton confermaButton;
    private GestoreFinestre gestoreFinestre;
    ImageIcon imageIcon = new ImageIcon("src/main/java/org/UninaDelivery/Icon/logoSenzaScritte.png");
    CardLayout cardLayout = (CardLayout) cards.getLayout();
    
    public WizardCreazioneSpedizione(JFrame parent, GestoreFinestre gestoreFinestre){
        setImpostazioniWizardPage(parent, gestoreFinestre);
        setImpostazioniAnnullaButton();
        setImpostazioniAvantiButton();
        setImpostazioniIndietroButton();
        setImpostazioniConfermaButton();
        setImpostazioniVarie();
        setImpostazioniIcone();

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
        cards.add(paginaSceltaCorriere, "Scelta Corriere");
        cards.add(paginaSceltaMezzo, "Scelta Mezzo Trasporto");
        cards.add(paginaRiepilogo, "Riepilogo");
    }

    public void setImpostazioniVarie() {
        panelContenenteJTable1.getViewport().setBackground(new Color(202, 192, 179));
        panelContenenteJTable2.getViewport().setBackground(new Color(202, 192, 179));
    }

    public void setImpostazioniIcone(){
        iconaSceltaCorriere.setIcon(new ImageIcon("src/main/java/org/UninaDelivery/Icon/sceltaCorriereNero.png"));
        iconaSceltaCorriere2.setIcon(new ImageIcon("src/main/java/org/UninaDelivery/Icon/sceltaCorriereRosso.png"));
        iconaSceltaCorriere3.setIcon(new ImageIcon("src/main/java/org/UninaDelivery/Icon/sceltaCorriereRosso.png"));

        iconaMezzo.setIcon(new ImageIcon("src/main/java/org/UninaDelivery/Icon/sceltaMezzoRosso.png"));
        iconaMezzo2.setIcon(new ImageIcon("src/main/java/org/UninaDelivery/Icon/sceltaMezzoNero.png"));
        iconaMezzo3.setIcon(new ImageIcon("src/main/java/org/UninaDelivery/Icon/sceltaMezzoRosso.png"));

        iconaRiepilogo.setIcon(new ImageIcon("src/main/java/org/UninaDelivery/Icon/RiepilogoRosso.png"));
        iconaRiepilogo2.setIcon(new ImageIcon("src/main/java/org/UninaDelivery/Icon/RiepilogoRosso.png"));
        iconaRiepilogo3.setIcon(new ImageIcon("src/main/java/org/UninaDelivery/Icon/RiepilogoNero.png"));
    }

    public void setImpostazioniIndietroButton() {
        indietroButton2.setIcon(new ImageIcon("src/main/java/org/UninaDelivery/Icon/Indietro.png"));
        indietroButton2.setMargin(new Insets(0, 0, 0, 0));
        indietroButton2.setOpaque(false);
        indietroButton2.setBorderPainted(false);
        indietroButton2.setContentAreaFilled(false);
        indietroButton2.setFocusable(false);

        indietroButton3.setIcon(new ImageIcon("src/main/java/org/UninaDelivery/Icon/Indietro.png"));
        indietroButton3.setMargin(new Insets(0, 0, 0, 0));
        indietroButton3.setOpaque(false);
        indietroButton3.setBorderPainted(false);
        indietroButton3.setContentAreaFilled(false);
        indietroButton3.setFocusable(false);
    }

    private void setImpostazioniAnnullaButton() {
        annullaButton.setIcon(new ImageIcon("src/main/java/org/UninaDelivery/Icon/annulla.png"));
        annullaButton.setMargin(new Insets(0, 0, 0, 0));
        annullaButton.setOpaque(false);
        annullaButton.setBorderPainted(false);
        annullaButton.setContentAreaFilled(false);
        annullaButton.setFocusable(false);
    }

    private void setImpostazioniAvantiButton() {
        avantiButton1.setIcon(new ImageIcon("src/main/java/org/UninaDelivery/Icon/avanti.png"));
        avantiButton1.setMargin(new Insets(0, 0, 0, 0));
        avantiButton1.setOpaque(false);
        avantiButton1.setBorderPainted(false);
        avantiButton1.setContentAreaFilled(false);
        avantiButton1.setFocusable(false);

        avantiButton2.setIcon(new ImageIcon("src/main/java/org/UninaDelivery/Icon/avanti.png"));
        avantiButton2.setMargin(new Insets(0, 0, 0, 0));
        avantiButton2.setOpaque(false);
        avantiButton2.setBorderPainted(false);
        avantiButton2.setContentAreaFilled(false);
        avantiButton2.setFocusable(false);
    }

    private void setImpostazioniConfermaButton(){
        confermaButton.setIcon(new ImageIcon("src/main/java/org/UninaDelivery/Icon/ConfermaRosso.png"));
        confermaButton.setMargin(new Insets(0, 0, 0, 0));
        confermaButton.setOpaque(false);
        confermaButton.setBorderPainted(false);
        confermaButton.setContentAreaFilled(false);
        confermaButton.setFocusable(false);
    }

    private void Listeners(){
        avantiButton1.addActionListener(new ActionListener() {
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
        avantiButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.next(cards);
            }
        });
        indietroButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.previous(cards);
            }
        });
        indietroButton3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {cardLayout.previous(cards); }
        });
    }


}
