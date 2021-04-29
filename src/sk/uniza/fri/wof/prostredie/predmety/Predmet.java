package sk.uniza.fri.wof.prostredie.predmety;

//import sk.uniza.fri.wof.hra.Hrac;

import sk.uniza.fri.wof.hra.Hrac;
import sk.uniza.fri.wof.prostredie.Miestnost;

import java.io.DataInputStream;
import java.io.DataOutputStream;

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
    public void pouziSa(Miestnost miestnost, Hrac hrac) throws NepuzitelnyPredmetExceptions {
        throw new NepuzitelnyPredmetExceptions("Predmet sa neda pouzit.");

    }

    @Override
    public void ulozPoziciu(DataOutputStream save) {

    }

    @Override
    public void nacitajPoziciu(DataInputStream save, int saveVersion) {

    }



}
