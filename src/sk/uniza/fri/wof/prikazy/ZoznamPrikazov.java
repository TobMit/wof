package sk.uniza.fri.wof.prikazy;

import sk.uniza.fri.wof.hra.HraKonciException;
import sk.uniza.fri.wof.hra.Hrac;
import sk.uniza.fri.wof.hra.NenanjdenyPredmetException;
import sk.uniza.fri.wof.hra.NeexistujuciVychodException;
import sk.uniza.fri.wof.hra.PredmetSaNedaPolozitException;
import sk.uniza.fri.wof.prostredie.SmrtException;
import sk.uniza.fri.wof.prostredie.npc.NpcObchodnik;
import sk.uniza.fri.wof.prostredie.npc.Npc;
import sk.uniza.fri.wof.prostredie.npc.NpcDialogove;
import sk.uniza.fri.wof.prostredie.npc.NpcReferentka;
import sk.uniza.fri.wof.prostredie.predmety.NepuzitelnyPredmetExceptions;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Trieda sk.uniza.fri.wof.prikazy.ZoznamPrikazov udrzuje zoznam nazvov platnych prikazov hry.
 * Za ulohu ma rozpoznavat platne prikazy a vykonavat ich.
 *
 * @author  Michael Kolling and David J. Barnes
 * @version 2006.03.30
 * @author  lokalizacia: Lubomir Sadlon, Jan Janech
 * @version 2012.02.21
 */

public class ZoznamPrikazov {
    // konstantne pole nazvov prikazov
    private static final String[] PLATNE_PRIKAZY = {
        "chod", "ukonci", "pomoc", "hovor", "zober", "poloz", "inventar", "pouzi", "nakupuj",
        "questlog", "save", "load"
    };

    /**
     * Kontroluje, ci nazov v parametri je platny prikaz.
     *
     * @return true ak je parameter platny prikaz,
     * false inak.
     */
    public boolean jePrikaz(String nazov) {
        for (int i = 0; i < ZoznamPrikazov.PLATNE_PRIKAZY.length; i++) {
            if (ZoznamPrikazov.PLATNE_PRIKAZY[i].equals(nazov)) {
                return true;
            }
        }
        // ak algoritmus dosiahne tento bod, parameter nie je platny prikaz
        return false;
    }

    /**
     * Prevezne prikaz a vykona ho.
     *
     * @param prikaz prikaz, ktory ma byt vykonany.
     * @param hrac hrac ktory prikaz vykonava
     */
    public void vykonajPrikaz(Prikaz prikaz, Hrac hrac) throws HraKonciException {
        if (prikaz.jeNeznamy()) {
            System.out.println("Nerozumiem, co mas na mysli...");
            return ;
        }

        String nazovPrikazu = prikaz.getNazov();

        switch (nazovPrikazu) {
            case "pomoc":
                this.vypisNapovedu();
                break;
            case "chod":
                this.chodDoMiestnosti(prikaz, hrac);
                break;
            case "ukonci":
                this.ukonciHru(prikaz);
                break;
            case "hovor":
                this.hovorSNpc(prikaz, hrac);
                break;
            case "zober":
                this.zoberPredmet(prikaz, hrac);
                break;
            case "poloz":
                this.polozPredmet(prikaz, hrac);
                break;
            case "inventar":
                this.zobrazInventar(hrac);
                break;
            case "pouzi":
                this.pouzi(prikaz, hrac);
                break;
            case "nakupuj":
                this.nakupujUNpc(prikaz, hrac);
                break;
            case "questlog":
                this.zobrazQuestlog(prikaz, hrac);
                break;
            case "save":
                this.ulozPOziciu(prikaz, hrac);
                break;
            case "load":
                this.nacitajPoziciu (prikaz, hrac);
                break;
            default:
                break;
        }
    }


    /**
     * Vykona pokus o prechod do miestnosti urcenej danym smerom.
     * Ak je tym smerom vychod, hrac prejde do novej miestnosti.
     * Inak sa vypise chybova sprava do terminaloveho okna.
     * @param prikaz prikaz na vykonanie
     * @param hrac hrac ktory prikaz vykonava
     */
    private void chodDoMiestnosti(Prikaz prikaz, Hrac hrac) {
        if (!prikaz.maParameter()) {
            // ak prikaz nema parameter - druhe slovo - nevedno kam ist
            System.out.println("Chod kam?");
            return;
        }

        String smer = prikaz.getParameter();

        // Pokus o opustenie aktualnej miestnosti danym vychodom.
        try {
            hrac.chodVSmere(smer);
            hrac.vypisPopisAktualnejMiestnosti();
        } catch (NeexistujuciVychodException e) {
            System.out.println("Tam nie je vychod!");
        }
    }

    /**
     * Vypise text pomocnika do terminaloveho okna.
     * Text obsahuje zoznam moznych prikazov.
     */
    private void vypisNapovedu() {
        System.out.println("Zabludil si. Si sam. Tulas sa po fakulte.");
        System.out.println();
        System.out.println("Mozes pouzit tieto prikazy:");
        System.out.println("   chod ukonci pomoc");
    }

    /**
     * Ukonci hru.
     * Skotroluje cely prikaz a zisti, ci je naozaj koniec hry.
     * sk.uniza.fri.wof.prikazy.Prikaz ukoncenia nema parameter.
     *
     * @param prikaz prikaz, ktory sa vykonava
     */
    private void ukonciHru(Prikaz prikaz) throws HraKonciException {
        if (prikaz.maParameter()) {
            System.out.println("Ukonci, co?");
        } else {
            throw new HraKonciException("Hra skonci.");
        }
    }

    private void hovorSNpc(Prikaz prikaz, Hrac hrac) {
        Npc npc = hrac.getAktualnaMiestnost().getNpc(prikaz.getParameter());
        if (npc instanceof NpcDialogove) {
            ((NpcDialogove)npc).hovor(hrac);
        } else if (npc instanceof NpcObchodnik) {
            ((NpcObchodnik)npc).nakupuj(hrac);
        } else if (npc instanceof NpcReferentka) {
            ((NpcReferentka)npc).hovor();
        } else {
            System.out.printf("NPC %s sa neda hovoriť!%n", prikaz.getParameter());
        }
    }

    private void polozPredmet(Prikaz prikaz, Hrac hrac) {
        try {
            hrac.polozPredmet(prikaz.getParameter());
            System.out.printf("Polozil si %s!%n", prikaz.getParameter());
        } catch (NenanjdenyPredmetException e) {
            System.out.printf("Predmet %s sa nemas!%n", prikaz.getParameter());
        } catch (PredmetSaNedaPolozitException e) {
            System.out.printf("Predmet %s sa neda polozit!%n", prikaz.getParameter());
        }
    }

    private void zoberPredmet(Prikaz prikaz, Hrac hrac) {
        try {
            hrac.zoberPredmet(prikaz.getParameter());
            System.out.printf("Zdvihol si %s%n", prikaz.getParameter());

        } catch (NenanjdenyPredmetException e) {
            System.out.printf("Predmet %s sa neda zvydhnut\n", prikaz.getParameter());
        }

    }

    private void zobrazInventar(Hrac hrac) {
        hrac.zobrazInventar();
    }

    private void pouzi(Prikaz prikaz, Hrac hrac) {
        try {
            hrac.pouzPredmet(prikaz.getParameter());
        } catch (NenanjdenyPredmetException e) {
            System.out.printf("Predmet %s nemas v inventary.\n", prikaz.getParameter());
        } catch (NepuzitelnyPredmetExceptions e) {
            System.out.format("Predmet %s sa neda pouzit.\n", prikaz.getParameter());
        } catch (SmrtException e) {
            System.out.println("Zomer si.");
            System.exit(0);
        }

    }

    private void nakupujUNpc(Prikaz prikaz, Hrac hrac) {
        Npc npc = hrac.getAktualnaMiestnost().getNpc(prikaz.getParameter());
        if (npc instanceof NpcDialogove) {
            ((NpcDialogove)npc).hovor(hrac);
        } else if (npc instanceof NpcObchodnik) {
            ((NpcObchodnik)npc).nakupuj(hrac);
        } else {
            System.out.printf("Od %s sa nedá kúpiť!%n", prikaz.getParameter());
        }
    }

    private void zobrazQuestlog(Prikaz prikaz, Hrac hrac) {
        hrac.zobrazQuestlog();
    }

    private void ulozPOziciu(Prikaz prikaz, Hrac hrac) {
        File saveSubor = new File(prikaz.getParameter() + ".wof");
        try (FileOutputStream save = new FileOutputStream(saveSubor)){
        } catch (FileNotFoundException e) {
            System.out.println("Nepodarilo sa ulozit hru - asi zly nazov.");
        } catch (IOException e) {
            System.out.println("Nepodarilo sa ulozit save.");
        }

    }

    private void nacitajPoziciu(Prikaz prikaz, Hrac hrac) {
        File saveSubor = new File(prikaz.getParameter() + ".wof");
        try (FileInputStream save = new FileInputStream(saveSubor)) {
        } catch (FileNotFoundException e) {
            System.out.println("Nepodarilo sa otvorit save - asi neexistuje.");
        } catch (IOException e) {
            System.out.println("Nepodarilo sa nacitat save.");
        }

    }

}