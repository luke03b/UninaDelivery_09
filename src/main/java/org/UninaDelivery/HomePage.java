package org.UninaDelivery;

import org.UninaDelivery.Operatore.OperatoreDTO;
import org.UninaDelivery.Ordine.DettagliOrdineDTO;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class HomePage extends JFrame{
    private JPanel homePanel;
    private JLabel logoLabel;
    private JLabel matricolaLabel;
    private JButton logOutButton;
    private JButton selezionaButton;
    private JButton programmaButton;
    private JButton statisticaButton;
    private JTable ordiniTable;
    private JButton userInformationButton;
    private JLabel nomeLabel;
    private JLabel cognomeLabel;
    private GestoreFinestre gestoreFinestre;
    private OperatoreDTO operatoreLoggato;
    
    public HomePage(JFrame parent, GestoreFinestre gf, OperatoreDTO operatoreLoggato){
        setImpostazioniHomePage(parent, gf, operatoreLoggato);
        setImpostazioniTabella();
        setImpostazioniUserInformationButton();
        setImpostazioniLogoutButton();
        setImpostazioniVarie();
        
        Listeners();
    }
    
    private void setImpostazioniHomePage(JFrame parent, GestoreFinestre gestoreFinestre, OperatoreDTO operatoreLoggato){
        setLayout(null);
        setResizable(true);
        setExtendedState(MAXIMIZED_BOTH);
        this.gestoreFinestre = gestoreFinestre;
        this.operatoreLoggato = operatoreLoggato;
        setTitle("Home");
        setContentPane(homePanel);
        setMinimumSize(new Dimension(1050, 430));
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
    
    private void setImpostazioniTabella(){
        ArrayList<DettagliOrdineDTO> listaOrdini = gestoreFinestre.RecuperaOrdiniNonSpediti();
        
        DefaultTableModel modelloTabella = new DefaultTableModel(){
            //rende tutte le colonne della tabella non editabili
            @Override
            public boolean isCellEditable(int row, int column){
                return false;
            };
        };
        modelloTabella.addColumn("Data");
        modelloTabella.addColumn("Nominativo");
        modelloTabella.addColumn("Indirizzo");
        modelloTabella.addColumn("Peso");
        modelloTabella.addColumn("Grandezza");
        ordiniTable.setModel(modelloTabella);
        
        for (DettagliOrdineDTO ordineDTO : listaOrdini){
            modelloTabella.addRow(new Object[]{ordineDTO.getDataOrdine(), ordineDTO.getNominativo(), ordineDTO.getIndirizzo(),
            ordineDTO.getPeso(), ordineDTO.getGrandezza()});
        }
        
        resizeColumnWidth(ordiniTable);
    }
    
    public void resizeColumnWidth(JTable table) {
        final TableColumnModel columnModel = table.getColumnModel();
        for (int column = 0; column < table.getColumnCount(); column++) {
            int width = 15; // Min width
            for (int row = 0; row < table.getRowCount(); row++) {
                TableCellRenderer renderer = table.getCellRenderer(row, column);
                Component comp = table.prepareRenderer(renderer, row, column);
                width = Math.max(comp.getPreferredSize().width +1 , width);
            }
            if(width > 300)
                width=300;
            columnModel.getColumn(column).setPreferredWidth(width);
        }
    }
    
    private void setImpostazioniVarie(){
        nomeLabel.setText(operatoreLoggato.getNome());
        cognomeLabel.setText(operatoreLoggato.getCognome());
        matricolaLabel.setText(String.valueOf(operatoreLoggato.getMatricola()));
        logoLabel.setIcon(new ImageIcon("src/main/java/org/UninaDelivery/Icon/logoSenzaScrittePiccolo.png"));
    }
    
    private void setImpostazioniUserInformationButton(){
        userInformationButton.setIcon(new ImageIcon("src/main/java/org/UninaDelivery/Icon/user.png"));
        userInformationButton.setMargin(new Insets(0, 0, 0, 0));
        userInformationButton.setOpaque(false);
        userInformationButton.setBorderPainted(false);
        userInformationButton.setBorder(null);
        userInformationButton.setContentAreaFilled(false);
    }
    
    private void setImpostazioniLogoutButton(){
        logOutButton.setIcon(new ImageIcon("src/main/java/org/UninaDelivery/Icon/logOut.png"));
        logOutButton.setMargin(new Insets(0, 0, 0, 0));
        logOutButton.setOpaque(false);
        logOutButton.setBorderPainted(false);
        logOutButton.setBorder(null);
        logOutButton.setContentAreaFilled(false);
    }
    
    private void Listeners(){
        logOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object[] Opzioni = {"Si", "No"};
                if (JOptionPane.showOptionDialog(HomePage.this, "Vuoi eseguire il LogOut?",
                        "LogOut", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, Opzioni,
                        Opzioni[0]) == JOptionPane.OK_OPTION){
                    gestoreFinestre.TornaLogin(HomePage.this);
                }
            }
        });
        
        statisticaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                gestoreFinestre.apriStatistica(operatoreLoggato);
                dispose();
            }
        });
    }
    
}
