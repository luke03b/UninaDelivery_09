package org.UninaDelivery;
import org.UninaDelivery.Controllori.ControlloreDAO;
import org.UninaDelivery.Controllori.ControlloreFinestre;

public class Starter {
    ControlloreDAO controlloreDAO = new ControlloreDAO();
    ControlloreFinestre controlloreFinestre = new ControlloreFinestre(controlloreDAO);
    public static void main(String[] args){
        Starter starter = new Starter();
    }

    public Starter() {
        SplashPage splashPage = controlloreFinestre.apriSplashPage();
        controlloreFinestre.chiudiSplashPage(splashPage);
        controlloreFinestre.apriLogin();
    }
}

