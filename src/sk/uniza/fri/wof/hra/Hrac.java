package sk.uniza.fri.wof.hra;

import sk.uniza.fri.wof.hra.questy.Quest;
import sk.uniza.fri.wof.hra.questy.IQuestKontrolaMiestnosti;
import sk.uniza.fri.wof.hra.questy.IQuestKontrolaNPC;
import sk.uniza.fri.wof.hra.questy.IquestKontrolaPredmet;
import sk.uniza.fri.wof.prostredie.*;
import sk.uniza.fri.wof.prostredie.predmety.IKontorlaPolozenia;
import sk.uniza.fri.wof.prostredie.predmety.IPredmet;
import sk.uniza.fri.wof.prostredie.predmety.QuestovyPredmet;

import java.util.ArrayList;
import java.util.TreeMap;

public class Hrac {
    private final TreeMap<String, IPredmet> inventar;
    private final ArrayList<Quest> zoznamQuestov;
    private Miestnost aktualnaMiestnost;

    public Hrac(Prostredie prostredie) {
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

    public void chodVSmere(String smer) {
        Miestnost novaMiestnost = this.aktualnaMiestnost.getMiestnostVSmere(smer);

        if (novaMiestnost == null) {
            throw new IllegalArgumentException("Nespravny smer");
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


    public boolean zoberPredmet(String nazov) {
        IPredmet predmet = this.aktualnaMiestnost.zoberPredmet(nazov);
        if (predmet == null) {
            return false;
        }
        this.inventar.put(predmet.getMeno(), predmet);
        return true;
    }

    public boolean polozPredmet(String nazov) {
        IPredmet predmet = this.inventar.get(nazov);

        if (predmet == null) {
            return false;
        }

        if (predmet instanceof IKontorlaPolozenia) {
            if (!((IKontorlaPolozenia)predmet).getDaSaPolozit()) {
                return false;
            }
        }

        this.inventar.remove(nazov);

        this.aktualnaMiestnost.polozPredmet(predmet);
        return true;
    }

    public boolean pouzPredmet(String nazov) {
        IPredmet predmet = this.inventar.get(nazov);
        if (predmet == null) {
            return false;
        }
        predmet.pouziSa(this.aktualnaMiestnost, this);
        return true;
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
}