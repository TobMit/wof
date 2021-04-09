package sk.uniza.fri.wof.prostredie.predmety;

//import sk.uniza.fri.wof.hra.Hrac;

import sk.uniza.fri.wof.hra.Hrac;
import sk.uniza.fri.wof.prostredie.Miestnost;

public class Predmet implements IPredmet {
    private final String meno;
    private boolean pouzity = false;


    @Override
    public String getMeno() {
        return this.meno;
    }

    public Predmet(String nazov) {
        this.meno = nazov;

    }

    @Override
    public void pouziSa(Miestnost miestnost, Hrac hrac) {
        System.out.println("Pouzil si predmet!");

    }



}
