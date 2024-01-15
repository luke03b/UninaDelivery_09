package org.UninaDelivery;

import org.UninaDelivery.Controllori.ControlloreDAO;
import org.UninaDelivery.Controllori.ControlloreFinestre;
import org.UninaDelivery.Corriere.CorriereDTO;
import org.UninaDelivery.Exception.NoCampiSelezionatiException;
import org.UninaDelivery.Exception.TroppiCampiSelezionatiException;
import org.UninaDelivery.MezzoTrasporto.MezzoTrasportoDTO;
import org.UninaDelivery.Ordine.DettagliOrdineDTO;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class WizardCreazioneSpedizione extends JDialog{
    private JPanel cards;
    private JPanel paginaSceltaCorriere;
    private JPanel paginaSceltaMezzo;
    private JPanel paginaRiepilogo;
    private JButton avantiButton1;
    private JButton avantiButton2;
    private JButton annullaButton;
    private JButton indietroButton2;
    private JButton indietroButton4;
    private JTable tabellaCorrieri;
    private JScrollPane panelContenenteTableCorrieri;
    private JLabel iconaSceltaCorriere;
    private JLabel iconaMezzo;
    private JLabel iconaRiepilogo;
    private JLabel iconaSceltaCorriere2;
    private JLabel iconaMezzo2;
    private JLabel iconaRiepilogo2;
    private JLabel iconaSceltaCorriere4;
    private JLabel iconaRiepilogo4;
    private JLabel iconaMezzo4;
    private JButton indietroButton3;
    private JScrollPane panelContenenteTableMezzi;
    private JButton confermaButton;
    private JTable tabellaMezzi;
    private JTable riepilogoOrdiniTable;
    private JLabel matricolaLabel;
    private JLabel nomeLabel;
    private JLabel cognomeLabel;
    private JLabel targaLabel;
    private JLabel marcaLabel;
    private JLabel modelloLabel;
    private JScrollPane panelContenenteTableOrdini;
    private JProgressBar pesoMezzoProgressBar;
    private JLabel iconaTipoSpedizione4;
    private JLabel iconaTipoSpedizione1;
    private JLabel iconaTipoSpedizione2;
    private JPanel paginaTipoSpedizione;
    private JLabel iconaSceltaCorriere3;
    private JLabel iconaMezzo3;
    private JLabel iconaRiepilogo3;
    private JLabel iconaTipoSpedizione3;
    private JButton avantiButton3;
    private JRadioButton radioButtonMensile;
    private JRadioButton radioButtonAnnuale;
    private JRadioButton radioButtonSettimanale;
    private JRadioButton radioButtonSingola;
    private ControlloreFinestre controlloreFinestre;
    private ControlloreDAO controlloreDAO;
    private ArrayList<DettagliOrdineDTO> listaOrdiniSelezionati;
    ImageIcon imageIcon = new ImageIcon("src/main/java/org/UninaDelivery/Icon/logoSenzaScritte.png");
    CardLayout cardLayout = (CardLayout) cards.getLayout();
    int matricolaOperatoreLoggato;
    private HomePage parent;
    
    public WizardCreazioneSpedizione(HomePage parent, ControlloreFinestre controlloreFinestre, ControlloreDAO controlloreDAO,  ArrayList<DettagliOrdineDTO> listaOrdiniSelezionati, int matricolaOperatoreLoggato){
        setImpostazioniWizardPage(parent, controlloreFinestre, controlloreDAO, listaOrdiniSelezionati, matricolaOperatoreLoggato);
        setImpostazioniAnnullaButton();
        setImpostazioniAvantiButton();
        setImpostazioniIndietroButton();
        setImpostazioniConfermaButton();
        setImpostazioniVarie();
        setImpostazioniIcone();
        setImpostazioniTabellaCorrieri();
        setImpostazioniTabellaMezzi();
        setImpostazioniTabellaRiepilogoOrdini();

        listeners();


    }
    
    private void setImpostazioniWizardPage(HomePage parent, ControlloreFinestre controlloreFinestre, ControlloreDAO controlloreDAO, ArrayList<DettagliOrdineDTO> listaOrdiniSelezionati, int matricolaOperatoreLoggato){
        this.listaOrdiniSelezionati = listaOrdiniSelezionati;
        this.matricolaOperatoreLoggato = matricolaOperatoreLoggato;
        setIconImage(imageIcon.getImage());
        setLayout(null);
        setResizable(true);
        this.controlloreFinestre = controlloreFinestre;
        this.controlloreDAO = controlloreDAO;
        this.parent = parent;
        setTitle("Creazione Spedizione");
        setContentPane(cards);
        setMinimumSize(new Dimension(1000, 400));
        setResizable(false);
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        cards.add(paginaSceltaCorriere, "Scelta Corriere");
        cards.add(paginaSceltaMezzo, "Scelta Mezzo Trasporto");
        cards.add(paginaTipoSpedizione, "Scelta Tipo Spedizione");
        cards.add(paginaRiepilogo, "Riepilogo");

        ButtonGroup group = new ButtonGroup();
        group.add(radioButtonSingola);
        group.add(radioButtonSettimanale);
        group.add(radioButtonMensile);
        group.add(radioButtonAnnuale);
    }

    public void setImpostazioniVarie() {
        panelContenenteTableCorrieri.getViewport().setBackground(new Color(202, 192, 179));
        panelContenenteTableMezzi.getViewport().setBackground(new Color(202, 192, 179));
        panelContenenteTableOrdini.getViewport().setBackground(new Color(202, 192, 179));
    }

    public void setImpostazioniIcone(){
        iconaSceltaCorriere.setIcon(new ImageIcon("src/main/java/org/UninaDelivery/Icon/sceltaCorriereNero.png"));
        iconaSceltaCorriere2.setIcon(new ImageIcon("src/main/java/org/UninaDelivery/Icon/sceltaCorriereRosso.png"));
        iconaSceltaCorriere3.setIcon(new ImageIcon("src/main/java/org/UninaDelivery/Icon/sceltaCorriereRosso.png"));
        iconaSceltaCorriere4.setIcon(new ImageIcon("src/main/java/org/UninaDelivery/Icon/sceltaCorriereRosso.png"));

        iconaMezzo.setIcon(new ImageIcon("src/main/java/org/UninaDelivery/Icon/sceltaMezzoRosso.png"));
        iconaMezzo2.setIcon(new ImageIcon("src/main/java/org/UninaDelivery/Icon/sceltaMezzoNero.png"));
        iconaMezzo3.setIcon(new ImageIcon("src/main/java/org/UninaDelivery/Icon/sceltaMezzoRosso.png"));
        iconaMezzo4.setIcon(new ImageIcon("src/main/java/org/UninaDelivery/Icon/sceltaMezzoRosso.png"));

        iconaRiepilogo.setIcon(new ImageIcon("src/main/java/org/UninaDelivery/Icon/RiepilogoRosso.png"));
        iconaRiepilogo2.setIcon(new ImageIcon("src/main/java/org/UninaDelivery/Icon/RiepilogoRosso.png"));
        iconaRiepilogo3.setIcon(new ImageIcon("src/main/java/org/UninaDelivery/Icon/RiepilogoRosso.png"));
        iconaRiepilogo4.setIcon(new ImageIcon("src/main/java/org/UninaDelivery/Icon/RiepilogoNero.png"));

        iconaTipoSpedizione1.setIcon(new ImageIcon("src/main/java/org/UninaDelivery/Icon/clockRosso.png"));
        iconaTipoSpedizione2.setIcon(new ImageIcon("src/main/java/org/UninaDelivery/Icon/clockRosso.png"));
        iconaTipoSpedizione3.setIcon(new ImageIcon("src/main/java/org/UninaDelivery/Icon/clockNero.png"));
        iconaTipoSpedizione4.setIcon(new ImageIcon("src/main/java/org/UninaDelivery/Icon/clockRosso.png"));
    }

    public void setImpostazioniIndietroButton() {
        indietroButton2.setIcon(new ImageIcon("src/main/java/org/UninaDelivery/Icon/Indietro.png"));
        indietroButton2.setMargin(new Insets(0, 0, 0, 0));
        indietroButton2.setOpaque(false);
        indietroButton2.setBorderPainted(false);
        indietroButton2.setContentAreaFilled(false);
        indietroButton2.setFocusable(false);

        indietroButton3.setIcon(new ImageIcon("src/main/java/org/UninaDelivery/Icon/Indietro.png"));
        indietroButton3.setMargin(new Insets(0, 0, 0, 0));
        indietroButton3.setOpaque(false);
        indietroButton3.setBorderPainted(false);
        indietroButton3.setContentAreaFilled(false);
        indietroButton3.setFocusable(false);

        indietroButton4.setIcon(new ImageIcon("src/main/java/org/UninaDelivery/Icon/Indietro.png"));
        indietroButton4.setMargin(new Insets(0, 0, 0, 0));
        indietroButton4.setOpaque(false);
        indietroButton4.setBorderPainted(false);
        indietroButton4.setContentAreaFilled(false);
        indietroButton4.setFocusable(false);
    }

    private void setImpostazioniAnnullaButton() {
        annullaButton.setIcon(new ImageIcon("src/main/java/org/UninaDelivery/Icon/annulla.png"));
        annullaButton.setMargin(new Insets(0, 0, 0, 0));
        annullaButton.setOpaque(false);
        annullaButton.setBorderPainted(false);
        annullaButton.setContentAreaFilled(false);
        annullaButton.setFocusable(false);
    }

    private void setImpostazioniAvantiButton() {
        avantiButton1.setIcon(new ImageIcon("src/main/java/org/UninaDelivery/Icon/avanti.png"));
        avantiButton1.setMargin(new Insets(0, 0, 0, 0));
        avantiButton1.setOpaque(false);
        avantiButton1.setBorderPainted(false);
        avantiButton1.setContentAreaFilled(false);
        avantiButton1.setFocusable(false);

        avantiButton2.setIcon(new ImageIcon("src/main/java/org/UninaDelivery/Icon/avanti.png"));
        avantiButton2.setMargin(new Insets(0, 0, 0, 0));
        avantiButton2.setOpaque(false);
        avantiButton2.setBorderPainted(false);
        avantiButton2.setContentAreaFilled(false);
        avantiButton2.setFocusable(false);

        avantiButton3.setIcon(new ImageIcon("src/main/java/org/UninaDelivery/Icon/avanti.png"));
        avantiButton3.setMargin(new Insets(0, 0, 0, 0));
        avantiButton3.setOpaque(false);
        avantiButton3.setBorderPainted(false);
        avantiButton3.setContentAreaFilled(false);
        avantiButton3.setFocusable(false);
    }

    private void setImpostazioniConfermaButton(){
        confermaButton.setIcon(new ImageIcon("src/main/java/org/UninaDelivery/Icon/ConfermaRosso.png"));
        confermaButton.setMargin(new Insets(0, 0, 0, 0));
        confermaButton.setOpaque(false);
        confermaButton.setBorderPainted(false);
        confermaButton.setContentAreaFilled(false);
        confermaButton.setFocusable(false);
    }
    
    private void setImpostazioniTabellaCorrieri(){
        ArrayList<CorriereDTO> listaCorrieriDisponibili = controlloreDAO.recuperaCorrieriDisponibili();
        DefaultTableModel modelloTabella = getModelloTabellaCorrieri();
        setRigheTabellaCorrieri(modelloTabella, listaCorrieriDisponibili);
        
        tabellaCorrieri.setModel(modelloTabella);
        tabellaCorrieri.getTableHeader().setBackground(new Color(0, 18, 51));
        tabellaCorrieri.getTableHeader().setForeground(new Color (253, 253, 253));
        controlloreFinestre.resizeColumnWidth(tabellaCorrieri);
        tabellaCorrieri.getTableHeader().setReorderingAllowed(false);
        tabellaCorrieri.getTableHeader().setResizingAllowed(false);
        
        DefaultTableCellRenderer modelloCelle = new DefaultTableCellRenderer();
        modelloCelle.setHorizontalAlignment(SwingConstants.CENTER);
        tabellaCorrieri.getColumnModel().getColumn(1).setCellRenderer(modelloCelle);
    }
    
    private void setImpostazioniTabellaMezzi(){
        ArrayList<MezzoTrasportoDTO> listaMezziDisponibili = controlloreDAO.recuperaMezziDisponibili(controlloreDAO.calcolaPesoOrdini(listaOrdiniSelezionati));
        DefaultTableModel modelloTabella = getModelloTabellaMezzi();
        setRigheTabellaMezzi(modelloTabella, listaMezziDisponibili);
        
        tabellaMezzi.setModel(modelloTabella);
        tabellaMezzi.getTableHeader().setBackground(new Color(0, 18, 51));
        tabellaMezzi.getTableHeader().setForeground(new Color (253, 253, 253));
        controlloreFinestre.resizeColumnWidth(tabellaMezzi);
        tabellaMezzi.getTableHeader().setReorderingAllowed(false);
        tabellaMezzi.getTableHeader().setResizingAllowed(false);
        
        DefaultTableCellRenderer modelloCelle = new DefaultTableCellRenderer();
        modelloCelle.setHorizontalAlignment(SwingConstants.CENTER);
        tabellaMezzi.getColumnModel().getColumn(1).setCellRenderer(modelloCelle);
    }

    private void setImpostazioniTabellaRiepilogoOrdini(){
        DefaultTableModel modelloTabella = getModelloTabellaOrdiniSelezionati();
        setRigheTabellaOrdiniSelezionati(modelloTabella, listaOrdiniSelezionati);

        riepilogoOrdiniTable.setModel(modelloTabella);
        riepilogoOrdiniTable.getTableHeader().setBackground(new Color(0, 18, 51));
        riepilogoOrdiniTable.getTableHeader().setForeground(new Color (253, 253, 253));
        controlloreFinestre.resizeColumnWidth(riepilogoOrdiniTable);
        riepilogoOrdiniTable.getTableHeader().setReorderingAllowed(false);
        riepilogoOrdiniTable.getTableHeader().setResizingAllowed(false);

        DefaultTableCellRenderer modelloCelle = new DefaultTableCellRenderer();
        modelloCelle.setHorizontalAlignment(SwingConstants.CENTER);
        riepilogoOrdiniTable.getColumnModel().getColumn(1).setCellRenderer(modelloCelle);
    }
    
    private static DefaultTableModel getModelloTabellaCorrieri() {
        Object[] nomiColonne = {"Seleziona", "Matricola", "Nome", "Cognome"};
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
                    case 1 -> int.class;
                    case 2, 3 -> String.class;
                    default -> null;
                };
            }
        };
        return modelloTabella;
    }
    
    private static DefaultTableModel getModelloTabellaMezzi() {
        Object[] nomiColonne = {"Seleziona", "Targa", "Marca", "Modello", "Capacità Trasportabile (L)", "Peso Trasportabile" + "(Kg)"};
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
                    case 1, 2, 3 -> String.class;
                    case 4, 5 -> float.class;
                    default -> null;
                };
            }
        };
        return modelloTabella;
    }

    private static DefaultTableModel getModelloTabellaOrdiniSelezionati() {
        Object[] nomiColonne = {"Numero Ordine", "Mittente", "Destinatario", "Indirizzo", "Peso (Kg)", "Grandezza"};
        DefaultTableModel modelloTabella = new DefaultTableModel(new Object[][]{}, nomiColonne){
            @Override
            public Class getColumnClass(int column){
                return switch (column) {
                    case 0 -> int.class;
                    case 1, 2, 3, 5 -> String.class;
                    case 4 -> float.class;
                    default -> null;
                };
            }
        };
        return modelloTabella;
    }

    private void setRigheTabellaCorrieri(DefaultTableModel modelloTabella, ArrayList<CorriereDTO> listaCorrieriDisponibili){
        for (CorriereDTO corriereDTO : listaCorrieriDisponibili){
            modelloTabella.addRow(new Object[]{Boolean.FALSE, corriereDTO.getMatricola(), corriereDTO.getNome(),
                    corriereDTO.getCognome()});
        }
    }
    
    private void setRigheTabellaMezzi(DefaultTableModel modelloTabella, ArrayList<MezzoTrasportoDTO> listaMezziDisponibili){
        for (MezzoTrasportoDTO mezzoTrasportoDTO : listaMezziDisponibili){
            modelloTabella.addRow(new Object[]{Boolean.FALSE, mezzoTrasportoDTO.getTarga(), mezzoTrasportoDTO.getMarca(),
                    mezzoTrasportoDTO.getModello(), mezzoTrasportoDTO.getCapienzaLitri(), mezzoTrasportoDTO.getCapienzaPeso()});
        }
    }

    private void setRigheTabellaOrdiniSelezionati(DefaultTableModel modelloTabella, ArrayList<DettagliOrdineDTO> listaOrdini){
        for(DettagliOrdineDTO ordineDTO : listaOrdini){
            modelloTabella.addRow(new Object[]{ordineDTO.getNumeroOrdine(), ordineDTO.getMittente(), ordineDTO.getDestinatario(),
                    ordineDTO.getIndirizzo(), ordineDTO.getPeso(), ordineDTO.getGrandezza()});
        }
    }

    private void listeners(){
        avantiButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    isSelezioneValida(tabellaCorrieri);
                } catch (NoCampiSelezionatiException ex) {
                    System.out.println("Nessuna checkBox selezionata: " + ex);
                } catch (TroppiCampiSelezionatiException ex) {
                    System.out.println("Più di una checkBox selezionata: " + ex);
                }
            }
        });

        indietroButton4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { cardLayout.previous(cards);
            }
        });
        annullaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        avantiButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    isSelezioneValida(tabellaMezzi);
                    setContenutiVisiviRiepilogo();
                } catch (NoCampiSelezionatiException ex) {
                    System.out.println("Nessuna checkBox selezionata: " + ex);
                } catch (TroppiCampiSelezionatiException ex) {
                    System.out.println("Più di una checkBox selezionata: " + ex);
                }
            }
        });
        avantiButton3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { cardLayout.next(cards);
            }
        });
        indietroButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { cardLayout.previous(cards);
            }
        });
        indietroButton3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { cardLayout.previous(cards); }
        });
        confermaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<Object> dettagliSpedizione = creaDettagliSpedizione();
                controlloreDAO.creaSpedizioneDaOrdini(WizardCreazioneSpedizione.this, dettagliSpedizione);
                controlloreFinestre.aggiornaTabellaHome(parent);
                dispose();
            }
        });
        
        addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent e)
            {
                confermaChiusura();
            }
        });
        
    }
    
    private ArrayList<Object> creaDettagliSpedizione(){
        ArrayList<Object> dettagliSpedizione = new ArrayList<>();
        dettagliSpedizione.add(0, matricolaOperatoreLoggato);
        dettagliSpedizione.add(1, Integer.parseInt(matricolaLabel.getText()));
        dettagliSpedizione.add(2, targaLabel.getText());
        dettagliSpedizione.add(3, creaListaOrdiniSelezionati());
        dettagliSpedizione.add(4, tipoSpedizioneSelezionato());
        return dettagliSpedizione;
    }
    
    private String tipoSpedizioneSelezionato(){
        if (radioButtonSingola.isSelected())
            return "Singola";
        if (radioButtonSettimanale.isSelected())
            return "Settimanale";
        if (radioButtonMensile.isSelected())
            return "Mensile";
        
        return "Annuale";
    }
    
    private int[] creaListaOrdiniSelezionati(){
        int[] listaCodiciOrdini = new int[listaOrdiniSelezionati.size()];
        int i = 0;
        for (DettagliOrdineDTO dettagliOrdineDTO : listaOrdiniSelezionati){
            listaCodiciOrdini[i] = dettagliOrdineDTO.getNumeroOrdine();
            i++;
        }
        return listaCodiciOrdini;
    }
    
    private int controllaQuanteFlagTabella(JTable tabella){
        int numeroFlag = 0;
        for (int i = 0; i < tabella.getRowCount(); i++){
            if ((Boolean) tabella.getValueAt(i, 0))
                numeroFlag++;
        }
        return numeroFlag;
    }
    
    private void isSelezioneValida(JTable tabella) throws NoCampiSelezionatiException, TroppiCampiSelezionatiException {
        switch (controllaQuanteFlagTabella(tabella)) {
            case 0:
                throw new NoCampiSelezionatiException(this, controlloreFinestre);
            case 1:
                cardLayout.next(cards);
                break;
            default:
                throw new TroppiCampiSelezionatiException(this, controlloreFinestre);
        }
    }
    
    private void setContenutiVisiviRiepilogo(){
        setCorriereSelezionatoLabel();
        setMezzoSelezionatoLabel();
    }

    private void setCorriereSelezionatoLabel(){
        for (int riga = 0; riga < tabellaCorrieri.getRowCount(); riga++){
            if (isCellaCorriereSelezionata(riga)){
                matricolaLabel.setText(String.valueOf((int) tabellaCorrieri.getValueAt(riga, 1)));
                nomeLabel.setText((String) tabellaCorrieri.getValueAt(riga, 2));
                cognomeLabel.setText((String) tabellaCorrieri.getValueAt(riga, 3));
                break;
            }
        }
    }

    private Boolean isCellaCorriereSelezionata(int riga) {
        return (Boolean) tabellaCorrieri.getValueAt(riga, 0);
    }

    private void setMezzoSelezionatoLabel(){
        float pesoTrasportabile;
        for (int riga = 0; riga < tabellaMezzi.getRowCount(); riga++){
            if (isCellaMezzoSelezionata(riga)){
                targaLabel.setText((String) tabellaMezzi.getValueAt(riga, 1));
                marcaLabel.setText((String) tabellaMezzi.getValueAt(riga, 2));
                modelloLabel.setText((String) tabellaMezzi.getValueAt(riga, 3));
                pesoTrasportabile = getInfoCapienzaMezzo(riga);
                setProgressBarRiepilogo(pesoTrasportabile);
                break;
            }
        }
    }

    private Boolean isCellaMezzoSelezionata(int riga) {
        return (Boolean) tabellaMezzi.getValueAt(riga, 0);
    }

    private float getInfoCapienzaMezzo(int riga){
        float pesoTrasportabile;
        pesoTrasportabile = ((float) tabellaMezzi.getValueAt(riga, 5));
        return pesoTrasportabile;
    }

    private void setProgressBarRiepilogo(float pesoTrasportabileMezzo){
        int percentuale = calcolaPercentualeProgressBar(pesoTrasportabileMezzo);
        pesoMezzoProgressBar.setValue(percentuale);
        pesoMezzoProgressBar.setStringPainted(true);
        if(percentuale <= 25)
            pesoMezzoProgressBar.setForeground(new Color(20, 161, 0));
        if(percentuale > 25 && percentuale < 75)
            pesoMezzoProgressBar.setForeground(new Color(206, 93, 0));
        if(percentuale >= 75)
            pesoMezzoProgressBar.setForeground(new Color(255, 89, 90));
    }

    private int calcolaPercentualeProgressBar(float pesoTrasportabileMezzo) {
        float pesoKgTotOrdini;
        int percentuale;
        pesoKgTotOrdini = controlloreDAO.calcolaPesoOrdini(listaOrdiniSelezionati);
        percentuale = (int) ((int) (100 * pesoKgTotOrdini)/pesoTrasportabileMezzo);
        return percentuale;
    }

    private void confermaChiusura(){
        Object[] Opzioni = {"Si", "No"};
        if (JOptionPane.showOptionDialog(this, "Sei sicuro di voler uscire? Le scelte fatte non verranno salvate.",
                "Uscita", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, Opzioni,
                Opzioni[0]) == JOptionPane.OK_OPTION){
            dispose();
        }
    }
}
