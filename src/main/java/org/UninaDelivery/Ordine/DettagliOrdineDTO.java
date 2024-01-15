package org.UninaDelivery.Ordine;

import java.time.LocalDate;

public class DettagliOrdineDTO {
    private int numeroOrdine;
    private LocalDate dataOrdine;
    private String mittente;
    private String destinatario;
    private String indirizzo;
    private float peso;
    private String grandezza;
    
    public int getNumeroOrdine() {
        return numeroOrdine;
    }
    
    public void setNumeroOrdine(int numeroOrdine) {
        this.numeroOrdine = numeroOrdine;
    }
    
    public LocalDate getDataOrdine() {
        return dataOrdine;
    }
    
    public void setDataOrdine(LocalDate dataOrdine) {
        this.dataOrdine = dataOrdine;
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
    
    public String getIndirizzo() {
        return indirizzo;
    }
    
    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }
    
    public float getPeso() {
        return peso;
    }
    
    public void setPeso(float peso) {
        this.peso = peso;
    }
    
    public String getGrandezza() {
        return grandezza;
    }
    
    public void setGrandezza(String grandezza) {
        this.grandezza = grandezza;
    }
}
