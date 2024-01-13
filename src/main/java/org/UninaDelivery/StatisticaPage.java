package org.UninaDelivery;

import org.UninaDelivery.Controllori.ControlloreDAO;
import org.UninaDelivery.Controllori.ControlloreFinestre;
import org.UninaDelivery.Exception.MeseNonValidoException;
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
    private ControlloreFinestre controlloreFinestre;
    private ControlloreDAO controlloreDAO;
    
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
                } catch (MeseNonValidoException exception){
                    System.out.println("nessun mese selezionato: " + exception);
                }
            }
        });
    }

    private void recuperaStatisticaMese(String meseSelezionato) throws MeseNonValidoException {
        StatisticheOrdineDTO statisticheOrdineDTO = new StatisticheOrdineDTO();
        switch (meseSelezionato){
            case "<Mese>" -> {AVGordineLabel.setText(""); maxProdotti.setText(""); minProdotti.setText(""); return;}
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
            default -> throw new MeseNonValidoException();
        }
        aggiornaLabels(statisticheOrdineDTO);
    }

    private void aggiornaLabels(StatisticheOrdineDTO statisticheOrdineDTO){
        AVGordineLabel.setText(String.valueOf(statisticheOrdineDTO.getAVGNumOrdini()));
        maxProdotti.setText(String.valueOf(statisticheOrdineDTO.getMaxNumProdottiInOrdine()));
        minProdotti.setText(String.valueOf(statisticheOrdineDTO.getMinNumProdottiInOrdine()));
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
