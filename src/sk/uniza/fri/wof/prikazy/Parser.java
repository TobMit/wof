package sk.uniza.fri.wof.prikazy;

import java.util.Scanner;

/**
 * Trieda sk.uniza.fir.wof.prikazy.Parser cita vstup zadany hracom do terminaloveho okna a pokusi sa
 * interpretovat ho ako prikaz hry. Kazda sprava dajPrikaz sposobi, ze parser
 * precita jeden riadok z terminaloveho okna a vyberie z neho prve dve slova.
 * Tie dve slova pouzije ako parametre v sprave new triede sk.uniza.fir.wof.prikazy.Prikaz.
 * 
 * @author  Michael Kolling and David J. Barnes
 * @version 2006.03.30
 * @author  lokalizacia: Lubomir Sadlon, Jan Janech
 * @version 2012.02.21
 */

/**
 * sk.uniza.fir.wof.prikazy.Parser je trieda alebo objekt ktorá slúži na čítanie textov, na základe toho potom pracuje. Čiže sk.uniza.fir.wof.prikazy.Parser je objedk ktorý načitáva zo vstupu.
 * Číta to čo niekto iný nedokáže čítať a remieňa na to čo vedia ostatný čitať.
 */
public class Parser {
    private final ZoznamPrikazov prikazy;  // odkaz na pripustne nazvy prikazov
    private final Scanner citac;         // zdroj vstupov od hraca

    /**
     * Vytvori citac na citanie vstupov z terminaloveho okna.
     * @param prikazy
     */
    public Parser(ZoznamPrikazov prikazy) {
        this.prikazy = prikazy;
        this.citac = new Scanner(System.in);
    }

    /**
     * @return prikaz zadany hracom
     */
    public Prikaz nacitajPrikaz() {
        System.out.print("> ");     // vyzva pre hraca na zadanie prikazu

        String vstupnyRiadok = this.citac.nextLine();

        String prikaz = null;
        String parameter = null;

        // najde prve dve slova v riadku 
        Scanner tokenizer = new Scanner(vstupnyRiadok);
        /**
         * Tokenizer je objekt kotrý rozdeluje slová na objekty.
         */
        if (tokenizer.hasNext()) {
            prikaz = tokenizer.next();      // prve slovo
            if (tokenizer.hasNext()) {
                parameter = tokenizer.next();      // druhe slovo
                // vsimnite si, ze zbytok textu sa ignoruje
            }
        }

        // kontrola platnosti prikazu
        if (this.prikazy.jePrikaz(prikaz)) {
            // vytvori platny prikaz
            return new Prikaz(prikaz, parameter);
        } else {
            // vytvori neplatny - "neznamy" prikaz
            return new Prikaz(null, parameter); 
        }
    }

    public int nacitajVolbu() {
        return this.citac.nextInt();
    }
}
