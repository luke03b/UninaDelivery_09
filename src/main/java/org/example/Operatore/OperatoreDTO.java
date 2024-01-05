package org.example.Operatore;
import java.time.LocalDate;

public class OperatoreDTO {
    private int Matricola;
    private String Nome;
    private String Cognome;
    private String CodiceFiscale;
    private int Eta;
    private float Stipendio;
    private String PasswordOp;
    private LocalDate DataNascita;
    private LocalDate DataAssunzione;
    private String PivaAzienda;
    
    public int getMatricola() {
        return Matricola;
    }
    
    public void setMatricola(int matricola) {
        Matricola = matricola;
    }
    
    public String getNome() {
        return Nome;
    }
    
    public void setNome(String nome) {
        Nome = nome;
    }
    
    public String getCognome() {
        return Cognome;
    }
    
    public void setCognome(String cognome) {
        Cognome = cognome;
    }
    
    public String getCodiceFiscale() {
        return CodiceFiscale;
    }
    
    public void setCodiceFiscale(String codiceFiscale) {
        CodiceFiscale = codiceFiscale;
    }
    
    public int getEta() {
        return Eta;
    }
    
    public void setEta(int eta) {
        Eta = eta;
    }
    
    public float getStipendio() {
        return Stipendio;
    }
    
    public void setStipendio(float stipendio) {
        Stipendio = stipendio;
    }
    
    public String getPasswordOp() {
        return PasswordOp;
    }
    
    public void setPasswordOp(String passwordOp) {
        PasswordOp = passwordOp;
    }
    
    public LocalDate getDataNascita() {
        return DataNascita;
    }
    
    public void setDataNascita(LocalDate dataNascita) {
        DataNascita = dataNascita;
    }
    
    public LocalDate getDataAssunzione() {
        return DataAssunzione;
    }
    
    public void setDataAssunzione(LocalDate dataAssunzione) {
        DataAssunzione = dataAssunzione;
    }
    
    public String getPivaAzienda() {
        return PivaAzienda;
    }
    
    public void setPivaAzienda(String pivaAzienda) {
        PivaAzienda = pivaAzienda;
    }
}
