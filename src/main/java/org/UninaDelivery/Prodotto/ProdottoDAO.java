package org.UninaDelivery.Prodotto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProdottoDAO {
    public ArrayList<ProdottoDTO> getProdottiDaOrdine(int numeroOrdine, Connection conn){
        ArrayList<ProdottoDTO> listaProdotti = new ArrayList<>();
        
        try{
            PreparedStatement stmt = conn.prepareStatement(
                    "SELECT Prodotto.*, ProdottiVenduti.Quantita " +
                        "FROM Ordine JOIN ProdottiVenduti ON Ordine.NumeroOrdine = ProdottiVenduti.NumeroOrdine " +
                        "JOIN Prodotto ON ProdottiVenduti.CodiceProdotto = Prodotto.CodiceProdotto " +
                        "WHERE Ordine.NumeroOrdine = ?");
            stmt.setInt(1, numeroOrdine);
            
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                ProdottoDTO prodottoCorrente;
                prodottoCorrente = creaProdottoDTO(rs);
                listaProdotti.add(prodottoCorrente);
            }

            stmt.close();
            rs.close();

        } catch (SQLException e) {
            System.out.println("errore SQL: " + e);
            throw new RuntimeException(e);
        } catch (Exception e) {
            System.out.println("errore generico: " + e);
        }
        
        return listaProdotti;
    }
    
    private ProdottoDTO creaProdottoDTO(ResultSet rs) throws SQLException {
        ProdottoDTO prodottoDTO = new ProdottoDTO();
        prodottoDTO.setCodiceProdotto(rs.getInt("codiceprodotto"));
        prodottoDTO.setNome(rs.getString("nome"));
        prodottoDTO.setPrezzo(rs.getFloat("prezzo"));
        prodottoDTO.setPeso(rs.getFloat("peso"));
        prodottoDTO.setCategoria(rs.getString("categoria"));
        prodottoDTO.setDescrizione(rs.getString("descrizione"));
        prodottoDTO.setQuantitaOrdine(rs.getInt("quantita"));
        return prodottoDTO;
    }
}
