package org.UninaDelivery;

import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;
import org.UninaDelivery.Cliente.ClienteDTO;
import org.UninaDelivery.Controllori.ControlloreDAO;
import org.UninaDelivery.Controllori.ControlloreFinestre;
import org.UninaDelivery.DettagliSpedizione.DettagliSpedizioneDTO;
import org.UninaDelivery.Exception.NoCampiSelezionatiException;
import org.UninaDelivery.Operatore.OperatoreDTO;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.LocalDate;
import java.util.ArrayList;

public class SpedizioniProgrammatePage extends JFrame {
    private JPanel spedizioniProgrammatePanel;
    private JLabel logoLabel;
    private JLabel matricolaLabel;
    private JButton logOutButton;
    private JTable spedizioniTable;
    private JButton infoUtenteButton;
    private JLabel nomeLabel;
    private JLabel cognomeLabel;
    private JScrollPane panelContenenteJTable;
    private JLabel ordiniNonSpeditiLabel;
    private JButton annullaProgrammazioneButton;
    private JButton inserisciDettagliOrdine;
    private JButton indietroButton;
    private JButton modificaButton;
    private JToolBar toolBar;
    private JButton resetButton;
    private JButton selezionaTuttoButton;
    private JButton aggiornaButton;
    private JComboBox filtroUtenti;
    private JLabel dataInizioLabel;
    private ControlloreFinestre controlloreFinestre;
    private ControlloreDAO controlloreDAO;
    private OperatoreDTO operatoreLoggato;
    private JLabel dataFineLabel;

    ImageIcon imageIcon = new ImageIcon("src/main/java/org/UninaDelivery/Icon/logoSenzaScritte.png");

    UtilDateModel modelDataInizio = new UtilDateModel();
    JDatePanelImpl datePanelDataInizio = new JDatePanelImpl(modelDataInizio);
    JDatePickerImpl pickerDataInizio = new JDatePickerImpl(datePanelDataInizio);
    UtilDateModel modelDataFine = new UtilDateModel();
    JDatePanelImpl datePanelDataFine = new JDatePanelImpl(modelDataFine);
    JDatePickerImpl pickerDataFine = new JDatePickerImpl(datePanelDataFine);


    public SpedizioniProgrammatePage(JFrame parent, ControlloreFinestre controlloreFinestre, ControlloreDAO controlloreDAO, OperatoreDTO operatoreLoggato){
        setImpostazioniSpedizioniProgrammatePage(parent, controlloreFinestre, controlloreDAO, operatoreLoggato);
        setImpostazioniTabella();
        setImpostazioniToolBar();
        setImpostazioniUserInformationButton();
        setImpostazioniLogoutButton();
        setImpostazioniVisive();
        setImpostazioniBottoni();
        setImpostazioniVarie();

        listeners();
    }

    private void setImpostazioniSpedizioniProgrammatePage(JFrame parent, ControlloreFinestre controlloreFinestre, ControlloreDAO controlloreDAO, OperatoreDTO operatoreLoggato){
        setIconImage(imageIcon.getImage());
        setLayout(null);
        setResizable(true);
        setExtendedState(MAXIMIZED_BOTH);
        this.controlloreDAO = controlloreDAO;
        this.controlloreFinestre = controlloreFinestre;
        this.operatoreLoggato = operatoreLoggato;
        setTitle("Crea Spedizioni Programmate");
        setContentPane(spedizioniProgrammatePanel);
        setMinimumSize(new Dimension(1550, 430));
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

    }

    private void setImpostazioniTabella(){
        ArrayList<DettagliSpedizioneDTO> listaSpedizioniProgrammate = controlloreDAO.getSpedizioniProgrammate();
        DefaultTableModel modelloTabella = getModelloTabella();

        spedizioniTable.setModel(modelloTabella);
        spedizioniTable.getTableHeader().setBackground(new Color(0, 18, 51));
        spedizioniTable.getTableHeader().setForeground(new Color (253, 253, 253));
        spedizioniTable.getTableHeader().setReorderingAllowed(false);
        spedizioniTable.getTableHeader().setResizingAllowed(false);

        aggiungiElementiATabella(listaSpedizioniProgrammate);

        DefaultTableCellRenderer modelloCelle = new DefaultTableCellRenderer();
        modelloCelle.setHorizontalAlignment(SwingConstants.CENTER);
        spedizioniTable.getColumnModel().getColumn(1).setCellRenderer(modelloCelle);
        spedizioniTable.getColumnModel().getColumn(2).setCellRenderer(modelloCelle);
        spedizioniTable.getColumnModel().getColumn(6).setCellRenderer(modelloCelle);
    }

    private static DefaultTableModel getModelloTabella() {
        Object[] nomiColonne = {"Seleziona", "Numero Tracciamento", "Data Prevista", "Mittente", "Destinatario", "Indirizzo di Spedizione", "Tipo Spedizione"};
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
                    case 1, 3, 4, 5, 6 -> String.class;
                    default -> null;
                };
            }
        };
        return modelloTabella;
    }

    public void setImpostazioniToolBar() {

        resetButton.setIcon(new ImageIcon("src/main/java/org/UninaDelivery/Icon/delete.png"));
        aggiornaButton.setIcon(new ImageIcon("src/main/java/org/UninaDelivery/Icon/refresh.png"));
        selezionaTuttoButton.setIcon(new ImageIcon("src/main/java/org/UninaDelivery/Icon/selezionaTutto.png"));

        toolBar.add(pickerDataInizio);

        dataFineLabel = new JLabel();
        dataFineLabel.setText("  Data Fine: ");
        dataFineLabel.setForeground(Color.WHITE);
        dataFineLabel.setFont(new Font("JetBrains Mono Medium",Font.BOLD,14));

        toolBar.add(dataFineLabel);
        toolBar.add(pickerDataFine);
        toolBar.add(aggiornaButton);

        pickerDataInizio.setToolTipText("Data dalla quale si inizierà a cercare");
        pickerDataInizio.setPreferredSize(new Dimension(150, 23));
        pickerDataInizio.setMinimumSize(new Dimension(150, 23));
        pickerDataInizio.setMaximumSize(new Dimension(150, 23));
        pickerDataFine.setToolTipText("Data dalla quale si finirà di cercare");
        pickerDataFine.setPreferredSize(new Dimension(150, 23));
        pickerDataFine.setMinimumSize(new Dimension(150, 23));
        pickerDataFine.setMaximumSize(new Dimension(150, 23));

        ArrayList<ClienteDTO> listaClienti = controlloreDAO.recuperaClienti();
        filtroUtenti.addItem("<Filtra Utente>");
        for (ClienteDTO clienteDTO : listaClienti) {
            filtroUtenti.addItem(clienteDTO.getNominativo() + " " + clienteDTO.getNumeroTelefono());
        }
        filtroUtenti.setPreferredSize(new Dimension(300, 30));
        filtroUtenti.setMinimumSize(new Dimension(300, 30));
        filtroUtenti.setMaximumSize(new Dimension(300, 30));
        AutoCompleteDecorator.decorate(filtroUtenti);
        toolBar.setFloatable(false);
    }

    private void setImpostazioniVarie(){
        nomeLabel.setText(operatoreLoggato.getNome());
        cognomeLabel.setText(operatoreLoggato.getCognome());
        matricolaLabel.setText(String.valueOf(operatoreLoggato.getMatricola()));
        panelContenenteJTable.getViewport().setBackground(new Color(202, 192, 179));
    }

    private void setImpostazioniVisive(){
        logoLabel.setIcon(new ImageIcon("src/main/java/org/UninaDelivery/Icon/logoSenzaScrittePiccolo.png"));
        ordiniNonSpeditiLabel.setIcon((new ImageIcon("src/main/java/org/UninaDelivery/Icon/order.png")));
    }

    private void setImpostazioniBottoni(){
        logOutButton.setFocusable(false);
    }

    private void setImpostazioniUserInformationButton(){
        infoUtenteButton.setIcon(new ImageIcon("src/main/java/org/UninaDelivery/Icon/user.png"));
        infoUtenteButton.setMargin(new Insets(0, 0, 0, 0));
        infoUtenteButton.setOpaque(false);
        infoUtenteButton.setBorderPainted(false);
        infoUtenteButton.setBorder(null);
        infoUtenteButton.setContentAreaFilled(false);
        infoUtenteButton.setFocusable(false);
    }

    private void setImpostazioniLogoutButton(){
        logOutButton.setIcon(new ImageIcon("src/main/java/org/UninaDelivery/Icon/logOut.png"));
        logOutButton.setMargin(new Insets(0, 0, 0, 0));
        logOutButton.setOpaque(false);
        logOutButton.setBorderPainted(false);
        logOutButton.setBorder(null);
        logOutButton.setContentAreaFilled(false);
    }

    private void aggiungiElementiATabella(ArrayList<DettagliSpedizioneDTO> listaAggiornata){
        DefaultTableModel model = (DefaultTableModel) spedizioniTable.getModel();
        model.setRowCount(0);

        for (DettagliSpedizioneDTO spedizioneDTO : listaAggiornata){
            model.addRow(new Object[]{Boolean.FALSE, spedizioneDTO.getNumeroTracciamento(),spedizioneDTO.getDataPrevista(), spedizioneDTO.getMittente(),
                    spedizioneDTO.getDestinatario(), spedizioneDTO.getIndirizzo(), spedizioneDTO.getTipoSpedizione()});
        }
        controlloreFinestre.resizeColumnWidth(spedizioniTable);
    }

    private void listeners(){
        logOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object[] Opzioni = {"Si", "No"};
                if (JOptionPane.showOptionDialog(SpedizioniProgrammatePage.this, "Vuoi eseguire il LogOut?",
                        "LogOut", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, Opzioni,
                        Opzioni[0]) == JOptionPane.OK_OPTION) {
                    controlloreFinestre.tornaLogin(SpedizioniProgrammatePage.this);
                }
            }
        });

        infoUtenteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controlloreFinestre.apriInfoOperatore(operatoreLoggato);
            }
        });

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                SpedizioniProgrammatePage.this.dispose();
                controlloreFinestre.apriHome(operatoreLoggato, SpedizioniProgrammatePage.this);
            }
        });

        indietroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SpedizioniProgrammatePage.this.dispose();
                controlloreFinestre.apriHome(operatoreLoggato, SpedizioniProgrammatePage.this);
            }
        });
        
        annullaProgrammazioneButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if(isSelezioneValida() && mostraMessageDialogDiAvvertimento()) {
                        annullaSpedizioniProgrammate();
                    }
                } catch (NoCampiSelezionatiException ex) {
                    System.out.println("nessuna checkBox selezionata: " + ex);
                }
            }
        });
    }

    private boolean mostraMessageDialogDiAvvertimento(){
        Object[] Opzioni = {"Si", "No"};

        return JOptionPane.showOptionDialog(SpedizioniProgrammatePage.this, "Vuoi annullare tutte le programmazioni selezionate?",
                "Attenzione", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, Opzioni,
                Opzioni[0]) == JOptionPane.OK_OPTION;
    }

    private void annullaSpedizioniProgrammate(){
        controlloreDAO.aggiornaSpedizioniProgrammate(getSpedizioniSelezionateDaTabella(), "Singola");
        controlloreFinestre.mostraMessageDialog(SpedizioniProgrammatePage.this, "Tutte le spedizioni annullate con successo!", "Avviso");
        ArrayList<DettagliSpedizioneDTO> listaSpedizioniProgrammate = controlloreDAO.getSpedizioniProgrammate();
        aggiungiElementiATabella(listaSpedizioniProgrammate);
    }

    private int controllaQuanteFlagTabella(){
        int numeroFlag = 0;
        for (int riga = 0; riga < spedizioniTable.getRowCount(); riga++){
            if (isCellaSelezionata(riga))
                numeroFlag++;
        }
        return numeroFlag;
    }

    private boolean isCellaSelezionata(int riga){
        return (Boolean) spedizioniTable.getValueAt(riga, 0);
    }

    public ArrayList<DettagliSpedizioneDTO> getSpedizioniSelezionateDaTabella(){
        ArrayList<DettagliSpedizioneDTO> listaOrdiniSelezionati = new ArrayList<>();
        for (int riga = 0; riga < spedizioniTable.getRowCount(); riga++){
            if(isCellaSelezionata(riga))
                listaOrdiniSelezionati.add(recuperaContenutoRiga(riga));
        }
        return listaOrdiniSelezionati;
    }

    private DettagliSpedizioneDTO recuperaContenutoRiga(int riga){
        DettagliSpedizioneDTO dettagliSpedizioneDTO = new DettagliSpedizioneDTO();
        dettagliSpedizioneDTO.setNumeroTracciamento((int) spedizioniTable.getValueAt(riga, 1));
        dettagliSpedizioneDTO.setDataPrevista((LocalDate) spedizioniTable.getValueAt(riga, 2));
        dettagliSpedizioneDTO.setMittente((String) spedizioniTable.getValueAt(riga, 3));
        dettagliSpedizioneDTO.setDestinatario((String) spedizioniTable.getValueAt(riga, 4));
        dettagliSpedizioneDTO.setIndirizzo((String) spedizioniTable.getValueAt(riga, 5));
        dettagliSpedizioneDTO.setTipoSpedizione((String) spedizioniTable.getValueAt(riga, 6));
        return dettagliSpedizioneDTO;
    }

    private boolean isSelezioneValida() throws NoCampiSelezionatiException {
        if (controllaQuanteFlagTabella() == 0) {
            throw new NoCampiSelezionatiException(this, controlloreFinestre);
        }
        return true;
    }
}