package sk.uniza.fri.wof.hra.questy;

import sk.uniza.fri.wof.hra.Hrac;
import sk.uniza.fri.wof.prostredie.NpcDialogKontrolaQuestu;
import sk.uniza.fri.wof.prostredie.predmety.QuestovyPredmet;

public interface IQuest {
    void hracVosielDoMiestnosti(Hrac hrac);
    void hracPouzilQuestovyPredmet(Hrac hrac, QuestovyPredmet questovyPredmet);
    boolean getJeUkonceny();
    String getNazov();
    boolean maPokracovatVQuestovomRozhovore(Hrac hrac, NpcDialogKontrolaQuestu kontrolaQuestu);
}
