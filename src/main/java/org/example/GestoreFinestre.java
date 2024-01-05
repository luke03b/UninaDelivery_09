package org.example;
import org.example.Operatore.OperatoreDAO;

import java.sql.*;

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

    public void EffettuaLogin(String Matricola, String Password, LoginForm parent){
        OperatoreDAO operatoreDAO = new OperatoreDAO();
        int matricola = Integer.parseInt(Matricola);
        if (operatoreDAO.ControllaLoginOperatore(matricola, Password, conn) == 0){
            parent.mostraMessageDialog("Matricola o Password non validi", "Errore");
        } else {
            HomePage homePage = new HomePage(null, this);
            loginForm.setVisible(false);
            homePage.setVisible(true);
            parent.dispose();
        }
    }
}

