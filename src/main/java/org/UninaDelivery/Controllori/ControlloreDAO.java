package org.UninaDelivery.Controllori;

import org.UninaDelivery.Cliente.ClienteDAO;
import org.UninaDelivery.Cliente.ClienteDTO;
import org.UninaDelivery.Corriere.CorriereDAO;
import org.UninaDelivery.Corriere.CorriereDTO;
import org.UninaDelivery.DBConnection;
import org.UninaDelivery.DettagliSpedizione.DettagliSpedizioneDAO;
import org.UninaDelivery.DettagliSpedizione.DettagliSpedizioneDTO;
import org.UninaDelivery.Exception.AlcuneSpedizioniNonEffettuateException;
import org.UninaDelivery.Exception.NessunaSpedizioneEffettuataException;
import org.UninaDelivery.Exception.OperatoreNonTrovatoException;
import org.UninaDelivery.MezziInUso.MezziInUsoDAO;
import org.UninaDelivery.MezzoTrasporto.MezzoTrasportoDAO;
import org.UninaDelivery.MezzoTrasporto.MezzoTrasportoDTO;
import org.UninaDelivery.Operatore.OperatoreDAO;
import org.UninaDelivery.Operatore.OperatoreDTO;
import org.UninaDelivery.Ordine.DettagliOrdineDAO;
import org.UninaDelivery.Ordine.DettagliOrdineDTO;
import org.UninaDelivery.Prodotto.ProdottoDAO;
import org.UninaDelivery.Prodotto.ProdottoDTO;
import org.UninaDelivery.Spedizione.SpedizioneDAO;
import org.UninaDelivery.StatisticheOrdini.StatisticheOrdineDTO;
import org.UninaDelivery.StatisticheOrdini.StatisticheOrdiniDAO;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

public class ControlloreDAO {
    static DBConnection dbConnection;
    static Connection conn;
    private ControlloreFinestre controlloreFinestre;

    public ControlloreDAO(ControlloreFinestre controlloreFinestre){
        // Esegue la connessione con il database
        dbConnection = DBConnection.getDBConnection();
        conn = dbConnection.getConnection();
        this.controlloreFinestre = controlloreFinestre;
    }

    public OperatoreDTO getOperatore(String matricola, String password) throws OperatoreNonTrovatoException{
        OperatoreDTO operatoreDTO;
        try{
            operatoreDTO = verificaDatiInput(matricola, password);
            return operatoreDTO;
        } catch(OperatoreNonTrovatoException e){
            throw new OperatoreNonTrovatoException();
        }
    }

    public void aggiungiMezziUtilizzati(int matricola, String targa, Connection conn){
        MezziInUsoDAO mezziInUsoDAO = new MezziInUsoDAO();
        mezziInUsoDAO.aggiungiMezziInUso(matricola, targa, conn);
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

    public OperatoreDTO verificaDatiInput(String Matricola, String password) throws OperatoreNonTrovatoException {
        try{
            OperatoreDAO operatoreDAO = new OperatoreDAO();
            int matricola = Integer.parseInt(Matricola);

            operatoreDAO.controllaLoginOperatore(matricola, password, conn);
            return operatoreDAO.getOperatoreByMatricola(matricola, conn);
        } catch (OperatoreNonTrovatoException e) {
            throw new OperatoreNonTrovatoException();
        }
    }

    public void chiudiConnessioneDB() throws SQLException {
        dbConnection.closeConnection(conn);
    }

    public ArrayList<ClienteDTO> recuperaClienti(){
        ClienteDAO clienteDAO = new ClienteDAO();
        return clienteDAO.getClientiDTO(conn);
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

    public void creaSpedizioneDaOrdini(Component chiamante, ArrayList<Object> dettagliSpedizione) {
        SpedizioneDAO spedizioneDAO = new SpedizioneDAO();
        try {
            if (spedizioneDAO.inserisciSpedizione(dettagliSpedizione, conn, chiamante, controlloreFinestre))
                controlloreFinestre.mostraMessageDialogInfo(chiamante, "Tutte le spedizioni sono state effettuate con successo",
                        "Avviso");
            aggiungiMezziUtilizzati((Integer) dettagliSpedizione.get(1), (String) dettagliSpedizione.get(2), conn);
        } catch (AlcuneSpedizioniNonEffettuateException e) {
            aggiungiMezziUtilizzati((Integer) dettagliSpedizione.get(1), (String) dettagliSpedizione.get(2), conn);
            System.out.println("Spedizioni non effettuate " + e);
        } catch (NessunaSpedizioneEffettuataException e) {
            System.out.println("Nessuna spedizione effettuata: " + e);
        }
    }

    public ArrayList<DettagliSpedizioneDTO> getSpedizioniProgrammate(){
        DettagliSpedizioneDAO spedizioneDAO = new DettagliSpedizioneDAO();
        return spedizioneDAO.getSpedizioniProgrammate(conn);
    }

    public float calcolaPesoOrdini(ArrayList<DettagliOrdineDTO> listaOrdiniSelezionati){
        float pesoTot = 0;
        for(DettagliOrdineDTO ordine : listaOrdiniSelezionati){
            pesoTot += ordine.getPeso();
        }
        return pesoTot;
    }

    public ArrayList<DettagliSpedizioneDTO> getSpedizioniByUtenteAndData(String utente, Date dataInizio, Date dataFine){
        DettagliSpedizioneDAO dettagliSpedizioneDAO = new DettagliSpedizioneDAO();
        return dettagliSpedizioneDAO.getSpedizioniByUtenteAndData(utente, dataInizio, dataFine, conn);
    }

    public ArrayList<DettagliSpedizioneDTO> getSpedizioniByUtente(String utente){
        DettagliSpedizioneDAO dettagliSpedizioneDAO = new DettagliSpedizioneDAO();
        return dettagliSpedizioneDAO.getSpedizioniByUtente(utente, conn);
    }

    public ArrayList<DettagliSpedizioneDTO> getSpedizioniByData(Date dataInizio, Date dataFine){
        DettagliSpedizioneDAO dettagliSpedizioneDAO = new DettagliSpedizioneDAO();
        return dettagliSpedizioneDAO.getSpedizioniByData(dataInizio, dataFine, conn);
    }

    public void aggiornaSpedizioniProgrammate(ArrayList<DettagliSpedizioneDTO> listaSpedizioni, String tipoSpedizione){
        DettagliSpedizioneDAO spedizioneDAO = new DettagliSpedizioneDAO();
        spedizioneDAO.aggiornaSpedizioniProgrammate(listaSpedizioni, tipoSpedizione, conn);
    }
    
    public void aggiornaDataSpedizioni(ArrayList<DettagliSpedizioneDTO> listaSpedizioni, Date dataPrevista){
        DettagliSpedizioneDAO spedizioneDAO = new DettagliSpedizioneDAO();
        spedizioneDAO.aggiornaDataSpedizioni(listaSpedizioni, dataPrevista, conn);
    }
}