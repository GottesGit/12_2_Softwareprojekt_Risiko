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
      case 0 : //truppenPlazieren
        truppenPlazieren(1, land);
        if (platzierteTruppen >= zuPlatzierendeTruppen) {
          phase++;
        }
        break;
      case 1 : //angreifen also Feind auswaehlen
        
        break;
      case 2 : //angreifen also im Kampf
        
        break;
      case 3 : //Truppen verschieben
        
        break;
    }
  }
  
  public boolean phasenWechsel(){
    
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
  
  public int getGewonnen (){
    
  }

  public String getSpielerName(byte sNr){
    
  }

}
