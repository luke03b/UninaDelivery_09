package org.UninaDelivery;

import org.UninaDelivery.Exception.meseNonValidoException;
import org.UninaDelivery.Operatore.OperatoreDTO;
import org.UninaDelivery.StatisticheOrdini.StatisticheOrdineDTO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StatisticaPage extends JDialog{
    private JLabel matricolaLabel;
    private JButton logOutButton;
    private JButton userInformationButton;
    private JLabel cognomeLabel;
    private JLabel nomeLabel;
    private JButton indietroButton;
    private JTable statisticaTable;
    private JComboBox comboBox1;
    private JButton LogoHomeButton;
    private JPanel StatisticaPanel;
    private JLabel AVGordineLabel;
    private JLabel MaxProdotti;
    private JLabel MinProdotti;
    private JScrollPane PanelContenenteJTable;
    private GestoreFinestre gestoreFinestre;
    private OperatoreDTO operatoreLoggato;
    
    public StatisticaPage(JFrame parent, GestoreFinestre gestoreFinestre, OperatoreDTO operatoreLoggato){
        setImpostazioniStatisticaPage(parent, gestoreFinestre, operatoreLoggato);
        setImpostazioniVarie();
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
            case "Gennaio" -> statisticheOrdineDTO = gestoreFinestre.eseguiStatistica(1);
            case "Febbraio" -> statisticheOrdineDTO = gestoreFinestre.eseguiStatistica(2);
            case "Marzo" -> statisticheOrdineDTO = gestoreFinestre.eseguiStatistica(3);
            case "Aprile" -> statisticheOrdineDTO = gestoreFinestre.eseguiStatistica(4);
            case "Maggio" -> statisticheOrdineDTO = gestoreFinestre.eseguiStatistica(5);
            case "Giugno" -> statisticheOrdineDTO = gestoreFinestre.eseguiStatistica(6);
            case "Luglio" -> statisticheOrdineDTO = gestoreFinestre.eseguiStatistica(7);
            case "Agosto" -> statisticheOrdineDTO = gestoreFinestre.eseguiStatistica(8);
            case "Settembre" -> statisticheOrdineDTO = gestoreFinestre.eseguiStatistica(9);
            case "Ottobre" -> statisticheOrdineDTO = gestoreFinestre.eseguiStatistica(10);
            case "Novembre" -> statisticheOrdineDTO = gestoreFinestre.eseguiStatistica(11);
            case "Dicembre" -> statisticheOrdineDTO = gestoreFinestre.eseguiStatistica(12);
            default -> throw new meseNonValidoException();
        }
        aggiornaLabels(statisticheOrdineDTO);
    }

    private void aggiornaLabels(StatisticheOrdineDTO statisticheOrdineDTO){
        AVGordineLabel.setText(String.valueOf(statisticheOrdineDTO.getAVGNumOrdini()));
        MaxProdotti.setText(String.valueOf(statisticheOrdineDTO.getMaxNumProdottiInOrdine()));
        MinProdotti.setText(String.valueOf(statisticheOrdineDTO.getMinNumProdottiInOrdine()));
    }

    private void setImpostazioniStatisticaPage(JFrame parent, GestoreFinestre gestoreFinestre, OperatoreDTO operatoreLoggato){
        setLayout(null);
        setResizable(true);
        this.gestoreFinestre = gestoreFinestre;
        this.operatoreLoggato = operatoreLoggato;
        setTitle("Report Statistica");
        setContentPane(StatisticaPanel);
        setMinimumSize(new Dimension(300, 400));
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setModal(true);
    }

    private void setImpostazioniVarie(){
        nomeLabel.setText(operatoreLoggato.getNome());
        cognomeLabel.setText(operatoreLoggato.getCognome());
        matricolaLabel.setText(String.valueOf(operatoreLoggato.getMatricola()));
    }
}
