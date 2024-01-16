package org.UninaDelivery.Controllori;

import org.UninaDelivery.*;
import org.UninaDelivery.DettagliSpedizione.DettagliSpedizioneDTO;
import org.UninaDelivery.Operatore.OperatoreDTO;
import org.UninaDelivery.Ordine.DettagliOrdineDTO;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.util.ArrayList;

public class ControlloreFinestre {
    private ControlloreDAO controlloreDAO;

    public void mostraMessageDialogErrore(Component parent, String testo, String titolo){
        JOptionPane.showMessageDialog(parent, testo, titolo, JOptionPane.ERROR_MESSAGE);
    }

    public void mostraMessageDialogInfo(Component parent, String testo, String titolo){
        JOptionPane.showMessageDialog(parent, testo, titolo, JOptionPane.INFORMATION_MESSAGE);
    }

    public SplashPage apriSplashPage(){
        return new SplashPage(this);
    }

    public void chiudiSplashPage(JWindow parent){
        parent.dispose();
    }

    public void apriLogin(){
        LoginPage loginPage = new LoginPage(null,this, controlloreDAO);
        loginPage.setVisible(true);
    }

    public void apriHome(OperatoreDTO operatoreDTO, JFrame parent){
        HomePage homePage = new HomePage(null, this, controlloreDAO, operatoreDTO);
        parent.dispose();
        homePage.setVisible(true);
    }

    public void tornaLogin(JFrame parent){
        parent.dispose();
        apriLogin();
    }

    public void apriStatistica(){
        StatisticaPage statisticaPage = new StatisticaPage(null, this, controlloreDAO);
        statisticaPage.setVisible(true);
    }


    public void apriInfoOperatore(OperatoreDTO operatoreLoggato){
        InfoOperatorePage infoOpPage = new InfoOperatorePage(null, this, operatoreLoggato);
        infoOpPage.setVisible(true);
    }

    public void apriInfoOrdine(int numOrdine){
        InfoOrdinePage infoOrdinePage = new InfoOrdinePage(null, this, controlloreDAO, numOrdine);
        infoOrdinePage.setVisible(true);
    }

    public void apriWizardCreazioneSpedizione(HomePage parent, ArrayList<DettagliOrdineDTO> listaOrdiniDaSpedire, int matricolaOperatoreLoggato){
        WizardCreazioneSpedizione wizardCreazioneSpedizione = new WizardCreazioneSpedizione(parent, this, controlloreDAO, listaOrdiniDaSpedire, matricolaOperatoreLoggato);
        wizardCreazioneSpedizione.setVisible(true);
    }

    public void apriSpedizioniProgrammatePage(HomePage parent, OperatoreDTO operatoreLoggato){
        SpedizioniProgrammatePage spedizioniProgrammatePage = new SpedizioniProgrammatePage(parent, this, controlloreDAO, operatoreLoggato);
        spedizioniProgrammatePage.setVisible(true);
        parent.dispose();
    }

    public void aggiornaTabellaHome(HomePage parent){
        parent.aggiornaTabella();
    }
    
    public void apriModificaSpedizioniPage(SpedizioniProgrammatePage parent, ArrayList<DettagliSpedizioneDTO> listaSpedizioniSelezionate){
        ModificaSpedizioniPage modificaSpedizioniPage = new ModificaSpedizioniPage(parent, this, controlloreDAO, listaSpedizioniSelezionate);
        modificaSpedizioniPage.setVisible(true);
    }
    
    public void apriModificaDataPage(SpedizioniProgrammatePage parent, ArrayList<DettagliSpedizioneDTO> listaSpedizioniSelezionate){
        CambiaDataPage cambiaDataPage = new CambiaDataPage(parent, this, controlloreDAO, listaSpedizioniSelezionate);
        cambiaDataPage.setVisible(true);
    }
    public void aggiornaTabellaSpedizioni(SpedizioniProgrammatePage parent){
        parent.aggiornaTabella();
    }

    public void resizeColumnWidth(JTable table) {
        final TableColumnModel columnModel = table.getColumnModel();
        for (int column = 0; column < table.getColumnCount(); column++) {
            int width = 5; // Min width
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

    public void setControlloreDAO(ControlloreDAO controlloreDAO) {
        this.controlloreDAO = controlloreDAO;
    }
}