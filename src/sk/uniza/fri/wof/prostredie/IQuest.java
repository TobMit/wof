package sk.uniza.fri.wof.prostredie;

import sk.uniza.fri.wof.hra.Hrac;

public interface IQuest {
    void hracVosielDoMiestnosti(Hrac hrac);
    void hracPouzilQuestovyPredmet(Hrac hrac, QuestovyPredmet questovyPredmet);
    boolean getJeUkonceny();

    String getNazov();
}
