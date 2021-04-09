package sk.uniza.fri.wof.prostredie.npc;

public class NpcDialogHrana {
    private final String replikaHraca;
    private final INpcDialogVrchol vystupnyVrchol;

    public NpcDialogHrana(String replikaHraca, INpcDialogVrchol vystupnyVrchol) {
        this.replikaHraca = replikaHraca;
        this.vystupnyVrchol = vystupnyVrchol;
    }

    public String getReplikaHraca() {
        return this.replikaHraca;
    }

    public INpcDialogVrchol getVystupnyVrchol() {
        return this.vystupnyVrchol;
    }
}