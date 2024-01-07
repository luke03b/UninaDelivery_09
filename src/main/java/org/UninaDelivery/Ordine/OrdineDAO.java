package org.UninaDelivery.Ordine;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrdineDAO {
    public ArrayList<DettagliOrdineDTO> getOrdiniNonSpediti(Connection conn){
        ArrayList<DettagliOrdineDTO> listaOrdini = new ArrayList<DettagliOrdineDTO>();
        
        try{
            PreparedStatement stmt = conn.prepareStatement(
                    "SELECT Ordine.DataOrdine, Cliente.Tipo, Cliente.Nome, Cliente.Cognome, Cliente.NomeAzienda, " +
                            "Indirizzo.Via, Indirizzo.NumeroCivico, " +
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
    
    private void creaOrdineDTO(DettagliOrdineDTO nuovoOrdine, ResultSet rs) throws SQLException {
        String IndirizzoCompleto; //variabile d'appoggio
        nuovoOrdine.setDataOrdine(rs.getDate("dataordine").toLocalDate());
        
        if (rs.getString("tipo").equals("Aziendale"))
            nuovoOrdine.setNominativo(rs.getString("nomeazienda"));
        else
            nuovoOrdine.setNominativo(rs.getString("nome") + " " + rs.getString("cognome"));
        
        IndirizzoCompleto = rs.getString("via") + " " + rs.getString("numerocivico") + ", " +
                rs.getString("cap") + " " + rs.getString("citta") + " " + rs.getString("provincia");
        nuovoOrdine.setIndirizzo(IndirizzoCompleto);
        nuovoOrdine.setPeso(rs.getFloat("peso"));
        nuovoOrdine.setGrandezza(rs.getString("grandezza"));
    }
}
