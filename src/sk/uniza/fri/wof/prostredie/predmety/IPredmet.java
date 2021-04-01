package sk.uniza.fri.wof.prostredie.predmety;
import sk.uniza.fri.wof.prostredie.Miestnost;

public interface IPredmet {
    String getMeno();
    void pouziSa(Miestnost aktualnaMiestnost);

}
