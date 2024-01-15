package org.UninaDelivery.Spedizione;

import org.UninaDelivery.Controllori.ControlloreFinestre;
import org.UninaDelivery.Exception.AlcuneSpedizioniNonEffettuateException;
import org.UninaDelivery.Exception.NessunaSpedizioneEffettuataException;

import java.awt.*;
import java.sql.*;
import java.util.ArrayList;

public class SpedizioneDAO {
    public boolean inserisciSpedizione(ArrayList<Object> dettagliSpedizione, Connection conn, Component parent, ControlloreFinestre controlloreFinestre) throws AlcuneSpedizioniNonEffettuateException, NessunaSpedizioneEffettuataException {
        try{
            Statement stmt;
            stmt = conn.createStatement();
            String comando = "INSERT INTO Spedizione (NumeroTracciamento, Descrizione, Stato, DataAffidamento, " +
                    "DataPrevista, DataConsegna, PrezzoSpedizione, Porto, Tipo, MatricolaOperatore, MatricolaCorriere, " +
                    "TargaTrasporto, NumeroOrdine) VALUES ";
            for (int numeroOrdine : (int[]) dettagliSpedizione.get(3)){
                comando = comando.concat("(DEFAULT, NULL, DEFAULT, DEFAULT, CURRENT_DATE + 3, NULL, 3.5, 'Franco', 'Singola', "
                        + dettagliSpedizione.get(0) + ", " + dettagliSpedizione.get(1) + ", '" + dettagliSpedizione.get(2) +
                        "', " + numeroOrdine + "), ");
            }
            comando = comando.substring(0, comando.length()-2);
            int numeroRigheInserite = stmt.executeUpdate(comando);
            if (numeroRigheInserite == 0)
                throw new NessunaSpedizioneEffettuataException(parent, controlloreFinestre);
            if (numeroRigheInserite < ((int[]) dettagliSpedizione.get(3)).length)
                throw new AlcuneSpedizioniNonEffettuateException(parent, controlloreFinestre, numeroRigheInserite);
            System.out.println("Tutte le spedizioni effettuate con successo");
            stmt.close();
        } catch (SQLException e) {
            System.out.println("SQL Exception: " + e);
        } catch (Exception e) {
            System.out.println("errore generico: " + e);
        }
        return true;
    }
}
