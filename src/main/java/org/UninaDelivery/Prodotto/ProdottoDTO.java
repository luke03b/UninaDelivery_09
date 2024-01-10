package org.UninaDelivery.Prodotto;

public class ProdottoDTO {
    private int CodiceProdotto;
    private String Nome;
    private float Prezzo;
    private float Peso;
    private String Categoria;
    private String Descrizione;
    private int QuantitaOrdine;
    
    public int getQuantitaOrdine() {
        return QuantitaOrdine;
    }
    
    public void setQuantitaOrdine(int quantitaOrdine) {
        QuantitaOrdine = quantitaOrdine;
    }
    
    public int getCodiceProdotto() {
        return CodiceProdotto;
    }
    
    public void setCodiceProdotto(int codiceProdotto) {
        CodiceProdotto = codiceProdotto;
    }
    
    public String getNome() {
        return Nome;
    }
    
    public void setNome(String nome) {
        Nome = nome;
    }
    
    public float getPrezzo() {
        return Prezzo;
    }
    
    public void setPrezzo(float prezzo) {
        Prezzo = prezzo;
    }
    
    public float getPeso() {
        return Peso;
    }
    
    public void setPeso(float peso) {
        Peso = peso;
    }
    
    public String getCategoria() {
        return Categoria;
    }
    
    public void setCategoria(String categoria) {
        Categoria = categoria;
    }
    
    public String getDescrizione() {
        return Descrizione;
    }
    
    public void setDescrizione(String descrizione) {
        Descrizione = descrizione;
    }
}
