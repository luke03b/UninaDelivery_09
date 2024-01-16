package org.UninaDelivery;

import org.UninaDelivery.Controllori.ControlloreDAO;
import org.UninaDelivery.Controllori.ControlloreFinestre;
import org.UninaDelivery.DettagliSpedizione.DettagliSpedizioneDTO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ModificaSpedizioniPage extends JDialog{
    private JPanel modificaSpedizioniPanel;
    private JButton confermaButton;
    private JButton annullaButton;
    private JRadioButton radioButtonSettimanale;
    private JRadioButton radioButtonMensile;
    private JRadioButton radioButtonAnnuale;
    private ControlloreDAO controlloreDAO;
    private ControlloreFinestre controlloreFinestre;
    private ImageIcon imageIcon = new ImageIcon("src/main/java/org/UninaDelivery/Icon/logoSenzaScritte.png");
    private ArrayList<DettagliSpedizioneDTO> listaSpedizioniSelezionate;
    private SpedizioniProgrammatePage parent;
    public ModificaSpedizioniPage(SpedizioniProgrammatePage parent, ControlloreFinestre controlloreFinestre, ControlloreDAO controlloreDAO, ArrayList<DettagliSpedizioneDTO> listaSpedizioniSelezionate){
        setImpostazioniModificaSpedizioniPage(parent, controlloreFinestre, controlloreDAO, listaSpedizioniSelezionate);
        listeners();
    }
    
    private void setImpostazioniModificaSpedizioniPage(SpedizioniProgrammatePage parent, ControlloreFinestre controlloreFinestre, ControlloreDAO controlloreDAO, ArrayList<DettagliSpedizioneDTO> listaSpedizioniSelezionate){
        this.listaSpedizioniSelezionate = listaSpedizioniSelezionate;
        setIconImage(imageIcon.getImage());
        setLayout(null);
        setResizable(true);
        this.controlloreDAO = controlloreDAO;
        this.controlloreFinestre = controlloreFinestre;
        this.parent = parent;
        setTitle("Crea Spedizioni Programmate");
        setContentPane(modificaSpedizioniPanel);
        setMinimumSize(new Dimension(1000, 400));
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        
        ButtonGroup group = new ButtonGroup();
        group.add(radioButtonSettimanale);
        group.add(radioButtonMensile);
        group.add(radioButtonAnnuale);
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
                aggiornaSpedizioniSelezionate();
                controlloreFinestre.mostraMessageDialog(ModificaSpedizioniPage.this, "Spedizioni aggiornate con successo", "Avviso");
                controlloreFinestre.aggiornaTabellaSpedizioni(parent);
                dispose();
            }
        });
    }
    
    private void aggiornaSpedizioniSelezionate(){
        if (radioButtonSettimanale.isSelected())
            controlloreDAO.aggiornaSpedizioniProgrammate(listaSpedizioniSelezionate, "Singola");
        if (radioButtonMensile.isSelected())
            controlloreDAO.aggiornaSpedizioniProgrammate(listaSpedizioniSelezionate, "Mensile");
        if (radioButtonAnnuale.isSelected())
            controlloreDAO.aggiornaSpedizioniProgrammate(listaSpedizioniSelezionate, "Annuale");
    }
}