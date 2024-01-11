package org.UninaDelivery;

import org.UninaDelivery.Corriere.CorriereDTO;
import org.UninaDelivery.Exception.NoCampiSelezionatiException;
import org.UninaDelivery.Exception.TroppiCampiSelezionatiException;
import org.UninaDelivery.MezzoTrasporto.MezzoTrasportoDTO;

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

public class WizardCreazioneSpedizione extends JDialog{
    private JPanel cards;
    private JPanel paginaSceltaCorriere;
    private JPanel paginaSceltaMezzo;
    private JPanel paginaRiepilogo;
    private JButton avantiButton1;
    private JButton avantiButton2;
    private JButton annullaButton;
    private JButton indietroButton2;
    private JTable tabellaCorrieri;
    private JScrollPane panelContenenteJTable1;
    private JLabel iconaSceltaCorriere;
    private JLabel iconaMezzo;
    private JLabel iconaRiepilogo;
    private JLabel iconaSceltaCorriere2;
    private JLabel iconaMezzo2;
    private JLabel iconaRiepilogo2;
    private JLabel iconaSceltaCorriere3;
    private JLabel iconaRiepilogo3;
    private JLabel iconaMezzo3;
    private JButton indietroButton3;
    private JScrollPane panelContenenteJTable2;
    private JButton confermaButton;
    private JTable tabellaMezzi;
    private JLabel matricolaLabel;
    private JLabel nomeLabel;
    private JLabel cognomeLabel;
    private JLabel targaLabel;
    private JLabel marcaLabel;
    private JLabel modelloLabel;
    private GestoreFinestre gestoreFinestre;
    ImageIcon imageIcon = new ImageIcon("src/main/java/org/UninaDelivery/Icon/logoSenzaScritte.png");
    CardLayout cardLayout = (CardLayout) cards.getLayout();
    
    public WizardCreazioneSpedizione(JFrame parent, GestoreFinestre gestoreFinestre){
        setImpostazioniWizardPage(parent, gestoreFinestre);
        setImpostazioniAnnullaButton();
        setImpostazioniAvantiButton();
        setImpostazioniIndietroButton();
        setImpostazioniConfermaButton();
        setImpostazioniVarie();
        setImpostazioniIcone();
        setImpostazioniTabellaCorrieri();
        setImpostazioniTabellaMezzi();

        Listeners();
    }
    
    private void setImpostazioniWizardPage(JFrame parent, GestoreFinestre gestoreFinestre){
        setIconImage(imageIcon.getImage());
        setLayout(null);
        setResizable(true);
        this.gestoreFinestre = gestoreFinestre;
        setTitle("Creazione Spedizione");
        setContentPane(cards);
        setMinimumSize(new Dimension(800, 400));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        cards.add(paginaSceltaCorriere, "Scelta Corriere");
        cards.add(paginaSceltaMezzo, "Scelta Mezzo Trasporto");
        cards.add(paginaRiepilogo, "Riepilogo");
    }

    public void setImpostazioniVarie() {
        panelContenenteJTable1.getViewport().setBackground(new Color(202, 192, 179));
        panelContenenteJTable2.getViewport().setBackground(new Color(202, 192, 179));
    }

    public void setImpostazioniIcone(){
        iconaSceltaCorriere.setIcon(new ImageIcon("src/main/java/org/UninaDelivery/Icon/sceltaCorriereNero.png"));
        iconaSceltaCorriere2.setIcon(new ImageIcon("src/main/java/org/UninaDelivery/Icon/sceltaCorriereRosso.png"));
        iconaSceltaCorriere3.setIcon(new ImageIcon("src/main/java/org/UninaDelivery/Icon/sceltaCorriereRosso.png"));

        iconaMezzo.setIcon(new ImageIcon("src/main/java/org/UninaDelivery/Icon/sceltaMezzoRosso.png"));
        iconaMezzo2.setIcon(new ImageIcon("src/main/java/org/UninaDelivery/Icon/sceltaMezzoNero.png"));
        iconaMezzo3.setIcon(new ImageIcon("src/main/java/org/UninaDelivery/Icon/sceltaMezzoRosso.png"));

        iconaRiepilogo.setIcon(new ImageIcon("src/main/java/org/UninaDelivery/Icon/RiepilogoRosso.png"));
        iconaRiepilogo2.setIcon(new ImageIcon("src/main/java/org/UninaDelivery/Icon/RiepilogoRosso.png"));
        iconaRiepilogo3.setIcon(new ImageIcon("src/main/java/org/UninaDelivery/Icon/RiepilogoNero.png"));
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
        ArrayList<CorriereDTO> listaCorrieriDisponibili = gestoreFinestre.recuperaCorrieriDisponibili();
        DefaultTableModel modelloTabella = getModelloTabellaCorrieri();
        setRigheTabellaCorrieri(modelloTabella, listaCorrieriDisponibili);
        
        tabellaCorrieri.setModel(modelloTabella);
        tabellaCorrieri.getTableHeader().setBackground(new Color(0, 18, 51));
        tabellaCorrieri.getTableHeader().setForeground(new Color (253, 253, 253));
        gestoreFinestre.resizeColumnWidth(tabellaCorrieri);
        tabellaCorrieri.getTableHeader().setReorderingAllowed(false);
        tabellaCorrieri.getTableHeader().setResizingAllowed(false);
        
        DefaultTableCellRenderer modelloCelle = new DefaultTableCellRenderer();
        modelloCelle.setHorizontalAlignment(SwingConstants.CENTER);
        tabellaCorrieri.getColumnModel().getColumn(1).setCellRenderer(modelloCelle);
    }
    
    private void setImpostazioniTabellaMezzi(){
        ArrayList<MezzoTrasportoDTO> listaMezziDisponibili = gestoreFinestre.recuperaMezziDisponibili();
        DefaultTableModel modelloTabella = getModelloTabellaMezzi();
        setRigheTabellaMezzi(modelloTabella, listaMezziDisponibili);
        
        tabellaMezzi.setModel(modelloTabella);
        tabellaMezzi.getTableHeader().setBackground(new Color(0, 18, 51));
        tabellaMezzi.getTableHeader().setForeground(new Color (253, 253, 253));
        gestoreFinestre.resizeColumnWidth(tabellaMezzi);
        tabellaMezzi.getTableHeader().setReorderingAllowed(false);
        tabellaMezzi.getTableHeader().setResizingAllowed(false);
        
        DefaultTableCellRenderer modelloCelle = new DefaultTableCellRenderer();
        modelloCelle.setHorizontalAlignment(SwingConstants.CENTER);
        tabellaMezzi.getColumnModel().getColumn(1).setCellRenderer(modelloCelle);
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
        Object[] nomiColonne = {"Seleziona", "Targa", "Marca", "Modello"};
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
                    default -> null;
                };
            }
        };
        return modelloTabella;
    }
    
    private void setRigheTabellaCorrieri(DefaultTableModel modelloTabella, ArrayList<CorriereDTO> listaCorrieriDisponibili){
        for (CorriereDTO corriereDTO : listaCorrieriDisponibili){
            modelloTabella.addRow(new Object[]{Boolean.FALSE, corriereDTO.getMatricola(), corriereDTO.getNome(), corriereDTO.getCognome()});
        }
    }
    
    private void setRigheTabellaMezzi(DefaultTableModel modelloTabella, ArrayList<MezzoTrasportoDTO> listaMezziDisponibili){
        for (MezzoTrasportoDTO mezzoTrasportoDTO : listaMezziDisponibili){
            modelloTabella.addRow(new Object[]{Boolean.FALSE, mezzoTrasportoDTO.getTarga(), mezzoTrasportoDTO.getMarca(), mezzoTrasportoDTO.getModello()});
        }
    }

    private void Listeners(){
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
                    setLabelRiepilogo();
                } catch (NoCampiSelezionatiException ex) {
                    System.out.println("Nessuna checkBox selezionata: " + ex);
                } catch (TroppiCampiSelezionatiException ex) {
                    System.out.println("Più di una checkBox selezionata: " + ex);
                }
            }
        });
        indietroButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.previous(cards);
            }
        });
        indietroButton3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {cardLayout.previous(cards); }
        });
        
        addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent e)
            {
                confermaChiusura();
            }
        });
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
                throw new NoCampiSelezionatiException(this, gestoreFinestre);
            case 1:
                cardLayout.next(cards);
                break;
            default:
                throw new TroppiCampiSelezionatiException(this, gestoreFinestre);
        }
    }
    
    private void setLabelRiepilogo(){
        for (int i = 0; i < tabellaCorrieri.getRowCount(); i++){
            if ((Boolean) tabellaCorrieri.getValueAt(i, 0)){
                matricolaLabel.setText(String.valueOf((int) tabellaCorrieri.getValueAt(i, 1)));
                nomeLabel.setText((String) tabellaCorrieri.getValueAt(i, 2));
                cognomeLabel.setText((String) tabellaCorrieri.getValueAt(i, 3));
                break;
            }
        }
        
        for (int i = 0; i < tabellaMezzi.getRowCount(); i++){
            if ((Boolean) tabellaMezzi.getValueAt(i, 0)){
                targaLabel.setText((String) tabellaMezzi.getValueAt(i, 1));
                marcaLabel.setText((String) tabellaMezzi.getValueAt(i, 2));
                modelloLabel.setText((String) tabellaMezzi.getValueAt(i, 3));
                break;
            }
        }
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
