package sk.uniza.fri.wof.prostredie.npc;

public class NpcDialogove extends Npc {
    private final INpcDialogVrchol korenDialogovehoStromu;

    public NpcDialogove(String meno, INpcDialogVrchol korenDialogovehoStromu) {
        super(meno);
        this.korenDialogovehoStromu = korenDialogovehoStromu;
    }

    public void hovor() {
        INpcDialogVrchol aktualny = this.korenDialogovehoStromu;
        do {
            aktualny = aktualny.vykonaj();
        } while (aktualny != null);
    }
}
