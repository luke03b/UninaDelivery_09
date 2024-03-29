package org.UninaDelivery;

import org.UninaDelivery.Controllori.ControlloreDAO;
import org.UninaDelivery.Controllori.ControlloreFinestre;
import org.UninaDelivery.Prodotto.ProdottoDTO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class InfoOrdinePage extends JDialog{
    private JLabel fedIILogo;
    private JLabel uninaDeliveryLogo;
    private JButton indietroButton;
    private JPanel infoOrdinePanel;
    private JTable prodottiTable;
    private JScrollPane panelContenenteJTable;
    private ControlloreFinestre controlloreFinestre;
    private ControlloreDAO controlloreDAO;
    private int numOrdine;
    private ImageIcon imageIcon = new ImageIcon("src/main/java/org/UninaDelivery/Icon/logoSenzaScritte.png");
    
    public InfoOrdinePage(JFrame parent, ControlloreFinestre controlloreFinestre, ControlloreDAO controlloreDAO, int numOrdine){
        setImpostazioniInfoOrdinePage(parent, controlloreFinestre, controlloreDAO, numOrdine);
        setContenutiVisivi();
        setImpostazioniTabella();
        setImpostazioniIndietroButton();
        listeners();
    }
    
    private void setImpostazioniInfoOrdinePage(JFrame parent, ControlloreFinestre controlloreFinestre, ControlloreDAO controlloreDAO, int numOrd){
        setLayout(null);
        setResizable(false);
        setTitle("Dettagli Ordine");
        setContentPane(infoOrdinePanel);
        setMinimumSize(new Dimension(800, 400));
        setModal(true);
        this.controlloreFinestre = controlloreFinestre;
        this.controlloreDAO = controlloreDAO;
        this.numOrdine = numOrd;
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setIconImage(imageIcon.getImage());
    }

    private void setImpostazioniTabella(){
        ArrayList<ProdottoDTO> listaProdotti = controlloreDAO.recuperaProdotti(numOrdine);
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
        
        setRigheTabella(modelloTabella, listaProdotti);
        prodottiTable.setModel(modelloTabella);
        prodottiTable.getTableHeader().setBackground(new Color(0, 18, 51));
        prodottiTable.getTableHeader().setForeground(new Color (255, 255, 255));
        controlloreFinestre.resizeColumnWidth(prodottiTable);
        prodottiTable.getColumnModel().getColumn(5).setCellRenderer(new WordWrapCellRenderer());
        prodottiTable.setFocusable(false);
        prodottiTable.setRowSelectionAllowed(false);
        prodottiTable.getTableHeader().setReorderingAllowed(false);
        prodottiTable.getTableHeader().setResizingAllowed(false);
    }

    private void setRigheTabella(DefaultTableModel modelloTabella, ArrayList<ProdottoDTO> listaProdotti){
        for (ProdottoDTO prodottoDTO : listaProdotti){
            modelloTabella.addRow(new Object[]{prodottoDTO.getCodiceProdotto(), prodottoDTO.getNome(), prodottoDTO.getPrezzo(), prodottoDTO.getPeso(),
                    prodottoDTO.getCategoria(), prodottoDTO.getDescrizione(), prodottoDTO.getQuantitaOrdine()});
        }
    }
    

    private void setContenutiVisivi(){
        uninaDeliveryLogo.setIcon(new ImageIcon("src/main/java/org/UninaDelivery/Icon/logoSenzaScrittePiccolo.png"));
        fedIILogo.setIcon(new ImageIcon("src/main/java/org/UninaDelivery/Icon/logoFedericoII.png"));
        panelContenenteJTable.getViewport().setBackground(new Color(202, 192, 179));
    }

    private void setImpostazioniIndietroButton(){
        indietroButton.setIcon(new ImageIcon("src/main/java/org/UninaDelivery/Icon/Indietro.png"));
        indietroButton.setMargin(new Insets(0, 0, 0, 0));
        indietroButton.setOpaque(false);
        indietroButton.setBorderPainted(false);
        indietroButton.setContentAreaFilled(false);
        indietroButton.setFocusable(false);
    }

    private void listeners(){
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