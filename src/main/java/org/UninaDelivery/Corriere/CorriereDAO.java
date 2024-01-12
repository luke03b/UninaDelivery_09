package org.UninaDelivery.Corriere;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CorriereDAO {
    public ArrayList<CorriereDTO> getCorrieriDisponibili(Connection conn){
        ArrayList<CorriereDTO> listaCorrieriDisp = new ArrayList<>();

        try{
            PreparedStatement stmt = conn.prepareStatement("((SELECT Cr.* " +
                    "FROM Corriere AS Cr) " +
                    "EXCEPT " +
                    "(SELECT Cr.* " +
                    "FROM MezziInUso AS MIU NATURAL JOIN Corriere AS Cr " +
                    "WHERE MIU.DataUtilizzo = CURRENT_DATE + 3))");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                CorriereDTO CorriereCorrente= new CorriereDTO();
                creaCorriereDTO(CorriereCorrente, rs);
                listaCorrieriDisp.add(CorriereCorrente);
            }
        } catch (SQLException exception) {
            System.out.println("Errore sql: " + exception);
        } catch (Exception exception){
            System.out.println("Errore generico: " + exception);
        }
        return listaCorrieriDisp;
    }

    private CorriereDTO creaCorriereDTO(CorriereDTO corriereDTO, ResultSet rs) throws SQLException {
        corriereDTO.setMatricola(rs.getInt("matricola"));
        corriereDTO.setNome(rs.getString("nome"));
        corriereDTO.setCognome(rs.getString("cognome"));
        corriereDTO.setCodiceFiscale(rs.getString("codicefiscale"));
        corriereDTO.setEta(rs.getInt("eta"));
        corriereDTO.setStipendio(rs.getFloat("stipendio"));
        corriereDTO.setPasswordCr(rs.getString("passwordcr"));
        corriereDTO.setDataNascita(rs.getDate("datanascita").toLocalDate());
        corriereDTO.setDataAssunzione(rs.getDate("dataassunzione").toLocalDate());
        corriereDTO.setPivaAzienda(rs.getString("pivaazienda"));
        return corriereDTO;
    }
}
