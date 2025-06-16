import java.util.Arrays;

public class Spiel {
  private byte phase = 100;
  private byte dran = 0;
  private byte zugNummer;
  private Spieler[] mitSpieler;
  private Land[] laender = new Land[42];
  private Kontinent[] kontinente = new Kontinent[6];
  private RisikoGui gui;
  private Land vonLand;
  private Land nachLand;
  private int[] kontinentLaender;
  private byte[] angreiferWuerfel = new byte[3];
  private byte[] verteidigerWuerfel = new byte[2];
  private byte angreiferWuerfelAnzahl;
  private byte verteidigerWuerfelAnzahl;
  private int truppenVorher;
  private int truppenZumSetzen;
  boolean kampfGewonnen = false;
  int startTruppen;//die man am Anfang kriegt, also in phase 100
  boolean kannAngreifen;
  
  public Spiel(String spielerNamen[], RisikoGui meineGui) {
    gui = meineGui;
    mitSpieler = new Spieler[spielerNamen.length]; //Erstellung der Spieler
    for (int i = 0; i < spielerNamen.length; i++) {
      mitSpieler[i] = new Spieler(spielerNamen[i]);
    }
    
    String[] laenderNamen = {"Argentinien", "Peru", "Brasilien", "Venezuela", "Mittelamerika", "Oststaaten", "Weststaaten", "Ontario", "Alberta", "Alaska", "Nordwest-Territorium", "Ostkanada", "Grönland", "Island", "Skandinavien", "Grossbritannien", "Westeuropa", "Südeuropa", "Nordeuropa", "Russland", "Nordafrika", "Ägypten", "Ostafrika", "Zentralafrika", "Südafrika", "Madagaskar", "Naher Osten", "Afghanistan", "Indien", "Ural", "China", "Südostasien", "Sibirien", "Mongolei", "Irkutsk", "Jakutien", "Kamtschatka", "Japan", "Indonesien", "Neuguinea", "Ostaustralien", "Westaustralien"}; //Erstellung der Laender und Kontinente
    byte[][] angrenzende = { //(aeussere, innere Dimension)
    {1, 2, 100, 100, 100, 100}, {0, 2, 3, 100, 100, 100}, {0, 1, 3, 20, 100, 100}, {1, 2, 4, 100, 100, 100}, //Suedamerika
    {3, 5, 6, 100, 100, 100}, {4, 6, 7, 11, 100, 100}, {4, 5, 7, 8, 100, 100}, {5, 6, 8, 10, 11, 12}, {6, 7, 9, 10, 100, 100}, {8, 10, 36, 100, 100, 100}, {7, 8, 9, 12, 100, 100}, {5, 7, 12, 100, 100, 100}, {7, 10, 11, 13, 100, 100}, //Nordamerika
    {12, 14, 15, 100, 100, 100}, {13, 14, 18, 19, 100, 100}, {13, 14, 16, 18, 100, 100}, {15, 17, 18, 20, 100, 100}, {16, 18, 19, 20, 21, 26}, {14, 15, 16, 17, 19, 100}, {14, 17, 18, 26, 27, 29}, //Europa
    {2, 16, 17, 21, 22, 23}, {17, 20, 22, 26, 100, 100}, {20, 21, 23, 24, 25, 26}, {20, 22, 24, 100, 100, 100}, {22, 23, 25, 100, 100, 100}, {22, 24, 100, 100, 100, 100}, //Afrika
    {17, 19, 21, 22, 27, 28}, {19, 26, 29, 28, 30, 100}, {26, 27, 30, 31, 100, 100}, {19, 27, 30, 32, 100, 100}, {27, 28, 29, 31, 32, 33}, {28, 30, 38, 100, 100, 100}, {29, 30, 33, 34, 35, 100}, {30, 34, 36, 37, 100, 100}, {32, 33, 35, 36, 100, 100}, {32, 34, 36, 100, 100, 100}, {9, 33, 34, 35, 37, 100}, {33, 36, 100, 100, 100, 100}, //Asien
    {31, 39, 41, 100, 100, 100}, {38, 40, 100, 100, 100, 100}, {39, 41, 100, 100, 100, 100}, {38, 40, 100, 100, 100, 100}, //Australien
    };
    byte[] kontinentLaender = {4, 9, 7, 6, 12, 4}; //Anzahl der Laender der jeweiligen Kontinente
    byte[] kontinentTruppen = {2, 5, 5, 3, 7, 2}; //Anzahl der Extratruppen der jeweiligen Kontinente
    String[] kontinentNamen = {"Suedamerika", "Nordmerika", "Europa", "Afrika", "Asien", "Australien"};
    
    switch (mitSpieler.length) {
      case 2 : 
        startTruppen = 40;
        break;
      case 3 : 
        startTruppen = 35;
        break;
      case 4 : 
        startTruppen = 30;
        break;
    }
    
    for (byte i = 0; i < 42; i++) { // die laender muessen erst alle erstellt werden...
      laender[i] = new Land(laenderNamen[i], i);
      byte herrscher;
      do {
        herrscher = (byte) (Math.random() * mitSpieler.length);
      } while (mitSpieler[herrscher].getGesamtTruppen() >= (byte) ((42.0 / mitSpieler.length) + 0.999));
      laender[i].setTruppen(herrscher, 1);
      mitSpieler[herrscher].setGesamtLaender(mitSpieler[herrscher].getGesamtLaender() + 1);
      mitSpieler[herrscher].setGesamtTruppen(mitSpieler[herrscher].getGesamtTruppen() + 1);
    }
    
    for (byte i = 0; i < 42; i++) { //...damit sie sich dann gegenseitig als Nachbarn bekommen koennen
      byte anzahlNachbarn = 0;
      for (int j = 0; j < 6; j++) {
        if (angrenzende[i][j] != 100){
          anzahlNachbarn++;
        }
      }
      Land[] nachbarn = new Land[anzahlNachbarn];
      for (int j = 0; j < anzahlNachbarn; j++) {
        nachbarn[j] = laender[angrenzende[i][j]];
      }
      laender[i].setNachbarn(nachbarn);
    }
    
    byte zuKontinentHinzugefuegt = 0;
    for (byte i = 0; i < 6; i++) { // fuer die 6 Kontinente
      Land[] hinzuzfuegendeLaender = new Land[kontinentLaender[i]]; // Das ist das Array fuer die Laender, die dem Kontinent hinzugefuegt werden sollen
      for (int j = 0; j < kontinentLaender[i]; j++) {
        hinzuzfuegendeLaender[j] = laender[zuKontinentHinzugefuegt++];
      }
      kontinente[i] = new Kontinent(hinzuzfuegendeLaender, kontinentNamen[i], kontinentTruppen[i]);
      //for (Land laendchen : hinzuzfuegendeLaender) {//NUR ZUM DEBUGGEN
      //  laendchen.kontin = i;
      //}
    }
  }
  
  public void landKlickAktion(Land land, int taste) {//int zu byte konversion ist kacke, deswegen kein byte 
    //System.out.println(land.getName() + " inPhase " + phase);
    switch (phase) {
      case 100:
        if (mitSpieler[dran].getGesamtTruppen() < startTruppen) {
          if (taste == 1){
            land.setTruppen(dran, (land.getTruppen() + 1));
            mitSpieler[dran].setGesamtTruppen(mitSpieler[dran].getGesamtTruppen() + 1);
          }
          else if (taste == 2) {
            truppenZumSetzen = Math.min(startTruppen - mitSpieler[dran].getGesamtTruppen(), 5);
            land.setTruppen(dran, land.getTruppen() + truppenZumSetzen);
            mitSpieler[dran].setGesamtTruppen(mitSpieler[dran].getGesamtTruppen() + truppenZumSetzen);
            System.out.println("Du hast " + truppenZumSetzen + " Truppen platziert!");
          }
        }
        else {
          System.out.println("Spieler " + (dran + 1) + " hat bereits genug Truppen!");
        }
        //System.out.println("GesamtTruppen:" + mitSpieler[dran].getGesamtTruppen());
        //System.out.println("taste: " + taste);
        
        int versuche = 0;
        do {
          dran = (byte)((dran + 1) % mitSpieler.length);
          versuche++;
        } while (mitSpieler[dran].getGesamtTruppen() >= startTruppen && versuche <= mitSpieler.length);
        
        boolean alleFertig = true;
        for (Spieler spieler : mitSpieler) {
          if (spieler.getGesamtTruppen() < startTruppen) {
            alleFertig = false;
            break;
          }
        }
        if (alleFertig) {
          System.out.println("Alle Spieler haben ihre Starttruppen gesetzt.");
          dran = 0;
          phasenWechsel();
        }
        break;
      case 0 : //truppenplatzieren je nach maustaste
        if (taste == 1) {
          land.setTruppen(dran, land.getTruppen() + 1);
        } else if (taste == 2) {
          land.setTruppen(dran, land.getTruppen() + Math.min(5, (mitSpieler[dran].getGesamtTruppen() + berechneZuPlatzierendeTruppen() - berechneGesamtTruppen(dran))));
        }
        if (berechneGesamtTruppen(dran) == mitSpieler[dran].getGesamtTruppen() + berechneZuPlatzierendeTruppen()) {
          phasenWechsel();
        } else if (berechneGesamtTruppen(dran) > mitSpieler[dran].getGesamtTruppen() + berechneZuPlatzierendeTruppen()) {
          System.out.println("Error zu viele Truppen wurden Platziert");
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
          if (taste == 1) {
            einmarschieren(1);
          } else if (taste == 2) {
            einmarschieren(Math.min(5, vonLand.getTruppen() - 1));
          }
          if (vonLand.getTruppen() < 2) {
            phasenWechsel();
          }
        } else {
          System.out.println("Error falsches Land 2");
        }
        break;
      case 3 : //angreifen also Truppen nach Zielland verschieben
        if (land == nachLand) {
          if (taste == 1) {
            einmarschieren(1);
          } else if (taste == 2) {
            einmarschieren(Math.min(5, vonLand.getTruppen() - 1));
          }
          if (vonLand.getTruppen() < 2) {
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
        if (istVerbunden(land.getIndex())) {
          nachLand = land;
          phasenWechsel();
          if (taste == 1) {
            verschiebeTruppen(1);
          } else if (taste == 2) {
            verschiebeTruppen(Math.min(5, vonLand.getTruppen() - 1));
          }
          if (vonLand.getTruppen() <= 1) {
            System.out.println("Alle Trupppen aus " + vonLand.getName() + " verschoben");
            phasenWechsel();
          }
        }
        schonDurchReset();
        break;
      case 7 : //Truppen verschieben also Truppen nach Zielland verschieben
        System.out.println("Verschieben sollte passieren");
        if (land == nachLand) {
          if (taste == 1) {
            verschiebeTruppen(1);
          } else if (taste == 2) {
            verschiebeTruppen(Math.min(5, vonLand.getTruppen() - 1));
          }
          if (vonLand.getTruppen() <= 1) {
            System.out.println("Alle Trupppen aus " + vonLand.getName() + " verschoben");
            phasenWechsel();
          }
        } else {
          System.out.println("Error falsches Land beim Verschieben ausgewaehlt");
        }
        break;
    }
    gui.grafikErneuern();
  }
  
  public int getStartTruppen() {
    return this.startTruppen;
  }
  
  public void buttonKlickAktion(byte knopf, byte taste) { //ok (1) und Kampfbutton (2)
    if (knopf == 1) {
      if (phase == 4 || phase == 3) { // im Kampf
        phase = 4;
        vonLand.setTruppen(dran, vonLand.getTruppen() + nachLand.getAngreiferTruppen());
        nachLand.setAngreiferTruppen(nachLand.getHerrscher(), 0);
        gui.grafikErneuern();
        phasenWechsel();
      } else if (phase == 5 || phase == 6) { //keine Truppen verschieben
        vonLand = null;//kopiert von phasenwechsel mit 7
        spielerWechsel();
      } else if (phase == 2) {
        vonLand = null;
        phase = 1;
      } else if (phase == 1) {
        vonLand = null;
        nachLand = null;
        if (kampfGewonnen == true) {
          mitSpieler[dran].karteZiehen();
        }
        phase = 5;
      } else {
        phasenWechsel();
      }
      gui.grafikErneuern();
    } else if (knopf == 2) {
      if (phase == 4 || phase == 3) {
        phase = 4;
        kampf();
        gui.grafikErneuern();
      } else {
        System.out.println("Error der Kampfbutton wurde faelschlich bedient");
      }
    } else {
      System.out.println("Error ein unbekannter Button wurde bedient");
    }
  }
  
  public void phasenWechsel() {
    boolean fehler = false;
    System.out.println("Phasenwechsel:" + phase);
    switch (phase) {
      case 100:
        phase = 0;
        break;
      case 0 : //truppenPlatzieren
        aktualisiereTruppenLaender(dran); //darf nicht schon vorher gemacht werden weil damit berechnet wird, wie viele noch Platziert werden duerfen
        phase++;
        mitSpieler[dran].kartenNutzen(true);
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
        } 
        else {
          fehler = true;
        }
        break;
      case 4 : //angreifen also im Kampf
        if (nachLand.getAngreiferTruppen() == 0) {
          aktualisiereTruppenLaender(dran);
          vonLand = null;
          nachLand = null;
          phase = 1;
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
        spielerWechsel();
        break;
    }
    
    if (fehler) {
      System.out.println("Error die Phase " + phase + " kann nicht geaendert werden");
    }    
  }
  
  private void spielerWechsel() {
    zugNummer++;
    dran++;
    if (dran >= mitSpieler.length) {
      dran = 0;
    }
    //System.out.println("Spieler" + dran + " hat Laender: " + mitSpieler[dran].getGesamtLaender());
    while (mitSpieler[dran].getGesamtLaender() <= 0) {
      if (++dran >= mitSpieler.length) {
        dran = 0;
        break;
      }
    }
    while (mitSpieler[dran].getGesamtLaender() <= 0) {
      if (++dran >= mitSpieler.length) {
        System.out.println("Fataler Error im Spielerwechsel");
        dran = 0;
        break;
      }
    }
    phase = 0;
  }
  
  private boolean truppenPlatzieren(int anzahl, Land meinLand) { //von LandKlickAktionen in Phase 0 aufgerufen
    if (dran == meinLand.getHerrscher()) {
      meinLand.setTruppen(dran, meinLand.getTruppen() + anzahl);
      return true;
    }
    else {
      System.out.println("Error es werden Truppen in fremde Laender Platziert");
      return false;  
    }
  }
  
  private void einmarschieren(int anzahl) {
    System.out.println("Verschiebe " + anzahl);
    if (dran != nachLand.getHerrscher()) {
      if (vonLand.getTruppen() > anzahl) {
        vonLand.setTruppen(dran, vonLand.getTruppen() - anzahl);
        nachLand.setAngreiferTruppen(dran, nachLand.getAngreiferTruppen() + anzahl);
      } else {
        System.out.println("Error es werden zu viele Truppen zum Angreifen verwendet");
      }
    } else {
      System.out.println("Error eigenes Land wird angegriffen");
    }
  }
  
  private void verschiebeTruppen(int anzahl) {
    if (dran == vonLand.getHerrscher()) {
      if (vonLand.getTruppen() > anzahl) {
        vonLand.setTruppen(dran, vonLand.getTruppen() - anzahl);
        nachLand.setTruppen(dran, nachLand.getTruppen() + anzahl);
      } else {
        System.out.println("Error es werden zu viele Truppen verschoben");
      }
    }
    else {
      System.out.println("Error Truppen werden aus Fremden Laendern verschoben");
    }
  }
  
  private void kampf() {
    angreiferWuerfelAnzahl = (byte) ((nachLand.getAngreiferTruppen() >= 3)?3:nachLand.getAngreiferTruppen()); // die beiden muessten je nach Truppenzahlen eingestellt werden
    verteidigerWuerfelAnzahl = (byte) ((nachLand.getTruppen() >= 2)?2:1);
    for (byte i = 0; i < angreiferWuerfelAnzahl; i++) {
      angreiferWuerfel[i] = (byte) ((Math.random() * 6) + 1);
    }
    for (byte i = 0; i < verteidigerWuerfelAnzahl; i++) {
      verteidigerWuerfel[i] = (byte) ((Math.random() * 6) + 1);
    }
    byte zwischen = 0; //bubblesort
    for (byte i = 0; i < angreiferWuerfelAnzahl - 1; i++) {
      for (byte j = 0; j < angreiferWuerfelAnzahl - 1; j++) {
        if (angreiferWuerfel[j] < angreiferWuerfel[j + 1]) {
          zwischen = angreiferWuerfel[j];
          angreiferWuerfel[j] = angreiferWuerfel[j + 1];
          angreiferWuerfel[j + 1] = zwischen;
        }
      }
    }
    if (verteidigerWuerfel[0] < verteidigerWuerfel[1]) { //und sortieren der zwei
      zwischen = verteidigerWuerfel[0];
      verteidigerWuerfel[0] = verteidigerWuerfel[1];
      verteidigerWuerfel[1] = zwischen;
    }
    
    byte wuerfelNr = 0;
    do { 
      //System.out.println(angreiferWuerfel[wuerfelNr] + " vs. " + verteidigerWuerfel[wuerfelNr]);
      if (angreiferWuerfel[wuerfelNr] > verteidigerWuerfel[wuerfelNr]) {
        nachLand.setTruppen(nachLand.getHerrscher(), nachLand.getTruppen() - 1);
      } else{
        nachLand.setAngreiferTruppen(dran, nachLand.getAngreiferTruppen() - 1);
      }
    } while (wuerfelNr++ < angreiferWuerfelAnzahl && wuerfelNr < verteidigerWuerfelAnzahl);
    
    if (nachLand.getTruppen() < 1 || nachLand.getAngreiferTruppen() < 1) {
      if (nachLand.getTruppen() < 1 && nachLand.getAngreiferTruppen() > 0){
        mitSpieler[nachLand.getHerrscher()].setGesamtLaender(mitSpieler[nachLand.getHerrscher()].getGesamtLaender() - 1);
        aktualisiereTruppenLaender(nachLand.getHerrscher());
        nachLand.setTruppen(dran, nachLand.getAngreiferTruppen());
        nachLand.setAngreiferTruppen(nachLand.getHerrscher(), 0);
        kampfGewonnen = true;
        mitSpieler[vonLand.getHerrscher()].setGesamtLaender(mitSpieler[vonLand.getHerrscher()].getGesamtLaender() + 1);
        getGewonnen();
      }
      aktualisiereTruppenLaender(nachLand.getHerrscher());
      nachLand.setAngreiferTruppen(dran, 0);
      gui.grafikErneuern();
      phasenWechsel();
    }
    gui.grafikErneuern();
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
  
  public boolean istVerbunden(byte mitLand) {
    boolean istEinVerbundener = vonLand.getVerbunden(laender[mitLand], dran);
    schonDurchReset();
    return istEinVerbundener;
  }
  
  public boolean kannAngreifen(byte i){
    for (Land nachbarLand : laender[i].getNachbarn()){
      if (nachbarLand.getHerrscher() != dran) {
        return true;
      }
    }
    return false;
  }
  
  public boolean kannVerschieben(byte i){
    for (Land nachbarLand : laender[i].getNachbarn()){
      if (nachbarLand.getHerrscher() == dran) {
        return true;
      }
    }
    return false;
  }
  
  public void schonDurchReset() {
    for (byte i = 0; i < laender.length; i++) {
      laender[i].resetSchonDurch();
    }
  }
  
  private int berechneZuPlatzierendeTruppen() {
    int extraTruppen = mitSpieler[dran].kartenNutzen(false);
    for (int i = 0; i < kontinente.length; i++) {
      if (kontinente[i].beherrschtVon(dran) == true) {
        extraTruppen = extraTruppen + kontinente[i].getExtraTruppen();
      }
    }
    if (((int)(mitSpieler[dran].getGesamtLaender() / 3)) < 3) {
      return 3 + extraTruppen;
    }
    else {
      return (int)(mitSpieler[dran].getGesamtLaender() / 3) + extraTruppen;
    }
  }
  
  private int berechneGesamtTruppen(byte meinSpieler) {
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
  
  public void setKampfGewonnen() {
    this.kampfGewonnen = false;
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
  
  public int getAnzahlLaender(byte meinSpieler) {
    return this.mitSpieler[meinSpieler].getGesamtLaender();
  }
  
  public int getAnzahlTruppen(byte meinSpieler) {
    return this.mitSpieler[meinSpieler].getGesamtTruppen();
  }
  
  public byte[] getVerteidigerWuerfel() {//von Gui abgefragt
    return this.verteidigerWuerfel;
  }
  
  public byte[] getAngreiferWuerfel() {//von Gui abgefragt
    return this.angreiferWuerfel;
  }
  
  public byte getVerteidigerWuerfelAnzahl() {
    return this.verteidigerWuerfelAnzahl;
  }
  
  public byte getAngreiferWuerfelAnzahl() {
    return this.angreiferWuerfelAnzahl;
  }
  
  public byte getPhase() {
    return this.phase;
  }
  
  public byte getDran() {
    return this.dran;
  }
  
  public Land getNachLand() {
    return this.nachLand;
  }
  
  public Land getVonLand() {
    return this.vonLand;
  }
  
  public String getSpielerName(byte sNr) {
    return this.mitSpieler[sNr].getName();
  }
  
  public int getZuPlazierendeTruppen() {
    return mitSpieler[dran].getGesamtTruppen() + berechneZuPlatzierendeTruppen() - berechneGesamtTruppen(dran);
  }
  
  public byte getKarte() {
    return this.mitSpieler[dran].getKarte();
  }
  
  public int[] getKartenAnzahl(int gefordert) {
    return this.mitSpieler[gefordert].getKartenAnzahl();
  }
  
  public boolean getKampfGewonnen() {
    return this.kampfGewonnen;
  }
}
