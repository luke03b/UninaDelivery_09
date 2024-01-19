package org.UninaDelivery;

import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;
import org.UninaDelivery.Controllori.ControlloreDAO;
import org.UninaDelivery.Controllori.ControlloreFinestre;
import org.UninaDelivery.DettagliSpedizione.DettagliSpedizioneDTO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CambiaDataPage extends JDialog{
    private JPanel cambiaDataPanel;
    private JButton confermaButton;
    private JButton annullaButton;
    private JPanel calendarioPanel;
    private JToolBar toolBar;
    private JLabel iconaLogoDelivery;
    private JLabel logoUnina;
    private ControlloreDAO controlloreDAO;
    private ControlloreFinestre controlloreFinestre;
    private SpedizioniProgrammatePage parent;
    private ArrayList<DettagliSpedizioneDTO> listaSpedizioniSelezionate;
    private ImageIcon imageIcon = new ImageIcon("src/main/java/org/UninaDelivery/Icon/logoSenzaScritte.png");
    private UtilDateModel modelData = new UtilDateModel();
    private JDatePanelImpl datePanelData = new JDatePanelImpl(modelData);
    private JDatePickerImpl pickerData = new JDatePickerImpl(datePanelData);
    public CambiaDataPage(SpedizioniProgrammatePage parent, ControlloreFinestre controlloreFinestre, ControlloreDAO controlloreDAO, ArrayList<DettagliSpedizioneDTO> listaSpedizioniSelezionate){
        setImpostazioniModificaDataPage(parent, controlloreFinestre, controlloreDAO, listaSpedizioniSelezionate);
        setImpostazioniCalendario();
        setImpostazioniConfermaButton();
        setImpostazioniAnnullaButton();
        setImpostazioniIcone();
        listeners();
    }

    private void setImpostazioniIcone(){
        iconaLogoDelivery.setIcon(new ImageIcon("src/main/java/org/UninaDelivery/Icon/logoSenzaScrittePiccolo.png"));
        logoUnina.setIcon(new ImageIcon("src/main/java/org/UninaDelivery/Icon/logoFedericoII.png"));
    }

    private void setImpostazioniModificaDataPage(SpedizioniProgrammatePage parent, ControlloreFinestre controlloreFinestre, ControlloreDAO controlloreDAO, ArrayList<DettagliSpedizioneDTO> listaSpedizioniSelezionate){
        this.listaSpedizioniSelezionate = listaSpedizioniSelezionate;
        setIconImage(imageIcon.getImage());
        setLayout(null);
        setResizable(true);
        this.controlloreDAO = controlloreDAO;
        this.controlloreFinestre = controlloreFinestre;
        this.parent = parent;
        setTitle("Crea Spedizioni Programmate");
        setContentPane(cambiaDataPanel);
        setMinimumSize(new Dimension(580, 310));
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    public void setImpostazioniAnnullaButton() {
        annullaButton.setIcon(new ImageIcon("src/main/java/org/UninaDelivery/Icon/annulla32x32.png"));
        annullaButton.setMargin(new Insets(0, 0, 0, 0));
        annullaButton.setOpaque(false);
        annullaButton.setBorderPainted(false);
        annullaButton.setBorder(null);
        annullaButton.setContentAreaFilled(false);
        annullaButton.setFocusable(false);
    }

    public void setImpostazioniConfermaButton() {
        confermaButton.setIcon(new ImageIcon("src/main/java/org/UninaDelivery/Icon/ConfermaRosso32x32.png"));
        confermaButton.setMargin(new Insets(0, 0, 0, 0));
        confermaButton.setOpaque(false);
        confermaButton.setBorderPainted(false);
        confermaButton.setBorder(null);
        confermaButton.setContentAreaFilled(false);
        confermaButton.setFocusable(false);
    }
    
    private void setImpostazioniCalendario(){
        toolBar.setFloatable(false);
        toolBar.add(pickerData);
        pickerData.setPreferredSize(new Dimension(150, 23));
        pickerData.setMinimumSize(new Dimension(150, 23));
        pickerData.setMaximumSize(new Dimension(150, 23));
    }
    
    private void listeners(){
        annullaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        
        confermaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                java.util.Date data = (java.util.Date) pickerData.getModel().getValue();
                controlloreDAO.aggiornaDataSpedizioni(listaSpedizioniSelezionate, new java.sql.Date(data.getTime()));
                controlloreFinestre.mostraMessageDialogInfo(CambiaDataPage.this, "Spedizioni aggiornate con successo", "Avviso");
                controlloreFinestre.aggiornaTabellaSpedizioni(parent);
                dispose();
            }
        });
    }
}
