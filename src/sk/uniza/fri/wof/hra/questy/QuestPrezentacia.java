package sk.uniza.fri.wof.hra.questy;

import sk.uniza.fri.wof.hra.Hrac;
import sk.uniza.fri.wof.prostredie.predmety.QuestovyPredmet;

public class QuestPrezentacia extends Quest implements IQuestKontrolaMiestnosti, IquestKontrolaPredmet {
    private final String quest;
    private boolean bolUsbSpawnuty;


    public QuestPrezentacia(String quest) {
        this.quest = quest;
        this.bolUsbSpawnuty = false;
    }

    @Override
    public void hracVosielDoMiestnosti(Hrac hrac) {
        if (hrac.getAktualnaMiestnost().getPopis().startsWith("RA006")) {
            if (hrac.getMaPredmet("usb")) {
                System.out.println("Ok, tak mozes spustit prezentaciu");
            } else {
                System.out.println("Ved si si zabudol prezentaciu");
            }
        } else if (hrac.getAktualnaMiestnost().getPopis().startsWith("IC")) {
            if (!this.bolUsbSpawnuty) {
                hrac.getAktualnaMiestnost().polozPredmet(new QuestovyPredmet("usb"));
                this.bolUsbSpawnuty = true;
            }
        }
    }

    @Override
    public void hracPouzilQuestovyPredmet(Hrac hrac, QuestovyPredmet questovyPredmet) {
        if (questovyPredmet.getMeno().equals("usb")) {
            if (hrac.getAktualnaMiestnost().getPopis().startsWith("RA006")) {
                this.ukonci();
            } else {
                System.out.println("Aby si mohol nieco robit s usb klucom, musis byt v labaku");
            }
        }
    }

    @Override
    public String getNazov() {
        return this.quest;
    }


}
