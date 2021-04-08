package sk.uniza.fri.wof.prostredie.npc;

import sk.uniza.fri.wof.prostredie.Miestnost;

public class Quest {
    private final String quest;
    private boolean jeUkonceny;


    public Quest(String quest) {
        this.quest = quest;
        this.jeUkonceny = false;
    }

    public void hracVosielDoMiestnosti(Miestnost miestnost) {
        if (miestnost.getPopis().startsWith("RA006")) {
            System.out.println("Vyriesil si quest! " + this.getNazov());
            this.jeUkonceny = true;
        }
    }

    public boolean getJeUkonceny() {
        return this.jeUkonceny;
    }

    public String getNazov() {
        return this.quest;
    }

}
