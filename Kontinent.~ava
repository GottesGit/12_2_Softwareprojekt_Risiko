public class Kontinent{
  private Land[] laender;
  private byte truppen;
  private String name;
  
  public Kontinent(Land staaten[], String meinName, byte extraTruppen) {
    this.laender = java.util.Arrays.copyOf(staaten, staaten.length);
    this.name = meinName;
    this.truppen = extraTruppen;
  }

  public boolean beherrschtVon(byte herrscher){//herrscher = dran
    for (int i = 0; i < laender.length; i++) {
      if (laender[i].getHerrscher() != herrscher) {
        return false;
      }
    } // end of for
    return true;
  }
  
  public int getExtraTruppen() {
    return this.truppen;
  }
}
