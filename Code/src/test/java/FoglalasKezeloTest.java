import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tesztek a FoglalasKezelo osztályhoz.
 */
public class FoglalasKezeloTest {

  private FoglalasKezelo foglalasKezelo;

  @BeforeEach
  void setUp() {
    foglalasKezelo = new FoglalasKezelo();
  }

  /**
   * Ellenőrzi, hogy a foglalás sikeresen megtörténik-e egy üres időpontnál.
   */
  @Test
  void testSikeresFoglalas() {
    assertTrue(foglalasKezelo.foglal(0, 0, "Teszt Felhasználó"),
            "Az üres időpontot sikeresen le kellett volna foglalni.");
  }

  /**
   * Ellenőrzi, hogy foglalt időpontot nem lehet újra lefoglalni.
   */
  @Test
  void testFoglalasUjraFoglalasra() {
    foglalasKezelo.foglal(0, 0, "Első Felhasználó");
    assertFalse(foglalasKezelo.foglal(0, 0, "Második Felhasználó"),
            "Már foglalt időpontot nem lehet újra lefoglalni.");
  }

  /**
   * Ellenőrzi a foglalások mentését és betöltését fájlból.
   */
  @Test
  void testMentesEsBetoltes() throws IOException {
    String tesztFajl = "foglalasok.txt";

    // Előzetes állapot mentése
    foglalasKezelo.foglal(0, 0, "Teszt Felhasználó");
    foglalasKezelo.mentes(tesztFajl);

    // Új példány létrehozása és fájlból betöltés
    FoglalasKezelo ujFoglalasKezelo = new FoglalasKezelo();
    ujFoglalasKezelo.betoltes(tesztFajl);

    // Ellenőrzés, hogy az időpontok megfelelően kerültek betöltésre
    assertEquals("Teszt Felhasználó", ujFoglalasKezelo.foglalasok[0][0],
            "A foglalásnak meg kellett volna maradnia betöltés után.");

    // Teszt fájl törlése
    new File(tesztFajl).delete();
  }

}
