package org.UninaDelivery.MezziInUso;

import java.time.LocalDate;

public class MezziInUsoDTO {
    private int matricola;
    private String targa;
    private LocalDate dataUtilizzo;

    public int getMatricola() {
        return matricola;
    }

    public void setMatricola(int matricola) {
        this.matricola = matricola;
    }

    public String getTarga() {
        return targa;
    }

    public void setTarga(String targa) {
        this.targa = targa;
    }

    public LocalDate getDataUtilizzo() {
        return dataUtilizzo;
    }

    public void setDataUtilizzo(LocalDate dataUtilizzo) {
        this.dataUtilizzo = dataUtilizzo;
    }
}
