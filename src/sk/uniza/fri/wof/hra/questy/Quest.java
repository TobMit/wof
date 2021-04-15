package sk.uniza.fri.wof.hra.questy;

public abstract class Quest {
    private boolean jeUkonceny;

    public Quest() {
        this.jeUkonceny = false;
    }

    public boolean getJeUkonceny() {
        return this.jeUkonceny;
    }
    public abstract String getNazov();

    protected void ukonci() {
        this.jeUkonceny = true;
    }
}
