package sk.uniza.fri.wof.hra.questy;

import sk.uniza.fri.wof.hra.Hrac;
import sk.uniza.fri.wof.prostredie.NpcDialogKontrolaQuestu;
import sk.uniza.fri.wof.prostredie.predmety.QuestovyPredmet;

public class QuestAspirin extends Quest implements  IQuestKontrolaMiestnosti, IQuestKontrolaNPC {
    private final String nazov;
    private final PredmetSpawner aspirinSpawner;
    public QuestAspirin(String aspirin) {
        this.nazov = aspirin;
        this.aspirinSpawner = new PredmetSpawner("Wc", new QuestovyPredmet("Aspirin"));
    }


    @Override
    public void hracVosielDoMiestnosti(Hrac hrac) {
        this.aspirinSpawner.spavniAkTreba(hrac);
    }

    @Override
    public String getNazov() {
        return this.nazov;
    }

    @Override
    public boolean maPokracovatVQuestovomRozhovore(Hrac hrac, NpcDialogKontrolaQuestu kontrolaQuestu) {
        if (hrac.getMaPredmet("aspirin")) {
            hrac.odstranPredmet("aspirin");
            this.ukonci();
            return false;
        } else {
            return true;
        }
    }

}

