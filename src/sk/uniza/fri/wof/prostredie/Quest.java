package sk.uniza.fri.wof.prostredie;

import sk.uniza.fri.wof.hra.Hrac;

public class Quest {
    private final String quest;
    private boolean jeUkonceny;
    private boolean bolUsbSpawnuty;


    public Quest(String quest) {
        this.quest = quest;
        this.jeUkonceny = false;
        this.bolUsbSpawnuty = false;
    }

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

    public void hracPouzilQuestovyPredmet(Hrac hrac, QuestovyPredmet questovyPredmet) {
        if (questovyPredmet.getMeno().equals("usb")) {
            if (hrac.getAktualnaMiestnost().getPopis().startsWith("RA006")) {
                this.jeUkonceny = true;
            } else {
                System.out.println("Aby si mohol nieco robit s usb klucom, musis byt v labaku");
            }
        }
    }

    public boolean getJeUkonceny() {
        return this.jeUkonceny;
    }

    public String getNazov() {
        return this.quest;
    }

}
