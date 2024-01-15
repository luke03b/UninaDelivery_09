package org.UninaDelivery.Prodotto;

public class ProdottoDTO {
    private int codiceProdotto;
    private String nome;
    private float prezzo;
    private float peso;
    private String categoria;
    private String descrizione;
    private int quantitaOrdine;
    
    public int getQuantitaOrdine() {
        return quantitaOrdine;
    }
    
    public void setQuantitaOrdine(int quantitaOrdine) {
        this.quantitaOrdine = quantitaOrdine;
    }
    
    public int getCodiceProdotto() {
        return codiceProdotto;
    }
    
    public void setCodiceProdotto(int codiceProdotto) {
        this.codiceProdotto = codiceProdotto;
    }
    
    public String getNome() {
        return nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public float getPrezzo() {
        return prezzo;
    }
    
    public void setPrezzo(float prezzo) {
        this.prezzo = prezzo;
    }
    
    public float getPeso() {
        return peso;
    }
    
    public void setPeso(float peso) {
        this.peso = peso;
    }
    
    public String getCategoria() {
        return categoria;
    }
    
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
    
    public String getDescrizione() {
        return descrizione;
    }
    
    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }
}
