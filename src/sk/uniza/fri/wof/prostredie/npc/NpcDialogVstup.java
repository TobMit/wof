package sk.uniza.fri.wof.prostredie.npc;

import java.util.Scanner;

public class NpcDialogVstup implements INpcDialogVrchol {
    private final String replikaNpc;
    private final INpcDialogVrchol vystupnyVrcholHrany;

    public NpcDialogVstup(String replikaNpc, INpcDialogVrchol vystupnyVrcholHrany) {
        this.replikaNpc = replikaNpc;
        this.vystupnyVrcholHrany = vystupnyVrcholHrany;
    }

    public INpcDialogVrchol vykonaj() {
        Scanner vstup = new Scanner(System.in);
        System.out.println(this.replikaNpc);
        System.out.print("> ");
        vstup.nextLine();
        return this.vystupnyVrcholHrany;
    }
}