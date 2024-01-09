package org.UninaDelivery;

import org.UninaDelivery.Operatore.OperatoreDTO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
    private JScrollPane PanelContenenteJTable;
    private GestoreFinestre gestoreFinestre;
    private OperatoreDTO operatoreLoggato;
    
    public StatisticaPage(JFrame parent, GestoreFinestre gestoreFinestre, OperatoreDTO operatoreLoggato){
        setImpostazioniStatisticaPage(parent, gestoreFinestre, operatoreLoggato);
        setImpostazioniVarie(parent, gestoreFinestre, operatoreLoggato);
        indietroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gestoreFinestre.apriHome(operatoreLoggato, StatisticaPage.this);
            }
        });
    }
    
    private void setImpostazioniStatisticaPage(JFrame parent, GestoreFinestre gestoreFinestre, OperatoreDTO operatoreLoggato){
        setLayout(null);
        setResizable(true);
        this.gestoreFinestre = gestoreFinestre;
        this.operatoreLoggato = operatoreLoggato;
        setTitle("Report Statistica");
        setContentPane(StatisticaPanel);
        setMinimumSize(new Dimension(1050, 430));
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        PanelContenenteJTable.getViewport().setBackground(new Color(167, 169, 172));
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

    private void setImpostazioniVarie(JFrame parent, GestoreFinestre gestoreFinestre, OperatoreDTO operatoreLoggato){
        nomeLabel.setText(operatoreLoggato.getNome());
        cognomeLabel.setText(operatoreLoggato.getCognome());
        matricolaLabel.setText(String.valueOf(operatoreLoggato.getMatricola()));
    }
}
