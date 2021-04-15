package sk.uniza.fri.wof.hra.questy;

import sk.uniza.fri.wof.hra.Hrac;
import sk.uniza.fri.wof.prostredie.NpcDialogKontrolaQuestu;
import sk.uniza.fri.wof.prostredie.predmety.Predmet;
import sk.uniza.fri.wof.prostredie.predmety.QuestovyPredmet;

public class QuestAspirin implements IQuest, IQuestKontrolaMiestnosti, IQuestKontrolaNPC {
    private final String nazov;
    private boolean jeUkonceny;
    private boolean bolAspirinSpavnuty;
    public QuestAspirin(String aspirin) {
        this.nazov = aspirin;
        this.jeUkonceny = false;
    }


    @Override
    public void hracVosielDoMiestnosti(Hrac hrac) {
         if (hrac.getAktualnaMiestnost().getPopis().startsWith("WC")) {
            if (!this.bolAspirinSpavnuty) {
                hrac.getAktualnaMiestnost().polozPredmet(new Predmet("aspirin"));
                this.bolAspirinSpavnuty = true;
            }
        }
    }


    @Override
    public boolean getJeUkonceny() {
        return this.jeUkonceny;
    }

    @Override
    public String getNazov() {
        return this.nazov;
    }

    @Override
    public boolean maPokracovatVQuestovomRozhovore(Hrac hrac, NpcDialogKontrolaQuestu kontrolaQuestu) {
        if (hrac.getMaPredmet("aspirin")) {
            hrac.odstranPredmet("aspirin");
            this.jeUkonceny = true;
            return false;
        } else {
            return true;
        }
    }

}

