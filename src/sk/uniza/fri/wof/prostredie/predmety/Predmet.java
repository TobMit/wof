package sk.uniza.fri.wof.prostredie.predmety;

//import sk.uniza.fri.wof.hra.Hrac;

import sk.uniza.fri.wof.prostredie.Miestnost;

public class Predmet implements IPredmet {
    private final String meno;


    @Override
    public String getMeno() {
        return this.meno;
    }

    public Predmet(String nazov) {
        this.meno = nazov;

    }

    @Override
    public void pouziSa(Miestnost miestnost) {
        System.out.println("Pouzil si predmet!");

    }


}
