package org.UninaDelivery;
import org.UninaDelivery.Cliente.ClienteDAO;
import org.UninaDelivery.Cliente.ClienteDTO;
import org.UninaDelivery.Corriere.CorriereDAO;
import org.UninaDelivery.Corriere.CorriereDTO;
import org.UninaDelivery.Exception.NessunaSpedizioneEffettuataException;
import org.UninaDelivery.Exception.OperatoreNonTrovatoException;
import org.UninaDelivery.Exception.AlcuneSpedizioniNonEffettuateException;
import org.UninaDelivery.MezziInUso.MezziInUsoDAO;
import org.UninaDelivery.MezzoTrasporto.MezzoTrasportoDAO;
import org.UninaDelivery.MezzoTrasporto.MezzoTrasportoDTO;
import org.UninaDelivery.Operatore.OperatoreDAO;
import org.UninaDelivery.Operatore.OperatoreDTO;
import org.UninaDelivery.Ordine.DettagliOrdineDTO;
import org.UninaDelivery.Ordine.DettagliOrdineDAO;
import org.UninaDelivery.Prodotto.ProdottoDAO;
import org.UninaDelivery.Prodotto.ProdottoDTO;
import org.UninaDelivery.Spedizione.SpedizioneDAO;
import org.UninaDelivery.StatisticheOrdini.StatisticheOrdineDTO;
import org.UninaDelivery.StatisticheOrdini.StatisticheOrdiniDAO;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.sql.*;
import java.util.ArrayList;

public class Controller {
    static DBConnection dbConnection;
    static Connection conn;

    public static void main(String[] args) throws Exception{
        // Esegue la connessione con il database
        dbConnection = DBConnection.getDBConnection();
        conn = dbConnection.getConnection();

        Controller controller = new Controller();
    }

    public Controller() {
        apriLogin();
    }
    
    public ArrayList<DettagliOrdineDTO> getOrdiniNonSpediti(){
        DettagliOrdineDAO dettagliOrdineDAO = new DettagliOrdineDAO();
        return dettagliOrdineDAO.getOrdiniNonSpediti(conn);
    }
    
    public ArrayList<DettagliOrdineDTO> getOrdiniByUtente(String utente){
        DettagliOrdineDAO dettagliOrdineDAO = new DettagliOrdineDAO();
        return dettagliOrdineDAO.getOrdiniByUtente(utente, conn);
    }
    
    public ArrayList<DettagliOrdineDTO> getOrdiniByData(Date dataInizio, Date dataFine){
        DettagliOrdineDAO dettagliOrdineDAO = new DettagliOrdineDAO();
        return dettagliOrdineDAO.getOrdiniByData(dataInizio, dataFine, conn);
    }
    
    public ArrayList<DettagliOrdineDTO> getOrdiniByUtenteAndData(String utente, Date DataInizio, Date DataFine){
        DettagliOrdineDAO dettagliOrdineDAO = new DettagliOrdineDAO();
        return dettagliOrdineDAO.getOrdiniByUtenteAndData(utente, DataInizio, DataFine, conn);
    }
    
    public void effettuaLogin(String Matricola, String Password, LoginForm parent){
        OperatoreDTO operatoreDTO;
        try{
            operatoreDTO = verificaDatiInput(Matricola, Password, parent);
            apriHome(operatoreDTO, parent);
        } catch(OperatoreNonTrovatoException e){
            System.out.println("operatore non valido: " + e);
            mostraMessageDialog(parent, "Matricola o Password non validi", "Errore");
        }
    }

    public OperatoreDTO verificaDatiInput(String Matricola, String Password, LoginForm parent) throws OperatoreNonTrovatoException {
        try{
            OperatoreDAO operatoreDAO = new OperatoreDAO();
            int matricola = Integer.parseInt(Matricola);

            operatoreDAO.ControllaLoginOperatore(matricola, Password, conn);
            return operatoreDAO.getOperatoreByMatricola(matricola, conn);
        } catch (OperatoreNonTrovatoException e) {
            throw new OperatoreNonTrovatoException();
        }
    }

    public void apriHome(OperatoreDTO operatoreDTO, JFrame parent){
        HomePage homePage = new HomePage(null, this, operatoreDTO);
        parent.dispose();
        homePage.setVisible(true);
    }
    
    public void mostraMessageDialog(Component parent, String testo, String titolo){
        JOptionPane.showMessageDialog(parent, testo, titolo, JOptionPane.ERROR_MESSAGE);
    }
    
    public void tornaLogin(JFrame parent){
        parent.dispose();
        apriLogin();
    }

    public void apriLogin(){
        LoginForm loginForm = new LoginForm(null,this);
        loginForm.setVisible(true);
    }
    
    public void apriStatistica(){
        StatisticaPage statisticaPage = new StatisticaPage(null, this);
        statisticaPage.setVisible(true);
    }

    public void apriInfoOperatore(OperatoreDTO operatoreLoggato){
        InfoOperatorePage infoOpPage = new InfoOperatorePage(null, this, operatoreLoggato);
        infoOpPage.setVisible(true);
    }
    
    public void apriInfoOrdine(int numOrdine){
        InfoOrdinePage infoOrdinePage = new InfoOrdinePage(null, this, numOrdine);
        infoOrdinePage.setVisible(true);
    }
    
    public void apriWizardCreazioneSpedizione(HomePage parent, ArrayList<DettagliOrdineDTO> listaOrdiniDaSpedire, int matricolaOperatoreLoggato){
        WizardCreazioneSpedizione wizardCreazioneSpedizione = new WizardCreazioneSpedizione(parent, this, listaOrdiniDaSpedire, matricolaOperatoreLoggato);
        wizardCreazioneSpedizione.setVisible(true);
    }
    
    public void chiudiConnessioneDB() throws SQLException {
        dbConnection.closeConnection(conn);
    }

    public ArrayList<ClienteDTO> recuperaClienti(){
        ClienteDAO clienteDAO = new ClienteDAO();
        return clienteDAO.recuperaClientiDTO(conn);
    }

    public ArrayList<ProdottoDTO> recuperaProdotti(int numOrdine){
        ProdottoDAO prodottoDAO = new ProdottoDAO();
        return prodottoDAO.getProdottiDaOrdine(numOrdine, conn);
    }

    public StatisticheOrdineDTO eseguiStatistica(int mese){
        StatisticheOrdiniDAO statisticheOrdiniDAO = new StatisticheOrdiniDAO();
        return statisticheOrdiniDAO.getStatisticheOrdine(mese, conn);
    }
    
    public ArrayList<MezzoTrasportoDTO> recuperaMezziDisponibili(float pesoOrdiniSelezionati){
        MezzoTrasportoDAO mezzoTrasportoDAO = new MezzoTrasportoDAO();
        return  mezzoTrasportoDAO.getMezziDisponibili(pesoOrdiniSelezionati,conn);
    }
    
    public ArrayList<CorriereDTO> recuperaCorrieriDisponibili(){
        CorriereDAO corriereDAO = new CorriereDAO();
        return corriereDAO.getCorrieriDisponibili(conn);
    }
    
    public void creaSpedizioneDaOrdini(Component chiamante, ArrayList<Object> dettagliSpedizione){
        SpedizioneDAO spedizioneDAO = new SpedizioneDAO();
        try {
            if (spedizioneDAO.creaSpedizione(dettagliSpedizione, conn, this))
                JOptionPane.showMessageDialog(chiamante, "Tutte le spedizioni sono state effettuate con successo",
                        "Avviso", JOptionPane.INFORMATION_MESSAGE);
            aggiungiMezziUtilizzati((Integer) dettagliSpedizione.get(1), (String) dettagliSpedizione.get(2), conn);
        } catch (AlcuneSpedizioniNonEffettuateException e) {
            aggiungiMezziUtilizzati((Integer) dettagliSpedizione.get(1), (String) dettagliSpedizione.get(2), conn);
            System.out.println("Spedizioni non effettuate " + e);
        } catch (NessunaSpedizioneEffettuataException e) {
            System.out.println("Nessuna spedizione effettuata: " + e);

        }
    }
    
    public void aggiornaTabellaHome(HomePage parent){
        parent.aggiornaTabella();
    }

    public void aggiungiMezziUtilizzati(int matricola, String targa, Connection conn){
        MezziInUsoDAO mezziInUsoDAO = new MezziInUsoDAO();
        mezziInUsoDAO.aggiungiMezziInUso(matricola, targa, conn);
    }

    public float calcolaPesoOrdini(ArrayList<DettagliOrdineDTO> listaOrdiniSelezionati){
        float pesoTot = 0;
        for(DettagliOrdineDTO ordine : listaOrdiniSelezionati){
            pesoTot += ordine.getPeso();
        }
        return pesoTot;
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
}

