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
    public GestoreFinestre gestoreFinestre;
    
    public HomePage(JFrame parent, GestoreFinestre gf, OperatoreDTO operatoreLoggato){
        setImpostazioniHomePage(parent, gf);
        setImpostazioniTable();
        setImpostazioniVarie(operatoreLoggato);

        logOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object[] Opzioni = {"Si", "No"};
                if (JOptionPane.showOptionDialog(HomePage.this, "Vuoi eseguire il LogOut?", "LogOut", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, Opzioni, Opzioni[0]) == JOptionPane.OK_OPTION){
                    gf.TornaLogin(HomePage.this);
                }
            }
        });
    }
    
    private void setImpostazioniHomePage(JFrame parent, GestoreFinestre gf){
        setLayout(null);
        setResizable(true);
        setExtendedState(MAXIMIZED_BOTH);
        gestoreFinestre = gf;
        setTitle("Home");
        setContentPane(homePanel);
        setMinimumSize(new Dimension(1050, 430));
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
    
    private void setImpostazioniTable(){
        ArrayList<DettagliOrdineDTO> listaOrdini = gestoreFinestre.RecuperaOrdiniNonSpediti();
        
        DefaultTableModel modelloTabella = new DefaultTableModel();
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

    public void setImpostazioniVarie(OperatoreDTO operatoreLoggato){
        nomeLabel.setText(operatoreLoggato.getNome());
        cognomeLabel.setText(operatoreLoggato.getCognome());
        matricolaLabel.setText(String.valueOf(operatoreLoggato.getMatricola()));
    }
}
