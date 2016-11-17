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
	// Array fuer die Fortschrittsleisten
	private ProgressBar[] progrPferd = new ProgressBar[Configuration.getAnzahlBahnen()];
	// Array fuer die Streckenbilder
	private ImageView[] imgPferd = new ImageView[Configuration.getAnzahlBahnen()];
	// Array fuer die Siegesbilder
	private ImageView[] imgSieg = new ImageView[Configuration.getAnzahlBahnen()];
	// Platzhalter fuer Schlussbild
	private ImageView imgSchluss;
	// Siegesbilder
	private Image schlussbild = new Image(getClass().getResource("theEnd.png").toExternalForm());
	// Siegesbilder
	private Image ersterPlatz = new Image(getClass().getResource("platz_1.png").toExternalForm());
	private Image zweiterPlatz = new Image(getClass().getResource("platz_2.png").toExternalForm());
	private Image dritterPlatz = new Image(getClass().getResource("platz_3.png").toExternalForm());



	/**
	 * Konstruktor
	 */
	public GameScene(Rennbahn rennbahn) {
		this.rennbahn = rennbahn;
		this.wurzel.setAlignment(Pos.CENTER);
		this.scene = new Scene(this.wurzel,700,700);
		this.erstelleScene();
		//Szene Formatierungs CSS  zuweisen
		this.scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
	}


	/**
	 * Funktion zum erstellen der Szene mit ihren Elementen
	 * @return komplette Szene mit allen Elementen
	 */
	private void erstelleScene() {

		//Aeussere VertikalBox zur Aufnahme aller weiteren Elemente
		VBox verboAeussereBox = new VBox();

		// Titelbild
		ImageView imgvTitelbild = new ImageView(getClass().getResource("KentuckyDerby.png").toExternalForm());

		//Horizontalbox zur Aufnahme der Streckenbilder und Fortschrittsbalken
		HBox horboObereBox = new HBox();
		//CSS-Klasse fuer Padding/Ausrichtung der Elemente
		horboObereBox.getStyleClass().add("horboObereBox");

		//Vertikalbox zur Aufnahme der Streckenbilder
		VBox verboLinkeBox = new VBox();

		//Schleife zum erstellen der Streckenbilder
		for(int i = 1; i <= imgPferd.length; i++) {
			//Zusammensetzen des Bilddateinamen des Streckenbildes
			String dateiname = "reiter_" + i + ".png";
			//erstellen der Imageview
			imgPferd[i-1] = new ImageView(getClass().getResource(dateiname).toExternalForm());
			//setzen von Breite und Hoehe des Bildes
			imgPferd[i-1].setFitHeight(30);
			imgPferd[i-1].setFitWidth(30);
			imgPferd[i-1].getStyleClass().add("pferdStyle");
			//einfuegen in Vertikalbox
			verboLinkeBox.getChildren().add(imgPferd[i-1]);
		};


		//Vertikalbox zur Aufnahme der Fortschrittsbalken
		VBox verboMittlereBox = new VBox();
		verboMittlereBox.getStyleClass().add("progressBarBox");
		//Schleife zum erstellen der Fortschrittsbalken
		for(int i = 0; i < progrPferd.length; i++) {
			//erstellen des balken
			progrPferd[i] = new ProgressBar();
			//setzen von Breite und Hoehe
			progrPferd[i].setMaxWidth(500);
			progrPferd[i].setMinWidth(500);
			progrPferd[i].setMaxHeight(30);
			progrPferd[i].setMinHeight(30);
			//Fortschrittsbalekn auf 0 setzen
			progrPferd[i].setProgress(0);
			//Klasse auf Progressbar
			progrPferd[i].getStyleClass().add("progressBar");
			//einfuegen in Vertikalbox
			verboMittlereBox.getChildren().add(progrPferd[i]);
		};



		//Vertikalbox zur Aufnahme der Streckenbilder
		VBox verboRechteBox = new VBox();


		//Schleife zum erstellen der Streckenbilder
		for(int i = 0; i < imgSieg.length; i++) {
			//erstellen der Imageview
			imgSieg[i] = new ImageView();
			//setzen von Breite und Hoehe des Bildes
			imgSieg[i].setFitHeight(30);
			imgSieg[i].setFitWidth(30);
			//einfuegen in Vertikalbox
			verboRechteBox.getChildren().add(imgSieg[i]);
		};

		//Vertikalboxen in Horizontalbox einfuegen
		horboObereBox.getChildren().add(verboLinkeBox);
		horboObereBox.getChildren().add(verboMittlereBox);
		horboObereBox.getChildren().add(verboRechteBox);

		//Horizontalbox zur Aufnahme de Startbutton
		HBox horboUntereBox = new HBox();

		//CSS-Klasse fuer Padding/Ausrichtung der Elemente
		horboUntereBox.getStyleClass().add("horboUntereBox");

		//Startbutton erstellen
		bttnStart = new Button("Start");
		System.out.println("setUserData "+this.rennbahn);
		//Uebergabe des Rennbahnobjekt an Buttonobjekt
		bttnStart.setUserData(this.rennbahn);
		//Button onClick Ereigniss zuweisen
		bttnStart.setOnAction(new EventHandler<ActionEvent>(){
		    //Funktion zur welche bei Klick ausgefuehrt wird
		    public void handle(ActionEvent arg0){
		    	//Hilfsobjekt anlegen und ausfuehrendes Objekt zuweisen
		    	Button obj = (Button) arg0.getSource();
		    	System.out.println(obj.getUserData());
		    	//Rennbahn aus Userdaten entgegennehmen
		    	Rennbahn rennbahn = (Rennbahn) obj.getUserData();
		    	//Szene zuruecksetzen
		    	ruecksetzenSzene();
		    	//Button deaktivieren
		    	obj.setDisable(true);
		    	//Rennbahnmethode ausfuehren
		    	rennbahn.startGame();
			}


		});

		//Platzhalter fuer Schlussbild
		imgSchluss = new ImageView();
		imgSchluss.setFitHeight(100);
		imgSchluss.setFitWidth(300);

		//Elemente Horizontalbox zuweisen
		horboUntereBox.getChildren().add(bttnStart);
		horboUntereBox.getChildren().add(imgSchluss);

		//Aeusserer VertikalBox Titelbild und beide Horizontalboxen zuweisen
		verboAeussereBox.getChildren().add(imgvTitelbild);
		verboAeussereBox.getChildren().add(horboObereBox);
		verboAeussereBox.getChildren().add(horboUntereBox);

		//Wurzelpanel Aeussere Vertikalbox zuweisen
		this.wurzel.getChildren().add(verboAeussereBox);
		//fertige Szene zurueckgeben
		//return this.scene;
	}

	/**
	 * Hilfsfunktion zur Rueckgabe der Szene
	 * @return komplette Szene mit allen Elementen
	 */
	public Scene getScene() {
		return this.scene;
	}


	/**
	 * Methode zum Anzeigen des Schlussbildes
	 *
	 */

	public void zeigeSchlussbild() {

		imgSchluss.setImage(schlussbild);
		bttnStart.setDisable(false);
	};

	/**
	 * Methode zum Zuruecksetzen auf Anfang
	 *
	 */

	public void ruecksetzenSzene() {

		for(int i = 0; i < imgSieg.length; i++) {
			//zuruecksetzen der Siegbilder
			imgSieg[i].setImage(null);
		};

		for(int i = 0; i < progrPferd.length; i++) {
			//Fortschrittsbalken auf 0 setzen
			progrPferd[i].setProgress(0);
		};
		// Schlussbild entfernen
		imgSchluss.setImage(null);
	};



	/**
	 * Methode zum Anzeigen des Siegers
	 * @param pferd -> pferd im Ziel
	 * @param platzierung -> platzierung des Pferdes
	 */

	public void zeigeSieger(int pferd, Integer platzierung){

		switch (platzierung) {
		case 1: imgSieg[pferd].setImage(ersterPlatz);
				break;
		case 2: imgSieg[pferd].setImage(zweiterPlatz);
				break;
		case 3: imgSieg[pferd].setImage(dritterPlatz);
				break;
		}

	};


	/**Hilfsfunktion zum aktualisieren der Fortschrittsbalken
	 *
	 *
	 * @param pferd -> betreffender Balken
	 * @param fortschritt -> Fortschritt in %
	 */
	public void bewegePferd(Integer pferd, double fortschritt) {
		// System.out.println("GameScene Pferd: " + pferd + " Fortschritt: " + fortschritt);
		progrPferd[pferd].setProgress(fortschritt);
	}

}
