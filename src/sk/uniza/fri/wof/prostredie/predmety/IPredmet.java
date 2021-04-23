package sk.uniza.fri.wof.prostredie.predmety;
import sk.uniza.fri.wof.hra.Hrac;
import sk.uniza.fri.wof.prostredie.Miestnost;
import sk.uniza.fri.wof.prostredie.SmrtException;

import java.io.Serializable;

// rozširenei interface
public interface IPredmet {
    String getMeno();
    void pouziSa(Miestnost aktualnaMiestnost, Hrac hrac) throws NepuzitelnyPredmetExceptions, SmrtException;

}
