import java.io.IOException;
import java.util.Scanner;

/**
 * A program belépési pontja.
 */
public class FodraszatApp {

  /**
   * A belépési pont konkrétuma
   * @param args Parancssori argumentumok
   */
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    System.out.println("Kérem adja mg a nevét:");
    String felhasznaloNev = scanner.nextLine();

    FoglalasKezelo foglalasKezelo = new FoglalasKezelo();

    try {
      foglalasKezelo.betoltes("src/main/foglalasok.txt");
      boolean sikeresFoglalas = false;

      while (!sikeresFoglalas) {
        foglalasKezelo.megjelenitFoglalasok();
        System.out.println("Kérem adja meg a sor koordinátát (0-hétfő, 1-kedd, 2-szerda):");
        int sor = scanner.nextInt();
        System.out.println("Kérem adja meg az oszlop koordinátát (0-08:00, 1-08:30, ...):");
        int oszlop = scanner.nextInt();

        sikeresFoglalas = foglalasKezelo.foglal(sor, oszlop, felhasznaloNev);
        if (!sikeresFoglalas) {
          System.out.println("Ez az időpont már foglalt. Kérem válasszon másikat.");
        }
      }

      foglalasKezelo.mentes("src/main/foglalasok.txt");
      System.out.println("Foglalása sikeresen rögzítve!");
    } catch (IOException e) {
      System.err.println("Hiba történt az állomány kezelés során: " + e.getMessage());
    }
  }
}
