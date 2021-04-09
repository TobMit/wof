package sk.uniza.fri.wof.prostredie.predmety;
import sk.uniza.fri.wof.hra.Hrac;
import sk.uniza.fri.wof.prostredie.Miestnost;

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
    public boolean getDaSaPolozit() {
        return !this.nasadene;
    }
}
