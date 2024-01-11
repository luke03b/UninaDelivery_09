package org.UninaDelivery.MezzoTrasporto;

public class MezzoTrasportoDTO {
    private String Targa;
    private String Modello;
    private float CapienzaPeso;
    private float CapienzaLitri;
    private String Marca;
    private String Tipo;
    
    public String getTarga() {
        return Targa;
    }
    
    public void setTarga(String targa) {
        Targa = targa;
    }
    
    public String getModello() {
        return Modello;
    }
    
    public void setModello(String modello) {
        Modello = modello;
    }
    
    public float getCapienzaPeso() {
        return CapienzaPeso;
    }
    
    public void setCapienzaPeso(float capienzaPeso) {
        CapienzaPeso = capienzaPeso;
    }
    
    public float getCapienzaLitri() {
        return CapienzaLitri;
    }
    
    public void setCapienzaLitri(float capienzaLitri) {
        CapienzaLitri = capienzaLitri;
    }
    
    public String getMarca() {
        return Marca;
    }
    
    public void setMarca(String marca) {
        Marca = marca;
    }
    
    public String getTipo() {
        return Tipo;
    }
    
    public void setTipo(String tipo) {
        Tipo = tipo;
    }
}
