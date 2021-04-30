package sk.uniza.fri.wof.prostredie;

import sk.uniza.fri.wof.prostredie.predmety.Predmet;
import sk.uniza.fri.wof.prostredie.predmety.PredmetPortalGun;
import sk.uniza.fri.wof.prostredie.predmety.PredmetRusko;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeMap;

public class NacitanieProstredia {

    private final Prostredie referenciaProstredia;
    private Miestnost startovaciaMiestnost;
    private ArrayList<String> riadkyVSubore;
    private final TreeMap<String, Miestnost> zoznamMiestnosti;

    public NacitanieProstredia(Prostredie prostredie) {
        this.referenciaProstredia = prostredie;
        this.zoznamMiestnosti = new TreeMap<>();
        this.riadkyVSubore = new ArrayList<>();
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File("src/Prostredie.txt"));
        } catch (FileNotFoundException e) {
            System.out.println("Subor neexitstuje");
        }

        if (scanner != null) {
            while (scanner.hasNextLine()) {
                this.riadkyVSubore.add(scanner.nextLine());
            }
            scanner.close();
        }

        String nazovMiestnosti = "";

        int sucastnyRiadok = 0;

        for (String nacitanie : this.riadkyVSubore) {
            sucastnyRiadok++;
            String[] slovaNacitania = nacitanie.split(" ");
            // ošetruje Miestnosti
            if (slovaNacitania[0].equals("Miestnost")) {
                this.zoznamMiestnosti.put(nacitanie.replace(slovaNacitania[0] + " ", ""),
                        new Miestnost(this.riadkyVSubore.get(sucastnyRiadok),
                                nacitanie.replace(slovaNacitania[0] + " ", "")));
            }
        }
        Miestnost aktualnaMiestnost = null;
        this.startovaciaMiestnost = null;

        for (String nacitanie : this.riadkyVSubore) {
            sucastnyRiadok++;
            String[] slovaNacitania = nacitanie.split(" ");
            // ošetruje Miestnosti
            if (slovaNacitania[0].equals("Miestnost")) {
                aktualnaMiestnost = this.zoznamMiestnosti.get(nacitanie.replace(slovaNacitania[0] + " ", ""));
            }
            else if (slovaNacitania[0].equals("Vychod")) {
                aktualnaMiestnost.nastavVychod(slovaNacitania[1],
                        this.zoznamMiestnosti.get(nacitanie.replace(slovaNacitania[0] + " ", "")
                                .replace(slovaNacitania[1] + " ", "")));
            }
            // ošetruje Predmet
            else if (slovaNacitania[0].equals("Predmet")) {
                System.out.println(nacitanie.replace(slovaNacitania[0] + " ", ""));
                switch (nacitanie.replace(slovaNacitania[0] + " ", "")) {
                    case "granat":
                        aktualnaMiestnost.polozPredmet(new PredmetGranat(nacitanie.replace(slovaNacitania[0] + " ", "")));
                        break;
                    case "TatraTea":
                        aktualnaMiestnost.polozPredmet(new PredmetPortalGun(nacitanie.replace(slovaNacitania[0] + " ", ""), referenciaProstredia));
                        break;
                    case "rusko":
                        aktualnaMiestnost.polozPredmet(new PredmetRusko());
                        break;
                    default:
                        aktualnaMiestnost.polozPredmet(new Predmet(nacitanie.replace(slovaNacitania[0] + " ", "")));
                        break;
                }
                //aktualnaMiestnost.polozPredmet(new Predmet(nacitanie.replace(slovaNacitania[0] + " ", "")));

            }
            // ošetruje nový riadok
            else if (nacitanie.equals("")) {
                continue;
            }
            else if (nacitanie.equals("Start")) {
                this.startovaciaMiestnost = aktualnaMiestnost;
            }
            // ošetruje popis
            else {
                continue;
            }
        }

    }

    public TreeMap<String, Miestnost> getZoznam() {
        return new TreeMap<String, Miestnost>(this.zoznamMiestnosti);
    }

    public Miestnost getStartovaciaMiestnost() {
        return this.startovaciaMiestnost;
    }

}
