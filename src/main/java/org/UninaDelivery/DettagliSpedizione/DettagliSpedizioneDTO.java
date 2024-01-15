package org.UninaDelivery.DettagliSpedizione;

import java.time.LocalDate;

public class DettagliSpedizioneDTO {
    private int numeroTracciamento;
    private String statoSpedizione;
    private LocalDate dataAffidamento;
    private LocalDate dataPrevista;
    private LocalDate dataConsegna;
    private Float prezzoSpedizione;
    private String tipoSpedizione;
    private int numeroOrdine;
    private String mittente;
    private String destinatario;
    private String numeroTelefono;
    private String nominativo;
    private String indirizzo;

    public int getNumeroTracciamento() {
        return numeroTracciamento;
    }

    public void setNumeroTracciamento(int numeroTracciamento) {
        this.numeroTracciamento = numeroTracciamento;
    }

    public String getStatoSpedizione() {
        return statoSpedizione;
    }

    public void setStatoSpedizione(String statoSpedizione) {
        this.statoSpedizione = statoSpedizione;
    }

    public LocalDate getDataAffidamento() {
        return dataAffidamento;
    }

    public void setDataAffidamento(LocalDate dataAffidamento) {
        this.dataAffidamento = dataAffidamento;
    }

    public LocalDate getDataPrevista() {
        return dataPrevista;
    }

    public void setDataPrevista(LocalDate dataPrevista) {
        this.dataPrevista = dataPrevista;
    }

    public LocalDate getDataConsegna() {
        return dataConsegna;
    }

    public void setDataConsegna(LocalDate dataConsegna) {
        this.dataConsegna = dataConsegna;
    }

    public Float getPrezzoSpedizione() {
        return prezzoSpedizione;
    }

    public void setPrezzoSpedizione(Float prezzoSpedizione) {
        this.prezzoSpedizione = prezzoSpedizione;
    }

    public String getTipoSpedizione() {
        return tipoSpedizione;
    }

    public void setTipoSpedizione(String tipoSpedizione) {
        this.tipoSpedizione = tipoSpedizione;
    }

    public int getNumeroOrdine() {
        return numeroOrdine;
    }

    public void setNumeroOrdine(int numeroOrdine) {
        this.numeroOrdine = numeroOrdine;
    }

    public String getMittente() {
        return mittente;
    }

    public void setMittente(String mittente) {
        this.mittente = mittente;
    }

    public String getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(String destinatario) {
        this.destinatario = destinatario;
    }

    public String getNumeroTelefono() {
        return numeroTelefono;
    }

    public void setNumeroTelefono(String numeroTelefono) {
        this.numeroTelefono = numeroTelefono;
    }

    public String getNominativo() {
        return nominativo;
    }

    public void setNominativo(String nominativo) {
        this.nominativo = nominativo;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }
}
