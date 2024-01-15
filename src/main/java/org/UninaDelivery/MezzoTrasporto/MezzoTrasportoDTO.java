package org.UninaDelivery.MezzoTrasporto;

public class MezzoTrasportoDTO {
    private String targa;
    private String modello;
    private float capienzaPeso;
    private float capienzaLitri;
    private String marca;
    private String tipo;
    
    public String getTarga() {
        return targa;
    }
    
    public void setTarga(String targa) {
        this.targa = targa;
    }
    
    public String getModello() {
        return modello;
    }
    
    public void setModello(String modello) {
        this.modello = modello;
    }
    
    public float getCapienzaPeso() {
        return capienzaPeso;
    }
    
    public void setCapienzaPeso(float capienzaPeso) {
        this.capienzaPeso = capienzaPeso;
    }
    
    public float getCapienzaLitri() {
        return capienzaLitri;
    }
    
    public void setCapienzaLitri(float capienzaLitri) {
        this.capienzaLitri = capienzaLitri;
    }
    
    public String getMarca() {
        return marca;
    }
    
    public void setMarca(String marca) {
        this.marca = marca;
    }
    
    public String getTipo() {
        return tipo;
    }
    
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
