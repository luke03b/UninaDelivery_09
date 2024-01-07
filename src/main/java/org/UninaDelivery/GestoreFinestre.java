package org.UninaDelivery;
import org.UninaDelivery.Operatore.OperatoreDAO;
import org.UninaDelivery.Operatore.OperatoreDTO;
import org.UninaDelivery.Ordine.DettagliOrdineDTO;
import org.UninaDelivery.Ordine.OrdineDAO;

import java.sql.*;
import java.util.ArrayList;

public class GestoreFinestre {

    LoginForm loginForm;
    static DBConnection dbConnection;
    static Connection conn;

    public static void main(String[] args) throws Exception{
        // Esegue la connessione con il database
        dbConnection = DBConnection.getDBConnection();
        conn = dbConnection.getConnection();
        
        GestoreFinestre gestoreFinestre = new GestoreFinestre();
        
//        dbConnection.closeConnection(conn);
    }

    public GestoreFinestre () {
        loginForm = new LoginForm(null,this);
        loginForm.setVisible(true);
    }
    
    public ArrayList<DettagliOrdineDTO> RecuperaOrdiniNonSpediti(){
        OrdineDAO ordineDAO = new OrdineDAO();
        return ordineDAO.getOrdiniNonSpediti(conn);
    }
    
    public void EffettuaLogin(String Matricola, String Password, LoginForm parent){
        OperatoreDAO operatoreDAO = new OperatoreDAO();
        int matricola = Integer.parseInt(Matricola);
        if (operatoreDAO.ControllaLoginOperatore(matricola, Password, conn) == 0){
            parent.mostraMessageDialog("Matricola o Password non validi", "Errore");
        } else {
            OperatoreDTO operatoreDTO = operatoreDAO.getOperatoreByMatricola(matricola, conn);
            HomePage homePage = new HomePage(null, this, operatoreDTO);
            loginForm.setVisible(false);
            homePage.setVisible(true);
            parent.dispose();
        }
    }

    public void TornaLogin(HomePage homePage){
        homePage.setVisible(false);
        homePage.dispose();
        apriLogin();
    }

    public void apriLogin(){
        loginForm = new LoginForm(null,this);
        loginForm.setVisible(true);
    }
}

