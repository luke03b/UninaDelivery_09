package org.UninaDelivery;
import org.UninaDelivery.Controllori.ControlloreDAO;
import org.UninaDelivery.Controllori.ControlloreFinestre;

public class Starter {
    private ControlloreFinestre controlloreFinestre = new ControlloreFinestre();
    private ControlloreDAO controlloreDAO = new ControlloreDAO(controlloreFinestre);
    public static void main(String[] args){
        Starter starter = new Starter();
    }

    public Starter() {
        controlloreFinestre.setControlloreDAO(controlloreDAO);
        SplashPage splashPage = controlloreFinestre.apriSplashPage();
        controlloreFinestre.chiudiPage(splashPage);
        controlloreFinestre.apriLogin();
    }
}