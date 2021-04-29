package sk.uniza.fri.wof.hra;

import sk.uniza.fri.wof.hra.questy.Quest;
import sk.uniza.fri.wof.hra.questy.IQuestKontrolaMiestnosti;
import sk.uniza.fri.wof.hra.questy.IQuestKontrolaNPC;
import sk.uniza.fri.wof.hra.questy.IquestKontrolaPredmet;
import sk.uniza.fri.wof.prostredie.*;
import sk.uniza.fri.wof.prostredie.predmety.IKontorlaPolozenia;
import sk.uniza.fri.wof.prostredie.predmety.IPredmet;
import sk.uniza.fri.wof.prostredie.predmety.NepuzitelnyPredmetExceptions;
import sk.uniza.fri.wof.prostredie.predmety.QuestovyPredmet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.TreeMap;

public class Hrac {
    private final TreeMap<String, IPredmet> inventar;
    private final ArrayList<Quest> zoznamQuestov;
    private final Prostredie prostredie;
    private Miestnost aktualnaMiestnost;

    public Hrac(Prostredie prostredie) {
        this.prostredie = prostredie;
        this.aktualnaMiestnost = prostredie.getStartovaciaMiestnost();
        this.inventar = new TreeMap<>();
        this.zoznamQuestov = new ArrayList<>();
    }

    public Miestnost getAktualnaMiestnost() {
        return this.aktualnaMiestnost;
    }

    public void vypisPopisAktualnejMiestnosti() {
        this.aktualnaMiestnost.vypisPopisMiestnosti();
    }

    public void chodVSmere(String smer) throws NeexistujuciVychodException {
        Miestnost novaMiestnost = this.aktualnaMiestnost.getMiestnostVSmere(smer);

        if (novaMiestnost == null) {
            throw new NeexistujuciVychodException("Nespravny smer");
        }

        this.aktualnaMiestnost = novaMiestnost;

        for (Quest quest : this.zoznamQuestov) {
            if (quest instanceof IQuestKontrolaMiestnosti) {
                ((IQuestKontrolaMiestnosti)quest).hracVosielDoMiestnosti(this);
            }
        }

        this.skontrolujQuesty();


    }

    private void skontrolujQuesty() {
        ArrayList<Quest> ukoncene = new ArrayList<>();

        for (Quest quest : this.zoznamQuestov) {
            if (quest.getJeUkonceny()) {
                ukoncene.add(quest);
                System.out.printf("Vyriesil si quest %s%n", quest.getNazov());
            }
        }

        this.zoznamQuestov.removeAll(ukoncene);
    }


    public void zobrazInventar() {
        if (!this.inventar.isEmpty()) {
            System.out.printf("%s: \n", "Inventar");
            for (String meno : this.inventar.keySet()) {
                System.out.printf("\t%s\n", meno);
            }
        }
    }


    public void zoberPredmet(String nazov) throws NenanjdenyPredmetException {
        IPredmet predmet = this.aktualnaMiestnost.zoberPredmet(nazov);
        if (predmet == null) {
            throw new NenanjdenyPredmetException("Nemôžeš zobrať taký predmet.");
        }
        this.inventar.put(predmet.getMeno(), predmet);
    }

    public IPredmet getPredmet(String nazov) throws NenanjdenyPredmetException {
        IPredmet predmet = this.inventar.get(nazov);
        if (predmet == null) {
            throw new NenanjdenyPredmetException("Predmet sa neda pouzit!");
        }

        return predmet;
    }

    public void polozPredmet(String nazov) throws NenanjdenyPredmetException, PredmetSaNedaPolozitException {
        IPredmet predmet = this.getPredmet(nazov);

        if (predmet instanceof IKontorlaPolozenia) {
            if (!((IKontorlaPolozenia)predmet).getDaSaPolozit()) {
                throw new PredmetSaNedaPolozitException("Predmet sa neda polozit.");
            }
        }

        this.inventar.remove(nazov);

        this.aktualnaMiestnost.polozPredmet(predmet);

    }

    public void pouzPredmet(String nazov) throws NenanjdenyPredmetException, NepuzitelnyPredmetExceptions, SmrtException {
        IPredmet predmet = this.getPredmet(nazov);
        predmet.pouziSa(this.aktualnaMiestnost, this);
    }

    /**
     * Používa sa pri nakupovanie od obchodníka inak sa používa zoberPredmet(String....)
     */
    public void zoberPredmet(IPredmet predmet) {
        this.inventar.put(predmet.getMeno(), predmet);
    }

    public void pridelQuest(Quest quest) {
        this.zoznamQuestov.add(quest);
        System.out.printf("Quest %s bol prideleny\n", quest.getNazov());
    }

    public void zobrazQuestlog() {
        if (!this.zoznamQuestov.isEmpty()) {
            System.out.println("Tvoje questy: ");
            for (Quest quest : this.zoznamQuestov) {
                System.out.println("\t" + quest.getNazov());
            }
        } else {
            System.out.println("Nemas ziadne questy.");
        }
    }

    public void poziQusetovyPredmet(QuestovyPredmet questovyPredmet) {
        for (Quest quest : this.zoznamQuestov) {
            if (quest instanceof IquestKontrolaPredmet) {
                ((IquestKontrolaPredmet)quest).hracPouzilQuestovyPredmet(this, questovyPredmet);
            }
        }

        this.skontrolujQuesty();

    }

    public boolean getMaPredmet(String nazov) {
        return this.inventar.containsKey(nazov);
    }

    public boolean maPokracovatVQuestovomRozhovore(NpcDialogKontrolaQuestu kontrolaQuestu) {
        boolean maPokracovat = true;
        for (Quest quest : this.zoznamQuestov) {
            if (quest instanceof IQuestKontrolaNPC) {
                if (!((IQuestKontrolaNPC)quest).maPokracovatVQuestovomRozhovore(this, kontrolaQuestu)) {
                    maPokracovat = false;
                }
            }
        }

        this.skontrolujQuesty();

        return maPokracovat;
    }
    public void odstranPredmet(String nazov) {
        this.inventar.remove(nazov);
    }

    //Pozostatok serializácie
    public void nahradUdajeZoSave(Hrac nacitanyHrac) {
        this.aktualnaMiestnost = nacitanyHrac.aktualnaMiestnost;
        this.inventar.clear();
        this.inventar.putAll(nacitanyHrac.inventar);
        this.zoznamQuestov.clear();
        this.zoznamQuestov.addAll(nacitanyHrac.zoznamQuestov);
    }

    public void ulozPoziciu(DataOutputStream save) throws IOException {
        save.writeUTF(this.aktualnaMiestnost.getMenoMiestnosi());
        save.writeInt(this.inventar.size());
        for (IPredmet predmet : inventar.values()) {
            save.writeUTF(predmet.getMeno());
            predmet.ulozPoziciu(save);

        }

    }

    public void nacitajPoziciu(DataInputStream save, int saveVersion) throws IOException {
        String miestnost = save.readUTF();
        this.aktualnaMiestnost = this.prostredie.getMiestnost(miestnost);
        this.inventar.clear();

        //predmety sa načítavajú len od nejakej verzie
        if (saveVersion >= 1) {
            int pocetPredmetov = save.readInt();
            for (int i = 0; i < pocetPredmetov; i++) {
                String predmet = save.readUTF();
                IPredmet predmetObject = this.prostredie.newPredmet(predmet);
                this.inventar.put(predmetObject.getMeno(), predmetObject);
                if (saveVersion >= 2) {
                    predmetObject.nacitajPoziciu(save, saveVersion);
                }
            }
        }
    }
}