package org.UninaDelivery;

import org.UninaDelivery.Prodotto.ProdottoDTO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class InfoOrdinePage extends JDialog{
    private JLabel FedIILogo;
    private JLabel UninaDeliveryLogo;
    private JButton indietroButton;
    private JPanel InfoOrdinePage;
    private JTable prodottiTable;
    private JScrollPane PanelContenenteJTable;
    private GestoreFinestre gestoreFinestre;
    private int numOrdine;
    
    public InfoOrdinePage(JFrame parent, GestoreFinestre gestoreFinestre, int numOrdine){
        setImpostazioniInfoOrdinePage(parent, gestoreFinestre, numOrdine);
        setContenutiVisivi();
        setImpostazioniTabella();
        setImpostazioniIndietroButton();
        Listeners();
    }
    
    private void setImpostazioniInfoOrdinePage(JFrame parent, GestoreFinestre gestoreFinestre, int numOrd){
        setLayout(null);
        setResizable(false);
        setTitle("Dettagli Ordine");
        setContentPane(InfoOrdinePage);
        setMinimumSize(new Dimension(800, 400));
        setModal(true);
        this.gestoreFinestre = gestoreFinestre;
        this.numOrdine = numOrd;
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    public void setImpostazioniTabella(){
        ArrayList<ProdottoDTO> listaProdotti = gestoreFinestre.recuperaProdotti(numOrdine);
        Object[] nomiColonne = {"Codice Prodotto", "Nome", "Prezzo", "Peso", "Categoria", "Descrizione", "Quantità"};
        DefaultTableModel modelloTabella = new DefaultTableModel(new Object[][]{}, nomiColonne){
            //nessuna cella della tabella editabile
            @Override
            public boolean isCellEditable(int row, int column){
                return false;
            }

            @Override
            public Class getColumnClass(int column){
                return switch (column) {
                    case 0 -> int.class;
                    case 1, 2, 3, 4, 5, 6 -> String.class;
                    default -> null;
                };
            }
        };
        
        setRigheTable(modelloTabella, listaProdotti);
        prodottiTable.setModel(modelloTabella);
        prodottiTable.getTableHeader().setBackground(new Color(0, 18, 51));
        prodottiTable.getTableHeader().setForeground(new Color (255, 255, 255));
        gestoreFinestre.resizeColumnWidth(prodottiTable);
        prodottiTable.getColumnModel().getColumn(5).setCellRenderer(new WordWrapCellRenderer());
        prodottiTable.setFocusable(false);
        prodottiTable.setRowSelectionAllowed(false);
        prodottiTable.getTableHeader().setReorderingAllowed(false);
        prodottiTable.getTableHeader().setResizingAllowed(false);
    }

    public void setRigheTable(DefaultTableModel modelloTabella, ArrayList<ProdottoDTO> listaProdotti){
        for (ProdottoDTO prodottoDTO : listaProdotti){
            modelloTabella.addRow(new Object[]{prodottoDTO.getCodiceProdotto(), prodottoDTO.getNome(), prodottoDTO.getPrezzo(), prodottoDTO.getPeso(),
                    prodottoDTO.getCategoria(), prodottoDTO.getDescrizione(), prodottoDTO.getQuantitaOrdine()});
        }
    }
    

    private void setContenutiVisivi(){
        UninaDeliveryLogo.setIcon(new ImageIcon("src/main/java/org/UninaDelivery/Icon/logoSenzaScrittePiccolo.png"));
        FedIILogo.setIcon(new ImageIcon("src/main/java/org/UninaDelivery/Icon/logoFedericoII.png"));
        PanelContenenteJTable.getViewport().setBackground(new Color(202, 192, 179));
    }

    private void setImpostazioniIndietroButton(){
        indietroButton.setIcon(new ImageIcon("src/main/java/org/UninaDelivery/Icon/Indietro.png"));
        indietroButton.setMargin(new Insets(0, 0, 0, 0));
        indietroButton.setOpaque(false);
        indietroButton.setBorderPainted(false);
        indietroButton.setContentAreaFilled(false);
        indietroButton.setFocusable(false);
    }

    private void Listeners(){
        indietroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }
    
    static class WordWrapCellRenderer extends JTextArea implements TableCellRenderer {
        WordWrapCellRenderer() {
            setLineWrap(true);
            setWrapStyleWord(true);
        }
        
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            setText(value.toString());
            setSize(table.getColumnModel().getColumn(column).getWidth(), getPreferredSize().height);
            setBackground(new Color(202, 192, 179));
            setForeground(new Color(0, 0, 0));
            setSelectionColor(new Color(255, 89, 90));
            setFont(new Font("JetBrains Mono Medium", Font.BOLD, 14));
            
            if (table.getRowHeight(row) != getPreferredSize().height) {
                table.setRowHeight(row, getPreferredSize().height);
            }
            return this;
        }
    }
}
