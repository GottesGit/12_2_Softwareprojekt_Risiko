public class Land{
  private String name;
  private Land[] nachbarn;
  private byte herrscher;
  private int anzahlTruppen;
  private byte angreiferSpieler;
  private int angreiferTruppen;
  private boolean schonDurch;
  private byte index;
  
  public Land(Land[] angrenzend, String meinName, byte meinIndex){ // ist der name nicht unnoetig bzw sind zahlen nicht einfacher? - Das Land muss trotzdem einen Namen haben
    this.nachbarn = java.util.Arrays.copyOf(angrenzend, angrenzend.length);
    this.name = meinName;
    this.index = meinIndex;
  }
 
  public void setTruppen(byte meinHerrscher, int anzahl) {
    this.anzahlTruppen = anzahl;
    this.herrscher = meinHerrscher;
  }

  public int getTruppen() {
    return this.anzahlTruppen;
  }
  
  public void setAngreiferTruppen(byte meinAngreifer, int anzahl) {
    this.angreiferTruppen = anzahl;
    this.angreiferSpieler = meinAngreifer;
  }
  public int getAngreiferTruppen() {
    return this.anzahlTruppen;
  }
  
  public byte getHerrscher() {
    return this.herrscher;
  }
  
  public String getName() {
    return this.name;
  }
  
  public Land[] getNachbarn() {
    return this.nachbarn[];//darf man das so?
  }
  
  public boolean getAngrenzend(Land anderesLand){
    for (i = 0; i < nachbarn.length; i++) {
      if (nachbarn[i] == anderesLand){//funktioniert das so oder muessen die Namen der Laender verglichen werden?
        return true;
      }
    } // end of for
    return false;
  }
  
  public boolean getVerbunden(Land anderesLand, byte dran) {//rekursiv, aber uebergeben, welche schon ueberprueft wurden
    this.schonDurch = true;
    if (this == anderesLand) {
      return true;
    }
    for (byte i = 0; i < nachbarn.length; i++) {
      if (nachbarn[i].getSchonDurch() != true && nachbarn[i].getHerrscher() == dran) {
        if (nachbarn[i].getVerbunden(anderesLand, dran)) {
          return true;
        }
      }
    }
    return false;
  }
  
  public boolean getSchonDurch() {
    return this.schonDurch;
  }
  
  public void resetSchonDurch() {
    this.setSchondurch = false;
  }
  
  public byte getIndex() {
    return this.index;
  }
}
