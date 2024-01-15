package org.UninaDelivery.Cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ClienteDAO {

    public ArrayList<ClienteDTO> getClientiDTO(Connection conn){
        ArrayList<ClienteDTO> listaClienti = new ArrayList<>();

        try {
            PreparedStatement stmt = conn.prepareStatement(
                    "SELECT DISTINCT C.* " +
                        "FROM cliente C " +
                        "JOIN ordine O ON C.numeroTelefono IN (O.numeroTelefonoDT, O.numeroTelefonoMT)");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                ClienteDTO clienteCorrente;
                clienteCorrente = creaClienteDTO(rs);
                listaClienti.add(clienteCorrente);
            }

        } catch (SQLException e){
            System.out.println("errore SQL: " + e);
        } catch (Exception e) {
            System.out.println("errore generico: " + e);
        }
        return listaClienti;
    }

    private ClienteDTO creaClienteDTO(ResultSet rs) throws SQLException {
        ClienteDTO clienteCorrente = new ClienteDTO();
        if (rs.getString("tipo").equals("Aziendale"))
            clienteCorrente.setNominativo(rs.getString("nomeazienda"));
        else
            clienteCorrente.setNominativo(rs.getString("nome") + " " + rs.getString("cognome"));
        clienteCorrente.setNumeroTelefono(rs.getString("numerotelefono"));
        return clienteCorrente;
    }
}
