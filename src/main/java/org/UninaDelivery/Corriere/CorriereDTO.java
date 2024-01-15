package org.UninaDelivery.Corriere;

import java.time.LocalDate;

public class CorriereDTO {
    private int matricola;
    private String nome;
    private String cognome;
    private String codiceFiscale;
    private int eta;
    private float stipendio;
    private String passwordCr;
    private LocalDate dataNascita;
    private LocalDate dataAssunzione;
    private String pivaAzienda;

    public int getMatricola() {
        return matricola;
    }

    public void setMatricola(int matricola) {
        this.matricola = matricola;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getCodiceFiscale() {
        return codiceFiscale;
    }

    public void setCodiceFiscale(String codiceFiscale) {
        this.codiceFiscale = codiceFiscale;
    }

    public int getEta() {
        return eta;
    }

    public void setEta(int eta) {
        this.eta = eta;
    }

    public float getStipendio() {
        return stipendio;
    }

    public void setStipendio(float stipendio) {
        this.stipendio = stipendio;
    }

    public String getPasswordCr() {
        return passwordCr;
    }

    public void setPasswordCr(String passwordCr) {
        this.passwordCr = passwordCr;
    }

    public LocalDate getDataNascita() {
        return dataNascita;
    }

    public void setDataNascita(LocalDate dataNascita) {
        this.dataNascita = dataNascita;
    }

    public LocalDate getDataAssunzione() {
        return dataAssunzione;
    }

    public void setDataAssunzione(LocalDate dataAssunzione) {
        this.dataAssunzione = dataAssunzione;
    }

    public String getPivaAzienda() {
        return pivaAzienda;
    }

    public void setPivaAzienda(String pivaAzienda) {
        this.pivaAzienda = pivaAzienda;
    }
}
