package org.UninaDelivery.MezzoTrasporto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class MezzoTrasportoDAO {
    public ArrayList<MezzoTrasportoDTO> getMezziDisponibili(Connection conn){
        ArrayList<MezzoTrasportoDTO> listaMezziDisponibili = new ArrayList<>();
        
        try{
            PreparedStatement stmt = conn.prepareStatement(
                    "SELECT * " +
                    "FROM MezzoTrasporto " +
                    "WHERE Targa NOT IN (SELECT Targa FROM MezziInUso WHERE DataUtilizzo = CURRENT_DATE + 3)");
            
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                MezzoTrasportoDTO mezzoCorrente = new MezzoTrasportoDTO();
                creaMezzoDTO(mezzoCorrente, rs);
                listaMezziDisponibili.add(mezzoCorrente);
            }
        } catch (SQLException e){
            System.out.println(e);
            throw new RuntimeException();
        } catch (Exception e){
            System.out.println(e);
        }
        
        return listaMezziDisponibili;
    }
    
    private void creaMezzoDTO(MezzoTrasportoDTO mezzoCorrente, ResultSet rs) throws SQLException{
        mezzoCorrente.setTarga(rs.getString("targa"));
        mezzoCorrente.setModello(rs.getString("modello"));
        mezzoCorrente.setCapienzaPeso(rs.getFloat("capienzapeso"));
        mezzoCorrente.setCapienzaLitri(rs.getFloat("capienzalitri"));
        mezzoCorrente.setMarca(rs.getString("marca"));
        mezzoCorrente.setTipo(rs.getString("tipo"));
    }
}
