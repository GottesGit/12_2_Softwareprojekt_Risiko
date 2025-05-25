public class Spieler{
  private int gesamtTruppen = 0;
  private String name;
  private int gesamtLaender = 0;
  private int[] kartenAnzahl = {0, 0, 0};// 0 = soldaten; 1 = pferde; 2 = kanonen

  public Spieler(String meinName){
    this.name = meinName;
  }

  public String getName(){
    return this.name;
  }
  
  public byte kartenNutzen(){ // könnten wir auch als switch case machen, ween das nicht automatisch sein soll
    if (kartenAnzahl[0] >= 3) {
      kartenAnzahl[0] = kartenAnzahl[0] - 3;
      return 4;
    } else if (kartenAnzahl[1] >= 3) {
      kartenAnzahl[1] = kartenAnzahl[1] - 3;
      return 6;
    } else if (kartenAnzahl[2] >= 3) {
      kartenAnzahl[2] = kartenAnzahl[2] - 3;
      return 8;
    } else if (kartenAnzahl[0] >= 1 && kartenAnzahl[1] >= 1 && kartenAnzahl[2] >= 1) {
      kartenAnzahl[0]-1;
      kartenAnzahl[1]-1;
      kartenAnzahl[2]-1;
      return 10;
    } else {
      return 0;
    }
  }
 
  public void karteZiehen(int[] kartenanzahl){
    int zahl = (int)(Math.random() * 3);// sollte glaube 0, 1 oder 2 geben  }warum ist da ne klammer?
    this.kartenAnzahl[zahl]++;
  }
  
  public void setGesamtTruppen(int anzahl){
    this.gesamtTruppen = gesamtTruppen + anzahl;
  }
  
  public int getGesamtTruppen(){
    return this.gesamtTruppen;
  }
 }
