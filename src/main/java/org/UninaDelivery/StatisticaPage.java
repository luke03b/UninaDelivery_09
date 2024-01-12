package org.UninaDelivery;

import org.UninaDelivery.Exception.meseNonValidoException;
import org.UninaDelivery.Operatore.OperatoreDTO;
import org.UninaDelivery.StatisticheOrdini.StatisticheOrdineDTO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StatisticaPage extends JDialog{
    private JButton indietroButton;
    private JTable statisticaTable;
    private JComboBox comboBox1;
    private JPanel statisticaPanel;
    private JLabel AVGordineLabel;
    private JLabel maxProdotti;
    private JLabel minProdotti;
    private JLabel logoLabel;
    private JLabel logoUnina;
    private JScrollPane panelContenenteJTable;
    private Controller controller;
    
    public StatisticaPage(JFrame parent, Controller controller){
        setImpostazioniStatisticaPage(parent, controller);
        setImpostazioniVarie();
        setImpostazioniIndietroButton();
        indietroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        comboBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String meseSelezionato;
                meseSelezionato = comboBox1.getSelectedItem().toString();
                try{
                    recuperaStatisticaMese(meseSelezionato);
                } catch (meseNonValidoException exception){
                    System.out.println("nessun mese selezionato: " + exception);
                }
            }
        });
    }

    private void recuperaStatisticaMese(String meseSelezionato) throws meseNonValidoException{
        StatisticheOrdineDTO statisticheOrdineDTO = new StatisticheOrdineDTO();
        switch (meseSelezionato){
            case "<Mese>" -> {AVGordineLabel.setText(""); maxProdotti.setText(""); minProdotti.setText(""); return;}
            case "Gennaio" -> statisticheOrdineDTO = controller.eseguiStatistica(1);
            case "Febbraio" -> statisticheOrdineDTO = controller.eseguiStatistica(2);
            case "Marzo" -> statisticheOrdineDTO = controller.eseguiStatistica(3);
            case "Aprile" -> statisticheOrdineDTO = controller.eseguiStatistica(4);
            case "Maggio" -> statisticheOrdineDTO = controller.eseguiStatistica(5);
            case "Giugno" -> statisticheOrdineDTO = controller.eseguiStatistica(6);
            case "Luglio" -> statisticheOrdineDTO = controller.eseguiStatistica(7);
            case "Agosto" -> statisticheOrdineDTO = controller.eseguiStatistica(8);
            case "Settembre" -> statisticheOrdineDTO = controller.eseguiStatistica(9);
            case "Ottobre" -> statisticheOrdineDTO = controller.eseguiStatistica(10);
            case "Novembre" -> statisticheOrdineDTO = controller.eseguiStatistica(11);
            case "Dicembre" -> statisticheOrdineDTO = controller.eseguiStatistica(12);
            default -> throw new meseNonValidoException();
        }
        aggiornaLabels(statisticheOrdineDTO);
    }

    private void aggiornaLabels(StatisticheOrdineDTO statisticheOrdineDTO){
        AVGordineLabel.setText(String.valueOf(statisticheOrdineDTO.getAVGNumOrdini()));
        maxProdotti.setText(String.valueOf(statisticheOrdineDTO.getMaxNumProdottiInOrdine()));
        minProdotti.setText(String.valueOf(statisticheOrdineDTO.getMinNumProdottiInOrdine()));
    }

    private void setImpostazioniStatisticaPage(JFrame parent, Controller controller){
        setLayout(null);
        setResizable(true);
        this.controller = controller;
        setTitle("Report Statistica");
        setContentPane(statisticaPanel);
        setMinimumSize(new Dimension(600, 270));
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setModal(true);
    }

    private void setImpostazioniVarie(){
        logoLabel.setIcon(new ImageIcon("src/main/java/org/UninaDelivery/Icon/logoSenzaScrittePiccolo.png"));
        logoUnina.setIcon(new ImageIcon("src/main/java/org/UninaDelivery/Icon/logoFedericoII.png"));
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
