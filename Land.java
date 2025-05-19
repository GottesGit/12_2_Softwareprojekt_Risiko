public class Land{
  private String name;
  private Land[] nachbarn;
  private byte herrscher;
  private int anzahlTruppen;
  private byte angreiferSpieler;
  private int angreiferTruppen;
  
  public Land(Land[] angrenzend,String laenderName){ // ist der name nicht unnötig bzw sind zahlen nicht einfacher?
    
  }
 
  public void setTruppen(byte meinHerrscher, int anzahl){
    
  }

  public int getTruppen(){
    return name.anzahlTruppen;
  }
  
  public byte getHerrscher(){
    return herrscher;
  }
  
  public String getName(Land name){ 
  return name;
  }
  
  public Land[] getNachbarn(String name){
    return name.nachbarn[];//darf man das so?
  }
  
  public boolean getAngrenzend(Land name){
    for (i = 0;i< nachbarn.length ;i++) {
      if (nachbarn[i] == name){
        return true;
      }
    } // end of for
    return false;
  }
  
  public boolean getVerbunden(Land name){
    return false;
  }

  }
