package sk.uniza.fri.wof.prostredie.npc;

import sk.uniza.fri.wof.hra.Hrac;

import java.util.Scanner;

public class NpcDialogVrchol implements INpcDialogVrchol {
    private final String replikaNpc;
    private final NpcDialogHrana[] hrany;

    public NpcDialogVrchol(String replikaNpc, NpcDialogHrana... hrany) {
        this.replikaNpc = replikaNpc;
        this.hrany = hrany;
    }

    public NpcDialogVrchol vykonaj(Hrac hrac) {
        Scanner vstup = new Scanner(System.in);

        System.out.println(this.replikaNpc);
        int no = 1;
        for (NpcDialogHrana hrana : this.hrany) {
            System.out.printf("%d. %s%n", no, hrana.getReplikaHraca());
            no++;
        }
        System.out.println("0. Ukonci rozhovor");

        int moznost = -1;
        do {
            System.out.format("Vyber moznost (0-%d)> ", this.hrany.length);
            String riadok = vstup.nextLine();
            Scanner riadokScanner = new Scanner(riadok);
            if (riadokScanner.hasNextInt()) {
                moznost = riadokScanner.nextInt();
            }
        } while (moznost < 0 || moznost > this.hrany.length);

        if (moznost == 0) {
            return null;
        }

        return this.hrany[moznost - 1].getVystupnyVrchol();
    }
}