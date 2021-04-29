package sk.uniza.fri.wof.prostredie;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class NacitajMiestnost {
    private ArrayList<String> riadkyVSubore;
    public NacitajMiestnost() {
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

    }
}
