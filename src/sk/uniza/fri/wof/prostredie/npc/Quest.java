package sk.uniza.fri.wof.prostredie.npc;

import sk.uniza.fri.wof.prostredie.Miestnost;

public class Quest {
    private final String quest;


    public Quest(String quest) {
        this.quest = quest;
    }

    public void hracVosielDoMiestnosti(Miestnost miestnost) {
        if (miestnost.getPopis().startsWith("RA006")) {
            System.out.println("Vyriesil si quest! " + this.getNazov());
        }
    }

    public String getNazov() {
        return this.quest;
    }

}
