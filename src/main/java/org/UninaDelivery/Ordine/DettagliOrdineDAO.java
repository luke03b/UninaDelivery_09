package org.UninaDelivery.Ordine;

import java.sql.*;
import java.util.ArrayList;

public class DettagliOrdineDAO {
    ArrayList<DettagliOrdineDTO> listaOrdini = new ArrayList<>();
    public ArrayList<DettagliOrdineDTO> getOrdiniNonSpediti(Connection conn){
        try{
            PreparedStatement stmt = conn.prepareStatement(
                    "SELECT Ordine.NumeroOrdine, Ordine.DataOrdine, Cliente.Tipo, Cliente.Nome, Cliente.Cognome, " +
                            "Cliente.NomeAzienda, Indirizzo.Via, Indirizzo.NumeroCivico, " +
                    "Indirizzo.CAP, Indirizzo.Citta, Indirizzo.Provincia, Ordine.Peso, Ordine.Grandezza, " +
                    "Cl2.Tipo AS TipoMT, Cl2.Nome AS NomeMT, Cl2.Cognome AS CognomeMT, " +
                    "Cl2.NomeAzienda AS NomeAziendaMT " +
                    "FROM Ordine LEFT JOIN Spedizione " +
                    "ON Ordine.NumeroOrdine = Spedizione.NumeroOrdine " +
                    "JOIN Cliente ON Cliente.Ruolo <> 'Mittente' AND Ordine.NumeroTelefonoDT = Cliente.NumeroTelefono " +
                    "JOIN Cliente    AS Cl2 ON Cl2.Ruolo <> 'Destinatario' AND Ordine.NumeroTelefonoMT = Cl2.NumeroTelefono " +
                    "JOIN Indirizzo ON Cliente.IdIndirizzo = Indirizzo.IdIndirizzo " +
                    "WHERE Spedizione.Stato IS NULL " +
                    "ORDER BY Ordine.DataOrdine ASC");
            
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                DettagliOrdineDTO ordineCorrente = new DettagliOrdineDTO();
                creaOrdineDTO(ordineCorrente, rs);
                listaOrdini.add(ordineCorrente);
            }

            stmt.close();
            rs.close();
            
        } catch (SQLException e) {
            System.out.println(e);
            throw new RuntimeException(e);
        } catch (Exception e) {
            System.out.println(e);
        }
        
        return listaOrdini;
    }

    public ArrayList<DettagliOrdineDTO> getOrdiniByUtenteAndData (String utente, Date dataInizio, Date dataFine, Connection conn) {
        try{
            PreparedStatement stmt = conn.prepareStatement(
                    "   SELECT O.NumeroOrdine, O.DataOrdine, Cl.Tipo, Cl.Nome, Cl.Cognome, Cl.NomeAzienda, I.Via, I.NumeroCivico, " +
                                  "I.CAP, I.Citta, I.Provincia, O.Peso, O.Grandezza, Cl2.Tipo AS TipoMT, Cl2.Nome AS NomeMT, Cl2.Cognome AS CognomeMT, " +
                                  "Cl2.NomeAzienda AS NomeAziendaMT " +
                             "FROM Ordine     AS O " +
                            "LEFT JOIN Spedizione AS S   ON O.NumeroOrdine = S.NumeroOrdine " +
                            "JOIN Cliente    AS Cl  ON Cl.Ruolo <> 'Mittente' AND O.NumeroTelefonoDT = Cl.NumeroTelefono " +
                            "JOIN Cliente    AS Cl2 ON Cl2.Ruolo <> 'Destinatario' AND O.NumeroTelefonoMT = Cl2.NumeroTelefono " +
                            "JOIN Indirizzo  AS I   ON Cl.IdIndirizzo = I.IdIndirizzo " +
                            "WHERE S.Stato IS NULL " +
                            "AND (O.numeroTelefonoDT = ? OR O.numeroTelefonoMT = ?) " +
                            "AND O.dataOrdine BETWEEN ? AND ? " +
                            " ORDER BY O.DataOrdine ASC");

            stmt.setString(1, utente);
            stmt.setString(2, utente);
            stmt.setDate(3, dataInizio);
            stmt.setDate(4, dataFine);

            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                DettagliOrdineDTO ordineCorrente = new DettagliOrdineDTO();
                creaOrdineDTO(ordineCorrente, rs);
                listaOrdini.add(ordineCorrente);
            }

            stmt.close();
            rs.close();

        } catch (SQLException e) {
            System.out.println(e);
            throw new RuntimeException(e);
        } catch (Exception e) {
            System.out.println(e);
        }

        return listaOrdini;
    }
    
    public ArrayList<DettagliOrdineDTO> getOrdiniByUtente (String utente, Connection conn) {
        try{
            PreparedStatement stmt = conn.prepareStatement(
                    "SELECT Ordine.NumeroOrdine, Ordine.DataOrdine, Cliente.Tipo, Cliente.Nome, Cliente.Cognome," +
                            "Cliente.NomeAzienda, Indirizzo.Via, Indirizzo.NumeroCivico, " +
                            "Indirizzo.CAP, Indirizzo.Citta, Indirizzo.Provincia, Ordine.Peso, Ordine.Grandezza, " +
                            "Cl2.Tipo AS TipoMT, Cl2.Nome AS NomeMT, Cl2.Cognome AS CognomeMT, " +
                            "Cl2.NomeAzienda AS NomeAziendaMT " +
                            "FROM Ordine LEFT JOIN Spedizione " +
                            "ON Ordine.NumeroOrdine = Spedizione.NumeroOrdine " +
                            "JOIN Cliente ON Cliente.Ruolo <> 'Mittente' AND Ordine.NumeroTelefonoDT = Cliente.NumeroTelefono " +
                            "JOIN Cliente    AS Cl2 ON Cl2.Ruolo <> 'Destinatario' AND Ordine.NumeroTelefonoMT = Cl2.NumeroTelefono " +
                            "JOIN Indirizzo ON Cliente.IdIndirizzo = Indirizzo.IdIndirizzo " +
                            "WHERE Spedizione.Stato IS NULL AND (ordine.numeroTelefonoDT = ? OR ordine.numeroTelefonoMT = ?) " +
                            "ORDER BY Ordine.DataOrdine ASC");
            
            stmt.setString(1, utente);
            stmt.setString(2, utente);
            
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                DettagliOrdineDTO ordineCorrente = new DettagliOrdineDTO();
                creaOrdineDTO(ordineCorrente, rs);
                listaOrdini.add(ordineCorrente);
            }

            stmt.close();
            rs.close();

        } catch (SQLException e) {
            System.out.println(e);
            throw new RuntimeException(e);
        } catch (Exception e) {
            System.out.println(e);
        }
        
        return listaOrdini;
    }
    
    public ArrayList<DettagliOrdineDTO> getOrdiniByData (Date dataInizio, Date dataFine, Connection conn) {
        try{
            PreparedStatement stmt = conn.prepareStatement(
                    "SELECT Ordine.NumeroOrdine, Ordine.DataOrdine, Cliente.Tipo, Cliente.Nome, Cliente.Cognome," +
                            "Cliente.NomeAzienda, Indirizzo.Via, Indirizzo.NumeroCivico, " +
                            "Indirizzo.CAP, Indirizzo.Citta, Indirizzo.Provincia, Ordine.Peso, Ordine.Grandezza, " +
                            "Cl2.Tipo AS TipoMT, Cl2.Nome AS NomeMT, Cl2.Cognome AS CognomeMT, " +
                            "Cl2.NomeAzienda AS NomeAziendaMT " +
                            "FROM Ordine LEFT JOIN Spedizione " +
                            "ON Ordine.NumeroOrdine = Spedizione.NumeroOrdine " +
                            "JOIN Cliente ON Cliente.Ruolo <> 'Mittente' AND Ordine.NumeroTelefonoDT = Cliente.NumeroTelefono " +
                            "JOIN Cliente    AS Cl2 ON Cl2.Ruolo <> 'Destinatario' AND Ordine.NumeroTelefonoMT = Cl2.NumeroTelefono " +
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

            stmt.close();
            rs.close();
            
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

        if (rs.getString("tipomt").equals("Aziendale"))
            nuovoOrdine.setMittente(rs.getString("nomeaziendamt"));
        else
            nuovoOrdine.setMittente(rs.getString("nomemt") + " " + rs.getString("cognomemt"));

        IndirizzoCompleto = rs.getString("via") + " " + rs.getString("numerocivico") + ", " +
                rs.getString("cap") + " " + rs.getString("citta") + " " + rs.getString("provincia");
        nuovoOrdine.setIndirizzo(IndirizzoCompleto);
        nuovoOrdine.setPeso(rs.getFloat("peso"));
        nuovoOrdine.setGrandezza(rs.getString("grandezza"));
    }
}
