package sk.uniza.fri.wof.prostredie.npc.vypocet;
/**
 * NEPUŽITÉ
 */
public class Hrana {

    private final Vrchol prvyVrchol;
    private final Vrchol druhyVrchol;
    private final int ohodnotenieHrany;

    public Hrana(Vrchol prvyVrchol, Vrchol druhyVrchol, int ohodnotenieHrany) {
        this.prvyVrchol = prvyVrchol;
        this.druhyVrchol = druhyVrchol;
        this.ohodnotenieHrany = ohodnotenieHrany;
    }

    public int getOhodnotenieHrany() {
        return this.ohodnotenieHrany;
    }

    public Vrchol getPrvyVrchol() {
        return this.prvyVrchol;
    }

    public Vrchol getDruhyVrchol() {
        return this.druhyVrchol;
    }
}
