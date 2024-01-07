package org.UninaDelivery.Operatore;
import java.sql.*;

public class OperatoreDAO {
    public boolean ControllaLoginOperatore(int MatricolaInput, String PasswordInput, Connection conn){
        
        int count = 0;
        OperatoreDTO operatoreDTO = new OperatoreDTO();
        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT COUNT(matricola) FROM operatore WHERE matricola = ? AND passwordop = ?");
            stmt.setInt(1, MatricolaInput);
            stmt.setString(2, PasswordInput);
            
            ResultSet rs = stmt.executeQuery();
            if (rs.next()){
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
        
        return count != 0;
    }

    public OperatoreDTO getOperatoreByMatricola(int matricola, Connection conn){
        OperatoreDTO operatoreDTO = new OperatoreDTO();
        try{
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Operatore WHERE Matricola = ?");
            stmt.setInt(1, matricola);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()){
                AggiungiOperatoreDTO(operatoreDTO, rs);
            }
        } catch(SQLException e) {
            System.out.println(e);
        }
        return operatoreDTO;
    }
    private void AggiungiOperatoreDTO(OperatoreDTO operatoreDTO, ResultSet rs) throws SQLException{
        operatoreDTO.setMatricola(rs.getInt("matricola"));
        operatoreDTO.setNome(rs.getString("nome"));
        operatoreDTO.setCognome(rs.getString("cognome"));
        operatoreDTO.setCodiceFiscale(rs.getString("codicefiscale"));
        operatoreDTO.setEta(rs.getInt("eta"));
        operatoreDTO.setStipendio(rs.getFloat("stipendio"));
        operatoreDTO.setPasswordOp(rs.getString("passwordop"));
        operatoreDTO.setDataNascita(rs.getDate("datanascita").toLocalDate());
        operatoreDTO.setDataAssunzione(rs.getDate("dataassunzione").toLocalDate());
        operatoreDTO.setPivaAzienda(rs.getString("pivaazienda"));
    }
}
