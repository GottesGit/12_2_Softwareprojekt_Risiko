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
  private byte[] würfel = new byte[??];// weiß nicht, wie viel
  
  public Spiel(String spielerNamen[], RisikoGui meineGui) {
    gui = meineGui;
    mitSpieler[] = new Spieler[spielerNamen.length];
    for (i = 0; i < spielerNamen.length; i++) {
      mitSpieler[i] = new Spieler(spielerNamen[i]);//array mitspieler müsste auf die Ruichtige Länge und dann miot Spielern gefüllt werden
    }
    Land[][] angrenzende = {
      {}
    };

    for (byte i = 0; i < 42; i++) {
      laender[i] = new Land();
    }
  }
  
  public void landKlickAktion(Land land, byte taste) {
    switch (phase) {
      case 0 : //truppenPlazieren, eigentlich je nach mauszeiger
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
          System.out.println("Dieses Land gehoert nich dir");
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
        //wie kann das aufgerufen werden also muss halt vom kampfbutton aufgerufen werden
        break;
      case 4 : //Truppen verschieben
        
        break;
    }
  }
  
  public void phasenWechsel(){
    
  }
  public byte getPhase(){
    return this.phase;
  }
  public int getDran(){
    return dran;
  }
  
  private boolean truppenPlatzieren(int anzahl, Land ziel){//anzahl aus gui übergeben, auch von da aufrufen
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
  
  private int anzahlLaenderSpieler(Spieler spieler){
    
  }
  
  private int anzahlTruppenSpieler(Spieler spieler){
    
  }
  
  private int einmarschieren(Land von, Land nach){
    
  }
  
  private void kampf(Land meinLand){
    
  }
  
  public int[] getWuerfel(){
    
  }
  
  private boolean verschiebeTruppen(Land von, Land nach){
    
  }
  
  public byte getGewonnen (){
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

  public String getSpielerName(byte sNr){
    
  }
}
