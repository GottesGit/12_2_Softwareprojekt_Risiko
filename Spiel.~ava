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
  private String[] laenderNamen;
  private String[] kontinentNamen;//keine ahnung, wo die hin sollten, deswegen erst mal global
  private int[] kontinentLaender;
  private byte[] angeiferWuerfel = new byte[3];
  private byte[] verteidigerWuerfel = new byte[2];
  
  public Spiel(String spielerNamen[], RisikoGui meineGui) {
    gui = meineGui;
    mitSpieler = new Spieler[spielerNamen.length]; //Erstellung der Spieler
    for (int i = 0; i < spielerNamen.length; i++) {
      mitSpieler[i] = new Spieler(spielerNamen[i]);
    }
    
    String[] laenderNamen = {"Argentinien", "Peru", "Brasilien", "Venezuela"}; //Erstellung der Laender und Kontinente
    byte[][] angrenzende = {//Todo (aeussere, innere Dimension)
      {2, 3, 0, 0, 0, 0}, {1, 2, 3, 0, 0, 0}, {1, 2, 3, 0, 0, 0}, {2, 3, 0, 0, 0, 0}, //Suedamerika
      {0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0}, //Nordmerika
      {0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0}, //Europa
      {0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0}, //Afrika
      {0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0}, //Asien
      {0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0}, //Australien
    }; //das ist gar nicht so falsch
    byte[] kontinentLaender = {4, 9, 6, 6, 12, 4}; //Anzahl der Laender der jeweiligen Kontinente
    String[] kontinentNamen = {"Suedamerika", "Nordmerika", "Europa", "Afrika", "Asien", "Australien"};

    for (byte i = 0; i < 42; i++) {
      laender[i] = new Land(angrenzende[i], laenderNamen[i], i);
    }
    
    byte zuKontinentHinzugefuegt = 0;
    
    for (byte i = 0; i < 6; i++) {
      Land[] hinzuzfuegendeLaender = new Land[kontinentLaender[i]];
      for (int j = 0; j < kontinentLaender[i]; i++) {
        hinzuzfuegendeLaender[j] = laender[zuKontinentHinzugefuegt++];
      }
      kontinente[i] = new Kontinent(hinzuzfuegendeLaender, kontinentNamen[i]);
  }
  }
  
  public void landKlickAktion(Land land, int taste) {//int zu byte konversion ist kacke, deswegen kein byte  
    switch (phase) {
      case 0 : //truppenPlazieren, eigentlich je nach maustaste
        land.setTruppen(dran, berechneZuPlazierendeTruppen());
        if (berechneGesamtTruppen(dran) == mitSpieler[dran].getGesamtTruppen() + berechneZuPlazierendeTruppen()) {
          phasenWechsel();
        } else if (berechneGesamtTruppen(dran) > mitSpieler[dran].getGesamtTruppen() + berchneZuPlazierendeTruppen()) {
          System.out.println("Error zu viele Truppen wurden plaziert");
          phasenWechsel();
        }
        break;
      case 1 : //angreifen also eigenes Land auswaehlen
        if (land.getHerrscher() == dran && land.getTruppen() > 1) {
          vonLand = land;
          phasenWechsel();
        } else {
          System.out.println("Error falsches Land 1");
        }
        break;
      case 2 : //angreifen also Feind auswaehlen
        if (land.getHerrscher() != dran && istBenachbart(land.getIndex())) {
          nachLand = land;
          phasenWechsel();
        } else {
          System.out.println("Error falsches Land 2");
        }
        break;
      case 3 : //angreifen also Truppen nach Zielland verschieben
        if (land == nachLand) {
          if (einmarschieren(1)) {
            phasenWechsel();
          }
        } else {
          System.out.println("Error falsches Land 3");
        }
        break;
      case 4 : //angreifen also im Kampf
        System.out.println("Error ein Land wurde im Kampf ausgewaehlt");
        break;
      case 5 : //Truppen verschieben also StartLand auswaehlen
        if (land.getHerrscher() == dran && land.getTruppen() > 1) {
          vonLand = land;
          phasenWechsel();
        } else {
          System.out.println("Error falsches Land 4");
        }
        break;
      case 6 : //Truppen verschieben also ZielLand auswaehlen
        if (land.getHerrscher() == dran && land != vonLand && istVerbunden(land.getIndex())) {
          nachLand = land;
        }
        schonDurchReset();
        break;
      case 7 : //Truppen verschieben also Truppen nach Zielland verschieben
        if (land == nachLand) {
          verschiebeTruppen();
        } else {
          System.out.println("Error falsches Land beim Verschieben ausgewaehlt");
        }
        break;
    }
    gui.grafikErneuern();
  }
  
  public void buttonKlickAktion(byte knopf, byte taste) { //ok (1) und Kampfbutton (2)
    if (knopf == 1) {
      phasenWechsel(); //vllt geht das nicht immer einfach so?
      gui.grafikErneuern();
    } else if (knopf == 2) {
      if (phase == 3) {
        kampf();
        gui.grafikErneuern(); //je nach dem, ob der Kampf bereits eine Animation macht unnoetig
      } else {
        System.out.println("Error der Kampfbutton wurde faelschlich bedient");
      }
    } else {
      System.out.println("Error ein unbekannter Button wurde bedient");
    }
  }
  
  public void phasenWechsel() {
    boolean fehler = false;
    switch (phase) {
      case 0 : //truppenPlazieren
        aktualisiereTruppenLaender(dran); //darf nicht schon vorher gemacht werden weil damit berechnet wird, wie viele noch plaziert werden duerfen
        phase++;
        break;
      case 1 : //angreifen also eigenes Land auswaehlen
        if (vonLand != null) {
          phase++;
        } else {
          fehler = true;
        }
        break;
      case 2 : //angreifen also Feind auswaehlen
        if (nachLand != null) {
          phase++;
        } else {
          fehler = true;
        }
        break;
      case 3 : //angreifen also Truppen nach Zielland verschieben
        if (nachLand.getAngreiferTruppen() > 0) {
          phase++;
        } else {
          fehler = true;
        }
        break;
      case 4 : //angreifen also im Kampf
        if (nachLand.getAngreiferTruppen() == 0) {
          aktualisiereTruppenLaender(dran);
          vonLand = null;
          nachLand = null;
          phase++;
        } else {
          fehler = true;
        }
        break;
      case 5 : //Truppen verschieben also StartLand auswaehlen
        if (vonLand != null) {
          phase++;
        } else {
          fehler = true;
        }
        break;
      case 6 : //Truppen verschieben also ZielLand auswaehlen
        if (nachLand != null) {
          phase++;
        } else {
          fehler = true;
        }
        break;
      case 7 : //Truppen verschieben also Truppen nach Zielland verschieben
        vonLand = null;
        nachLand = null;
        zugNummer++;
        if (dran >= mitSpieler.length - 1) {
          dran = 0;
        } else {
          dran++;
        }
        phase = 0;
        break;
    }
    if (fehler) {
      System.out.println("Error die Phase " + phase + " kann nicht geaendert werden");
    }
  }
  
  public boolean istBenachbart(byte meinLand) {
    boolean istEinNachbar = false;
    for (byte i = 0; i < vonLand.getNachbarn().length; i++) {
      if (vonLand.getNachbarn()[i] == laender[meinLand]) {
        istEinNachbar = true;
      }
    }
    return istEinNachbar;
  }
  
  public boolean istVerbunden(byte meinLand) {
    boolean istEinVerbundener = vonLand.getVerbunden(meinLand, dran);
    schonDurchReset();
    return istEinVerbundener;
  }

  public byte getPhase() {
    return this.phase;
  }
  
  public byte getDran() {
    return this.dran;
  }
  
  public byte getNachLand() {
    return this.nachLand;
  }
  
  public byte getVonLand() {
    return this.vonLand;
  }
  
  private boolean truppenPlatzieren(int anzahl, Land meinLand) { //von LandKlickAktionen in Phase 0 aufgerufen
    if (dran == meinLand.getHerrscher()) {
      meinLand.setTruppen(dran, meinLand.getTruppen() + anzahl);
      return true;
    }
    else {
      System.out.println("Error es werden Truppen in fremde Laender plaziert");
      return false;  
    }
  }
  
  private int getAnzahlLaender(byte meinSpieler) {
    return this.mitSpieler[meinSpieler].getGesamtLaender();
  }
  
  private int getAnzahlTruppen(byte meinSpieler) {
    return this.mitSpieler[meinSpieler].getGesamtTruppen();
  }
  
  private boolean einmarschieren(int anzahl) {
    if (dran != nachLand.getHerrscher()) {
      if (vonLand.getTruppen() > 1 + anzahl) {
        vonLand.setTruppen(mitSpieler[dran], vonLand.getTruppen() - anzahl);
        nachLand.setAngreiferTruppen(mitSpieler[dran], nachLand.getAngreiferTruppen() + anzahl);
        return true;
      } else {
        System.out.println("Error es werden zu viele Truppen zum Angreifen verwendet"); //bis jetzt ist diese Fehlermeldung einfach auszuloesen
        return false;
      }
    }
    else {
      System.out.println("Error eigenes Land wird angegriffen");
      return false;
    }
  }
  
  private void kampf() {
    //..
    if (nachLand.getTruppen() == 0 || nachLand.getAngeiferTruppen == 0) {
      aktualisiereTruppenLaender(nachLand.getHerrscher());
      phasenwechsel();
    }
  }
  
  public int[] getWuerfel() {//sollte dann von Gui abgefragt werden
    
  }
  
  private boolean verschiebeTruppen(int anzahl) {
    if (dran == vonLand.getHerrscher() && dran == nachLand.getHerrscher()) {
      if (vonLand.getTruppen() > 1 + anzahl) {
        vonLand.setTruppen(mitSpieler[dran], vonLand.getTruppen() - anzahl);
        nachLand.setTruppen(mitSpieler[dran], nachLand.getTruppen() + anzahl);
        return true;
      } else {
        System.out.println("Error es werden zu viele Truppen verschoben"); //bis jetzt ist diese Fehlermeldung einfach auszuloesen
        return false;
      }
    }
    else {
      System.out.println("Error eigenes Land wird angegriffen");
      return false;
    }
  }
  
  public Land getLand(byte stelle) {
    if (stelle < laender.length) {
      return laender[stelle];
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
    return this.mitSpieler[sNr].getName();
  }
  
  private int berchneZuPlazierendeTruppen() {
    int truppen = 0;
    for (byte i = 0; i < laender.length; i++) {
      if (laender[i].getHerrscher() == dran) {
        truppen++;//1 Truppe pro Land?
      }
    }
    for (byte i = 0; i < kontinente.length; i++) {
      if (kontinente[i].beherrschtVon(dran)) {
        truppen += kontinente[i].getExtraTruppen();
      }
    }
    return truppen;
  }
  
  private void berechneGesamtTruppen(byte meinSpieler) {
    int truppen = 0;
    for (byte i = 0; i < laender.length; i++) {
      if (laender[i].getHerrscher() == meinSpieler) {
        truppen += laender[i].getTruppen();
      }
    }
    return truppen;
  }
  
  private void aktualisiereTruppenLaender(byte meinSpieler){
    mitSpieler[meinSpieler].setGesamtTruppen(berechneGesamtTruppen(meinSpieler));
  }

  public void schonDurchReset() {//extramethode??
    for (byte i = 0; i < laender.length; i++) {
      laender[i].resetSchonDurch();
    }
  }
}
