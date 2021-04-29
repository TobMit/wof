package sk.uniza.fri.wof.prostredie;

import sk.uniza.fri.wof.prostredie.npc.Npc;
import sk.uniza.fri.wof.prostredie.predmety.IPredmet;

import java.io.Serializable;
import java.util.Set;
import java.util.TreeMap;

/**
 * Trieda sk.uniza.fri.wof.prostredie.Miestnost realizuje jednu miestnost/priestor v celom priestore hry.
 * Kazda "miestnost" je z inymi miestnostami spojena vychodmi.
 * Vychody z miestnosti su oznacovane svetovymi stranami sever, vychod, juh
 * a zapad. Pre kazdy vychod si miestnost pamata odkaz na susednu miestnost
 * alebo null, ak tym smerom vychod nema.
 *
 * @author  Michael Kolling, David J. Barnes
 * @version 2006.03.30
 * @author  lokalizacia: Lubomir Sadlon, Jan Janech
 * @version 2012.02.21
 */
public class Miestnost {
    private final String popisMiestnosti;
    // prvý je smer a ciel
    private final TreeMap<String, Miestnost> vychody;
    private final TreeMap<String, Npc> npccka;
    private final TreeMap<String, IPredmet> predmety;
    private final String menoMiestnosti;

    /**
     * Vytvori miestnost popis ktorej je v parametrom.
     * Po vytvoreni miestnost nema ziadne vychody. Popis miesnost strucne
     * charakterizuje.
     *
     * @param popis text popisu miestnosti.
     * @param nazov meno miestnosti
     */
    public Miestnost(String popis, String nazov) {
        this.popisMiestnosti = popis;
        this.vychody = new TreeMap<>();
        this.npccka = new TreeMap<>();
        this.predmety = new TreeMap<>();
        this.menoMiestnosti = nazov;
    }

    public void nastavVychod(String smer, Miestnost ciel) {
        this.vychody.put(smer, ciel);
    }

    /**
     * @return textovy popis miestnosti.
     */
    public String getNazov() {
        return this.menoMiestnosti;
    }

    public String getPopis() {
        return this.popisMiestnosti;
    }

    public void vypisPopisMiestnosti() {
        System.out.println("Teraz si v miestnosti " + this.getNazov() + " " + this.getPopis());

        this.vypisMiestnosti("Vychody", this.vychody);
        this.vypisPolozky("NPC", this.npccka.keySet());
        this.vypisPolozky("Predmety", this.predmety.keySet());
    }

    private void vypisPolozky(String nadpis, Set<String> polozky) {
        if (!polozky.isEmpty()) {
            System.out.printf("%s: \n", nadpis);
            for (String meno : polozky) {
                System.out.printf("\t%s\n", meno);
            }
        }
    }

    private void vypisMiestnosti(String nadpis, TreeMap<String, Miestnost> vychody) {

        System.out.printf("%s: \n", nadpis);

        for (String meno : vychody.keySet()) {
            System.out.printf("\t%s [%s]\n", meno, vychody.get(meno).getMenoMiestnosi());
        }

    }

    public Miestnost getMiestnostVSmere(String smer) {
        return this.vychody.get(smer);
    }

    public void postavNpc(Npc npc) {
        this.npccka.put(npc.getMeno(), npc);
    }

    public Npc getNpc(String meno) {
        return this.npccka.get(meno);
    }

    public void polozPredmet(IPredmet predmet) {
        this.predmety.put(predmet.getMeno(), predmet);
    }

    public IPredmet zoberPredmet(String predmet) {
        return this.predmety.remove(predmet);
    }
    public int getPocetVychodov() {
        return this.vychody.size();
    }

    public TreeMap<String, Miestnost> getZoznamVychodov() {
        return this.vychody;
    }

    public String getMenoMiestnosi() {
        return this.menoMiestnosti;
    }

    public void vymazVychod(String nazovVychodu) {
        if (vychody.containsKey(nazovVychodu)) {
            this.vychody.remove(nazovVychodu);
        }
    }
}