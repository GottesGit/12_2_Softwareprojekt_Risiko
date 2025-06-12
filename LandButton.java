import javafx.scene.control.*;
import javafx.event.*;
import javafx.scene.shape.SVGPath;
import javafx.scene.text.Text;             
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class LandButton extends Button{//geht anscheinend nicht
  private Land land;
  private Spiel spiel;
  private Text schriftzug;
  private ImageView kontinente;
  private SVGPath form;
  int globalerSwitchWert;
  
  public LandButton(Spiel meinSpiel, SVGPath meineForm, Land meinLand, Text meinText, ImageView imageViewKontinente) {
    this.spiel = meinSpiel;
    this.form = meineForm;
    this.land = meinLand;
    this.schriftzug = meinText;
    this.kontinente = imageViewKontinente;
    refresh(globalerSwitchWert);
  }
  
  public void landButton_gedrueckt(MouseEvent evt) {
    if (evt.getButton() == MouseButton.PRIMARY) {
      spiel.landKlickAktion(land, 1);//erstmal nur linke Maustaste 
    } else if (evt.getButton() == MouseButton.SECONDARY) {
      spiel.landKlickAktion(land, 2);   
    }
    System.out.println(land.getName() + "gedrueckt");
  }
  
  //  public void refresh() {
  //    this.schriftzug.setText(land.getName() + " " + land.getTruppen() + " Angreifertrupps: " + land.getAngreiferTruppen());
  //  }
  
  public void refresh(int switchWert) {
    switch (switchWert) {
      case 0 : 
        this.schriftzug.setText(land.getTruppen() + "");
        this.kontinente.setVisible(false);
        break;
      case 1 : 
        this.schriftzug.setText(land.getName() + "");// + land.kontin);// LETZTERES NUR ZUM DEBUGGEN
        this.schriftzug.toFront();
        break;
      case 2 :
        this.schriftzug.setText("");
        this.kontinente.toFront();
        this.kontinente.setVisible(true);
        break;
      case 10 :
        this.schriftzug.setText(land.getTruppen() + " vs. " + land.getAngreiferTruppen());
        this.kontinente.setVisible(false);
        break;
    }
    globalerSwitchWert = switchWert;
  }
  
  public byte getHerrscher() {
    return land.getHerrscher();
  }
  
  public int getTruppen() {
    return land.getTruppen();
  }
  
  public void angriffsSchrift(int truppen){
    this.schriftzug.setText("Angreifer " + truppen);
    this.kontinente.setVisible(false);
  }
  
  public void setKlickbar(boolean klick) {
    this.setDisable(!klick);
    if (klick) {
      form.setStroke(Color.valueOf("#CC0099"));
      form.setStrokeWidth(0.5);
    } else {
      form.setStrokeWidth(0);
    }
  }
}
