package sk.uniza.fri.wof.prostredie.npc.vypocet;

import sk.uniza.fri.wof.prostredie.Miestnost;

import java.util.ArrayList;
import java.util.TreeMap;

public class Graf {
    private int n;
    private int m = 0;
    private int[][] H;
    private TreeMap<String, Integer> konverzia;
    private TreeMap<Integer, String> dekonverzia;

    public Graf(TreeMap<String, Miestnost> zoznamMiestnosti) {
        this.n = zoznamMiestnosti.size();
        this.konverzia = new TreeMap<>();
        this.dekonverzia = new TreeMap<>();

        int pocitadlo = 1;
        for (String miestnosti : zoznamMiestnosti.keySet()) {
            this.m += zoznamMiestnosti.get(miestnosti).getPocetVychodov();
            this.konverzia.put(miestnosti, pocitadlo);
            this.dekonverzia.put(pocitadlo, miestnosti);
            pocitadlo++;
        }

        this.H = new int[1 + m][3];

        pocitadlo = 0;
        for (String miestnosti : zoznamMiestnosti.keySet()) {
            TreeMap<String, Miestnost> vychody = zoznamMiestnosti.get(miestnosti).getZoznamVychodov();
            for (String vychod : vychody.keySet()) {
                this.H[pocitadlo][0] = konverzia.get(miestnosti);
                this.H[pocitadlo][1] = konverzia.get(vychody.get(vychod).getMenoMiestnosi());
                this.H[pocitadlo][2] = 1;
                pocitadlo++;
            }
        }

    }

    public void najdiTrasu(String zaciatok, String koniec) {
        //začiatok
        int u = konverzia.get(zaciatok);
        //koniec
        int v = konverzia.get(koniec);

        // 1. krok - inicializácia
        int[] t = new int[this.n + 1];
        int[] x = new int[this.n + 1];

        for (int i = 0; i <= this.n; i++) {
            t[i] = Integer.MAX_VALUE / 2 - 2;
            x[i] = 0;
        }

        //Hodnota zaciatocneho vrcholu na 0
        t[u] = 0;

        //2.Krok

        boolean zlesenie = true;

        while (zlesenie) {
            zlesenie = false;


            for (int k = 1; k <= this.m; k++) {
                //Zaciatok hrany
                int i = H[k][0];
                //Koniec hrany
                int j = H[k][1];
                //Cena hrany
                int cij = H[k][2];

                if (t[j] > t[i] + cij) {
                    t[j] = t[i] + cij;
                    x[j] = i;
                    zlesenie = true;
                }

            }
        }

        //Najkratsia cesta
        int w = v;

        System.out.println("Našla som pre teba cestu:");
        ArrayList<Integer> arrayListX = new ArrayList<>();
        arrayListX.add(w);
        while (x[w] > 0) {
            arrayListX.add(x[w]);
            w = x[w];
        }
        while (!arrayListX.isEmpty()) {
            System.out.println("\t" + dekonverzia.get(arrayListX.get(arrayListX.size() - 1)));
            arrayListX.remove(arrayListX.size() - 1);
        }
    }
}
