package org.UninaDelivery;

import org.UninaDelivery.Operatore.OperatoreDTO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InfoOperatorePage extends JDialog{
    private JPanel infoOpPane;
    private JButton indietroButton;
    private JLabel matricolaLabel;
    private JLabel nomeLabel;
    private JLabel cognomeLabel;
    private JLabel codiceFiscaleLabel;
    private JLabel etaLabel;
    private JLabel stipendioLabel;
    private JLabel dataNascitaLabel;
    private JLabel dataAssunzioneLabel;
    private JLabel uninaDeliveryLogo;
    private JLabel fedIILogo;
    private Controller controller;
    private OperatoreDTO operatoreLoggato;

    public InfoOperatorePage(JFrame parent, Controller gf, OperatoreDTO operatoreLoggato){
        setImpostazioniInfoOpPage(parent, gf, operatoreLoggato);
        setContenutiVisivi();
        setImpostazioniIndietroButton();
        listeners();
    }

    private void setImpostazioniInfoOpPage(JFrame parent, Controller controller, OperatoreDTO operatoreLoggato){
        setLayout(null);
        setResizable(false);
        setTitle("Informazioni Operatore");
        setContentPane(infoOpPane);
        setMinimumSize(new Dimension(380, 450));
        setModal(true);
        this.controller = controller;
        this.operatoreLoggato = operatoreLoggato;
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    private void setImpostazioniIndietroButton() {
        indietroButton.setIcon(new ImageIcon("src/main/java/org/UninaDelivery/Icon/Indietro.png"));
        indietroButton.setMargin(new Insets(0, 0, 0, 0));
        indietroButton.setOpaque(false);
        indietroButton.setBorderPainted(false);
        indietroButton.setContentAreaFilled(false);
        indietroButton.setFocusable(false);
    }

    private void setContenutiVisivi(){
        matricolaLabel.setText(String.valueOf(operatoreLoggato.getMatricola()));
        nomeLabel.setText(operatoreLoggato.getNome());
        cognomeLabel.setText(operatoreLoggato.getCognome());
        codiceFiscaleLabel.setText(operatoreLoggato.getCodiceFiscale());
        etaLabel.setText(String.valueOf(operatoreLoggato.getEta()) + " anni");
        stipendioLabel.setText(String.valueOf(operatoreLoggato.getStipendio()) + "€");
        dataNascitaLabel.setText(String.valueOf(operatoreLoggato.getDataNascita()));
        dataAssunzioneLabel.setText(String.valueOf(operatoreLoggato.getDataAssunzione()));
        uninaDeliveryLogo.setIcon(new ImageIcon("src/main/java/org/UninaDelivery/Icon/logoSenzaScrittePiccolo.png"));
        fedIILogo.setIcon(new ImageIcon("src/main/java/org/UninaDelivery/Icon/logoFedericoII.png"));
    }
    
    private void listeners(){
        indietroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }
}
