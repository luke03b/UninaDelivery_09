package org.UninaDelivery.MezzoTrasporto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MezzoTrasportoDAO {
    public ArrayList<MezzoTrasportoDTO> getMezziDisponibili(float totPeso, Connection conn){
        ArrayList<MezzoTrasportoDTO> listaMezziDisponibili = new ArrayList<>();
        
        try{
            PreparedStatement stmt = conn.prepareStatement(
                    "SELECT * " +
                    "FROM MezzoTrasporto " +
                    "WHERE Targa NOT IN (SELECT Targa " +
                                          "FROM MezziInUso " +
                                         "WHERE DataUtilizzo = CURRENT_DATE + 3) " +
                           "AND MezzoTrasporto.CapienzaPeso >= ?");
            stmt.setFloat(1, totPeso);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                MezzoTrasportoDTO mezzoCorrente;
                mezzoCorrente = creaMezzoDTO(rs);
                listaMezziDisponibili.add(mezzoCorrente);
            }
        } catch (SQLException e){
            System.out.println("errore SQL: " + e);
            throw new RuntimeException();
        } catch (Exception e){
            System.out.println("errore generico: " + e);
        }
        
        return listaMezziDisponibili;
    }
    
    private MezzoTrasportoDTO creaMezzoDTO(ResultSet rs) throws SQLException{
        MezzoTrasportoDTO mezzoCorrente = new MezzoTrasportoDTO();
        mezzoCorrente.setTarga(rs.getString("targa"));
        mezzoCorrente.setModello(rs.getString("modello"));
        mezzoCorrente.setCapienzaPeso(rs.getFloat("capienzapeso"));
        mezzoCorrente.setCapienzaLitri(rs.getFloat("capienzalitri"));
        mezzoCorrente.setMarca(rs.getString("marca"));
        mezzoCorrente.setTipo(rs.getString("tipo"));
        return mezzoCorrente;
    }
}
