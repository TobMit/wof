package sk.uniza.fri.wof.prostredie.npc.vypocet;
/**
 * NEPUŽITÉ
 */

public class Vrchol {


    private int tV;
    private Vrchol xV;
    private final int oznacenie;
    private final String nazov;

    public Vrchol(int oznacenie, String nazov) {
        this.oznacenie = oznacenie;
        this.nazov = nazov;
        this.tV = 0;
        this.xV = null;
    }


    public int gettV() {
        return this.tV;
    }

    public void settV(int tV) {
        this.tV = tV;
    }

    public Vrchol getxV() {
        return this.xV;
    }

    public void setxV(Vrchol xV) {
        this.xV = xV;
    }

    public String getNazov() {
        return this.nazov;
    }

    public int getOznacenie() {
        return this.oznacenie;
    }

    @Override
    public String toString() {
        return "Vrchol{" +
                "tV=" + this.tV +
                ", xV=" + this.xV +
                ", oznacenie=" + this.oznacenie +
                ", nazov='" + this.nazov + '\'' +
                '}';
    }
}
