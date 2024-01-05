package org.example.Operatore;
import java.sql.*;

public class OperatoreDAO {
    public int ControllaLoginOperatore(int MatricolaInput, String PasswordInput, Connection conn){
        
        int count = 0;
        OperatoreDTO operatoreDTO = new OperatoreDTO();
        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT COUNT(matricola) FROM operatore WHERE matricola = ? AND passwordop = ?");
//            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Operatore WHERE Matricola = ? AND Password = ?");
            stmt.setInt(1, MatricolaInput);
            stmt.setString(2, PasswordInput);
            
            ResultSet rs = stmt.executeQuery();
            if (rs.next()){
//                AggiungiOperatoreDTO(operatoreDTO, rs);
                count = rs.getInt(1);
            }
            
            stmt.close();
            rs.close();
        } catch (SQLException e) {
            System.out.println("Errore SQL");
            System.out.println(e);
        } catch (Exception e){
            System.out.println("Errore generico");
            System.out.println(e);
        }
        
        return count;
    }
    
//    private void AggiungiOperatoreDTO(OperatoreDTO operatoreDTO, ResultSet rs) throws SQLException{
//        operatoreDTO.setMatricola(rs.getInt("matricola"));
//        operatoreDTO.setNome(rs.getString("nome"));
//        operatoreDTO.setCognome(rs.getString("cognome"));
//        operatoreDTO.setCodiceFiscale(rs.getString("codicefiscale"));
//        operatoreDTO.setEta(rs.getInt("eta"));
//        operatoreDTO.setStipendio(rs.getFloat("stipendio"));
//        operatoreDTO.setPasswordOp(rs.getString("password"));
//        operatoreDTO.setDataNascita(rs.getDate("datanascita").toLocalDate());
//        operatoreDTO.setDataAssunzione(rs.getDate("dataassunzione").toLocalDate());
//        operatoreDTO.setPivaAzienda(rs.getString("piivazienda"));
//    }
}
