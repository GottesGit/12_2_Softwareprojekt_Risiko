import javafx.scene.control.*;
import javafx.event.*;

public class LandButton extends Button{//geht anscheinend nicht
  private String positionSvg;
  private Land land;
  private Spiel spiel;
  
  public LandButton(Spiel meinSpiel, String position, Land meinLand) {
    this.spiel = meinSpiel;
    this.positionSvg = position;
    this.land = meinLand;
    refresh();
  }
  
  public void landButton_gedrueckt() {
    spiel.landKlickAktion(land, 1);//erstmal nur linke Maustaste
  }
  
  public void refresh() {
    this.setText("" + land.getTruppen());//gibt es setText bei Buttons?
  }
  
  public byte getHerrscher() {
    return land.getHerrscher();
  }
  
  public int getTruppen() {
    return land.getTruppen();
  }
}
