package org.UninaDelivery.StatisticheOrdini;

import java.util.ArrayList;

public class StatisticheOrdineDTO {
    private double AVGNumOrdini;
    private int maxNumProdottiInOrdine;
    private int minNumProdottiInOrdine;


    public double getAVGNumOrdini() {
        return AVGNumOrdini;
    }

    public void setAVGNumOrdini(double AVGNumOrdini) {
        this.AVGNumOrdini = AVGNumOrdini;
    }

    public int getMaxNumProdottiInOrdine() {
        return maxNumProdottiInOrdine;
    }

    public void setMaxNumProdottiInOrdine(int maxNumProdottiInOrdine) {
        this.maxNumProdottiInOrdine = maxNumProdottiInOrdine;
    }

    public int getMinNumProdottiInOrdine() {
        return minNumProdottiInOrdine;
    }

    public void setMinNumProdottiInOrdine(int minNumProdottiInOrdine) {
        this.minNumProdottiInOrdine = minNumProdottiInOrdine;
    }
}
