package org.UninaDelivery;

import org.UninaDelivery.Operatore.OperatoreDTO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InfoOperatorePage extends JDialog{
    private JPanel InfoOpPane;
    private JButton backButton;
    private JLabel fotoProfiloLabel;
    private JLabel matricolaLabel;
    private JLabel nomeLabel;
    private JLabel cognomeLabel;
    private JLabel codiceFiscaleLabel;
    private JLabel etaLabel;
    private JLabel stipendioLabel;
    private JLabel dataNascitaLabel;
    private JLabel dataAssunzioneLabel;
    private JLabel UninaDeliveryLogo;
    private JLabel FedIILogo;
    private GestoreFinestre gestoreFinestre;
    private OperatoreDTO operatoreLoggato;

    public InfoOperatorePage(JFrame parent, GestoreFinestre gf, OperatoreDTO operatoreLoggato){
        setImpostazioniInfoOpPage(parent, gf, operatoreLoggato);
        setContenutiVisivi();
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    private void setImpostazioniInfoOpPage(JFrame parent, GestoreFinestre gestoreFinestre, OperatoreDTO operatoreLoggato){
        setLayout(null);
        setResizable(false);
        setTitle("Informazioni Operatore");
        setContentPane(InfoOpPane);
        setMinimumSize(new Dimension(300, 400));
        setModal(true);
        this.gestoreFinestre = gestoreFinestre;
        this.operatoreLoggato = operatoreLoggato;
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
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
        UninaDeliveryLogo.setIcon(new ImageIcon("src/main/java/org/UninaDelivery/Icon/logoSenzaScrittePiccolo.png"));
        FedIILogo.setIcon(new ImageIcon("src/main/java/org/UninaDelivery/Icon/logoFedericoII.png"));
        fotoProfiloLabel.setIcon(new ImageIcon("src/main/java/org/UninaDelivery/Icon/user.png"));
    }
}
