package sk.uniza.fri.wof.prostredie.npc;

public class NpcDialogHrana {
    private final String replikaHraca;
    private final NpcDialogVrchol vystupnyVrchol;

    public NpcDialogHrana(String replikaHraca, NpcDialogVrchol vystupnyVrchol) {
        this.replikaHraca = replikaHraca;
        this.vystupnyVrchol = vystupnyVrchol;
    }

    public String getReplikaHraca() {
        return this.replikaHraca;
    }

    public NpcDialogVrchol getVystupnyVrchol() {
        return this.vystupnyVrchol;
    }
}