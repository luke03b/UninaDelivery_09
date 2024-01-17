package org.UninaDelivery;

import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;
import org.UninaDelivery.Cliente.ClienteDTO;
import org.UninaDelivery.Controllori.ControlloreDAO;
import org.UninaDelivery.Controllori.ControlloreFinestre;
import org.UninaDelivery.Exception.FiltroNonValidoException;
import org.UninaDelivery.Exception.NoCampiSelezionatiException;
import org.UninaDelivery.Exception.TroppiCampiSelezionatiException;
import org.UninaDelivery.Operatore.OperatoreDTO;
import org.UninaDelivery.Ordine.DettagliOrdineDTO;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class HomePage extends JFrame {
    private JPanel homePanel;
    private JLabel logoLabel;
    private JLabel matricolaLabel;
    private JLabel dataInizioLabel;
    private JLabel dataFineLabel;
    private JButton logoutButton;
    private JButton creaSpedizioneButton;
    private JButton programmaButton;
    private JButton statisticaButton;
    private JTable ordiniTable;
    private JButton infoUtenteButton;
    private JLabel nomeLabel;
    private JLabel cognomeLabel;
    private JScrollPane panelContenenteJTable;
    private JToolBar toolBar;
    private JComboBox filtroUtenti;
    private JButton aggiornaButton;
    private JButton dettagliOrdineButton;
    private JButton resetButton;
    private JButton selezionaTuttoButton;
    private JLabel ordiniNonSpeditiLabel;
    private ControlloreFinestre controlloreFinestre;
    private ControlloreDAO controlloreDAO;
    private OperatoreDTO operatoreLoggato;

    private ImageIcon imageIcon = new ImageIcon("src/main/java/org/UninaDelivery/Icon/logoSenzaScritte.png");

    private UtilDateModel modelDataInizio = new UtilDateModel();
    private JDatePanelImpl datePanelDataInizio = new JDatePanelImpl(modelDataInizio);
    private JDatePickerImpl pickerDataInizio = new JDatePickerImpl(datePanelDataInizio);
    private UtilDateModel modelDataFine = new UtilDateModel();
    private JDatePanelImpl datePanelDataFine = new JDatePanelImpl(modelDataFine);
    private JDatePickerImpl pickerDataFine = new JDatePickerImpl(datePanelDataFine);


    public HomePage(JFrame parent, ControlloreFinestre controlloreFinestre, ControlloreDAO controlloreDAO, OperatoreDTO operatoreLoggato) {
        setImpostazioniHomePage(parent, controlloreFinestre, controlloreDAO, operatoreLoggato);
        setImpostazioniTabella();
        setImpostazioniToolBar();
        setImpostazioniInfoUtenteButton();
        setImpostazioniLogoutButton();
        setImpostazioniVisive();
        setImpostazioniBottoni();
        setImpostazioniVarie();

        listeners();
    }

    private void setImpostazioniHomePage(JFrame parent, ControlloreFinestre controlloreFinestre, ControlloreDAO controlloreDAO, OperatoreDTO operatoreLoggato) {
        setIconImage(imageIcon.getImage());
        setLayout(null);
        setResizable(true);
        setExtendedState(MAXIMIZED_BOTH);
        this.controlloreDAO = controlloreDAO;
        this.controlloreFinestre = controlloreFinestre;
        this.operatoreLoggato = operatoreLoggato;
        setTitle("Home");
        setContentPane(homePanel);
        setMinimumSize(new Dimension(1950, 530));
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

    }

    private void setImpostazioniTabella() {
        ArrayList<DettagliOrdineDTO> listaOrdini = controlloreDAO.getOrdiniNonSpediti();
        DefaultTableModel modelloTabella = getModelloTabella();

        ordiniTable.setModel(modelloTabella);
        ordiniTable.getTableHeader().setBackground(new Color(0, 18, 51));
        ordiniTable.getTableHeader().setForeground(new Color(253, 253, 253));
        ordiniTable.getTableHeader().setReorderingAllowed(false);
        ordiniTable.getTableHeader().setResizingAllowed(false);

        aggiungiElementiTabella(listaOrdini);

        DefaultTableCellRenderer modelloCelle = new DefaultTableCellRenderer();
        modelloCelle.setHorizontalAlignment(SwingConstants.CENTER);
        ordiniTable.getColumnModel().getColumn(1).setCellRenderer(modelloCelle);
        ordiniTable.getColumnModel().getColumn(2).setCellRenderer(modelloCelle);
        ordiniTable.getColumnModel().getColumn(6).setCellRenderer(modelloCelle);
        ordiniTable.getColumnModel().getColumn(7).setCellRenderer(modelloCelle);
    }

    private static DefaultTableModel getModelloTabella() {
        Object[] nomiColonne = {"Seleziona", "Numero Ordine", "Data", "Mittente", "Destinatario", "Indirizzo di Spedizione", "Peso (Kg)", "Grandezza"};
        DefaultTableModel modelloTabella = new DefaultTableModel(new Object[][]{}, nomiColonne) {
            //rende solo la prima colonna della tabella editabile
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 0;
            }

            @Override
            public Class getColumnClass(int column) {
                return switch (column) {
                    case 0 -> Boolean.class;
                    case 2 -> LocalDate.class;
                    case 1, 3, 4, 5, 7 -> String.class;
                    case 6 -> Float.class;
                    default -> null;
                };
            }
        };
        return modelloTabella;
    }

    private void setImpostazioniVarie() {
        nomeLabel.setText(operatoreLoggato.getNome());
        cognomeLabel.setText(operatoreLoggato.getCognome());
        matricolaLabel.setText(String.valueOf(operatoreLoggato.getMatricola()));
        panelContenenteJTable.getViewport().setBackground(new Color(202, 192, 179));
    }

    private void setImpostazioniVisive() {
        logoLabel.setIcon(new ImageIcon("src/main/java/org/UninaDelivery/Icon/logoSenzaScrittePiccolo.png"));
        statisticaButton.setIcon(new ImageIcon("src/main/java/org/UninaDelivery/Icon/data-analytics.png"));
        programmaButton.setIcon(new ImageIcon("src/main/java/org/UninaDelivery/Icon/schedule.png"));
        creaSpedizioneButton.setIcon(new ImageIcon("src/main/java/org/UninaDelivery/Icon/tracking.png"));
        dettagliOrdineButton.setIcon(new ImageIcon("src/main/java/org/UninaDelivery/Icon/find.png"));
        ordiniNonSpeditiLabel.setIcon((new ImageIcon("src/main/java/org/UninaDelivery/Icon/order.png")));
    }

    private void setImpostazioniBottoni() {
        statisticaButton.setFocusable(false);
        programmaButton.setFocusable(false);
        dettagliOrdineButton.setFocusable(false);
        creaSpedizioneButton.setFocusable(false);
        aggiornaButton.setFocusable(false);
        creaSpedizioneButton.setOpaque(true);
        selezionaTuttoButton.setFocusable(false);
        resetButton.setFocusable(false);
        logoutButton.setFocusable(false);
    }

    private void setImpostazioniInfoUtenteButton() {
        infoUtenteButton.setIcon(new ImageIcon("src/main/java/org/UninaDelivery/Icon/user.png"));
        infoUtenteButton.setMargin(new Insets(0, 0, 0, 0));
        infoUtenteButton.setOpaque(false);
        infoUtenteButton.setBorderPainted(false);
        infoUtenteButton.setBorder(null);
        infoUtenteButton.setContentAreaFilled(false);
        infoUtenteButton.setFocusable(false);
    }

    private void setImpostazioniLogoutButton() {
        logoutButton.setIcon(new ImageIcon("src/main/java/org/UninaDelivery/Icon/logOut.png"));
        logoutButton.setMargin(new Insets(0, 0, 0, 0));
        logoutButton.setOpaque(false);
        logoutButton.setBorderPainted(false);
        logoutButton.setBorder(null);
        logoutButton.setContentAreaFilled(false);
    }

    public void setImpostazioniToolBar() {

        resetButton.setIcon(new ImageIcon("src/main/java/org/UninaDelivery/Icon/delete.png"));
        aggiornaButton.setIcon(new ImageIcon("src/main/java/org/UninaDelivery/Icon/refresh.png"));
        selezionaTuttoButton.setIcon(new ImageIcon("src/main/java/org/UninaDelivery/Icon/selezionaTutto.png"));

        toolBar.add(pickerDataInizio);

        dataFineLabel = new JLabel();
        dataFineLabel.setText("  Data Fine: ");
        dataFineLabel.setForeground(Color.WHITE);
        dataFineLabel.setFont(new Font("JetBrains Mono Medium", Font.BOLD, 14));

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

    private void listeners() {
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object[] Opzioni = {"Si", "No"};
                if (JOptionPane.showOptionDialog(HomePage.this, "Vuoi eseguire il LogOut?",
                        "LogOut", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, Opzioni,
                        Opzioni[0]) == JOptionPane.OK_OPTION) {
                    controlloreFinestre.tornaLogin(HomePage.this);
                }
            }
        });

        statisticaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controlloreFinestre.apriStatistica();
            }
        });

        infoUtenteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controlloreFinestre.apriInfoOperatore(operatoreLoggato);
            }
        });

        aggiornaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                aggiornaTabella();
            }
        });

        dettagliOrdineButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    selezioneValida();
                } catch (NoCampiSelezionatiException ex) {
                    System.out.println("Nessuna checkBox selezionata: " + ex);
                } catch (TroppiCampiSelezionatiException ex) {
                    System.out.println("Più di una checkBox selezionata: " + ex);
                }
            }
        });

        creaSpedizioneButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (getNumeroCelleSelezionateTabella() == 0)
                        throw new NoCampiSelezionatiException(HomePage.this, controlloreFinestre);
                    controlloreFinestre.apriWizardCreazioneSpedizione(HomePage.this, getOrdiniSelezionatiDaTabella(), operatoreLoggato.getMatricola());
                } catch (NoCampiSelezionatiException exception) {
                    System.out.println("Nessuna checkBox selezionata: " + exception);
                }
            }
        });

        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resettaFiltri();
                aggiornaTabella();
            }
        });

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    controlloreDAO.chiudiConnessioneDB();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        selezionaTuttoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selezionaTutteCelleTabella();
            }
        });

        programmaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controlloreFinestre.apriSpedizioniProgrammatePage(HomePage.this, operatoreLoggato);
            }
        });
    }

    private void selezionaTutteCelleTabella() {
        for (int riga = 0; riga < ordiniTable.getRowCount(); riga++) {
            ordiniTable.setValueAt(true, riga, 0);
        }
    }

    private void resettaFiltri() {
        filtroUtenti.setSelectedIndex(0);
        pickerDataInizio.getJFormattedTextField().setText("");
        datePanelDataInizio.getModel().setSelected(false);
        pickerDataFine.getJFormattedTextField().setText("");
        datePanelDataFine.getModel().setSelected(false);
    }

    private void selezioneValida() throws NoCampiSelezionatiException, TroppiCampiSelezionatiException {
        switch (getNumeroCelleSelezionateTabella()) {
            case 0:
                throw new NoCampiSelezionatiException(this, controlloreFinestre);
            case 1:
                mostraDettagliOrdine();
                break;
            default:
                throw new TroppiCampiSelezionatiException(this, controlloreFinestre);
        }
    }

    public void aggiornaTabella() {
        java.util.Date dataInizio = (java.util.Date) pickerDataInizio.getModel().getValue();
        java.util.Date dataFine = (java.util.Date) pickerDataFine.getModel().getValue();
        String utenteSelezionato = filtroUtenti.getSelectedItem().toString();
        utenteSelezionato = utenteSelezionato.replaceAll("[^0-9]", "");

        try {
            setFiltro(utenteSelezionato, dataInizio, dataFine);
        } catch (FiltroNonValidoException exception) {
            System.out.println("Filtro non valido: " + exception);
        }
    }

    private void setFiltro(String utenteSelezionato, java.util.Date dataInizio, java.util.Date dataFine) throws FiltroNonValidoException {
        if (!utenteSelezionato.isEmpty() && dataInizio != null && dataFine != null) {
            ArrayList<DettagliOrdineDTO> listaOrdini = controlloreDAO.getOrdiniByUtenteAndData(utenteSelezionato, new java.sql.Date(dataInizio.getTime()), new java.sql.Date(dataFine.getTime()));
            aggiungiElementiTabella(listaOrdini);
            return;
        }
        if (dataInizio == null ^ dataFine == null) {
            throw new FiltroNonValidoException(this, controlloreFinestre);
        }
        if (!utenteSelezionato.isEmpty()) {
            ArrayList<DettagliOrdineDTO> listaOrdini = controlloreDAO.getOrdiniByUtente(utenteSelezionato);
            aggiungiElementiTabella(listaOrdini);
            return;
        }
        if (dataInizio != null && dataFine != null) {
            ArrayList<DettagliOrdineDTO> listaOrdini = controlloreDAO.getOrdiniByData(new java.sql.Date(dataInizio.getTime()), new java.sql.Date(dataFine.getTime()));
            aggiungiElementiTabella(listaOrdini);
            return;
        }
        ArrayList<DettagliOrdineDTO> listaOrdini = controlloreDAO.getOrdiniNonSpediti();
        aggiungiElementiTabella(listaOrdini);
    }

    private void aggiungiElementiTabella(ArrayList<DettagliOrdineDTO> listaAggiornata) {
        DefaultTableModel model = (DefaultTableModel) ordiniTable.getModel();
        model.setRowCount(0);

        for (DettagliOrdineDTO ordineDTO : listaAggiornata) {
            model.addRow(new Object[]{Boolean.FALSE, ordineDTO.getNumeroOrdine(), ordineDTO.getDataOrdine(), ordineDTO.getMittente(),
                    ordineDTO.getDestinatario(), ordineDTO.getIndirizzo(),
                    ordineDTO.getPeso(), ordineDTO.getGrandezza()});
        }
        controlloreFinestre.resizeColumnWidth(ordiniTable);
    }

    private int getNumeroCelleSelezionateTabella() {
        int celleSelezionate = 0;
        for (int riga = 0; riga < ordiniTable.getRowCount(); riga++) {
            if (isCellaSelezionata(riga))
                celleSelezionate++;
        }
        return celleSelezionate;
    }

    private ArrayList<DettagliOrdineDTO> getOrdiniSelezionatiDaTabella() {
        ArrayList<DettagliOrdineDTO> listaOrdiniSelezionati = new ArrayList<>();
        for (int riga = 0; riga < ordiniTable.getRowCount(); riga++) {
            if (isCellaSelezionata(riga))
                listaOrdiniSelezionati.add(getContenutoRiga(riga));
        }
        return listaOrdiniSelezionati;
    }

    private boolean isCellaSelezionata(int riga) {
        return (Boolean) ordiniTable.getValueAt(riga, 0);
    }

    private DettagliOrdineDTO getContenutoRiga(int riga) {
        DettagliOrdineDTO ordineCorrente = new DettagliOrdineDTO();
        ordineCorrente.setNumeroOrdine((int) ordiniTable.getValueAt(riga, 1));
        ordineCorrente.setMittente((String) ordiniTable.getValueAt(riga, 3));
        ordineCorrente.setDestinatario((String) ordiniTable.getValueAt(riga, 4));
        ordineCorrente.setIndirizzo((String) ordiniTable.getValueAt(riga, 5));
        ordineCorrente.setPeso((float) ordiniTable.getValueAt(riga, 6));
        ordineCorrente.setGrandezza((String) ordiniTable.getValueAt(riga, 7));
        return ordineCorrente;
    }

    private void mostraDettagliOrdine() {
        for (int i = 0; i < ordiniTable.getRowCount(); i++) {
            if (isCellaSelezionata(i)) {
                controlloreFinestre.apriInfoOrdine((int) ordiniTable.getValueAt(i, 1));
            }
        }
    }
}