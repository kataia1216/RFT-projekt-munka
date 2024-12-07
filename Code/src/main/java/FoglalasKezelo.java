import java.io.*;
import java.util.Arrays;

/**
 * Kezeli a fodrászat időpontfoglalásait mátrix formában.
 *
 */
public class FoglalasKezelo {
  /**
   * A foglalási mátrix definiálása
   */
  public final String[][] foglalasok = new String[3][16]; // 3 nap, 16 félórás időpont

  /**
   * Betölti a foglalásokat egy fájlból.
   *
   * @param fajlEleres Az állomány elérési útvonala.
   * @throws IOException Ha a fájl olvasása sikertelen.
   */
  public void betoltes(String fajlEleres) throws IOException {
    try (BufferedReader reader = new BufferedReader(new FileReader(fajlEleres))) {
      for (int i = 0; i < foglalasok.length; i++) {
        String sor = reader.readLine();
        if (sor != null) {
          String[] reszek = sor.split(";");
          for (int j = 0; j < reszek.length; j++) {
            foglalasok[i][j] = reszek[j].equals("Szabad") ? null : reszek[j];
          }
        }
      }
    }
  }


  /**
   * Mentés fájlba.
   *
   * @param fajlEleres Az állomány elérési útvonala.
   * @throws IOException Ha a fájl írása sikertelen.
   */
  public void mentes(String fajlEleres) throws IOException {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(fajlEleres))) {
      for (String[] sor : foglalasok) {
        String[] sorMenteni = new String[sor.length];
        for (int j = 0; j < sor.length; j++) {
          sorMenteni[j] = (sor[j] == null) ? "Szabad" : sor[j];
        }
        writer.write(String.join(";", sorMenteni));
        writer.newLine();
      }
    }
  }


  /**
   * Megjeleníti a foglalási táblázatot a konzolban.
   */
  public void megjelenitFoglalasok() {
    System.out.println("Foglalási táblázat:");
    String[] napok = {"Hétfő", "Kedd", "Szerda"};
    String[] idopontok = {"08:00", "08:30", "09:00", "09:30", "10:00", "10:30", "11:00", "11:30",
            "12:00", "12:30", "13:00", "13:30", "14:00", "14:30", "15:00", "15:30"};
    System.out.print("       ");
    for (String idopont : idopontok) {
      System.out.printf("%8s", idopont);
    }
    System.out.println();

    for (int i = 0; i < foglalasok.length; i++) {
      System.out.printf("%-7s", napok[i]);
      for (int j = 0; j < foglalasok[i].length; j++) {
        System.out.printf("%8s", foglalasok[i][j] == null ? "Szabad" : foglalasok[i][j]);
      }
      System.out.println();
    }
  }

  /**
   * Időpont foglalása.
   *
   * @param sor            A nap sorindexe.
   * @param oszlop         Az időpont oszlopindexe.
   * @param felhasznaloNev A foglaló neve.
   * @return true, ha sikeres volt a foglalás; false, ha az időpont foglalt.
   */
  public boolean foglal(int sor, int oszlop, String felhasznaloNev) {
    if (sor >= 0 && sor < foglalasok.length && oszlop >= 0 && oszlop < foglalasok[sor].length) {
      if (foglalasok[sor][oszlop] == null) {
        foglalasok[sor][oszlop] = felhasznaloNev;
        return true;
      }
    }
    return false;
  }

}
