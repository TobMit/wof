package sk.uniza.fri.wof.prostredie.predmety;

import sk.uniza.fri.wof.hra.Hrac;
import sk.uniza.fri.wof.prostredie.Miestnost;
import sk.uniza.fri.wof.prostredie.Prostredie;

import java.io.DataInputStream;
import java.io.DataOutputStream;


public class PredmetPortalGun implements IPredmet, IKontorlaPolozenia {
    private final String nazov;
    private final String popis;
    private final Prostredie prostredie;
    private boolean cerveny;
    private boolean modry;
    private boolean puzity;
    private  Miestnost miestnostModry;
    private  Miestnost miestnostCerveny;

    public PredmetPortalGun(String nazov, Prostredie prostredie) {

        this.nazov = nazov;
        this.popis = "Slovenska verzia PorgalGun";
        this.prostredie = prostredie;
        this.modry = false;
        this.cerveny = false;
        this.miestnostModry = null;
        this.miestnostCerveny = null;
        this.puzity = false;
    }

    @Override
    public String getMeno() {
        return this.nazov;
    }

    @Override
    public void pouziSa(Miestnost miestnost, Hrac hrac) {

        // 1 použie
        if (!this.cerveny) {
            System.out.println("Polozil si cerveny");
            if (this.miestnostModry != null && this.miestnostCerveny != null) {
                this.prostredie.odstranVychod("cerveny", this.miestnostCerveny);
            }
            this.miestnostCerveny = miestnost;
            this.cerveny = true;
            this.modry = false;
        } else if (!this.modry) {
            System.out.println("Polozil si modry");
            if (this.miestnostModry != null && this.miestnostCerveny != null) {
                this.prostredie.odstranVychod("modry", this.miestnostModry);
            }
            this.miestnostModry = miestnost;
            this.modry = true;
            this.cerveny = false;
        }

        if (this.miestnostModry != null && this.miestnostCerveny != null) {
            this.prostredie.pridajVychod("cerveny", this.miestnostModry, this.miestnostCerveny);
            this.prostredie.pridajVychod("modry", this.miestnostCerveny, this.miestnostModry);
            return;
        }





    }

    @Override
    public void ulozPoziciu(DataOutputStream save) {

    }

    @Override
    public boolean getDaSaPolozit() {
        return true;
    }

    @Override
    public void nacitajPoziciu(DataInputStream save, int saveVersion) {

    }
}
