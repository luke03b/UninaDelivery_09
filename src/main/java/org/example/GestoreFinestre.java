package org.example;

public class GestoreFinestre {

    LoginForm loginForm;


    public static void main(String[] args) throws Exception{
        // Esegue la connessione con il database
        DBConnection dbConnection = DBConnection.getDBConnection();
        dbConnection.getConnection();

        GestoreFinestre gestoreFinestre = new GestoreFinestre();
        
        dbConnection.closeConnection();
    }

    public GestoreFinestre () {
        loginForm = new LoginForm(null,this);
        loginForm.setVisible(true);
    }

}

