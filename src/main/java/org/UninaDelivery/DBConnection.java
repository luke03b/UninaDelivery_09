package org.UninaDelivery;
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
    
    public Connection getConnection(){
        try{
            if (conn == null || conn.isClosed()) {
                Class.forName("org.postgresql.Driver");
                String url = "jdbc:postgresql://localhost:5432/postgres?currentSchema=\"dbUninaDelivery\"";
                conn = DriverManager.getConnection(url, "postgres", "24112003");
                System.out.println("Connessione al database riuscita");
            }
        } catch(ClassNotFoundException e) {
            System.out.println("DB driver non trovato");
            System.out.println(e);
        } catch(SQLException e) {
            System.out.println("Connessione al database fallita");
            System.out.println(e);
        }
        return conn;
    }
    
    public void closeConnection(Connection conn) throws SQLException {
        conn.close();
    }
}
