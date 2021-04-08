package sk.uniza.fri.wof.prostredie.npc;

import sk.uniza.fri.wof.hra.Hrac;

public class NpcDialogove extends Npc {
    private final INpcDialogVrchol korenDialogovehoStromu;

    public NpcDialogove(String meno, INpcDialogVrchol korenDialogovehoStromu) {
        super(meno);
        this.korenDialogovehoStromu = korenDialogovehoStromu;
    }

    public void hovor(Hrac hrac) {
        INpcDialogVrchol aktualny = this.korenDialogovehoStromu;
        do {
            aktualny = aktualny.vykonaj(hrac);
        } while (aktualny != null);
    }
}
