package sk.uniza.fri.wof.prostredie;

import sk.uniza.fri.wof.hra.Hrac;
import sk.uniza.fri.wof.prostredie.predmety.IPredmet;

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
        if (bum.nextBoolean()) {
            System.err.println("Bum. Granat ti vybuchol v ruke.\n");
            throw new SmrtException("Bum. Zomrel si.");
        } else {
            System.out.println("Tento krát si mal stastie.\n");
        }
    }
}
