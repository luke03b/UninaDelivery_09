package org.UninaDelivery.Operatore;

import org.UninaDelivery.Exception.OperatoreNonTrovatoException;
import java.sql.*;

public class OperatoreDAO {
    public void ControllaLoginOperatore(int MatricolaInput, String PasswordInput, Connection conn) throws OperatoreNonTrovatoException {
        int count = 0;
        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT COUNT(matricola) " +
                    "FROM operatore " +
                    "WHERE matricola = ? AND passwordop = ?");
            stmt.setInt(1, MatricolaInput);
            stmt.setString(2, PasswordInput);
            
            ResultSet rs = stmt.executeQuery();

            if (rs.next())
                count = rs.getInt(1);
            
            stmt.close();
            rs.close();
        } catch (SQLException e) {
            System.out.println("Errore SQL: " + e);
        } catch (Exception e){
            System.out.println("Errore generico: " + e);
        }
        if(count == 0)
            throw new OperatoreNonTrovatoException();
    }

    public OperatoreDTO getOperatoreByMatricola(int matricola, Connection conn){
        OperatoreDTO operatoreDTO = new OperatoreDTO();
        try{
            PreparedStatement stmt = conn.prepareStatement("SELECT * " +
                    "FROM Operatore " +
                    "WHERE Matricola = ?");
            stmt.setInt(1, matricola);
            ResultSet rs = stmt.executeQuery();

            if (rs.next())
                operatoreDTO = creaOperatoreDTO(rs);

            stmt.close();
            rs.close();
        } catch(SQLException e) {
            System.out.println("errore SQL: " + e);
        } catch(Exception e) {
            System.out.println("errore generico: " + e);
        }
        return operatoreDTO;
    }
    private OperatoreDTO creaOperatoreDTO(ResultSet rs) throws SQLException{
        OperatoreDTO operatoreDTO = new OperatoreDTO();
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
        return operatoreDTO;
    }
}
