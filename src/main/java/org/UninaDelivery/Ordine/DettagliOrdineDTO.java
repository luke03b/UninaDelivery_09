package org.UninaDelivery.Ordine;

import java.time.LocalDate;

public class DettagliOrdineDTO {
    private LocalDate DataOrdine;
    private String Nominativo;
    private String Indirizzo;
    private float Peso;
    private String Grandezza;
    
    public LocalDate getDataOrdine() {
        return DataOrdine;
    }
    
    public void setDataOrdine(LocalDate dataOrdine) {
        DataOrdine = dataOrdine;
    }
    
    public String getNominativo() {
        return Nominativo;
    }
    
    public void setNominativo(String nominativo) {
        Nominativo = nominativo;
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
