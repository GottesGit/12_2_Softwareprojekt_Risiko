import javafx.scene.control.*;
import javafx.event.*;
import javafx.scene.shape.SVGPath;
import javafx.scene.text.Text;

import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

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
  
  public void landButton_gedrueckt(MouseEvent evt) {
    if (evt.getButton() == MouseButton.PRIMARY) {
      spiel.landKlickAktion(land, 1);//erstmal nur linke Maustaste 
    } else if (evt.getButton() == MouseButton.SECONDARY) {
      spiel.landKlickAktion(land, 2);   
    }
    System.out.println(land.getName() + "gedrueckt");
  }
  
  public void refresh() {
    this.schriftzug.setText(land.getName() + " " + land.getTruppen() + " Angreifertrupps: " + land.getAngreiferTruppen());
  }
  
  public byte getHerrscher() {
    return land.getHerrscher();
  }
  
  public int getTruppen() {
    return land.getTruppen();
  }
}
