package sk.uniza.fri.wof.prostredie.npc;

import java.io.Serializable;

public abstract class Npc {

    private final String meno;

    public Npc(String meno) {
        this.meno = meno;
    }

    public String getMeno() {
        return this.meno;
    }
}