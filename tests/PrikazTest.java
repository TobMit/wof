import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sk.uniza.fri.wof.prikazy.Prikaz;

/**
 *
 * @author janik
 */
public class PrikazTest {

    private Prikaz chodJuh;
    private Prikaz ukonciNull;
    private Prikaz nullSever;

    @BeforeEach
    public void setUp() {
        this.chodJuh = new Prikaz("chod", "juh");
        this.ukonciNull = new Prikaz("ukonci", null);
        this.nullSever = new Prikaz(null, "sever");
    }

    @Test
    public void getNazov() {
        Assertions.assertEquals("chod", this.chodJuh.getNazov());
        Assertions.assertNull(this.nullSever.getNazov());
        Assertions.assertEquals("ukonci", this.ukonciNull.getNazov());
    }

    @Test
    public void getParameter() {
        Assertions.assertEquals("juh", this.chodJuh.getParameter());
        Assertions.assertEquals("sever", this.nullSever.getParameter());
        Assertions.assertNull(this.ukonciNull.getParameter());
    }

    @Test
    public void jeNeznamy() {
        Assertions.assertFalse(this.chodJuh.jeNeznamy());
        Assertions.assertTrue(this.nullSever.jeNeznamy());
        Assertions.assertFalse(this.ukonciNull.jeNeznamy());
    }

    @Test
    public void maParameter() {
        Assertions.assertTrue(this.chodJuh.maParameter());
        Assertions.assertTrue(this.nullSever.maParameter());
        Assertions.assertFalse(this.ukonciNull.maParameter());
    }
}
