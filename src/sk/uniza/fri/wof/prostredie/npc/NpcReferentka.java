package sk.uniza.fri.wof.prostredie.npc;

import sk.uniza.fri.wof.prostredie.Miestnost;
import sk.uniza.fri.wof.prostredie.npc.vypocet.Graf;

import java.util.Scanner;
import java.util.TreeMap;

public class NpcReferentka extends Npc {
    private final TreeMap<String, Miestnost> zoznamMiestnosti;
    private final String menoAktualnejMiestnosti;

    public NpcReferentka(String meno, TreeMap<String, Miestnost> zoznamMiestnosti, String menoMiestnosi) {
        super(meno);
        this.zoznamMiestnosti = zoznamMiestnosti;
        this.menoAktualnejMiestnosti = menoMiestnosi;
    }

    public void hovor() {
        System.out.print("\nAhoj ja som referentka. ");
        Scanner vstup = new Scanner(System.in);
        while (true) {
            System.out.println("Čo potrebuješ?");
            System.out.println("1. Zobraz mapu");
            System.out.println("2. Poraď cestu");
            System.out.println("0. Ukonci rozhovor");

            int moznost = -1;
            System.out.format("Vyber moznost (0-%d)> ", 2);
            String riadok = vstup.nextLine();
            Scanner riadokScanner = new Scanner(riadok);
            if (riadokScanner.hasNextInt()) {
                moznost = riadokScanner.nextInt();
            }
            switch (moznost) {
                case 1:
                    this.vypisSa();
                    break;
                case 2:
                    System.out.println("Kam sa chceš dostať?");
                    riadok = vstup.nextLine();
                    if (!zoznamMiestnosti.containsKey(riadok)) {
                        System.out.println("Taká miestnosť na fakulte nieje!");
                        break;
                    }
                    this.najdiNajkratsiuCestu(riadok);
                    System.out.println("\n\n");
                    break;
                case 0:
                    return;
                default:
                    break;
            }
        }

    }

    private void vypisSa() {
        System.out.println("Všetky miestnosti:");
        for (String aktualnaMiestnost : this.zoznamMiestnosti.keySet()) {
            System.out.println("\t- " + aktualnaMiestnost);
        }
        System.out.println();
    }

    private void najdiNajkratsiuCestu(String ciel) {
        Graf cesta = new Graf(this.zoznamMiestnosti);
        cesta.najdiTrasu(this.menoAktualnejMiestnosti, ciel);

    }
}
