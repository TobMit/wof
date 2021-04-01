package sk.uniza.fri.wof.prostredie.predmety;

import sk.uniza.fri.wof.prostredie.Miestnost;
import sk.uniza.fri.wof.prostredie.Prostredie;
import sk.uniza.fri.wof.prostredie.predmety.IPredmet;

import java.util.TreeMap;

public class PredmetPortalGun implements IPredmet, IKontorlaPolozenia {
    private final String nazov;
    private final String popis;
    private final Prostredie prostredie;
    private boolean cerveny;
    private boolean modry;
    private boolean puzity;
    private  Miestnost miestnostModry;
    private  Miestnost miestnostCerveny;

    public PredmetPortalGun(String nazov, String popis, TreeMap<String, Miestnost> zoznamMiestnosti) {

        this.nazov = nazov;
        this.popis = popis;
        this.prostredie = prostredie;
        this.modry = false;
        this.cerveny = false;
        this.miestnostModry = null;
        this.miestnostCerveny = null;
        this.puzity = false;
    }

    @Override
    public String getMeno() {
        return this.nazov;
    }

    @Override
    public void pouziSa() {

    }

    @Override
    public boolean getDaSaPolozit() {
        return true;
    }
}
