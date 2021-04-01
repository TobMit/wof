package sk.uniza.fri.wof.hra;

import sk.uniza.fri.wof.prostredie.*;
import sk.uniza.fri.wof.prostredie.predmety.IKontorlaPolozenia;
import sk.uniza.fri.wof.prostredie.predmety.IPredmet;

import java.util.TreeMap;

public class Hrac {
    private final TreeMap<String, IPredmet> inventar;
    private Miestnost aktualnaMiestnost;

    public Hrac(Prostredie prostredie) {
        this.aktualnaMiestnost = prostredie.getStartovaciaMiestnost();
        this.inventar = new TreeMap<>();
    }

    public Miestnost getAktualnaMiestnost() {
        return this.aktualnaMiestnost;
    }

    public void vypisPopisAktualnejMiestnosti() {
        this.aktualnaMiestnost.vypisPopisMiestnosti();
    }

    public boolean chodVSmere(String smer) {
        Miestnost novaMiestnost = this.aktualnaMiestnost.getMiestnostVSmere(smer);

        if (novaMiestnost == null) {
            return false;
        }

        this.aktualnaMiestnost = novaMiestnost;
        return true;
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

        //toto je bezpečné pretipovanie, overenie či je ten predmet rusko
//        if (predmet instanceof PredmetRusko) {
//            PredmetRusko rusko = (PredmetRusko)predmet;
//            if (rusko.getNasadene()) {
//                return false;
//            }
//        }
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
        predmet.pouziSa(this.aktualnaMiestnost);
        return true;
    }

    public void zoberPredmet(IPredmet predmet) {
        this.inventar.put(predmet.getMeno(), predmet);
    }
}