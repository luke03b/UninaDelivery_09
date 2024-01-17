package org.UninaDelivery;

import org.UninaDelivery.Controllori.ControlloreDAO;
import org.UninaDelivery.Controllori.ControlloreFinestre;
import org.UninaDelivery.StatisticheOrdini.StatisticheOrdineDTO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

public class StatisticaPage extends JDialog{
    private JButton indietroButton;
    private JComboBox comboBoxMese;
    private JPanel statisticaPanel;
    private JLabel AVGordineLabel;
    private JLabel maxProdottiLabel;
    private JLabel minProdottiLabel ;
    private JLabel logoLabel;
    private JLabel logoUnina;
    private ControlloreDAO controlloreDAO;
    private ControlloreFinestre controlloreFinestre;
    ImageIcon imageIcon = new ImageIcon("src/main/java/org/UninaDelivery/Icon/logoSenzaScritte.png");
    
    public StatisticaPage(JFrame parent, ControlloreFinestre controlloreFinestre, ControlloreDAO controlloreDAO){
        setImpostazioniStatisticaPage(parent, controlloreFinestre, controlloreDAO);
        setImpostazioniVarie();
        setImpostazioniIndietroButton();

        listeners();
    }

    private void listeners(){
        indietroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controlloreFinestre.chiudiPage(StatisticaPage.this);
            }
        });
        comboBoxMese.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String meseSelezionato = comboBoxMese.getSelectedItem().toString();
                getStatisticaMese(meseSelezionato);
            }
        });
    }

    private void getStatisticaMese(String meseSelezionato) {
        StatisticheOrdineDTO statisticheOrdineDTO = new StatisticheOrdineDTO();
        switch (meseSelezionato){
            case "<Mese>" -> {AVGordineLabel.setText(""); maxProdottiLabel.setText(""); minProdottiLabel.setText(""); return;}
            case "Gennaio" -> statisticheOrdineDTO = controlloreDAO.eseguiStatistica(1);
            case "Febbraio" -> statisticheOrdineDTO = controlloreDAO.eseguiStatistica(2);
            case "Marzo" -> statisticheOrdineDTO = controlloreDAO.eseguiStatistica(3);
            case "Aprile" -> statisticheOrdineDTO = controlloreDAO.eseguiStatistica(4);
            case "Maggio" -> statisticheOrdineDTO = controlloreDAO.eseguiStatistica(5);
            case "Giugno" -> statisticheOrdineDTO = controlloreDAO.eseguiStatistica(6);
            case "Luglio" -> statisticheOrdineDTO = controlloreDAO.eseguiStatistica(7);
            case "Agosto" -> statisticheOrdineDTO = controlloreDAO.eseguiStatistica(8);
            case "Settembre" -> statisticheOrdineDTO = controlloreDAO.eseguiStatistica(9);
            case "Ottobre" -> statisticheOrdineDTO = controlloreDAO.eseguiStatistica(10);
            case "Novembre" -> statisticheOrdineDTO = controlloreDAO.eseguiStatistica(11);
            case "Dicembre" -> statisticheOrdineDTO = controlloreDAO.eseguiStatistica(12);
            default -> System.out.println("il mese selezionato non è presente nella comboBox, evento strano.");
        }
        aggiornaLabels(statisticheOrdineDTO);
    }

    private void aggiornaLabels(StatisticheOrdineDTO statisticheOrdineDTO){
        AVGordineLabel.setText(String.valueOf(new DecimalFormat("#.##").format(statisticheOrdineDTO.getAVGNumOrdini())));
        maxProdottiLabel.setText(String.valueOf(statisticheOrdineDTO.getMaxNumProdottiInOrdine()));
        minProdottiLabel.setText(String.valueOf(statisticheOrdineDTO.getMinNumProdottiInOrdine()));
    }

    private void setImpostazioniStatisticaPage(JFrame parent, ControlloreFinestre controlloreFinestre, ControlloreDAO controlloreDAO){
        setLayout(null);
        setResizable(true);
        this.controlloreFinestre = controlloreFinestre;
        this.controlloreDAO = controlloreDAO;
        setTitle("Report Statistica");
        setContentPane(statisticaPanel);
        setMinimumSize(new Dimension(600, 270));
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setModal(true);
        setIconImage(imageIcon.getImage());
    }

    private void setImpostazioniVarie(){
        logoLabel.setIcon(new ImageIcon("src/main/java/org/UninaDelivery/Icon/logoSenzaScrittePiccolo.png"));
        logoUnina.setIcon(new ImageIcon("src/main/java/org/UninaDelivery/Icon/logoFedericoII.png"));
        comboBoxMese.setFocusable(false);
    }

    private void setImpostazioniIndietroButton(){
        indietroButton.setIcon(new ImageIcon("src/main/java/org/UninaDelivery/Icon/Indietro.png"));
        indietroButton.setMargin(new Insets(0, 0, 0, 0));
        indietroButton.setOpaque(false);
        indietroButton.setBorderPainted(false);
        indietroButton.setContentAreaFilled(false);
        indietroButton.setFocusable(false);
    }
}