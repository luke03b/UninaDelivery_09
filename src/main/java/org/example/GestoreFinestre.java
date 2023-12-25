package org.example;

public class GestoreFinestre {
    public static void main(String[] args) throws Exception{
        DBConnection dbConnection = DBConnection.getDBConnection();
        dbConnection.getConnection();
        
        LoginPage loginPage = new LoginPage(null);
    }
}