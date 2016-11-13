package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class MainController extends Application {

	private Stage primaryStage;
	private Scene scene;
	private view_haupt viewHaupt = new view_haupt();
	// in View verschieben und Konstruktor this von View mitgeben
	private Rennbahn rennbahn = new Rennbahn();
	
	public void launcher() {

		this.viewHaupt.setRennbahn(this.rennbahn);
		this.rennbahn.setView(this.viewHaupt);
		
		// in View verschieben 
		//  this.rennbahn.startGame();

		
		// TESTING START
		System.out.println( this.rennbahn.getAnzahlStrecken() );
		/*
		Observer observer = new Observer();
		observer.addSubscriber(this, "test");
		Object test = new Object();
		observer.trigger("test", test);
		*/
		// TESTING END
		
		
		System.out.println("launcher method");
		launch(); // JavaFX
	}
	public void setScene(Scene s) {
		this.scene = s;
	}
	public Scene getScene() {
		if(! (this.scene instanceof Scene) ) {
			// durch view Objekt ersetzen
			this.scene = this.viewHaupt.erstelleScene();
		}
		return this.scene;
	}
	
	
	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		try {
			this.primaryStage.setScene(this.getScene());
			this.primaryStage.show();
			System.out.println(this.primaryStage);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void calling(Object obj) {
		System.out.println( obj.getClass().toString() );
	}
	
}
