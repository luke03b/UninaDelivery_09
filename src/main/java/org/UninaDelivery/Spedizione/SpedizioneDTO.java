package org.UninaDelivery.Spedizione;

import java.time.LocalDate;

public class SpedizioneDTO {
    int numeroTracciamento;
    String statoSpedizione;
    LocalDate dataAffidamento;
    LocalDate dataPrevista;
    LocalDate dataConsegna;
    Float prezzoSpedizione;
    String tipo;
    int matricolaOperatore;
    int matricolaCorriere;
    String targaTrasporto;
    int numeroOrdine;
    
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
    
    public String getTipo() {
        return tipo;
    }
    
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    public int getMatricolaOperatore() {
        return matricolaOperatore;
    }
    
    public void setMatricolaOperatore(int matricolaOperatore) {
        this.matricolaOperatore = matricolaOperatore;
    }
    
    public int getMatricolaCorriere() {
        return matricolaCorriere;
    }
    
    public void setMatricolaCorriere(int matricolaCorriere) {
        this.matricolaCorriere = matricolaCorriere;
    }
    
    public String getTargaTrasporto() {
        return targaTrasporto;
    }
    
    public void setTargaTrasporto(String targaTrasporto) {
        this.targaTrasporto = targaTrasporto;
    }
    
    public int getNumeroOrdine() {
        return numeroOrdine;
    }
    
    public void setNumeroOrdine(int numeroOrdine) {
        this.numeroOrdine = numeroOrdine;
    }
}
