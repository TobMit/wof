package sk.uniza.fri.wof.prostredie;

import sk.uniza.fri.wof.prostredie.predmety.Predmet;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeMap;

public class NacitanieProstredia {
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
        System.out.println(this.riadkyVSubore);
    }

    public static void main(String[] args) {
        new NacitanieProstredia();
    }
}
