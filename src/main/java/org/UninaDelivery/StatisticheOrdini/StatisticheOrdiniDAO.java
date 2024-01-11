package org.UninaDelivery.StatisticheOrdini;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StatisticheOrdiniDAO {
    public StatisticheOrdineDTO getStatisticheOrdine(int mese,Connection conn){
        StatisticheOrdineDTO statisticheOrdineDTO = new StatisticheOrdineDTO();
        try{
            getNumeroMedioOrdiniMensili(mese, statisticheOrdineDTO, conn);
            getOrdineNumProdottiMaxMensile(mese, statisticheOrdineDTO, conn);
            getOrdineNumProdottiMinMensile(mese, statisticheOrdineDTO, conn);
        } catch (SQLException e) {
            System.out.println("errore sql: " + e);
        } catch (Exception e) {
            System.out.println("errore: " + e);
        }
        return statisticheOrdineDTO;
    }

    private void getNumeroMedioOrdiniMensili(int mese, StatisticheOrdineDTO statisticheOrdineDTO, Connection conn) throws SQLException{
        PreparedStatement stmt = conn.prepareStatement("SELECT AVG(NumOrdiniMese.NumeroOrdini) " +
                "FROM (SELECT COUNT(O.NumeroOrdine) AS NumeroOrdini, date_part('MONTH', O.DataOrdine) AS Mese " +
                "FROM Ordine AS O " +
                "GROUP BY (date_part('MONTH', O.DataOrdine))) AS NumOrdiniMese " +
                "WHERE Mese = ? " +
                "GROUP BY Mese");
        stmt.setInt(1, mese);
        ResultSet rs = stmt.executeQuery();
        while(rs.next()){
            statisticheOrdineDTO.setAVGNumOrdini(rs.getDouble(1));
        }
    }

    private void getOrdineNumProdottiMaxMensile(int mese, StatisticheOrdineDTO statisticheOrdineDTO, Connection conn) throws SQLException{
        PreparedStatement stmt = conn.prepareStatement("SELECT SUM(PV2.Quantita), O2.NumeroOrdine, date_part('MONTH', O2.DataOrdine) AS Mese " +
                "FROM Ordine AS O2 NATURAL JOIN ProdottiVenduti AS PV2 " +
                "WHERE date_part('MONTH', O2.DataOrdine) = ? " +
                "GROUP BY (date_part('MONTH', O2.DataOrdine), O2.NumeroOrdine) " +
                "HAVING (SUM(PV2.Quantita) = (SELECT MAX(NumProdottiInOrdine.NumProdotti) " +
                "FROM\t(SELECT SUM(PV.Quantita) AS NumProdotti, date_part('MONTH', O.DataOrdine) AS Mese " +
                "FROM Ordine AS O NATURAL JOIN ProdottiVenduti AS PV " +
                "GROUP BY (date_part('MONTH', O.DataOrdine), O.NumeroOrdine)) AS NumProdottiInOrdine " +
                "WHERE Mese = ? " +
                "GROUP BY (Mese)))");
        stmt.setInt(1, mese);
        stmt.setInt(2, mese);
        ResultSet rs = stmt.executeQuery();
        while(rs.next()){
            statisticheOrdineDTO.setMaxNumProdottiInOrdine(rs.getInt(1));
        }
    }

    private void getOrdineNumProdottiMinMensile(int mese, StatisticheOrdineDTO statisticheOrdineDTO, Connection conn) throws SQLException{
        PreparedStatement stmt = conn.prepareStatement("SELECT SUM(PV2.Quantita), O2.NumeroOrdine, date_part('MONTH', O2.DataOrdine) AS Mese " +
                "FROM Ordine AS O2 NATURAL JOIN ProdottiVenduti AS PV2 " +
                "WHERE date_part('MONTH', O2.DataOrdine) = ? " +
                "GROUP BY (date_part('MONTH', O2.DataOrdine), O2.NumeroOrdine) " +
                "HAVING (SUM(PV2.Quantita) = (SELECT MIN(NumProdottiInOrdine.NumProdotti) " +
                "FROM (SELECT SUM(PV.Quantita) AS NumProdotti, date_part('MONTH', O.DataOrdine) AS Mese " +
                "FROM Ordine AS O NATURAL JOIN ProdottiVenduti AS PV " +
                "GROUP BY (date_part('MONTH', O.DataOrdine), O.NumeroOrdine)) AS NumProdottiInOrdine " +
                "WHERE Mese = ? " +
                "GROUP BY (Mese)))");
        stmt.setInt(1, mese);
        stmt.setInt(2, mese);
        ResultSet rs = stmt.executeQuery();
        while(rs.next()){
            statisticheOrdineDTO.setMinNumProdottiInOrdine(rs.getInt(1));
        }
    }
}
