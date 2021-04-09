package sk.uniza.fri.wof.prostredie.npc;

import sk.uniza.fri.wof.hra.Hrac;
import sk.uniza.fri.wof.prostredie.IQuest;
import sk.uniza.fri.wof.prostredie.Quest;

public class NpcDialogQuest implements INpcDialogVrchol {
    private final String repilika;
    private IQuest quest;

    public NpcDialogQuest(String repilika, IQuest quest) {
        this.repilika = repilika;
        this.quest = quest;
    }

    @Override
    public NpcDialogQuest vykonaj(Hrac hrac) {

        if (this.quest != null) {
            System.out.printf("%s\n", this.repilika);
            hrac.pridelQuest(this.quest);
            this.quest = null;
        } else {
            System.out.println("Uz ti nemam co povedat.");
        }
        return null;
    }
}
