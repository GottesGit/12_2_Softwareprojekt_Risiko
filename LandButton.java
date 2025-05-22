public class LandButton extends Button{
  private String positionSvg;
  private Land land;
  private Spiel spiel;
  public LandButton(Spiel meinSpiel, String position, Land meinLand){
    
  }
  
  public void landButton_gedrueckt(){
    spiel.landKlickAktion(land, 1);//erstmal nur linke Maustaste
  }
  
  public void refresh(){
    land.getTruppen();//dann anzeigen
  }
}
