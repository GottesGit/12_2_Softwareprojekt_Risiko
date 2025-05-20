import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class RisikoGui extends Application {
  private String[] laenderSvg = new String[42];
  private byte spielerAnzahl;
  private Spiel spiel;
  private LandButton[] landButtons = new LandButton[laenderNamen.length];
  private Button kampfButton;
  private Button fertigButton;
  private Label wuerfelLabel;
  
  public void start(Stage primaryStage) { 
    Pane root = new Pane();
    Scene scene = new Scene(root, 284, 262);
    // Anfang Komponenten
    
    // Ende Komponenten
    
    primaryStage.setOnCloseRequest(e -> System.exit(0));
    primaryStage.setTitle("RisikoGui");
    primaryStage.setScene(scene);
    primaryStage.show();
  }
  
  // Anfang Methoden
  
  public static void main(String[] args) {
    launch(args);
    String[] namen = {"Bob", "Peter"};//da fehlt natuerlich noch die Startseite
    spiel = new Spiel(namen, this);
    for (byte i = 0; i < spiel.laender.length; i++) {
      landButtons[i] = new LandButton(spiel, laenderSvg[i], spiel.laender[i]);
    }
  }
  
  public void grafikErneuern (){
  
  }
  
  public void kampfButton_gedrueckt (){
    
  }
  
  public void fertigButton_gedrueckt (){
    
  }
  // Ende Methoden
}
