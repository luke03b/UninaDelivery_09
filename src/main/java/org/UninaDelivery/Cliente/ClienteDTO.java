package org.UninaDelivery.Cliente;

import java.time.LocalDate;

public class ClienteDTO {
    private String NumeroTelefono;
    private String Mail;
    private String CodiceFiscale;
    private LocalDate DataNascita;
    private String Nominativo;
    private String PartitaIVA;
    private String Tipo;
    private String Ruolo;
    private int IdIndirizzo;

    public String getNumeroTelefono() {
        return NumeroTelefono;
    }

    public void setNumeroTelefono(String numeroTelefono) {
        NumeroTelefono = numeroTelefono;
    }

    public String getMail() {
        return Mail;
    }

    public void setMail(String mail) {
        Mail = mail;
    }

    public String getCodiceFiscale() {
        return CodiceFiscale;
    }

    public void setCodiceFiscale(String codiceFiscale) {
        CodiceFiscale = codiceFiscale;
    }

    public LocalDate getDataNascita() {
        return DataNascita;
    }

    public void setDataNascita(LocalDate dataNascita) {
        DataNascita = dataNascita;
    }

    public String getNominativo() {
        return Nominativo;
    }

    public void setNominativo(String nominativo) {
        Nominativo = nominativo;
    }

    public String getPartitaIVA() {
        return PartitaIVA;
    }

    public void setPartitaIVA(String partitaIVA) {
        PartitaIVA = partitaIVA;
    }

    public String getTipo() {
        return Tipo;
    }

    public void setTipo(String tipo) {
        Tipo = tipo;
    }

    public String getRuolo() {
        return Ruolo;
    }

    public void setRuolo(String ruolo) {
        Ruolo = ruolo;
    }

    public int getIdIndirizzo() {
        return IdIndirizzo;
    }

    public void setIdIndirizzo(int idIndirizzo) {
        IdIndirizzo = idIndirizzo;
    }
}
