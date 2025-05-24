public class Spiel {
  private byte dran;
  private byte phase;
  private byte zugNummer;
  private Spieler[] mitSpieler;
  private Kontinent[] kontinente;
  private Land[] laender;
  private RisikoGui gui;
  private Land vonLand;
  private Land nachLand;
  private byte[] wuerfel = new byte[5];
  
  public Spiel(String spielerNamen[], RisikoGui meineGui) {
    gui = meineGui;
    mitSpieler[] = new Spieler[spielerNamen.length]; //Erstellung der Spieler
    for (i = 0; i < spielerNamen.length; i++) {
      mitSpieler[i] = new Spieler(spielerNamen[i]);
    }
    
    String[42] laenderNamen = {"Argentinien", "Peru", "Brasilien", "Venezuela"}; //Erstellung der Laender und Kontinente
    int[42][5] angrenzende = {//Todo (aeussere, innere Dimension
      {}
    };
    int[6] kontinentLaender = {4, 9, 6, 6, 12, 4}; //die Laender muessen alle nach Kontinenten (siehe naechste Zeile) sortiert im Array stehen!
    String[6] kontinentNamen = {"Suedamerika", "Nordmerika", "Europa", "Afrika", "Asien", "Australien"};

    for (byte i = 0; i < 42; i++) {
      laender[i] = new Land(angrenzende[i], laenderNamen[i]);
    }
    byte zuKontinentHinzugefuegt = 0;
    for (byte i = 0; i < 6; i++) {
      Land[] hinzuzfuegendeLaender = new Land[kontinentLaender[i];
      for (int j = 0; j < kontinentLaender[i]; i++) {
        hinzuzfuegendeLaender[j] = laender[zuKontinentHinzugefuegt+];
      }
      kontinente[i] = new Kontinent(hinzuzfuegendeLaender, kontinentNamen[i]);
    }
  }
  
  public void landKlickAktion(Land land, byte taste) {
    switch (phase) {
      case 0 : //truppenPlazieren, eigentlich je nach maustaste
        truppenPlazieren(1, land);
        if (platzierteTruppen >= zuPlatzierendeTruppen) {
          phasenWechsel();
        }
        break;
      case 1 : //angreifen also eigenes Land auswaehlen
        if (land.getHerrscher() == dran) {
          vonLand = land;
          phasenWechsel();
        } else {
          System.out.println("Dieses Land gehoert nicht dir");
        }
        break;
      case 2 : //angreifen also Feind auswaehlen
        if (land.getHerrscher() != dran) {
          nachLand = land;
          phasenWechsel();
        } else {
          System.out.println("Dieses Land gehoert dir");
        }
        break;
      case 3 : //angreifen also im Kampf
        //wie kann das aufgerufen werden also muss halt vom kampfbutton aufgerufen werden; was? -j
        break;
      case 4 : //Truppen verschieben also StartLand auswaehlen
        if (land.getHerrscher() == dran) {
          vonLand = land;
          phasenWechsel();
        } else {
          System.out.println("Dieses Land gehoert nicht dir");
        }
        break;
      case 5 : //Truppen verschieben also zielLand auswaehlen
        nichtDurch();
        break;
    }
  }
  
  public void phasenWechsel() {
    //...
    gui.grafikErneuern();
  }
  public byte getPhase() {
    return this.phase;
  }
  public int getDran() {
    return this.dran;
  }
  
  private boolean truppenPlatzieren(int anzahl, Land ziel) {//anzahl aus gui uebergeben, auch von da aufrufen
    if (byte dran == ziel.getHerrscher()) {
      ziel.setTruppen(dran, anzahl);
      mitSpieler[dran].setGesamtTruppen(anzahl);
      return true;
    } // end of if
    else {
      System.out.println("Dies ist nicht dein Land!");
      return false;  
    } // end of if-else
  }
  
  private int anzahlLaenderSpieler(Spieler spieler) {
    
  }
  
  private int anzahlTruppenSpieler(Spieler spieler) {
    
  }
  
  private int einmarschieren(Land von, Land nach) {
    
  }
  
  private void kampf(Land meinLand) {
    
  }
  
  public int[] getWuerfel() {
    
  }
  
  private boolean verschiebeTruppen(Land von, Land nach) {
    
  }
  
  public void getLand(byte stelle) {
    if (stelle < laender.length) {
      return laender[i];
    } else {
      System.out.println("Error das Land gibt es nicht");
      return null;
    }
  }
  
  public byte getGewonnen() {
    boolean gewonnen;
    for (byte i = 0; i < mitSpieler.length; i++) {
      gewonnen = true;
      for (byte j = 0; j < laender.length; j++) {
        if (laender[j].getHerrscher() != i) {
          gewonnen = false;
        }
      }
      if (gewonnen == true) {
        System.out.println("Spieler" + i + " hat gewonnen");
        return i;
      }
    }
    return 100;
  }

  public String getSpielerName(byte sNr) {
    
  } 
  
  public void schonDurchReset() {//extramethode??
    for (byte i = 0; i < laender.length; i++) {
      laender[i].setSchonDurch(false);
    }
  }
}
