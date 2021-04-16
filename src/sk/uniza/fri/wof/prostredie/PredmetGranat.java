package sk.uniza.fri.wof.prostredie;

import sk.uniza.fri.wof.hra.Hrac;
import sk.uniza.fri.wof.prostredie.predmety.IPredmet;
import sk.uniza.fri.wof.prostredie.predmety.NepuzitelnyPredmetExceptions;

import java.util.Random;

public class PredmetGranat implements IPredmet {
    private final String nazov;

    public PredmetGranat(String nazov) {
        this.nazov = nazov;
    }

    @Override
    public String getMeno() {
        return this.nazov;
    }

    @Override
    public void pouziSa(Miestnost aktualnaMiestnost, Hrac hrac) throws SmrtException {
        Random bum = new Random();
        if (bum.nextInt(2) > 0) {
            throw new SmrtException("Bum. Zomrel si.");
        } else {
            System.out.println("Mas stastie.");
        }
    }
}
