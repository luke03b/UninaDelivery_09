package org.UninaDelivery;

import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;
import org.UninaDelivery.Cliente.ClienteDTO;
import org.UninaDelivery.Exception.FiltroNonValidoException;
import org.UninaDelivery.Exception.NoCampiSelezionatiException;
import org.UninaDelivery.Exception.TroppiCampiSelezionatiException;
import org.UninaDelivery.Operatore.OperatoreDTO;
import org.UninaDelivery.Ordine.DettagliOrdineDTO;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
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
    private JScrollPane PanelContenenteJTable;
    private JToolBar toolBar;
    private JComboBox filtroUtenti;
    private JButton aggiornaButton;
    private JButton dettagliOrdineButton;
    private GestoreFinestre gestoreFinestre;
    private OperatoreDTO operatoreLoggato;

    ImageIcon imageIcon = new ImageIcon("src/main/java/org/UninaDelivery/Icon/logoSenzaScritte.png");

    UtilDateModel modelDataInizio = new UtilDateModel();
    JDatePanelImpl datePanelDataInizio = new JDatePanelImpl(modelDataInizio);
    JDatePickerImpl pickerDataInizio = new JDatePickerImpl(datePanelDataInizio);

    UtilDateModel modelDataFine = new UtilDateModel();
    JDatePanelImpl datePanelDataFine = new JDatePanelImpl(modelDataFine);
    JDatePickerImpl pickerDataFine = new JDatePickerImpl(datePanelDataFine);


    public HomePage(JFrame parent, GestoreFinestre gf, OperatoreDTO operatoreLoggato){
        setImpostazioniHomePage(parent, gf, operatoreLoggato);
        setImpostazioniTabella();
        setImpostazioniToolBar();
        setImpostazioniUserInformationButton();
        setImpostazioniLogoutButton();
        setImpostazioniVarie();

        Listeners();
    }
    
    private void setImpostazioniHomePage(JFrame parent, GestoreFinestre gestoreFinestre, OperatoreDTO operatoreLoggato){
        setIconImage(imageIcon.getImage());
        setLayout(null);
        setResizable(true);
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
        Object[] nomiColonne = {"Selezionato", "Numero Ordine", "Data", "Destinatario", "Indirizzo", "Peso", "Grandezza"};
        DefaultTableModel modelloTabella = new DefaultTableModel(new Object[][]{}, nomiColonne){
            //rende solo la prima colonna della tabella editabile
            @Override
            public boolean isCellEditable(int row, int column){
                return column == 0;
            }
            
            @Override
            public Class getColumnClass(int column){
                return switch (column) {
                    case 0 -> Boolean.class;
                    case 2 -> LocalDate.class;
                    case 1, 3, 4, 6 -> String.class;
                    case 5 -> Float.class;
                    default -> null;
                };
            }
        };
        
        ordiniTable.setModel(modelloTabella);
        ordiniTable.getTableHeader().setBackground(new Color(155, 155, 155));
        
        aggiornaTabella(listaOrdini);
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
        PanelContenenteJTable.getViewport().setBackground(new Color(167, 169, 172));
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

    public void setImpostazioniToolBar() {

        aggiornaButton.setIcon(new ImageIcon("src/main/java/org/UninaDelivery/Icon/refresh.png"));

        toolBar.add(pickerDataInizio);
        toolBar.add(pickerDataFine);

        ArrayList<ClienteDTO> listaClienti = gestoreFinestre.recuperaClienti();
        filtroUtenti.addItem("<Filtra Utente>");
        for (ClienteDTO clienteDTO : listaClienti) {
            filtroUtenti.addItem(clienteDTO.getNominativo() + " " + clienteDTO.getNumeroTelefono());
        }
        ((JLabel)filtroUtenti.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
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

        userInformationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gestoreFinestre.apriInfoOperatore(operatoreLoggato);
            }
        });

        aggiornaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                java.util.Date dataInizio = (java.util.Date) pickerDataInizio.getModel().getValue();
                java.util.Date dataFine = (java.util.Date) pickerDataFine.getModel().getValue();
                String utenteSelezionato = filtroUtenti.getSelectedItem().toString();
                utenteSelezionato = utenteSelezionato.replaceAll("[^0-9]", "");

                try {
                    applicaFiltro(utenteSelezionato, dataInizio, dataFine);
                } catch (FiltroNonValidoException exception) {
                    System.out.println("Filtro non valido: " + exception);
                }
            }
        });

        dettagliOrdineButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    isSelezioneValida();
                } catch (NoCampiSelezionatiException ex) {
                    System.out.println("Nessuna checkBox selezionata: " + ex);
                } catch (TroppiCampiSelezionatiException ex) {
                    System.out.println("Più di una checkBox selezionata: " + ex);
                }
            }
        });
    }

    private void isSelezioneValida() throws NoCampiSelezionatiException, TroppiCampiSelezionatiException {
        switch (controllaQuanteFlagTabella()) {
            case 0:
                throw new NoCampiSelezionatiException();
            case 1:
                mostraDettagliOrdine();
                break;
            default:
                throw new TroppiCampiSelezionatiException();
        }
    }

    private void applicaFiltro(String utenteSelezionato, java.util.Date dataInizio, java.util.Date dataFine) throws FiltroNonValidoException {
        if (!utenteSelezionato.isEmpty() && dataInizio != null && dataFine != null) {
            ArrayList<DettagliOrdineDTO> listaOrdini = gestoreFinestre.RecuperaOrdiniByUtenteAndData(utenteSelezionato, new java.sql.Date(dataInizio.getTime()), new java.sql.Date(dataFine.getTime()));
            aggiornaTabella(listaOrdini);
            return;
        }
        if (dataInizio == null ^ dataFine == null) {
            throw new FiltroNonValidoException();
        }
        if (!utenteSelezionato.isEmpty()) {
            ArrayList<DettagliOrdineDTO> listaOrdini = gestoreFinestre.RecuperaOrdiniByUtente(utenteSelezionato);
            aggiornaTabella(listaOrdini);
            return;
        }
        if(dataInizio != null && dataFine != null) {
            ArrayList<DettagliOrdineDTO> listaOrdini = gestoreFinestre.RecuperaOrdiniByData(new java.sql.Date(dataInizio.getTime()), new java.sql.Date(dataFine.getTime()));
            aggiornaTabella(listaOrdini);
            return;
        }
        ArrayList<DettagliOrdineDTO> listaOrdini = gestoreFinestre.RecuperaOrdiniNonSpediti();
        aggiornaTabella(listaOrdini);
    }

    private void aggiornaTabella(ArrayList<DettagliOrdineDTO> listaAggiornata){
        DefaultTableModel model = (DefaultTableModel) ordiniTable.getModel();
        model.setRowCount(0);
        
        for (DettagliOrdineDTO ordineDTO : listaAggiornata){
            model.addRow(new Object[]{Boolean.FALSE, ordineDTO.getNumeroOrdine(),ordineDTO.getDataOrdine(), ordineDTO.getDestinatario(), ordineDTO.getIndirizzo(),
                    ordineDTO.getPeso(), ordineDTO.getGrandezza()});
        }
        resizeColumnWidth(ordiniTable);
    }
    
    private int controllaQuanteFlagTabella(){
        int numeroFlag = 0;
        for (int i=0; i<ordiniTable.getRowCount(); i++){
            if ((Boolean) ordiniTable.getValueAt(i, 0))
                numeroFlag++;
        }
        return numeroFlag;
    }
    
    private void mostraDettagliOrdine(){
        for (int i = 0; i < ordiniTable.getRowCount(); i++){
            if ((Boolean) ordiniTable.getValueAt(i, 0)){
                gestoreFinestre.apriInfoOrdine((int) ordiniTable.getValueAt(i, 1));
            }
        }
    }
}
