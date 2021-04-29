package sk.uniza.fri.wof.prostredie.predmety;
import sk.uniza.fri.wof.hra.Hrac;
import sk.uniza.fri.wof.prostredie.Miestnost;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class PredmetRusko implements IPredmet, IKontorlaPolozenia {
    private boolean nasadene;

    public PredmetRusko() {
        this.nasadene = false;
    }

    @Override
    public String getMeno() {
        return "rusko";
    }

    @Override
    public void pouziSa(Miestnost miestnost, Hrac hrac) {
        if (!this.nasadene) {
            System.out.println("Nasadil si si rusko");
            this.nasadene = true;
        } else {
            System.out.println("Zlozil si si rusko");
            this.nasadene = false;
        }


    }

    @Override
    public void ulozPoziciu(DataOutputStream save) {

    }

    @Override
    public boolean getDaSaPolozit() {
        return !this.nasadene;
    }

    @Override
    public void nacitajPoziciu(DataInputStream save, int saveVersion) throws IOException {
        this.nasadene = save.readBoolean();
    }
}
