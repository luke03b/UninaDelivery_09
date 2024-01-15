package org.UninaDelivery.MezziInUso;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MezziInUsoDAO {
    public void aggiungiMezziInUso(int matricola, String targa, Connection conn){
        try {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO MezziInUso " +
                    "VALUES " +
                    "(?, ?, CURRENT_DATE + 3)");
            stmt.setInt(1, matricola);
            stmt.setString(2, targa);
            stmt.executeQuery();
            stmt.close();
        } catch (SQLException e) {
            System.out.println("errore SQL: " + e);
        }
    }
}