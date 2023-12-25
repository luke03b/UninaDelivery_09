package org.example;
import java.sql.*;

public class DBConnection {
    // istanza statica e privata di questa classe
    private static DBConnection dbcon = null;
    // istanza privata della connessione ad SQL
    private Connection conn = null;
    
    // costruttore private
    private DBConnection(){}
    
    // metodo pubblico per ottenere una istanza della classe privata
    public static DBConnection getDBConnection()
    {   // se la classe connessione è nulla, la crea
        if (dbcon == null) {
            dbcon = new DBConnection();
        }
        // e la restituisce
        return dbcon;
    }
    
    
    public void getConnection() throws Exception{
        try{
            Class.forName("org.postgres.driver");
            String url = "jdbc:postgresql://localhost:5432/postgres";
            Connection conn = DriverManager.getConnection(url, "postgres", "24112003");
            System.out.println("Connessione al database riuscita");
            conn.close();
        }catch(ClassNotFoundException e){
            System.out.println("DB driver non trovato");
            System.out.println(e);
        }catch(SQLException e){
            System.out.println("Connessione al database fallita");
            System.out.println(e);
        }
    }
}
