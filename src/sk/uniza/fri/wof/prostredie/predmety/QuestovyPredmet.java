package sk.uniza.fri.wof.prostredie.predmety;

import sk.uniza.fri.wof.hra.Hrac;
import sk.uniza.fri.wof.prostredie.Miestnost;
import sk.uniza.fri.wof.prostredie.predmety.IKontorlaPolozenia;
import sk.uniza.fri.wof.prostredie.predmety.IPredmet;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class QuestovyPredmet implements IPredmet, IKontorlaPolozenia {
    private final String meno;
    private boolean pouzity;

    public QuestovyPredmet(String meno) {
        this.meno = meno;
        this.pouzity = false;
    }

    @Override
    public boolean getDaSaPolozit() {
        return pouzity;
    }

    @Override
    public String getMeno() {
        return this.meno;
    }

    @Override
    public void pouziSa(Miestnost aktualnaMiestnost, Hrac hrac) {
        hrac.poziQusetovyPredmet(this);

        this.pouzity = true;

    }

    @Override
    public void ulozPoziciu(DataOutputStream save) {

    }

    @Override
    public void nacitajPoziciu(DataInputStream save, int saveVersion) {

    }
}
