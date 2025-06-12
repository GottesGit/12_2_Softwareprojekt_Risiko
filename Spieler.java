public class Spieler{
  private int gesamtTruppen = 0;
  private String name;
  private int gesamtLaender = 0;
  private int[] kartenAnzahl = {0, 0, 0};// 0 = soldaten; 1 = pferde; 2 = kanonen
  byte karte;

  public Spieler(String meinName){
    this.name = meinName;
  }

  public String getName(){
    return this.name;
  }
  
  public byte kartenNutzen(boolean entfernen){ // könnten wir auch als switch case machen, wenn das nicht automatisch sein soll
    //for (int i = 0; i < 3; i++) {
    //  System.out.println("karte" + i + ": " + kartenAnzahl[i]);
    //}
    
    if (this.kartenAnzahl[0] > 0 && this.kartenAnzahl[1] > 0 && this.kartenAnzahl[2] > 0) {
      if (entfernen) {
        kartenAnzahl[0]--;
        kartenAnzahl[1]--;
        kartenAnzahl[2]--;
        System.out.println("k10");
      }
      return 10;
    }
    else if (this.kartenAnzahl[2] >= 3) {
      if (entfernen) {
        kartenAnzahl[2] = kartenAnzahl[2] - 3;
        System.out.println("k8");
      }
      return 8;
    }
    else if (this.kartenAnzahl[1] >= 3) {
      if (entfernen) {
        kartenAnzahl[1] = kartenAnzahl[1] - 3;
        System.out.println("k6");
      }
      return 6;
    } 
    else if (this.kartenAnzahl[0] >= 3) {
      if (entfernen) {
        kartenAnzahl[0] = kartenAnzahl[0] - 3;
        System.out.println("k4");
      }
      return 4;
    }
    else {
      System.out.println("k0");
      return 0;
    }
  }
 
  public void karteZiehen(){
    karte = (byte)(Math.random() * 3);
    this.kartenAnzahl[karte]++;
  }
  
  public byte getKarte() {
    return this.karte;
  }
  
  public int[] getKartenAnzahl() {
    return this.kartenAnzahl;
  }
  
  public void setGesamtTruppen(int anzahl){
    this.gesamtTruppen = anzahl;
  }
  
  public int getGesamtTruppen(){
    return this.gesamtTruppen;
  }
  
  public void setGesamtLaender(int anzahl){
    this.gesamtLaender = anzahl;
  }
  
  public int getGesamtLaender(){
    return this.gesamtLaender;
  }
}
