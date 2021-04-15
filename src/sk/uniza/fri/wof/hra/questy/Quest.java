package sk.uniza.fri.wof.hra.questy;

public class Quest {
    private boolean jeUkonceny;

    public Quest() {
        this.jeUkonceny = false;
    }

    public boolean getJeUkonceny() {
        return this.jeUkonceny;
    }
    public String getNazov() {
        return null;
    }

    protected void ukonci() {
        this.jeUkonceny = true;
    }
}
