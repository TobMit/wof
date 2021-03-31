import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sk.uniza.fri.wof.prostredie.Miestnost;

/**
 *
 * @author janik
 */
public class MiestnostTest {

    private Miestnost nemaVychody;
    private Miestnost zapad;
    private Miestnost vychod;
    private Miestnost sever;
    private Miestnost juh;

    @BeforeEach
    public void setUp() {
        this.nemaVychody = new Miestnost("nema vychody", "terasa");
        this.zapad = new Miestnost("zapad", "terasa");
        this.vychod = new Miestnost("vychod", "terasa");
        this.sever = new Miestnost("sever", "terasa");
        this.juh = new Miestnost("juh", "terasa");
        this.zapad.nastavVychod("sever", this.sever);
        this.zapad.nastavVychod("vychod", this.vychod);
        this.zapad.nastavVychod("juh", this.juh);
        this.vychod.nastavVychod("sever", this.sever);
        this.vychod.nastavVychod("juh", this.juh);
        this.vychod.nastavVychod("zapad", this.zapad);
        this.sever.nastavVychod("vychod", this.vychod);
        this.sever.nastavVychod("juh", this.juh);
        this.sever.nastavVychod("zapad", this.zapad);
        this.juh.nastavVychod("sever", this.sever);
        this.juh.nastavVychod("vychod", this.vychod);
        this.juh.nastavVychod("zapad", this.zapad);
    }

    @Test
    public void nemaVychody() {
        Assertions.assertEquals("nema vychody", this.nemaVychody.getPopis());
        Assertions.assertNull(this.nemaVychody.getMiestnostVSmere("vychod"));
        Assertions.assertNull(this.nemaVychody.getMiestnostVSmere("zapad"));
        Assertions.assertNull(this.nemaVychody.getMiestnostVSmere("sever"));
        Assertions.assertNull(this.nemaVychody.getMiestnostVSmere("juh"));
    }

    @Test
    public void sever() {
        Assertions.assertEquals("sever", this.sever.getPopis());
        Assertions.assertSame(this.vychod, this.sever.getMiestnostVSmere("vychod"));
        Assertions.assertSame(this.zapad, this.sever.getMiestnostVSmere("zapad"));
        Assertions.assertNull(this.sever.getMiestnostVSmere("sever"));
        Assertions.assertSame(this.juh, this.sever.getMiestnostVSmere("juh"));
    }

    @Test
    public void juh() {
        Assertions.assertEquals("juh", this.juh.getPopis());
        Assertions.assertSame(this.vychod, this.juh.getMiestnostVSmere("vychod"));
        Assertions.assertSame(this.zapad, this.juh.getMiestnostVSmere("zapad"));
        Assertions.assertSame(this.sever, this.juh.getMiestnostVSmere("sever"));
        Assertions.assertNull(this.juh.getMiestnostVSmere("juh"));
    }
}