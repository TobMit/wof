package sk.uniza.fri.wof.prostredie;

import sk.uniza.fri.wof.hra.questy.QuestAspirin;
import sk.uniza.fri.wof.hra.questy.QuestPrezentacia;
import sk.uniza.fri.wof.prostredie.npc.*;
import sk.uniza.fri.wof.prostredie.predmety.Predmet;
import sk.uniza.fri.wof.prostredie.predmety.PredmetPortalGun;
import sk.uniza.fri.wof.prostredie.predmety.PredmetRusko;

import java.io.Serializable;
import java.util.TreeMap;

public class Prostredie {
    private final Miestnost startovaciaMiestnost;
    private final TreeMap<String, Miestnost> zoznamMiestnosti;

    public Prostredie() {
        this.zoznamMiestnosti = new TreeMap<>();

        // vytvorenie miestnosti
        this.zoznamMiestnosti.put("terasa", new Miestnost("terasa - hlavny vstup na fakultu", "terasa"));

        //Vchod a prvá miestnosť.
        this.zoznamMiestnosti.put("vestibula", new Miestnost("Vestibula", "vestibula"));
        this.zoznamMiestnosti.put("ic", new Miestnost("IC - informačné centrum", "ic"));

        //Miestnosti ktoré sú priamo spojené s chodbou A
        this.zoznamMiestnosti.put("chodbaA", new Miestnost("Chudba A", "chodbaA"));
        this.zoznamMiestnosti.put("RA006", new Miestnost("RA006", "RA006"));
        this.zoznamMiestnosti.put("WcVChodbeA", new Miestnost("WC - Chodba - A", "WcVChodbeA"));

        //Miestnosti ktoré sú priamo spojené s chodbou C
        this.zoznamMiestnosti.put("chodbaC", new Miestnost("chodba C", "chodbaC"));
        this.zoznamMiestnosti.put("aula", new Miestnost("Aula", "aula"));
        this.zoznamMiestnosti.put("WcVChodbeC", new Miestnost("WC - Chodba - C", "WcVChodbeC"));
        this.zoznamMiestnosti.put("jedalen", new Miestnost("Jedaleň", "jedalen"));
        //this.zoznamMiestnosti.put("bufet", new Miestnost("Bufet", "bufet"));

        //Miestnosti ktoré sú priamo spojené s chodbou C
        this.zoznamMiestnosti.put("chodbaB", new Miestnost("chodba B", "chodbaB"));
        this.zoznamMiestnosti.put("chillZone", new Miestnost("Chill Zone", "chillZone"));


        this.startovaciaMiestnost = this.zoznamMiestnosti.get("vestibula");
        //Miestnosti ktoré neviem kde sú
        //Miestnost labak = new Miestnost("pocitacove laboratorium");
        //Miestnost kancelaria = new Miestnost("kancelaria spravcu pocitacoveho laboratoria");


        // inicializacia miestnosti = nastavenie vychodov
        this.zoznamMiestnosti.get("terasa").nastavVychod("vychod", this.zoznamMiestnosti.get("vestibula"));

        //vestibula
        this.zoznamMiestnosti.get("vestibula").nastavVychod("sever", this.zoznamMiestnosti.get("chodbaA"));
        this.zoznamMiestnosti.get("vestibula").nastavVychod("juh", this.zoznamMiestnosti.get("chodbaB"));
        this.zoznamMiestnosti.get("terasa").nastavVychod("zapad", this.zoznamMiestnosti.get("terasa"));
        this.zoznamMiestnosti.get("vestibula").nastavVychod("vychod", this.zoznamMiestnosti.get("ic"));


        this.zoznamMiestnosti.get("ic").nastavVychod("zapad", this.zoznamMiestnosti.get("vestibula"));

        NpcDialogVrchol vsetkoMaBoli = new NpcDialogVrchol("Vsetko ma boli, tak nezavadzaj.");
        NpcDialogVrchol padajPrec = new NpcDialogVrchol("Joj, tak padaj prec.");
        NpcDialogQuest vratnickaQuest = new NpcDialogQuest("Dakujem", new QuestAspirin("aspirin"));
        //NpcDialogVrchol zlaty = new NpcDialogVrchol("Ach to budes zlaty.");
        NpcDialogVrchol donesAspirin = new NpcDialogVrchol(
                "Tak ja neviem, dones mi aspirin.",
                new NpcDialogHrana("Lekarne zavreli kvoli covid", padajPrec),
                new NpcDialogHrana("Skusim nejaky najst", vratnickaQuest)
        );
        NpcDialogVrchol uvod = new NpcDialogVrchol(
                "Tak cau.",
                new NpcDialogHrana("Ako sa mas?", vsetkoMaBoli),
                new NpcDialogHrana("Nemas pre mna nejaku ulohu?", donesAspirin)
        );
        NpcDialogVstup korenDialogovehoStromuVratnicka = new NpcDialogVstup("Ahoj, ja som tu nejaka vratnicka. A ty si?", uvod);
        NpcDialogKontrolaQuestu kontrolaAspirinu = new NpcDialogKontrolaQuestu("aspirin", korenDialogovehoStromuVratnicka);
        NpcDialogQuest korenDialogovehoStromuUcitel = new NpcDialogQuest("Uz meskas na prezentaciu", new QuestPrezentacia("prezentacia"));
        this.zoznamMiestnosti.get("vestibula").postavNpc(new NpcDialogove("ucitel", korenDialogovehoStromuUcitel));

        this.zoznamMiestnosti.get("vestibula").postavNpc(new NpcDialogove("vratnicka", kontrolaAspirinu));

        NpcDialogVrchol korenDialogovehoStromuBufetarka = new NpcDialogVrchol("Zatial nemas naprogramovane predmety, tak sa s tebou nebavim");
        this.zoznamMiestnosti.get("vestibula").postavNpc(new NpcDialogove("bufetarka", korenDialogovehoStromuBufetarka));
        this.zoznamMiestnosti.get("vestibula").postavNpc(new NpcObchodnik("jozo", new PredmetRusko(), new Predmet("index"), new Predmet("borovicka")));
        this.zoznamMiestnosti.get("vestibula").postavNpc(new NpcReferentka("referentka", this.zoznamMiestnosti, this.startovaciaMiestnost.getMenoMiestnosi()));


        //vestibula.polozPredmet(new PredmetRusko());
        this.zoznamMiestnosti.get("vestibula").polozPredmet(new Predmet("index"));
        //this.zoznamMiestnosti.get("vestibula").polozPredmet(new PredmetPortalGun("TatraTea", "Slovenská verzia PortalGan", this));
        this.zoznamMiestnosti.get("vestibula").polozPredmet(new PredmetPortalGun("TatraTea", "Slovenská verzia PortalGan", this));

        //chodbaB
        this.zoznamMiestnosti.get("chodbaB").nastavVychod("zapad", this.zoznamMiestnosti.get("chillZone"));
        this.zoznamMiestnosti.get("chillZone").nastavVychod("vychod", this.zoznamMiestnosti.get("chodbaB"));
        this.zoznamMiestnosti.get("chodbaB").nastavVychod("sever", this.zoznamMiestnosti.get("vestibula"));
        this.zoznamMiestnosti.get("chodbaB").polozPredmet(new PredmetGranat("granat"));

        //chodbaA
        this.zoznamMiestnosti.get("chodbaA").nastavVychod("juh", this.zoznamMiestnosti.get("vestibula"));
        this.zoznamMiestnosti.get("chodbaA").nastavVychod("vychod", this.zoznamMiestnosti.get("WcVChodbeA"));
        this.zoznamMiestnosti.get("WcVChodbeA").nastavVychod("zapad", this.zoznamMiestnosti.get("chodbaA"));
        this.zoznamMiestnosti.get("chodbaA").nastavVychod("zapad", this.zoznamMiestnosti.get("RA006"));
        this.zoznamMiestnosti.get("RA006").nastavVychod("vychod", this.zoznamMiestnosti.get("chodbaA"));
        this.zoznamMiestnosti.get("chodbaA").nastavVychod("dole", this.zoznamMiestnosti.get("chodbaC"));

        //chodbaC
        this.zoznamMiestnosti.get("chodbaC").nastavVychod("hore", this.zoznamMiestnosti.get("chodbaA"));
        this.zoznamMiestnosti.get("chodbaC").nastavVychod("zapad1", this.zoznamMiestnosti.get("jedalen"));
        this.zoznamMiestnosti.get("jedalen").nastavVychod("vychod", this.zoznamMiestnosti.get("chodbaC"));
        this.zoznamMiestnosti.get("chodbaC").nastavVychod("zapad2", this.zoznamMiestnosti.get("WcVChodbeC"));
        this.zoznamMiestnosti.get("WcVChodbeC").nastavVychod("vychod", this.zoznamMiestnosti.get("chodbaC"));
        this.zoznamMiestnosti.get("chodbaC").nastavVychod("juh", this.zoznamMiestnosti.get("aula"));
        this.zoznamMiestnosti.get("aula").nastavVychod("sever", this.zoznamMiestnosti.get("chodbaC"));

    }

    public Miestnost getStartovaciaMiestnost() {
        return this.startovaciaMiestnost;
    }

    public void pridajVychod(String nazovPortalu, Miestnost miestnost, Miestnost miestnostPolozenia) {
        this.zoznamMiestnosti.get(miestnostPolozenia.getMenoMiestnosi()).nastavVychod(nazovPortalu, this.zoznamMiestnosti.get(miestnost.getMenoMiestnosi()));
    }

    public void odstranVychod(String nazovPortalu, Miestnost miestnost) {
        this.zoznamMiestnosti.get(miestnost.getMenoMiestnosi()).vymazVychod(nazovPortalu);
    }
}