package org.UninaDelivery;
import org.UninaDelivery.Controllori.ControlloreDAO;
import org.UninaDelivery.Controllori.ControlloreFinestre;

public class Starter {
    ControlloreFinestre controlloreFinestre = new ControlloreFinestre();
    ControlloreDAO controlloreDAO = new ControlloreDAO(controlloreFinestre);
    public static void main(String[] args){
        Starter starter = new Starter();
    }

    public Starter() {
        controlloreFinestre.setControlloreDAO(controlloreDAO);
        SplashPage splashPage = controlloreFinestre.apriSplashPage();
        controlloreFinestre.chiudiSplashPage(splashPage);
        controlloreFinestre.apriLogin();
    }
}