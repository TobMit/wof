package sk.uniza.fri.wof.prostredie.npc;

import sk.uniza.fri.wof.hra.Hrac;

import java.io.Serializable;

public interface INpcDialogVrchol extends Serializable {
    INpcDialogVrchol vykonaj(Hrac hrac);
}
