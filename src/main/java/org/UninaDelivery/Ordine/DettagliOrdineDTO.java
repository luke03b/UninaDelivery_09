package org.UninaDelivery.Ordine;

import java.time.LocalDate;

public class DettagliOrdineDTO {
    private int NumeroOrdine;
    private LocalDate DataOrdine;
    private String Mittente;
    private String Destinatario;
    private String Indirizzo;
    private float Peso;
    private String Grandezza;
    
    public int getNumeroOrdine() {
        return NumeroOrdine;
    }
    
    public void setNumeroOrdine(int numeroOrdine) {
        NumeroOrdine = numeroOrdine;
    }
    
    public LocalDate getDataOrdine() {
        return DataOrdine;
    }
    
    public void setDataOrdine(LocalDate dataOrdine) {
        DataOrdine = dataOrdine;
    }
    
    public String getMittente() {
        return Mittente;
    }
    
    public void setMittente(String mittente) {
        Mittente = mittente;
    }
    
    public String getDestinatario() {
        return Destinatario;
    }
    
    public void setDestinatario(String destinatario) {
        Destinatario = destinatario;
    }
    
    public String getIndirizzo() {
        return Indirizzo;
    }
    
    public void setIndirizzo(String indirizzo) {
        Indirizzo = indirizzo;
    }
    
    public float getPeso() {
        return Peso;
    }
    
    public void setPeso(float peso) {
        Peso = peso;
    }
    
    public String getGrandezza() {
        return Grandezza;
    }
    
    public void setGrandezza(String grandezza) {
        Grandezza = grandezza;
    }
}
