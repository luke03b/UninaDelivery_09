package org.UninaDelivery.Ordine;

import java.sql.*;
import java.util.ArrayList;

public class OrdineDAO {
    public ArrayList<DettagliOrdineDTO> getOrdiniNonSpediti(Connection conn){
        ArrayList<DettagliOrdineDTO> listaOrdini = new ArrayList<DettagliOrdineDTO>();
        
        try{
            PreparedStatement stmt = conn.prepareStatement(
                    "SELECT Ordine.NumeroOrdine, Ordine.DataOrdine, Cliente.Tipo, Cliente.Nome, Cliente.Cognome, " +
                            "Cliente.NomeAzienda, Indirizzo.Via, Indirizzo.NumeroCivico, " +
                    "Indirizzo.CAP, Indirizzo.Citta, Indirizzo.Provincia, Ordine.Peso, Ordine.Grandezza " +
                    "FROM Ordine LEFT JOIN Spedizione " +
                    "ON Ordine.NumeroOrdine = Spedizione.NumeroOrdine " +
                    "JOIN Cliente ON Cliente.Ruolo <> 'Mittente' AND Ordine.NumeroTelefonoDT = Cliente.NumeroTelefono " +
                    "JOIN Indirizzo ON Cliente.IdIndirizzo = Indirizzo.IdIndirizzo " +
                    "WHERE Spedizione.Stato IS NULL " +
                    "ORDER BY Ordine.DataOrdine ASC");
            
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                DettagliOrdineDTO ordineCorrente = new DettagliOrdineDTO();
                creaOrdineDTO(ordineCorrente, rs);
                listaOrdini.add(ordineCorrente);
            }
            
        } catch (SQLException e) {
            System.out.println(e);
            throw new RuntimeException(e);
        } catch (Exception e) {
            System.out.println(e);
        }
        
        return listaOrdini;
    }

    public ArrayList<DettagliOrdineDTO> getOrdiniByUtenteAndData (String utente, Date dataInizio, Date dataFine, Connection conn) {
        ArrayList<DettagliOrdineDTO> listaOrdini = new ArrayList<DettagliOrdineDTO>();

        try{
            PreparedStatement stmt = conn.prepareStatement(
                    "SELECT Ordine.NumeroOrdine, Ordine.DataOrdine, Cliente.Tipo, Cliente.Nome, Cliente.Cognome," +
                            "Cliente.NomeAzienda, Indirizzo.Via, Indirizzo.NumeroCivico, " +
                            "Indirizzo.CAP, Indirizzo.Citta, Indirizzo.Provincia, Ordine.Peso, Ordine.Grandezza " +
                            "FROM Ordine LEFT JOIN Spedizione " +
                            "ON Ordine.NumeroOrdine = Spedizione.NumeroOrdine " +
                            "JOIN Cliente ON Cliente.Ruolo <> 'Mittente' AND Ordine.NumeroTelefonoDT = Cliente.NumeroTelefono " +
                            "JOIN Indirizzo ON Cliente.IdIndirizzo = Indirizzo.IdIndirizzo " +
                            "WHERE Spedizione.Stato IS NULL AND (ordine.numeroTelefonoDT = ? OR ordine.numeroTelefonoMT = ?) AND " +
                            "ordine.dataOrdine BETWEEN ? AND ? " +
                            "ORDER BY Ordine.DataOrdine ASC");

            stmt.setString(1,utente);
            stmt.setString(2,utente);
            stmt.setDate(3,dataInizio);
            stmt.setDate(4,dataFine);

            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                DettagliOrdineDTO ordineCorrente = new DettagliOrdineDTO();
                creaOrdineDTO(ordineCorrente, rs);
                listaOrdini.add(ordineCorrente);
            }

        } catch (SQLException e) {
            System.out.println(e);
            throw new RuntimeException(e);
        } catch (Exception e) {
            System.out.println(e);
        }

        return listaOrdini;
    }
    
    public ArrayList<DettagliOrdineDTO> getOrdiniByUtente (String utente, Connection conn) {
        ArrayList<DettagliOrdineDTO> listaOrdini = new ArrayList<DettagliOrdineDTO>();
        
        try{
            PreparedStatement stmt = conn.prepareStatement(
                    "SELECT Ordine.NumeroOrdine, Ordine.DataOrdine, Cliente.Tipo, Cliente.Nome, Cliente.Cognome," +
                            "Cliente.NomeAzienda, Indirizzo.Via, Indirizzo.NumeroCivico, " +
                            "Indirizzo.CAP, Indirizzo.Citta, Indirizzo.Provincia, Ordine.Peso, Ordine.Grandezza " +
                            "FROM Ordine LEFT JOIN Spedizione " +
                            "ON Ordine.NumeroOrdine = Spedizione.NumeroOrdine " +
                            "JOIN Cliente ON Cliente.Ruolo <> 'Mittente' AND Ordine.NumeroTelefonoDT = Cliente.NumeroTelefono " +
                            "JOIN Indirizzo ON Cliente.IdIndirizzo = Indirizzo.IdIndirizzo " +
                            "WHERE Spedizione.Stato IS NULL AND (ordine.numeroTelefonoDT = ? OR ordine.numeroTelefonoMT = ?) " +
                            "ORDER BY Ordine.DataOrdine ASC");
            
            stmt.setString(1,utente);
            stmt.setString(2,utente);
            
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                DettagliOrdineDTO ordineCorrente = new DettagliOrdineDTO();
                creaOrdineDTO(ordineCorrente, rs);
                listaOrdini.add(ordineCorrente);
            }
            
        } catch (SQLException e) {
            System.out.println(e);
            throw new RuntimeException(e);
        } catch (Exception e) {
            System.out.println(e);
        }
        
        return listaOrdini;
    }
    
    public ArrayList<DettagliOrdineDTO> getOrdiniByData (Date dataInizio, Date dataFine, Connection conn) {
        ArrayList<DettagliOrdineDTO> listaOrdini = new ArrayList<DettagliOrdineDTO>();
        
        try{
            PreparedStatement stmt = conn.prepareStatement(
                    "SELECT Ordine.NumeroOrdine, Ordine.DataOrdine, Cliente.Tipo, Cliente.Nome, Cliente.Cognome," +
                            "Cliente.NomeAzienda, Indirizzo.Via, Indirizzo.NumeroCivico, " +
                            "Indirizzo.CAP, Indirizzo.Citta, Indirizzo.Provincia, Ordine.Peso, Ordine.Grandezza " +
                            "FROM Ordine LEFT JOIN Spedizione " +
                            "ON Ordine.NumeroOrdine = Spedizione.NumeroOrdine " +
                            "JOIN Cliente ON Cliente.Ruolo <> 'Mittente' AND Ordine.NumeroTelefonoDT = Cliente.NumeroTelefono " +
                            "JOIN Indirizzo ON Cliente.IdIndirizzo = Indirizzo.IdIndirizzo " +
                            "WHERE Spedizione.Stato IS NULL AND ordine.dataOrdine BETWEEN ? AND ? " +
                            "ORDER BY Ordine.DataOrdine ASC");
            
            stmt.setDate(1,dataInizio);
            stmt.setDate(2,dataFine);
            
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                DettagliOrdineDTO ordineCorrente = new DettagliOrdineDTO();
                creaOrdineDTO(ordineCorrente, rs);
                listaOrdini.add(ordineCorrente);
            }
            
        } catch (SQLException e) {
            System.out.println(e);
            throw new RuntimeException(e);
        } catch (Exception e) {
            System.out.println(e);
        }
        
        return listaOrdini;
    }

    private void creaOrdineDTO(DettagliOrdineDTO nuovoOrdine, ResultSet rs) throws SQLException {
        String IndirizzoCompleto; //variabile d'appoggio
        nuovoOrdine.setNumeroOrdine(rs.getInt("numeroordine"));
        nuovoOrdine.setDataOrdine(rs.getDate("dataordine").toLocalDate());
        
        if (rs.getString("tipo").equals("Aziendale"))
            nuovoOrdine.setDestinatario(rs.getString("nomeazienda"));
        else
            nuovoOrdine.setDestinatario(rs.getString("nome") + " " + rs.getString("cognome"));
        
        IndirizzoCompleto = rs.getString("via") + " " + rs.getString("numerocivico") + ", " +
                rs.getString("cap") + " " + rs.getString("citta") + " " + rs.getString("provincia");
        nuovoOrdine.setIndirizzo(IndirizzoCompleto);
        nuovoOrdine.setPeso(rs.getFloat("peso"));
        nuovoOrdine.setGrandezza(rs.getString("grandezza"));
    }
}
