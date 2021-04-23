package sk.uniza.fri.wof.hra.questy;

import sk.uniza.fri.wof.hra.Hrac;
import sk.uniza.fri.wof.prostredie.predmety.QuestovyPredmet;

import java.io.Serializable;

public class PredmetSpawner {
    private final String nazovMiestnosti;
    private final QuestovyPredmet predmet;
    private boolean bolSpawnuty;

    public PredmetSpawner(String nazovMiestnosti, QuestovyPredmet predmet) {
        this.nazovMiestnosti = nazovMiestnosti;
        this.predmet = predmet;
        this.bolSpawnuty = false;
    }

    public void spavniAkTreba(Hrac hrac) {
        if (hrac.getAktualnaMiestnost().getPopis().startsWith(this.nazovMiestnosti)) {
            if (!this.bolSpawnuty) {
                hrac.getAktualnaMiestnost().polozPredmet(this.predmet);
                this.bolSpawnuty = true;
            }
        }
    }
}
