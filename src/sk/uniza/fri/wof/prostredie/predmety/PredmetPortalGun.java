package sk.uniza.fri.wof.prostredie.predmety;

import sk.uniza.fri.wof.prostredie.Miestnost;
import sk.uniza.fri.wof.prostredie.predmety.IPredmet;

import java.util.TreeMap;

public class PredmetPortalGun implements IPredmet {
    private final String nazov;
    private final String popis;
    private final TreeMap<String, Miestnost> zoznamMiestnosti;
    private final boolean modry;
    private final boolean puzity;
    private final boolean cerveny;
    private final Miestnost miestnostModry;
    private final Miestnost miestnostCerveny;

    public PredmetPortalGun(String nazov, String popis, TreeMap<String, Miestnost> zoznamMiestnosti) {

        this.nazov = nazov;
        this.popis = popis;
        this.zoznamMiestnosti = zoznamMiestnosti;
        this.modry = false;
        this.cerveny = false;
        this.miestnostModry = null;
        this.miestnostCerveny = null;
        this.puzity = false;
    }

    @Override
    public String getMeno() {
        return String.format("%s - %s", this.nazov, this.popis);
    }

    @Override
    public void pouziSa() {

    }
}
