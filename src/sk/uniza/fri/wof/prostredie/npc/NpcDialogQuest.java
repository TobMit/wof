package sk.uniza.fri.wof.prostredie.npc;

import sk.uniza.fri.wof.hra.Hrac;

public class NpcDialogQuest implements INpcDialogVrchol {
    private final String repilika;
    private Quest quest;

    public NpcDialogQuest(String repilika, Quest quest) {
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
