public class Spieler{
  private int gesamtTruppen;
  private String name;
  private int gesamtLänder;
  private int[] kartenAnzahl = new int[3];// 0 = soldaten; 1 = pferde; 2 = kanonen

  public Spieler(String Name){
    
  }

  public String getName(Spieler spieler){
    return this.name;
  }
  
  public void kartenNutzen(int[] kartenAnzahl){//keine ahnung, ob die eckigen da hin müssen
    
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