import javafx.scene.control.*;
import javafx.event.*;
import javafx.scene.shape.SVGPath;
import javafx.scene.text.Text;

public class LandButton extends Button{//geht anscheinend nicht
  private String positionSvg;
  private Land land;
  private Spiel spiel;
  private Text schriftzug;
  
  public LandButton(Spiel meinSpiel, String position, Land meinLand, Text meinText) {
    this.spiel = meinSpiel;
    this.positionSvg = position;
    this.land = meinLand;
    this.schriftzug = meinText;
    refresh();
  }
  
  public void landButton_gedrueckt(Event evt) {
    System.out.println(land.getName() + "gedrueckt");
    spiel.landKlickAktion(land, 1);//erstmal nur linke Maustaste
  }
  
  public void refresh() {
    this.schriftzug.setText(land.getName() + " " + land.getTruppen());
  }
  
  public byte getHerrscher() {
    return land.getHerrscher();
  }
  
  public int getTruppen() {
    return land.getTruppen();
  }
}
