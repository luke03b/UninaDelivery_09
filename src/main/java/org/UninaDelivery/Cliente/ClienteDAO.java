package org.UninaDelivery.Cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ClienteDAO {

    public ArrayList<ClienteDTO> recuperaClientiDTO(Connection conn){
        ArrayList<ClienteDTO> listaClienti = new ArrayList<>();

        try {
            PreparedStatement stmt = conn.prepareStatement(
                    "SELECT C.* FROM cliente C JOIN ordine O ON C.numeroTelefono IN (O.numeroTelefonoDT, O.numeroTelefonoMT)");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                ClienteDTO clienteCorrente = new ClienteDTO();
                creaClienteDTO(clienteCorrente, rs);
                listaClienti.add(clienteCorrente);
            }

        } catch (SQLException e){
            System.out.println(e);
        } catch (Exception e) {
            System.out.println(e);
        }
        return listaClienti;
    }

    private void creaClienteDTO(ClienteDTO clienteCorrente, ResultSet rs) throws SQLException {

        if (rs.getString("tipo").equals("Aziendale"))
            clienteCorrente.setNominativo(rs.getString("nomeazienda"));
        else
            clienteCorrente.setNominativo(rs.getString("nome") + " " + rs.getString("cognome"));
    }
}
