package sk.uniza.fri.wof.prostredie.npc;

import sk.uniza.fri.wof.hra.Hrac;
import sk.uniza.fri.wof.prostredie.predmety.IPredmet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class NpcObchodnik extends Npc {
    private final ArrayList<IPredmet> inventar;

    public NpcObchodnik(String meno, IPredmet... inventar) {
        super(meno);
        this.inventar = new ArrayList<>();
        Collections.addAll(this.inventar, inventar);
    }

    public void nakupuj(Hrac hrac) {
        Scanner vstup = new Scanner(System.in);
        while (!this.inventar.isEmpty()) {
            System.out.println("Momentalne mam v ponuke:");
            int no = 1;
            for (IPredmet predmet : this.inventar) {
                System.out.printf("%d. %s%n", no, predmet.getMeno());
                no++;
            }
            System.out.println("0. Ukonci rozhovor");

            int moznost = -1;
            do {
                System.out.format("Vyber moznost (0-%d)> ", this.inventar.size());
                String riadok = vstup.nextLine();
                Scanner riadokScanner = new Scanner(riadok);
                if (riadokScanner.hasNextInt()) {
                    moznost = riadokScanner.nextInt();
                }
                if (moznost == 0) {
                    return;
                }
            } while (moznost < 0 || moznost > this.inventar.size());

            IPredmet kupovany = this.inventar.remove(moznost - 1);
            hrac.zoberPredmet(kupovany);
            System.out.printf("Kupil si %s\n", kupovany.getMeno());
        }

        System.out.println("Už som vybrakovaný!");

    }
}
