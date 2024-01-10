package org.UninaDelivery.Prodotto;

import org.UninaDelivery.Ordine.DettagliOrdineDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProdottoDAO {
    public ArrayList<ProdottoDTO> getProdottiDaOrdine(int numeroOrdine, Connection conn){
        ArrayList<ProdottoDTO> listaProdotti = new ArrayList<ProdottoDTO>();
        
        try{
            PreparedStatement stmt = conn.prepareStatement(
                    "SELECT Prodotto.*, ProdottiVenduti.Quantita" +
                        "FROM Ordine JOIN ProdottiVenduti ON Ordine.NumeroOrdine = ProdottiVenduti.NumeroOrdine " +
                        "JOIN Prodotto ON ProdottiVenduti.CodiceProdotto = Prodotto.CodiceProdotto" +
                        "WHERE Ordine.NumeroOrdine = ?");
            stmt.setInt(1, numeroOrdine);
            
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                ProdottoDTO prodottoCorrente = new ProdottoDTO();
                creaProdottoDTO(prodottoCorrente, rs);
                listaProdotti.add(prodottoCorrente);
            }
            
        } catch (SQLException e) {
            System.out.println(e);
            throw new RuntimeException(e);
        } catch (Exception e) {
            System.out.println(e);
        }
        
        return listaProdotti;
    }
    
    private void creaProdottoDTO(ProdottoDTO prodottoDTO, ResultSet rs) throws SQLException {
        prodottoDTO.setCodiceProdotto(rs.getInt("codiceprodotto"));
        prodottoDTO.setNome(rs.getString("nome"));
        prodottoDTO.setPrezzo(rs.getFloat("prezzo"));
        prodottoDTO.setPeso(rs.getFloat("peso"));
        prodottoDTO.setCategoria(rs.getString("categoria"));
        prodottoDTO.setDescrizione(rs.getString("descrizione"));
        prodottoDTO.setQuantitaOrdine(rs.getInt("quantita"));
    }
}
