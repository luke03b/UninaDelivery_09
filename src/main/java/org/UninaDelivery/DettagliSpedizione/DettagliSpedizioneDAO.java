package org.UninaDelivery.DettagliSpedizione;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

public class DettagliSpedizioneDAO {

    public ArrayList<DettagliSpedizioneDTO> getSpedizioniProgrammate(Connection conn){
        ArrayList<DettagliSpedizioneDTO> spedizioniProgrammate = new ArrayList<>();
        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT S.Tipo AS TipoSpedizione, S.*, O.*, Cl.Nome AS NomeMT, Cl.Cognome AS CognomeMT," +
                    "Cl.NomeAzienda AS NomeAziendaMT, Cl.Tipo AS TipoMT, Cl2.Nome AS NomeDT, Cl2.Cognome AS CognomeDT, " +
                    "Cl2.Tipo AS TipoDT, Cl2.NomeAzienda AS NomeAziendaDT, I.* " +
                    "FROM Spedizione AS S NATURAL JOIN Ordine AS O JOIN Cliente AS Cl " +
                    "ON Cl.NumeroTelefono = O.NumeroTelefonoMT " +
                    "JOIN Cliente AS Cl2 ON Cl2.NumeroTelefono = O.NumeroTelefonoDT " +
                    "JOIN Indirizzo AS I ON I.IDIndirizzo = Cl2.IDIndirizzo " +
                    "WHERE S.Tipo <> 'Singola'");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                DettagliSpedizioneDTO spedizioneDTO;
                spedizioneDTO = creaSpedizioneDTO(rs);
                spedizioniProgrammate.add(spedizioneDTO);
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println("errore SQL: " + e);
        } catch (Exception e) {
            System.out.println("errore generico: " + e);
        }
        return spedizioniProgrammate;
    }

    public void aggiornaSpedizioniProgrammate(ArrayList<DettagliSpedizioneDTO> listaSpedizioni, String tipoSpedizione, Connection conn){
        try {
            Statement stmt;
            stmt = conn.createStatement();
            String comando = "UPDATE Spedizione SET tipo = '" + tipoSpedizione + "' WHERE numeroTracciamento IN (";
            for (DettagliSpedizioneDTO dettagliSpedizioneDTO : listaSpedizioni) {
                comando = comando.concat(dettagliSpedizioneDTO.getNumeroTracciamento() + ", ");
            }
            comando = comando.substring(0, comando.length() - 2);
            comando = comando.concat(")");
            stmt.executeUpdate(comando);
            stmt.close();
        } catch (SQLException e) {
            System.out.println("SQL Exception: " + e);
        } catch (Exception e) {
            System.out.println("errore generico: " + e);
        }
    }
    
    public ArrayList<DettagliSpedizioneDTO> getSpedizioniByUtenteAndData(String utente, Date dataInizio, Date dataFine, Connection conn){
        ArrayList<DettagliSpedizioneDTO> listaSpedizioni = new ArrayList<>();
        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT S.Tipo AS TipoSpedizione, S.*, O.*, Cl.Nome AS NomeMT, Cl.Cognome AS CognomeMT," +
                    "Cl.NomeAzienda AS NomeAziendaMT, Cl.Tipo AS TipoMT, Cl2.Nome AS NomeDT, Cl2.Cognome AS CognomeDT," +
                    "Cl2.Tipo AS TipoDT, Cl2.NomeAzienda AS NomeAziendaDT, I.* " +
                    "FROM Spedizione AS S NATURAL JOIN Ordine AS O JOIN Cliente AS Cl " +
                    "ON Cl.NumeroTelefono = O.NumeroTelefonoMT " +
                    "JOIN Cliente AS Cl2 ON Cl2.NumeroTelefono = O.NumeroTelefonoDT " +
                    "JOIN Indirizzo AS I ON I.IDIndirizzo = Cl2.IDIndirizzo " +
                    "WHERE S.Tipo <> 'Singola' " +
                    "AND (O.NumeroTelefonoDT = ? OR O.NumeroTelefonoMT = ?) " +
                    "AND S.DataPrevista BETWEEN ? AND ? " +
                    "ORDER BY S.DataPrevista ASC");

            stmt.setString(1, utente);
            stmt.setString(2, utente);
            stmt.setDate(3, (java.sql.Date) dataInizio);
            stmt.setDate(4, (java.sql.Date) dataFine);

            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                DettagliSpedizioneDTO spedizioneCorrente;
                spedizioneCorrente = creaSpedizioneDTO(rs);
                listaSpedizioni.add(spedizioneCorrente);
            }
            stmt.close();
        } catch(SQLException e) {
            System.out.println("errore SQL: " + e);
        } catch(Exception e) {
            System.out.println("errore generico: " + e);
        }
        return listaSpedizioni;
    }
    
    public ArrayList<DettagliSpedizioneDTO> getSpedizioniByUtente(String utente, Connection conn){
        ArrayList<DettagliSpedizioneDTO> listaSpedizioni = new ArrayList<>();
        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT S.Tipo AS TipoSpedizione, S.*, O.*, Cl.Nome AS NomeMT, Cl.Cognome AS CognomeMT," +
                    "Cl.NomeAzienda AS NomeAziendaMT, Cl.Tipo AS TipoMT, Cl2.Nome AS NomeDT, Cl2.Cognome AS CognomeDT," +
                    "Cl2.Tipo AS TipoDT, Cl2.NomeAzienda AS NomeAziendaDT, I.* " +
                    "FROM Spedizione AS S NATURAL JOIN Ordine AS O JOIN Cliente AS Cl " +
                    "ON Cl.NumeroTelefono = O.NumeroTelefonoMT " +
                    "JOIN Cliente AS Cl2 ON Cl2.NumeroTelefono = O.NumeroTelefonoDT " +
                    "JOIN Indirizzo AS I ON I.IDIndirizzo = Cl2.IDIndirizzo " +
                    "WHERE S.Tipo <> 'Singola' " +
                    "AND (O.NumeroTelefonoDT = ? OR O.NumeroTelefonoMT = ?) " +
                    "ORDER BY S.DataPrevista ASC");
            stmt.setString(1, utente);
            stmt.setString(2, utente);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                DettagliSpedizioneDTO spedizioneCorrente;
                spedizioneCorrente = creaSpedizioneDTO(rs);
                listaSpedizioni.add(spedizioneCorrente);
            }
            stmt.close();
        } catch(SQLException e) {
            System.out.println("errore SQL: " + e);
        } catch(Exception e) {
            System.out.println("errore generico: " + e);
        }
        return listaSpedizioni;
    }
    
    public ArrayList<DettagliSpedizioneDTO> getSpedizioniByData(Date dataInizio, Date dataFine, Connection conn){
        ArrayList<DettagliSpedizioneDTO> listaSpedizioni = new ArrayList<>();
        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT S.Tipo AS TipoSpedizione, S.*, O.*, Cl.Nome AS NomeMT, Cl.Cognome AS CognomeMT," +
                    "Cl.NomeAzienda AS NomeAziendaMT, Cl.Tipo AS TipoMT, Cl2.Nome AS NomeDT, Cl2.Cognome AS CognomeDT," +
                    "Cl2.Tipo AS TipoDT, Cl2.NomeAzienda AS NomeAziendaDT, I.* " +
                    "FROM Spedizione AS S NATURAL JOIN Ordine AS O JOIN Cliente AS Cl " +
                    "ON Cl.NumeroTelefono = O.NumeroTelefonoMT " +
                    "JOIN Cliente AS Cl2 ON Cl2.NumeroTelefono = O.NumeroTelefonoDT " +
                    "JOIN Indirizzo AS I ON I.IDIndirizzo = Cl2.IDIndirizzo " +
                    "WHERE S.Tipo <> 'Singola' " +
                    "AND S.DataPrevista BETWEEN ? AND ? " +
                    "ORDER BY S.DataPrevista ASC");
            stmt.setDate(1, (java.sql.Date) dataInizio);
            stmt.setDate(2, (java.sql.Date) dataFine);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                DettagliSpedizioneDTO spedizioneCorrente;
                spedizioneCorrente = creaSpedizioneDTO(rs);
                listaSpedizioni.add(spedizioneCorrente);
            }
            stmt.close();
        } catch(SQLException e) {
            System.out.println("errore SQL: " + e);
        } catch(Exception e) {
            System.out.println("errore generico: " + e);
        }
        return listaSpedizioni;
    }
    
    private DettagliSpedizioneDTO creaSpedizioneDTO(ResultSet rs) throws SQLException {
        DettagliSpedizioneDTO dettagliSpedizioneDTO = new DettagliSpedizioneDTO();
        dettagliSpedizioneDTO.setNumeroTracciamento(rs.getInt("numerotracciamento"));
        dettagliSpedizioneDTO.setStatoSpedizione(rs.getString("stato"));
        dettagliSpedizioneDTO.setDataAffidamento(rs.getDate("dataaffidamento").toLocalDate());
        dettagliSpedizioneDTO.setDataPrevista(rs.getDate("dataprevista").toLocalDate());
        dettagliSpedizioneDTO.setTipoSpedizione(rs.getString("tipospedizione"));
        dettagliSpedizioneDTO.setNumeroOrdine(rs.getInt("numeroordine"));
        if (rs.getString("tipodt").equals("Aziendale"))
            dettagliSpedizioneDTO.setDestinatario(rs.getString("nomeaziendadt"));
        else
            dettagliSpedizioneDTO.setDestinatario(rs.getString("nomedt") + " " + rs.getString("cognomedt"));

        if (rs.getString("tipomt").equals("Aziendale"))
            dettagliSpedizioneDTO.setMittente(rs.getString("nomeaziendamt"));
        else
            dettagliSpedizioneDTO.setMittente(rs.getString("nomemt") + " " + rs.getString("cognomemt"));

        dettagliSpedizioneDTO.setIndirizzo(rs.getString("via") + " " + rs.getString("numerocivico") + ", " +
                rs.getString("cap") + " " + rs.getString("citta") + " " + rs.getString("provincia"));

        return dettagliSpedizioneDTO;
    }
    
    public void aggiornaDataSpedizioni(ArrayList<DettagliSpedizioneDTO> listaSpedizioni, Date dataPrevista, Connection conn){
        try{
            Statement stmt;
            stmt = conn.createStatement();
            String comando = "UPDATE Spedizione SET dataprevista = '" + dataPrevista + "' WHERE numeroTracciamento IN (";
            for (DettagliSpedizioneDTO dettagliSpedizioneDTO : listaSpedizioni){
                comando = comando.concat(dettagliSpedizioneDTO.getNumeroTracciamento() + ", ");
            }
            comando = comando.substring(0, comando.length() - 2);
            comando = comando.concat(")");
            stmt.executeUpdate(comando);
            stmt.close();
        } catch (SQLException e){
            System.out.println("SQL Exception: " + e);
        } catch (Exception e){
            System.out.println("Errore generico: " + e);
        }
    }
}