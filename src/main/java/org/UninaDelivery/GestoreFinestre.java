package org.UninaDelivery;
import org.UninaDelivery.Cliente.ClienteDAO;
import org.UninaDelivery.Cliente.ClienteDTO;
import org.UninaDelivery.Exception.OperatoreNonTrovatoException;
import org.UninaDelivery.Operatore.OperatoreDAO;
import org.UninaDelivery.Operatore.OperatoreDTO;
import org.UninaDelivery.Ordine.DettagliOrdineDTO;
import org.UninaDelivery.Ordine.DettagliOrdineDAO;
import org.UninaDelivery.Prodotto.ProdottoDAO;
import org.UninaDelivery.Prodotto.ProdottoDTO;
import org.UninaDelivery.StatisticheOrdini.StatisticheOrdineDTO;
import org.UninaDelivery.StatisticheOrdini.StatisticheOrdiniDAO;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;

public class GestoreFinestre {

    LoginForm loginForm;
    static DBConnection dbConnection;
    static Connection conn;

    public static void main(String[] args) throws Exception{
        // Esegue la connessione con il database
        dbConnection = DBConnection.getDBConnection();
        conn = dbConnection.getConnection();
        
        GestoreFinestre gestoreFinestre = new GestoreFinestre();
    }

    public GestoreFinestre () {
        apriLogin();
    }
    
    public ArrayList<DettagliOrdineDTO> RecuperaOrdiniNonSpediti(){
        DettagliOrdineDAO dettagliOrdineDAO = new DettagliOrdineDAO();
        return dettagliOrdineDAO.getOrdiniNonSpediti(conn);
    }
    
    public ArrayList<DettagliOrdineDTO> RecuperaOrdiniByUtente(String utente){
        DettagliOrdineDAO dettagliOrdineDAO = new DettagliOrdineDAO();
        return dettagliOrdineDAO.getOrdiniByUtente(utente, conn);
    }
    
    public ArrayList<DettagliOrdineDTO> RecuperaOrdiniByData(Date dataInizio, Date dataFine){
        DettagliOrdineDAO dettagliOrdineDAO = new DettagliOrdineDAO();
        return dettagliOrdineDAO.getOrdiniByData(dataInizio, dataFine, conn);
    }
    
    public ArrayList<DettagliOrdineDTO> RecuperaOrdiniByUtenteAndData(String utente, Date DataInizio, Date DataFine){
        DettagliOrdineDAO dettagliOrdineDAO = new DettagliOrdineDAO();
        return dettagliOrdineDAO.getOrdiniByUtenteAndData(utente, DataInizio, DataFine, conn);
    }
    
    public void EffettuaLogin(String Matricola, String Password, LoginForm parent){
        OperatoreDTO operatoreDTO = new OperatoreDTO();
        try{
            operatoreDTO = VerificaDatiInput(Matricola, Password, parent);
            apriHome(operatoreDTO, parent);
        } catch(OperatoreNonTrovatoException e){
            System.out.println("operatore non valido: " + e);
            mostraMessageDialog(parent, "Matricola o Password non validi", "Errore");
        }
    }

    public OperatoreDTO VerificaDatiInput(String Matricola, String Password, LoginForm parent) throws OperatoreNonTrovatoException {
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
    
    public void mostraMessageDialog(JFrame parent, String testo, String titolo){
        JOptionPane.showMessageDialog(parent, testo, titolo, JOptionPane.ERROR_MESSAGE);
    }
    
    public void TornaLogin(JFrame parent){
        parent.dispose();
        apriLogin();
    }

    public void apriLogin(){
        loginForm = new LoginForm(null,this);
        loginForm.setVisible(true);
    }
    
    public void apriStatistica(OperatoreDTO operatoreLoggato){
        StatisticaPage statisticaPage = new StatisticaPage(null, this, operatoreLoggato);
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
        return statisticheOrdiniDAO.getStatisticheOrdine(conn, mese);
    }
}

