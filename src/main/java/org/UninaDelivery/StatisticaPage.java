package org.UninaDelivery;

import org.UninaDelivery.Operatore.OperatoreDTO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class StatisticaPage extends JFrame{
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
    private GestoreFinestre gestoreFinestre;
    private OperatoreDTO operatoreLoggato;
    
    public StatisticaPage(JFrame parent, GestoreFinestre gestoreFinestre, OperatoreDTO operatoreLoggato){
        setImpostazioniStatisticaPage(parent, gestoreFinestre, operatoreLoggato);
    }
    
    private void setImpostazioniStatisticaPage(JFrame parent, GestoreFinestre gestoreFinestre, OperatoreDTO operatoreLoggato){
        setLayout(null);
        setResizable(true);
        setExtendedState(MAXIMIZED_BOTH);
        this.gestoreFinestre = gestoreFinestre;
        this.operatoreLoggato = operatoreLoggato;
        setTitle("Report Statistica");
        setContentPane(StatisticaPanel);
        setMinimumSize(new Dimension(1050, 430));
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
    
    private void setImpostazioniTabella(String mese){
        DefaultTableModel modelloTabella = new DefaultTableModel(){
            //rende tutte le colonne della tabella non editabili
            @Override
            public boolean isCellEditable(int row, int column){
                return false;
            };
        };
        modelloTabella.addColumn("Numero Medio Ordini");
        modelloTabella.addColumn("Ordine con maggior numero di prodotti");
        modelloTabella.addColumn("Ordine con minor numero di prodotti");
        statisticaTable.setModel(modelloTabella);
    }
}