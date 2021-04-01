package sk.uniza.fri.wof.prostredie;

import sk.uniza.fri.wof.prostredie.predmety.IPredmet;

import java.util.TreeMap;

public class PredmetPortalGun implements IPredmet {
    private final String nazov;
    private final String popis;
    private final TreeMap<String, Miestnost> zoznamMiestnosti;

    public PredmetPortalGun(String nazov, String popis, TreeMap<String, Miestnost> zoznamMiestnosti) {

        this.nazov = nazov;
        this.popis = popis;
        this.zoznamMiestnosti = zoznamMiestnosti;
    }

    @Override
    public String getMeno() {
        return String.format("%s - %s", this.nazov, this.popis);
    }

    @Override
    public void pouziSa() {

    }
}
