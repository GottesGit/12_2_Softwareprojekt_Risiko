public class Spieler{
  private int gesamtTruppen = 0;
  private String name;
  private int gesamtLaender = 0;
  private int[] kartenAnzahl = {0, 0, 0};
;// 0 = soldaten; 1 = pferde; 2 = kanonen

  public Spieler(String meinName){
    this.name = meinName;
  }

  public String getName(Spieler spieler){
    return this.name;
  }
  
  public byte kartenNutzen(){
    if (karten[0] >= 3) {
      karten[0] = karten[0] - 3;
      return //kp wie viel
    } else if (karten[1] >= 3) {
      karten[1] = karten[1] - 3;
      return //kp wie viel
    } else if (karten[2] >= 3) {
      karten[2] = karten[2] - 3;
      return //kp wie viel
    } else if (karten[0] >= 1 && karten[1] >= 1 && karten[2] >= 1) {
      karten[0]--;
      karten[1]--;
      karten[2]--;
      return //kp wie viel
    } else {
      return 0;
    }
  }
 
  public void karteZiehen(int[] kartenanzahl){
    int zahl = (int)(Math.random() * 3);// sollte glaube 0, 1 oder 2 geben  }
    this.kartenAnzahl[zahl]++;
  }
  
  public void setGesamtTruppen(int anzahl){
    this.gesamtTruppen = gesamtTruppen + anzahl;
  }
  
  public void getGesamtTruppen(){
    return this.gesamtTruppen;
  }
 }
