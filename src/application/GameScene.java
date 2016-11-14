package application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;

/**
 * @author JackRyan
 *
 */
public class GameScene {

	private Scene scene; 
	private Rennbahn rennbahn;
	// Hauptpanel
	private GridPane wurzel = new GridPane();
	// Startbutton
	private Button bttnStart;
	// Array für die Fortschrittsleisten
	private ProgressBar[] progrPferd = new ProgressBar[12];
	// Array für die Streckenbilder
	private ImageView[] imgPferd = new ImageView[12];
	
	
	/**
	 * Konstruktor
	 */
	public GameScene() {
		this.wurzel.setAlignment(Pos.CENTER);
		this.scene = new Scene(this.wurzel,700,700);
	}
	
	/**
	 * Hilfsfunktion um Rennbahn mit GUI bekannt zu machen
	 * @param rennbahn
	 */
	public void setRennbahn(Rennbahn rennbahn) {
		this.rennbahn = rennbahn;
		System.out.println("rennbahn set "+this.rennbahn);
	}
	
	/**
	 * Funktion zum erstellen der Szene mit ihren Elementen
	 * @return komplette Szene mit allen Elementen
	 */
	private Scene erstelleScene() {	
		
		//Äußere VertikalBox zur Aufnahme aller weiteren Elemente
		VBox verboAeussereBox = new VBox();
		
		// Titelbild
		ImageView imgvTitelbild = new ImageView(getClass().getResource("KentuckyDerby.png").toExternalForm());
		
		//Horizontalbox zur Aufnahme der Streckenbilder und Fortschrittsbalken
		HBox horboObereBox = new HBox();
		
		//Vertikalbox zur Aufnahme der Streckenbilder
		VBox verboLinkeBox = new VBox();
		
		//Schleife zum erstellen der Streckenbilder
		for(int i = 1; i <= imgPferd.length; i++) {
			//ZUsammensetzen des Bilddateinamen des Streckenbildes
			String dateiname = "reiter_" + i + ".jpg";
			//erstellen der Imageview
			imgPferd[i-1] = new ImageView(getClass().getResource(dateiname).toExternalForm());
			//setzen von breite und höhe des Bildes
			imgPferd[i-1].setFitHeight(20);
			imgPferd[i-1].setFitWidth(20);
			//einfügen in Vertikalbox
			verboLinkeBox.getChildren().add(imgPferd[i-1]);
		};
		//Vertikalbox zur Aufnahme der Fortschrittsbalken
		VBox verboRechteBox = new VBox();
		//Schleife zum erstellen der Fortschrittsbalken
		for(int i = 0; i < progrPferd.length; i++) {
			//erstellen des balken
			progrPferd[i] = new ProgressBar();
			//setzen von Breite und Höhe
			progrPferd[i].setMaxWidth(600);
			progrPferd[i].setMinWidth(600);
			//Fortschrittsbalekn auf 0 setzen
			progrPferd[i].setProgress(0);
			//einfügen in Vertikalbox
			verboRechteBox.getChildren().add(progrPferd[i]);
		};
		
		//Vertikalboxen in Horizontalbox einfügen
		horboObereBox.getChildren().add(verboLinkeBox);
		horboObereBox.getChildren().add(verboRechteBox);
		
		//Horizontalbox zur Aufnahme de Startbutton
		HBox horboUntereBox = new HBox();
		//Startbutton erstellen
		bttnStart = new Button("Start");
		System.out.println("setUserData "+this.rennbahn);
		//Übergabe des Rennbahnobjekt an Buttonobjekt
		bttnStart.setUserData(this.rennbahn);
		//Button onClick Ereigniss zuweisen 
		bttnStart.setOnAction(new EventHandler<ActionEvent>(){
		    //Funktion zur welche bei Klick ausgeführt wird 
		    public void handle(ActionEvent arg0){
		    	//Hilfsobjekt anlegen und ausführendes Objekt zuweisen
		    	Button obj = (Button) arg0.getSource();
		    	System.out.println(obj.getUserData());
		    	//Rennbahn aus Userdaten entgegennehmen
		    	Rennbahn rennbahn = (Rennbahn) obj.getUserData();
		    	//Rennbahnmethode ausführen
		    	rennbahn.startGame();
			}

		
		});
		//Button Horizontalbox zuweisen
		horboUntereBox.getChildren().add(bttnStart);
		
		////Äußerer VertikalBox Titelbild und beide Horizontalboxen zuweisen
		verboAeussereBox.getChildren().add(imgvTitelbild);
		verboAeussereBox.getChildren().add(horboObereBox);
		verboAeussereBox.getChildren().add(horboUntereBox);
		
		//Wurzelpanel äußere Vertikalbox zuweisen
		this.wurzel.getChildren().add(verboAeussereBox);
		//Szene Formatierungs CSS  zuweisen 
		this.scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());		
		//fertige Szene zurückgeben
		return this.scene;
	}
	
	/**
	 * Hilfsfunktion zur Rückgabe der Szene
	 * @return komplette Szene mit allen Elementen
	 */
	public Scene getScene() {
		if(this.scene == null) {
			this.scene = this.erstelleScene();
		}
		return this.scene;
	}
	
	/**Hilfsfunktion zum aktualisieren der Fortschrittsbalken
	 * 
	 * 
	 * @param pferd -> betreffender Balken
	 * @param fortschritt -> Fortschritt in %
	 */
	public void bewegePferd(int pferd, double fortschritt) {
		progrPferd[pferd].setProgress(progrPferd[pferd].getProgress() + fortschritt);
	}
	
}
