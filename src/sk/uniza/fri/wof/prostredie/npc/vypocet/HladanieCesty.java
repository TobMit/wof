package sk.uniza.fri.wof.prostredie.npc.vypocet;

/**
 * NEPUŽITÉ
 */

import java.util.ArrayList;

public class HladanieCesty {

    private final int nekonecno;
    private final ArrayList<Hrana> zoznamHran;
    private final ArrayList<Vrchol> zoznamVrcholov;
    private final int zaciatocnyVrchol;
    private final int cielovyVrchol;

    public HladanieCesty(int zaciatocnyVrchol, int cielovyVrchol) {
        this.zaciatocnyVrchol = zaciatocnyVrchol;
        this.cielovyVrchol = cielovyVrchol;
        this.zoznamVrcholov = new ArrayList<>();
        this.zoznamHran = new ArrayList<>();

        this.nacitajVrcholy();
        this.nacitajHrany();
        this.nekonecno = this.vypocitajNekonecno();
        this.nastavNajkratsieVzdialenosti();
        System.out.printf("Nedosiahnutelna hodnota je: %d%n", this.nekonecno);


        this.vypocitajNajkratsieCesty();
        this.vypisNajkratsiuCestuDoZadanehoVrcholu();
    }



    public int vypocitajNekonecno() {
        int nedosiahnutelneCislo = 1;
        for (Hrana hrana : this.zoznamHran) {
            nedosiahnutelneCislo += hrana.getOhodnotenieHrany();
        }
        return nedosiahnutelneCislo;
    }


    public void vypisNajkratsiuCestuDoZadanehoVrcholu() {
        System.out.printf("Najkratsia cesta do vrcholu %s je:%n", this.najdiVrchol(this.cielovyVrchol).getNazov());


        Vrchol predchadzajuciVrchol = this.najdiVrchol(this.cielovyVrchol);
        boolean run = true;

        ArrayList<Vrchol> cesta = new ArrayList<>();

        cesta.add(predchadzajuciVrchol);


        while (run) {
            if (predchadzajuciVrchol.getxV() != null) {
                cesta.add(0, predchadzajuciVrchol.getxV());
                predchadzajuciVrchol = predchadzajuciVrchol.getxV();
            } else {
                run = false;
            }
        }

        for (Vrchol vrchol : cesta) {
            System.out.printf("%s --> ", vrchol.getNazov());
        }
    }

    public void vypocitajNajkratsieCesty() {
        boolean nastalaZmena;
        do {
            nastalaZmena = false;
            for (Hrana hrana : this.zoznamHran) {
                if (hrana.getDruhyVrchol().gettV() > hrana.getPrvyVrchol().gettV() + hrana.getOhodnotenieHrany()) {
                    System.out.println(hrana.getDruhyVrchol().gettV() + " > " + hrana.getPrvyVrchol().gettV() + " + " + hrana.getOhodnotenieHrany() + " ✔");
                    hrana.getDruhyVrchol().settV(hrana.getPrvyVrchol().gettV() + hrana.getOhodnotenieHrany());
                    hrana.getDruhyVrchol().setxV(hrana.getPrvyVrchol());
                    nastalaZmena = true;
                } else {
                    System.out.println(hrana.getDruhyVrchol().gettV() + " > " + hrana.getPrvyVrchol().gettV() + " + " + hrana.getOhodnotenieHrany() + " ❌");
                }

            }
            System.out.println("--------------------------------------------------");
        } while (nastalaZmena);
    }

    public void nastavNajkratsieVzdialenosti() {
        for (Vrchol vrchol : this.zoznamVrcholov) {
            if (vrchol.getOznacenie() != this.zaciatocnyVrchol) {
                vrchol.settV(this.nekonecno);
            }
            System.out.println(vrchol);
        }
    }

    public void nacitajVrcholy() {
        Vrchol vTerasa = new Vrchol(1, "Terasa");
        Vrchol vVestibulA = new Vrchol(2, "VestibulA");
        Vrchol vIC = new Vrchol(3, "IC");
        Vrchol vChodbaA = new Vrchol(4, "ChodbaA");
        Vrchol vRA06 = new Vrchol(5, "RA06");
        Vrchol vWCA = new Vrchol(6, "WCA");
        Vrchol vChodbaC = new Vrchol(7, "Chodba C");
        Vrchol vAula = new Vrchol(8, "Aula");
        Vrchol vWCC = new Vrchol(9, "WCC");
        Vrchol vJedalen = new Vrchol(10, "Jedalen");
        Vrchol vBufet = new Vrchol(11, "Bufet");
        Vrchol vChodbaB = new Vrchol(12, "Chodba B");
        Vrchol vChillZone = new Vrchol(13, "Chill Zone");

        this.zoznamVrcholov.add(vTerasa);
        this.zoznamVrcholov.add(vVestibulA);
        this.zoznamVrcholov.add(vIC);
        this.zoznamVrcholov.add(vChodbaA);
        this.zoznamVrcholov.add(vRA06);
        this.zoznamVrcholov.add(vWCA);
        this.zoznamVrcholov.add(vChodbaC);
        this.zoznamVrcholov.add(vAula);
        this.zoznamVrcholov.add(vWCC);
        this.zoznamVrcholov.add(vJedalen);
        this.zoznamVrcholov.add(vBufet);
        this.zoznamVrcholov.add(vChodbaB);
        this.zoznamVrcholov.add(vChillZone);

    }

    public void nacitajHrany() {
        Hrana hrana1 = new Hrana(this.najdiVrchol(1), this.najdiVrchol(2), 1);

        Hrana hrana2 = new Hrana(this.najdiVrchol(2), this.najdiVrchol(4), 1);


        Hrana hrana3 = new Hrana(this.najdiVrchol(4), this.najdiVrchol(6), 1);
        Hrana hrana4 = new Hrana(this.najdiVrchol(4), this.najdiVrchol(5), 1);
        Hrana hrana5 = new Hrana(this.najdiVrchol(4), this.najdiVrchol(7), 1);

        Hrana hrana6 = new Hrana(this.najdiVrchol(7), this.najdiVrchol(8), 1);
        Hrana hrana7 = new Hrana(this.najdiVrchol(7), this.najdiVrchol(9), 1);
        Hrana hrana8 = new Hrana(this.najdiVrchol(7), this.najdiVrchol(10), 1);

        Hrana hrana9 = new Hrana(this.najdiVrchol(7), this.najdiVrchol(11), 1);

        Hrana hrana10 = new Hrana(this.najdiVrchol(12), this.najdiVrchol(13), 1);

        Hrana hrana11 = new Hrana(this.najdiVrchol(2), this.najdiVrchol(12), 1);




        this.zoznamHran.add(hrana1);
        this.zoznamHran.add(hrana2);
        this.zoznamHran.add(hrana3);
        this.zoznamHran.add(hrana4);
        this.zoznamHran.add(hrana5);
        this.zoznamHran.add(hrana6);
        this.zoznamHran.add(hrana7);
        this.zoznamHran.add(hrana8);
        this.zoznamHran.add(hrana9);
        this.zoznamHran.add(hrana10);
        this.zoznamHran.add(hrana11);
    }


    public Vrchol najdiVrchol(int oznacenie) {
        for (Vrchol vrchol : this.zoznamVrcholov) {
            if (vrchol.getOznacenie() == oznacenie) {
                return vrchol;
            }
        }
        return null;
    }

}
