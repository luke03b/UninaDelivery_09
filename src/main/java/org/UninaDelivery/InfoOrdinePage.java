package org.UninaDelivery;

import org.UninaDelivery.Prodotto.ProdottoDTO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class InfoOrdinePage extends JDialog{
    private JLabel FedIILogo;
    private JLabel UninaDeliveryLogo;
    private JLabel fotoProfiloLabel;
    private JButton backButton;
    private JPanel InfoOrdinePage;
    private JTable prodottiTable;
    private GestoreFinestre gestoreFinestre;
    private int codiceProdotto;
    
    public InfoOrdinePage(JFrame parent, GestoreFinestre gestoreFinestre, int codiceProdotto){
        setImpostazioniInfoOrdinePage(parent, gestoreFinestre, codiceProdotto);
        setContenutiVisivi();
        Listeners();
    }
    
    private void setImpostazioniInfoOrdinePage(JFrame parent, GestoreFinestre gestoreFinestre, int codiceProdotto){
        setLayout(null);
        setResizable(false);
        setTitle("Dettagli Ordine");
        setContentPane(InfoOrdinePage);
        setMinimumSize(new Dimension(300, 400));
        setModal(true);
        this.gestoreFinestre = gestoreFinestre;
        this.codiceProdotto = codiceProdotto;
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
    
    private void setContenutiVisivi(){
        UninaDeliveryLogo.setIcon(new ImageIcon("src/main/java/org/UninaDelivery/Icon/logoSenzaScrittePiccolo.png"));
        FedIILogo.setIcon(new ImageIcon("src/main/java/org/UninaDelivery/Icon/logoFedericoII.png"));
        fotoProfiloLabel.setIcon(new ImageIcon("src/main/java/org/UninaDelivery/Icon/user.png"));
    }
    
    private void Listeners(){
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }
}
