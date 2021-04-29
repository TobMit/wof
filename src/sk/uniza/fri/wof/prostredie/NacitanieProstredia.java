package sk.uniza.fri.wof.prostredie;

import sk.uniza.fri.wof.prostredie.predmety.Predmet;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeMap;

public class NacitanieProstredia {

    private Miestnost startovaciaMiestnost;
    private ArrayList<String> riadkyVSubore;
    private final TreeMap<String, Miestnost> zoznamMiestnosti;
    public NacitanieProstredia() {
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
                this.zoznamMiestnosti.put(nacitanie.replace(slovaNacitania[0] + " ", ""), new Miestnost(this.riadkyVSubore.get(sucastnyRiadok), nacitanie.replace(slovaNacitania[0] + " ", "")));
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
                aktualnaMiestnost.nastavVychod(slovaNacitania[1], this.zoznamMiestnosti.get(slovaNacitania[2]));
            }
            // ošetruje Predmet
            else if (slovaNacitania[0].equals("Predmet")) {
                aktualnaMiestnost.polozPredmet(new Predmet(nacitanie.replace(slovaNacitania[0] + " ", "")));

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

    System.out.println(this.zoznamMiestnosti.keySet());
    }

    public TreeMap<String, Miestnost> getZoznam() {
        return this.zoznamMiestnosti;
    }

    public Miestnost getStartovaciaMiestnost() {
        return this.startovaciaMiestnost;
    }

    public static void main(String[] args) {
        new NacitanieProstredia();
    }
}
