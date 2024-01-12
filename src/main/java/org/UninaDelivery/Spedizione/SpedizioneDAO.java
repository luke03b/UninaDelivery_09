package org.UninaDelivery.Spedizione;

import org.UninaDelivery.Controller;
import org.UninaDelivery.Exception.SpedizioniNonEffettuateException;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class SpedizioneDAO {
    public boolean creaSpedizione(ArrayList<Object> dettagliSpedizione, Connection conn, Controller controller) throws SpedizioniNonEffettuateException {
        try{
            Statement stmt = null;
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
            if (numeroRigheInserite < ((int[]) dettagliSpedizione.get(3)).length)
                throw new SpedizioniNonEffettuateException(numeroRigheInserite, controller);
            System.out.println("Tutte le spedizioni effettuate con successo");
        } catch (SQLException e) {
            System.out.println("SQL Exception: " + e);
        }
        return true;
    }
}
