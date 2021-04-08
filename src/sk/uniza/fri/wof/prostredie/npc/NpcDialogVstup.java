package sk.uniza.fri.wof.prostredie.npc;

import sk.uniza.fri.wof.hra.Hrac;

import java.util.Scanner;

public class NpcDialogVstup implements INpcDialogVrchol {
    private final String replikaNpc;
    private final INpcDialogVrchol vystupnyVrcholHrany;

    public NpcDialogVstup(String replikaNpc, INpcDialogVrchol vystupnyVrcholHrany) {
        this.replikaNpc = replikaNpc;
        this.vystupnyVrcholHrany = vystupnyVrcholHrany;
    }

    public INpcDialogVrchol vykonaj(Hrac hrac) {
        Scanner vstup = new Scanner(System.in);
        System.out.println(this.replikaNpc);
        System.out.print("> ");
        vstup.nextLine();
        return this.vystupnyVrcholHrany;
    }
}