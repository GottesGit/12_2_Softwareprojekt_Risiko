public class Kontinent{
  private Land[] laender;
  private byte truppen;
  
  public Kontinent(Land staaten[], byte extraTruppen) {
    this.laender = java.util.Arrays.copyOf(staaten, staaten.length);//geht das so?
    this.truppen = extraTruppen;
  }

  public boolean beherrschtVon(byte herrscher){//herrscher = dran
    for (i = 0; i < laender.length; i++) {
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
