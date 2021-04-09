package sk.uniza.fri.wof.prostredie;

import sk.uniza.fri.wof.hra.Hrac;
import sk.uniza.fri.wof.prostredie.npc.INpcDialogVrchol;
public class NpcDialogKontrolaQuestu implements INpcDialogVrchol {

    private final String identifikator;
    private final INpcDialogVrchol pokracovanie;

    public NpcDialogKontrolaQuestu(String identifikator, INpcDialogVrchol pokracovanie) {
        this.identifikator = identifikator;
        this.pokracovanie = pokracovanie;
    }

    @Override
    public INpcDialogVrchol vykonaj(Hrac hrac) {
        if (hrac.maPokracovatVQuestovomRozhovore(this)) {
            return this.pokracovanie;
        }
        return null;
    }
}
